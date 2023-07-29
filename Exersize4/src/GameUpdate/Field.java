package GameUpdate;

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.util.*;

public class Field extends JComponent{
    public static final float GRAVITY = 9.8f;
    public static final int STEP = 40;
    public static final int APPLE_SIZE_IN_PIXELS = 30;
    public static final int TREE_WIDTH_IN_PIXELS = 60;
    public static final int TREE_HEIGHT_IN_PIXELS = 2 * TREE_WIDTH_IN_PIXELS;
    public static final int PHYSICIST_SIZE_IN_PIXELS = 75;
    public static final int MAX_TREES = 12;

    Color fieldColor = Color.GRAY;
    Random random = new Random();

    Physicist physicist;
    List<Tree> trees = Collections.synchronizedList(new ArrayList<>());

    protected void paintComponent(Graphics g) {
        g.setColor(fieldColor);
        g.fillRect(0, 0, getWidth(), getHeight());
        for (Tree t : trees){
            t.draw(g);
        }
        physicist.draw(g);
    }

    public void setPlayer(Physicist p){
        physicist = p;
    }

    public void addTree(int x, int y){
        Tree tree = new Tree();
        tree.setPosition(x, y);
        trees.add(tree);
    }
}