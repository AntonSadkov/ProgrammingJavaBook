package GameUpdateThread;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class AppleToss extends JFrame{
    public static final int FIELD_WIDTH = 800;
    public static final int FIELD_HEIGHT = 600;

    Field field = new Field();
    Physicist player1 = new Physicist();
    ArrayList<Physicist> otherPlayers = new ArrayList<>();
    Random random = new Random();

    public AppleToss(){
        super("Apple Toss Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(FIELD_WIDTH, FIELD_HEIGHT + 20);
        setResizable(true);

        setupFieldForOnePlayer();
        add(field);
    }

    private int goodX(){
        int leftMargin = Field.TREE_WIDTH_IN_PIXELS / 2 + 5;
        int rightMargin = FIELD_WIDTH - leftMargin;

        return leftMargin + random.nextInt(rightMargin - leftMargin);
    }
    private int goodY(){
        int topMargin = Field.TREE_WIDTH_IN_PIXELS / 2 + 3;
        int bottomMargin = FIELD_HEIGHT - Field.TREE_HEIGHT_IN_PIXELS;

        return topMargin + random.nextInt(bottomMargin - topMargin);
    }

    private void setupFieldForOnePlayer(){
        if (field.physicists.size() == 0){
            player1.setPosition(Field.PHYSICIST_SIZE_IN_PIXELS,
                    FIELD_HEIGHT - (int)(Field.PHYSICIST_SIZE_IN_PIXELS * 1.5));
            field.physicists.add(player1);
            player1.setField(field);
        }

        for (int i = field.trees.size(); i < 10; i++){
            Tree t = new Tree();
            t.setPosition(goodX(), goodY());
            while (player1.isTouching(t)){
                t.setPosition(goodX(), goodY());
                System.err.println("Repositioning an intersecting tree...");
            }
            field.trees.add(t);
        }
    }

    public static void main(String[] args){
        AppleToss game = new AppleToss();
        game.setVisible(true);
        try {
            game.player1.setAimingAngle(45.0f);
            game.field.repaint();
            Thread.sleep(1000);
            game.field.startTossFromPlayer(game.player1);
        } catch (InterruptedException ie) {
            System.err.println("Interrupted during initial pause before tossing an apple.");
        }
    }
}