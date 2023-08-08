package Events;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionDemo_2 {
    public static void main(String[] args){
        JFrame frame = new JFrame("ActionListener Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setSize(300, 100);

        JLabel label = new JLabel("Result go here", JLabel.CENTER);
        ActionCommandHelper helper = new ActionCommandHelper(label);

        JButton simpleButton = new JButton("Button");
        simpleButton.addActionListener(helper);

        JTextField simpleField = new JTextField(10);
        simpleField.addActionListener(helper);

        frame.add(simpleButton);
        frame.add(simpleField);
        frame.add(label);
        frame.setVisible(true);
    }
}

class ActionCommandHelper implements ActionListener{
    JLabel resultLabel;

    public ActionCommandHelper(JLabel label){
        resultLabel = label;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        resultLabel.setText(e.getActionCommand());
    }
}