package Game;

import javax.swing.*;
import java.awt.*;

public class Field extends JComponent {
    public static final float GRAVITY = 9.8f;
    public static final int STEP = 40;
    public static final int APPLE_SIZE_IN_PIXELS = 30;
    public static final int TREE_WIDTH_IN_PIXELS = 60;
    public static final int TREE_HEIGHT_IN_PIXELS = 2 * TREE_WIDTH_IN_PIXELS;
    public static final int PHYSICIST_SIZE_IN_PIXELS = 75;
    public static final int MAX_TREES = 12;

    Color fieldColor = Color.GRAY;

    Apple a1 = new Apple();
    Apple a2 = new Apple();
    Apple a3 = new Apple();
    Apple a4 = new Apple();
    Tree tree1 = new Tree();
    Tree tree2 = new Tree();
    Tree tree3 = new Tree();
    Physicist physicist1 = new Physicist();
    Physicist physicist2 = new Physicist();

    public void setupApples() {
        a1.diameter = 3.0f;
        a1.mass = 5.0f;
        a1.x = 20;
        a1.y = 40;
        a2.diameter = 8.0f;
        a2.mass = 10.0f;
        a2.x = 70;
        a2.y = 250;
        a3.diameter = 10.0f;
        a3.mass = 12.0f;
        a3.x = 270;
        a3.y = 500;
        a4.diameter = 5.0f;
        a4.mass = 7.0f;
        a4.x = 300;
        a4.y = 200;
    }
    public void setupTree() {
        tree1.setPosition(500,200);
        tree2.setPosition(700,600);
        tree3.setPosition(320,400);
    }
    public void setupPlayer(Physicist p) {
        physicist1.setPosition(100,790);
        physicist2.setPosition(300,790);
    }
    protected void paintComponent(Graphics g) {
        g.setColor(fieldColor);
        g.fillRect(0,0, getWidth(), getHeight());
        tree1.draw(g);
        tree2.draw(g);
        tree3.draw(g);
        physicist1.draw(g);
        physicist2.draw(g);
        a1.draw(g);
        a2.draw(g);
        a3.draw(g);
        a4.draw(g);
    }
    public void detectCollisions() {
        if (a1.isTouching(a2)) {
            System.out.println("Collision detected!");
        } else {
            System.out.println("Apples are not touching.");
        }
    }
}