package HelloJava3;

import javax.swing.*;

public class HelloJava3 extends JFrame {
    public static void main(String[] args) {
        HelloJava3 demo = new HelloJava3();
        demo.setVisible(true);
    }

    public HelloJava3() {
        super("Тестовый пример");
        add(new HelloComponent3("Передвинь =)"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize( 300, 300);
    }
}