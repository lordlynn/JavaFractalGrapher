package Fractals;

import com.aparapi.Kernel;

public class FractalCalculations {
    private Complex z;
    private Complex c;
    double[] center;


    private int width;
    private int height;

    private double magnitude;
    private float[] magnitudes;

    
    public static void main(String[] args) {
        FractalCalculations test  = new FractalCalculations(new Complex(-0.5, 0.5), new Complex(0.0, 0.0));
        float[] example = new float[2];
        example[0] = (float)-0.5;
        example[1] = (float)0.5;
        System.out.println("Convergence factor: " + test.computeMagnitudeBehavior(50));

    }

    public FractalCalculations(Complex c, Complex z) {
        this.z = z;
        this.c = c;
        center = new double[2];
    }

    public FractalCalculations(Complex z, int width , int height) {
        this.z = z;
        center = new double[2];
        this.width = width;
        this.height = height;
    }

    public double computeMagnitudeBehaviorTest(float[] initialValues) {
        magnitudes = initialValues;

        Kernel kernel = new Kernel() {
            @Override
            public void run() {
                int gid = getGlobalId();
                magnitudes[gid] = initialValues[gid];
//                int flag = 0;
//
//                float real = (float) (z.re() * z.re() - z.im() * z.im());
//                float imag = (float) (z.re() * z.im() + z.im() * z.re());
//                float temp;
//
//                for (int i = 0; i < 50; i++) {
//                    temp = real * real - imag * imag;
//                    imag = real * imag + imag * real;
//                    real = temp;
//
//                    real += c.re();
//                    imag += c.im();
//
//                    magnitude = Math.abs(Math.sqrt(Math.pow(real, 2.0) + Math.pow(imag, 2.0)));
//
//                    if (magnitude > Math.pow(10, 50)) {
//                       // break;
//                    }
//
//                    flag++;
//                }
//                if (flag == iterations) {
//                    magnitude = 0;
//                }
//               //do math

            }
        };

        kernel.execute(512);
        kernel.dispose();

        return magnitude;
    }


    public double computeMagnitudeBehavior(int iterations) {
        int flag = 0;
        magnitude = 0;

        for (int i = 0; i < iterations; i++) {
            if (magnitude > Math.pow(2, 24)) {
                break;
            }
            magnitude = Math.abs(Math.sqrt(Math.pow(z.re(), 2) + Math.pow(z.im(), 2)));
            z = z.times(z).plus(c);
            flag++;
        }

//        if (flag == iterations) {
//            return 0;
//        }
        return magnitude;
    }

    private void computeCenter(Complex c, Complex z) {
        Complex tempZ = z;
        Complex tempC = c;

        for (int i = 0; i < 10; i++) {
            tempZ = tempZ.times(tempZ).plus(tempC);

            center[0] += tempZ.re();
            center[1] += tempZ.im();
        }

        center[0] /= 5.0;
        center[1] /= 5.0;

        //System.out.println("center: (" + center[0] + ", " + center[1] + ")");
    }


}
