package Fractals;

import javax.swing.*;
import java.awt.*;

public class FractalDrawer extends JPanel{
    public static double width;
    public static double height;
    public static double yOffset = 450;
    public static double xOffset = 450;

    public static double yScale = 1;
    public static double xScale = 1;

    private static double[] cMandelbrotSymmetry = {1 - 1.61803398875, 0};
    private static double[] cElongatedSpiral = {-0.8, 0.156};
    private static int cFlag = 2;

    private static double[] z = new double[2];
    private static double[] c = new double[2];
    private double convergence;


    private float[] initialValues;

    private FractalCalculations fractal;


    public static void main(String[] args) {
        FractalDrawer drawer = new FractalDrawer(450, 450, 1.5, 1);
        JFrame frame = new JFrame("Complex Square Rates of Convergence");


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 900);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        frame.add(drawer);
    }


    public static double getWindowWidth() {
        return width;
    }

    public static double getWindowHeight() {
        return height;
    }

    public static double getyOffset() {
        return yOffset;
    }

    public static double getxOffset() {
        return xOffset;
    }

    public static double getyScale() {
        return yScale;
    }

    public static double getxScale() {
        return xScale;
    }

    public FractalDrawer(double xScale, double yScale, double xOffset, double yOffset) {
        setxScale(xScale);
        setyScale(yScale);
        setxOffset(xOffset);
        setyOffset(yOffset);

        cFlag = 1;
        changeFractal();
    }

    public static void setyOffset(double temp) {
        yOffset = temp;
    }

    public static void setxOffset(double temp) {
        xOffset = temp;
    }

    public static void setyScale(double temp) {
        yScale = temp;
    }

    public static void setxScale(double temp) {
        xScale = temp;
    }

    public static void changeFractal() {
        if (cFlag == 0) {
            c[0] = cElongatedSpiral[0];
            c[1] = cElongatedSpiral[1];
            cFlag++;
        }
        else if (cFlag == 1) {
            c[0] = cMandelbrotSymmetry[0];
            c[1] = cMandelbrotSymmetry[1];
            cFlag++;
        }
        else if (cFlag == 2) {
            cFlag = 0;
        }
    }







    //Views
    private void ratioView(Graphics2D g2d) {
        z[0] = 0.0;
        z[1] = 0.0;

        initialValues = new float[(int)(width * height)];
        System.out.println(width * height);
        for (int i = 0; i < width; i++) {
            z[0] = (i - xOffset * xScale) / (xOffset);

            for (int j = 0; j < height; j++) {
                z[1] = (j - yOffset * yScale) / (yOffset);

                fractal = new FractalCalculations(new Complex(c[0], c[1]), new Complex(z[0], z[1]));
                convergence = fractal.computeMagnitudeBehavior(47);

                g2d = basicColorLimit(g2d);
                g2d.drawOval(i, j, 1, 1);
                //            if (convergence > max) {
//                    max = convergence;
//                }
//                System.out.println("Fractal convergence factor: " + convergence);

            }
        }
    }


    //Coloring methods
    private Graphics2D basicColorLimit(Graphics2D g2d) {

        if (convergence <= 15) {
            g2d.setColor(Color.MAGENTA);
        } else if (convergence < Math.pow(2, 28)) {
            g2d.setColor(Color.CYAN);
        } else if (convergence < Math.pow(2, 30)) {
            g2d.setColor(Color.YELLOW);
        } else if (convergence < Math.pow(2, 38)) {
            g2d.setColor(Color.RED);
        } else {
            g2d.setColor(Color.GREEN);
        }
        return g2d;
    }


    //System.out.println("max: " + max);

    public void scaledView(Graphics2D g2d) {
        //super.setSize((int)width, (int)height);

        for (int i = 0; i < width; i++) {
            z[0] = (i - xOffset * xScale) / (xScale);

            for (int j = 0; j < height; j++) {
                z[1] = (j - yOffset * yScale) / (yScale);

                fractal = new FractalCalculations(new Complex(c[0], c[1]), new Complex(z[0], z[1]));
                convergence = fractal.computeMagnitudeBehavior(50);

                basicColorLimit(g2d);

                //Draw the point in the selected color
                g2d.drawOval(i, j, 1, 1);
            }
        }
    }

    public void scaledMandelbrotView(Graphics2D g2d) {

        for (int i = 0; i < width; i++) {
            z[0] = (i - xOffset * xScale) / (xScale);

            for (int j = 0; j < height; j++) {
                z[1] = (j - yOffset * yScale) / (yScale);

                fractal = new FractalCalculations(new Complex(z[0], z[1]), new Complex(0, 0));
                convergence = fractal.computeMagnitudeBehavior(50);



                basicColorLimit(g2d);

                //Draw the point in the selected color
                g2d.drawOval(i, j, 1, 1);
            }
        }
    }



    public void testView(Graphics2D g2d) {
        c[0] = 0.0;
        c[1] = 0.0;

        initialValues = new float[(int)(width * height * 2)];
        for (int i = 0; i < width; i++) {
            z[0] = (i - xOffset * xScale) / (xScale);

            for (int j = 0; j < height; j++) {
                z[1] = (j - yOffset * yScale) / (yScale);


            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        width = this.getParent().getWidth();
        height = this.getParent().getHeight();
        //xOffset = width / 2.0;
        //yOffset = height / 2.0;

        //ratioView(g2d); // uses if statements and magnitude limits to color
        if (cFlag == 2 ) {
            scaledMandelbrotView(g2d);
        }
        else {
            scaledView(g2d);
        }
    }

}
