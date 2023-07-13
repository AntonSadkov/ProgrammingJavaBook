package HelloJava;

import javax.swing.*;

public class HelloJava {
    public static void main(String[] args) {
        JFrame frame1 = new JFrame("Hello, Java!");
        frame1.add(new HelloComponent());
        frame1.setSize(300, 300);
        frame1.setVisible(true);

        JFrame frame2 = new JFrame("Ты выучишь Java!");
        JLabel label2 = new JLabel("Просто терпения друг мой. Всё получиться!", JLabel.CENTER);
        frame2.add(label2);
        frame2.setSize(500, 500);
        frame2.setVisible(true);
    }
}