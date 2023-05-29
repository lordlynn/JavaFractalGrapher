package Fractals;

import javax.swing.*;
import java.awt.*;

public class ComplexSquareGrapher extends JPanel{
    private double width;
    private double height;
    private double yOffset;
    private double xOffset;
    private double scale;

    private static ComplexSquare lines;

    private int iterations = 125;
    private double[] line;


    // Try -0.5+0.485i for star spiral
    private static Complex startingValue = new Complex(-0.485, 0.5);

    public static void main(String[] args) {
        ComplexSquareGrapher drawer = new ComplexSquareGrapher();
        JFrame frame = new JFrame("Converging Complex Square");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 750);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        frame.add(drawer);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        BasicStroke axis = new BasicStroke(1);
        BasicStroke fractal = new BasicStroke((float)1.25);


        Graphics2D g2d = (Graphics2D) g;

        width = this.getParent().getWidth();
        height = this.getParent().getHeight();
        xOffset = width / 2.0;
        yOffset = height / 2.0;

        scale = Math.sqrt(Math.pow(xOffset, 2.2) + Math.pow(yOffset, 2.2)); // CHECK ON THIS AND AUTOMATE
        //System.out.println("scale = " + scale);

        lines = new ComplexSquare(startingValue);

        g2d.setStroke(axis);

        g2d.drawLine((int)xOffset, (int)height, (int)xOffset, 0);
        g2d.drawLine(0, (int)yOffset, (int)width, (int)yOffset);


        line = lines.computeLineCoordinates(xOffset, yOffset, scale);
        line = lines.computeLineCoordinates(xOffset, yOffset, scale);

        g2d.setStroke(fractal);
        for (int i = 0; i < iterations; i++) {

            g2d.setColor(Color.BLUE);
            line = lines.computeLineCoordinates(xOffset, yOffset, scale);
            g2d.drawLine((int) line[0], (int) line[1], (int) line[2], (int) line[3]);

        }


    }
}
