package Thread;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProgressPretender implements Runnable{

    JLabel label;
    int progress;

    public ProgressPretender(JLabel label){
        this.label = label;
        progress = 0;
    }

    @Override
    public void run() {
        while(progress <= 100) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    label.setText(progress + "%");
                }
            });
            try {
                Thread.sleep(1000);
            }catch (InterruptedException ie){
                System.err.println("Someone interrupted us. Skipping download.");
                break;
            }
            progress++;
        }

    }
}