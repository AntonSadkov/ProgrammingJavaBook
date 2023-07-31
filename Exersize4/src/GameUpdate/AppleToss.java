package GameUpdate;

import javax.swing.*;
import java.util.Random;

public class AppleToss extends JFrame{
    public static final int FIELD_WIDTH = 800;
    public static final int FIELD_HEIGHT = 600;

    Field field = new Field();
    Physicist player1 = new Physicist();
    Random random = new Random();

    public AppleToss(){
        super("Apple Toss Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(FIELD_WIDTH, FIELD_HEIGHT);
        setResizable(true);

        setupFieldForOnePlayer();
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
        player1.setPosition(100, 500);
        field.setPlayer(player1);
        player1.setField(field);

        for(int i = field.trees.size(); i < Field.MAX_TREES; i++){
            Tree t = new Tree();
            t.setPosition(goodX(), goodY());

            while (player1.isTouching(t)){
                t.setPosition(goodX(),goodY());
                System.err.println("Обнаруженно пересечение, попробуем снова...");
            }
            field.addTree(t);
        }
        add(field);
    }

    public static void main(String[] args){
        AppleToss game = new AppleToss();
        game.setVisible(true);
    }
}