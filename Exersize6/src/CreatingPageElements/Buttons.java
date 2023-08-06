package CreatingPageElements;

import javax.swing.*;
import java.awt.*;

public class Buttons {
    public static void main (String[] args) {
        JFrame frame = new JFrame("JButtons Examples");
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JButton basic = new JButton("Нажми");
        frame.add(basic);

        frame.setVisible(true);
    }
}