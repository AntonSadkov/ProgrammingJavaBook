package Events;

import java.awt.*;
import javax.swing.*;

public class HelloMouseHelper {
    public static void main(String[] args){
        JFrame frame = new JFrame("MouseEvent Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(300,300);

        JLabel label = new JLabel("Hello, Mouse!", JLabel.CENTER);
        label.setOpaque(true);
        label.setBackground(Color.YELLOW);
        label.setSize(100,20);
        label.setLocation(100,100);
        frame.add(label);

        LabelMover mover = new LabelMover(label);
        frame.getContentPane().addMouseListener(mover);
        frame.setVisible(true);
    }
}