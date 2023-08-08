package Events;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LabelMover extends MouseAdapter {
    JLabel labelToMove;
    public LabelMover(JLabel label) {
        labelToMove = label;
    }

    public void mouseClicked(MouseEvent e){
        labelToMove.setLocation(e.getX(), e.getY());
    }
}