package Fractals;

public class ComplexSquare {
    private Complex c;
    private Complex z = new Complex(0, 0);
    double[] line = new double[4]; // w1, h1, 12, h2
    double[] center;

    public ComplexSquare(Complex c) {
        this.c = c;
        center = new double[2];
        computeCenter(c, new Complex(0, 0));
    }

    public double[] computeLineCoordinates(double xOffset, double yOffset, double scale) {
        line[0] = line[2];
        line[1] = line[3];

        z = computeNextPoint(z, c);
        line[2] = z.re();
        line[3] = z.im();


        line[2] = (line[2] - center[0]) * scale + xOffset;
        line[3] = yOffset - scale * (center[1] - line[3]);

        return line;
    }

    public Complex computeNextPoint(Complex z, Complex c) {
        return z.times(z).plus(c);
    }

    private void computeCenter(Complex c, Complex z) {
        Complex tempZ = z;
        Complex tempC = c;

        for (int i = 0; i < 50; i++) {
            tempZ = computeNextPoint(tempZ, tempC);

            center[0] += tempZ.re();
            center[1] += tempZ.im();
        }

        center[0] /= 50.0;
        center[1] /= 50.0;
        //System.out.println("center: (" + center[0] + ", " + center[1] + ")");
    }

}
