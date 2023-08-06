package CreatingPageElements;

import javax.swing.*;
import java.awt.*;

public class PhoneGridDemo {
    public static void main(String[] args){
        JFrame frame = new JFrame("Phone Grid Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 300);

        JPanel phonePad = new JPanel(new GridLayout(4,3));

        phonePad.add(new JButton("1"));
        phonePad.add(new JButton("2"));
        phonePad.add(new JButton("3"));
        phonePad.add(new JButton("4"));
        phonePad.add(new JButton("5"));
        phonePad.add(new JButton("6"));
        phonePad.add(new JButton("7"));
        phonePad.add(new JButton("8"));
        phonePad.add(new JButton("9"));
        phonePad.add(new JButton("*"));
        phonePad.add(new JButton("0"));
        phonePad.add(new JButton("#"));

        frame.add(phonePad, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}