package Events;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionDemo_1 extends JFrame implements ActionListener{

    int counterValue = 0;
    JLabel counterLabel;

    public ActionDemo_1(){
        super("ActionEvent Counter Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(300, 200);

        counterLabel = new JLabel("count: 0", JLabel.CENTER);
        add(counterLabel);

        JButton incrementer = new JButton("increment");
        incrementer.addActionListener(this);
        add(incrementer);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        counterValue++;
        counterLabel.setText("Count: " + counterValue);
    }

    public static void main(String[] args){
        ActionDemo_1 demo = new ActionDemo_1();
        demo.setVisible(true);
    }
}