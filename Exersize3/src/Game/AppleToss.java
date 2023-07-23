package Game;

import javax.swing.*;

public class AppleToss extends JFrame {
    Field field = new Field();
    Physicist player1 = new Physicist();
    Physicist player2 = new Physicist();

    public AppleToss() {
        super("Apple Toss Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,900);
        setResizable(false);

        setupFieldForOnePlayer();
    }

    private void setupFieldForOnePlayer() {
        field.setupPlayer(player1);
        field.setupPlayer(player2);
        player1.setField(field);
        player2.setField(field);
        field.setupApples();
        field.setupTree();
        add(field);
    }

    public static void main(String args[]){
        AppleToss game = new AppleToss();
        game.setVisible(true);
    }
}