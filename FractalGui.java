package Fractals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FractalGui {

    public static JFrame frame;
    public static FractalDrawer drawer;

    public static void main (String[] args) {
        JFrame controlFrame = new JFrame ("Controls");
        ControlPanel controls = new ControlPanel();
        controlFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        controlFrame.setSize(375, 375);
        controlFrame.add(controls);
        controlFrame.setVisible(true);



        frame = new JFrame ("Fractal Viewer");
        drawer = new FractalDrawer(450, 450, 1.5, 1);
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.setSize (1275, 900);

        frame.add(drawer);
        frame.setVisible(true);

    }

    private static class ControlPanel extends JPanel {

        private JButton moveUp;
        private JButton moveDown;
        private JButton moveLeft;
        private JButton moveRight;

        private JButton zoomIn;
        private JButton zoomOut;

        private JButton selectFractal;

        private JTextField moveStep;
        private JTextField zoomStep;
        private JCheckBox autoSize;


        public ControlPanel() {
            ButtonListener listen = new ButtonListener();
            moveUp = new JButton("up");
            moveDown = new JButton("down");
            moveLeft = new JButton("left");
            moveRight = new JButton("right");
            zoomIn = new JButton("+");
            zoomOut = new JButton("-");
            selectFractal = new JButton("Change Fractal");
            moveStep = new JTextField("0.5");
            zoomStep = new JTextField("0.25");
            autoSize = new JCheckBox();


            moveUp.addActionListener(listen);
            moveDown.addActionListener(listen);
            moveLeft.addActionListener(listen);
            moveRight.addActionListener(listen);
            zoomIn.addActionListener(listen);
            zoomOut.addActionListener(listen);
            selectFractal.addActionListener(listen);

            setLayout(new GridLayout(6,3));

            add(zoomOut);
            add(moveUp);
            add(zoomIn);

            add(moveLeft);
            add(moveDown);
            add(moveRight);

            add(new JLabel("Auto step size: "));
            add(new JLabel());
            add(autoSize);

            add(new JLabel("Movement step: "));
            add(new JLabel());
            add(moveStep);

            add(new JLabel("Zoom step: "));
            add(new JLabel());
            add(zoomStep);

            add(selectFractal);

        }


        private class ButtonListener implements ActionListener {
            double mStep;
            double zStep;


            public void actionPerformed(ActionEvent arg) {
                getStep();
                if (arg.getSource() == moveUp) {
                    FractalDrawer.setyOffset(
                            FractalDrawer.getyOffset() + mStep);
                }
                else if (arg.getSource() == moveDown) {
                    FractalDrawer.setyOffset(
                            FractalDrawer.getyOffset() - mStep);
                }
                else if (arg.getSource() == moveLeft) {
                    FractalDrawer.setxOffset(
                            FractalDrawer.getxOffset() + mStep);
                }
                else if (arg.getSource() == moveRight) {
                    FractalDrawer.setxOffset(
                            FractalDrawer.getxOffset() - mStep);
                }
                else if (arg.getSource() == zoomIn) {
                    FractalDrawer.setxScale(
                            FractalDrawer.getxScale() + zStep);
                    FractalDrawer.setyScale(
                            FractalDrawer.getyScale() + zStep);
                }
                else if (arg.getSource() == zoomOut) {
                    FractalDrawer.setxScale(
                            FractalDrawer.getxScale() - zStep);
                    FractalDrawer.setyScale(
                            FractalDrawer.getyScale() - zStep);
                }
                else if (arg.getSource() == selectFractal) {
                    FractalDrawer.changeFractal();
                }
                frame.add(drawer);
                frame.repaint();

            }

            private void getStep() {
                if (!autoSize.isSelected()) {
                    mStep = Double.parseDouble(moveStep.getText());
                    zStep = Double.parseDouble(zoomStep.getText());
                }
                else {
                    mStep = 0.1 * Math.abs(-FractalDrawer.getxOffset() +
                            (FractalDrawer.getWindowWidth() - FractalDrawer.getxOffset() * FractalDrawer.getxScale()) / FractalDrawer.getxScale());
                    zStep = 250 * Math.abs(-FractalDrawer.getxOffset() +
                            (FractalDrawer.getWindowWidth() - FractalDrawer.getxOffset() * FractalDrawer.getxScale()) / FractalDrawer.getxScale());
                }
                moveStep.setText(String.format("%.8f", mStep));
                zoomStep.setText(String.format("%.8f", zStep));
            }

        }
    }


}




