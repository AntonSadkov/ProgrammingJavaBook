package GameUpdate;

import javax.swing.*;

public class AppleToss extends JFrame{

    Field field = new Field();
    Physicist player1 = new Physicist();

    public AppleToss(){
        super("Apple Toss Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setResizable(true);

        setupFieldForOnePlayer();
    }

    private void setupFieldForOnePlayer(){
        player1.setPosition(100, 500);
        field.setPlayer(player1);
        player1.setField(field);

        for (int row = 1; row <= 3; row ++){
            for (int col = 1; col <=4; col++){
                field.addTree(col * 100, row * 100);
            }
        }
        add(field);
    }

    public static void main(String[] args){
        AppleToss game = new AppleToss();
        game.setVisible(true);
    }
}