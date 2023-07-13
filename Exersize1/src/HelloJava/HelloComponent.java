package HelloJava;

import javax.swing.*;
import java.awt.*;

class HelloComponent extends JComponent {
    @Override
    public void paintComponent(Graphics g) {

        g.drawString("Hello, Java!", 125, 95);
    }
}