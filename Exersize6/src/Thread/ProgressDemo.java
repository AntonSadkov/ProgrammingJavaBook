package Thread;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProgressDemo {
    public static void main(String[] args){
        JFrame frame = new JFrame("SwingUtilities 'invoke' Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setSize(300, 400);

        JLabel label = new JLabel("Download Progress Goes here!", JLabel.CENTER);
        Thread pretender = new Thread(new ProgressPretender(label));

        JButton simpleButton = new JButton("Start");
        simpleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simpleButton.setEnabled(false);
                pretender.start();
            }
        });

        JLabel checkLabel = new JLabel("Can you still type?");
        JTextField checkField = new JTextField(10);

        frame.add(label);
        frame.add(simpleButton);
        frame.add(checkLabel);
        frame.add(checkField);
        frame.setVisible( true );
    }
}