package CreatingPageElements;

import javax.swing.*;
import java.awt.*;

public class Labels {
    public static void main(String[] args){
        JFrame frame = new JFrame("JLabel Examples");
        frame.setLayout(new GridLayout(0,1));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);

        JLabel basic = new JLabel("Default Label");
        basic.setOpaque(true);
        basic.setBackground(Color.WHITE);

        JLabel another = new JLabel("Another Label");
        another.setOpaque(true);
        another.setBackground(Color.BLUE);

        JLabel simple = new JLabel("Simple Label");
        simple.setOpaque(true);
        simple.setBackground(Color.RED);

        JLabel standard = new JLabel("Standard Label");
        standard.setOpaque(true);
        standard.setBackground(Color.DARK_GRAY);

        frame.add(basic);
        frame.add(another);
        frame.add(simple);
        frame.add(standard);

        frame.setVisible(true);
    }
}