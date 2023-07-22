package HelloJava3;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class HelloComponent3 extends JComponent {
    String theMessage;
    int messageX = 125, messageY = 95;

    public HelloComponent3 (String message) {
        this.theMessage = message;
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                messageX = e.getX();
                messageY = e.getY();
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {}
        });
    }

    public void paintComponent(Graphics g) {
        g.drawString(theMessage, messageX, messageY);
    }
}