package Reshala;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class LogicsReshala extends JFrame implements ActionListener {
    JLabel logicsReshala;

    public LogicsReshala(){
        super("Приложение Решала");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(400, 300);

        logicsReshala = new JLabel("Загадай вопрос для решалы", JLabel.CENTER);
        add(logicsReshala);

        JButton button = new JButton("Да или Нет");
        button.addActionListener(this);
        add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String[] texts = {"Да", "Нет", "Да", "Нет", "Будет точно", "Мало вероятно", "Скоро"};
        Random random = new Random ();
        int pos = random.nextInt(texts.length);
        JOptionPane.showMessageDialog(this, texts[pos], "Результат решения",
                JOptionPane.INFORMATION_MESSAGE);
        logicsReshala.setText("Попробуй ещё!");
    }
}