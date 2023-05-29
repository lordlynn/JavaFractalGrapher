package Fractals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {

    private JButton moveUp;
    private JButton moveDown;
    private JButton moveLeft;
    private JButton moveRight;

    private JButton zoomIn;
    private JButton zoomOut;


    public ControlPanel() {
        ButtonListener listen = new ButtonListener();
        moveUp = new JButton("up");
        moveDown = new JButton("down");
        moveLeft = new JButton("left");
        moveRight = new JButton("right");
        zoomIn = new JButton("+");
        zoomOut = new JButton("-");

        moveUp.addActionListener(listen);
        moveDown.addActionListener(listen);
        moveLeft.addActionListener(listen);
        moveRight.addActionListener(listen);
        zoomIn.addActionListener(listen);
        zoomOut.addActionListener(listen);

        setLayout(new GridLayout(3,3));

        add(zoomOut);
        add(moveUp);
        add(zoomIn);
        add(moveLeft);
        add(new JLabel());
        add(moveRight);
        add(new JLabel());
        add(moveDown);
        add(new JLabel());

        super.setLocation(800, 200);
    }


    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent arg) {
            if (arg.getSource() == moveUp) {
                FractalDrawer.setyOffset(
                        FractalDrawer.getyOffset() + FractalDrawer.getyOffset() * 0.1);
            }
            else if (arg.getSource() == moveDown) {
                FractalDrawer.setyOffset(
                        FractalDrawer.getyOffset() - FractalDrawer.getyOffset() * 0.1);
            }
            else if (arg.getSource() == moveLeft) {
                FractalDrawer.setxOffset(
                        FractalDrawer.getxOffset() + FractalDrawer.getxOffset() * 0.1);
            }
            else if (arg.getSource() == moveRight) {
                FractalDrawer.setxOffset(
                        FractalDrawer.getxOffset() - FractalDrawer.getxOffset() * 0.1);
            }
            else if (arg.getSource() == zoomIn) {
                FractalDrawer.setxScale(
                        FractalDrawer.getxScale() + FractalDrawer.getxScale() * 0.05);
                FractalDrawer.setyScale(
                        FractalDrawer.getyScale() + FractalDrawer.getyScale() * 0.05);
            }
            else if (arg.getSource() == zoomOut) {
                FractalDrawer.setxScale(
                        FractalDrawer.getxScale() - FractalDrawer.getxScale() * 0.05);
                FractalDrawer.setyScale(
                        FractalDrawer.getyScale() - FractalDrawer.getyScale() * 0.05);
            }

        }
    }
}
