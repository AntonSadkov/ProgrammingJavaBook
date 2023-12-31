package GameFinal;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

public class Field extends JComponent implements ActionListener{

    public static final float GRAVITY = 9.8f;
    public static final int STEP = 40;
    public static final int APPLE_SIZE_IN_PIXELS  = 30;
    public static final int TREE_WIDTH_IN_PIXELS  = 60;
    public static final int TREE_HEIGHT_IN_PIXELS  = 2 * TREE_WIDTH_IN_PIXELS;
    public static final int PHYSICIST_SIZE_IN_PIXELS  = 75;
    public static final int MAX_TREES  = 10;

    Color fieldColor = Color.GRAY;
    Random random = new Random();

    Physicist physicist;
    int myScore = 0;
    String[] scores = new String[3];
    JLabel[] scoreLabels = new JLabel[3];
    List<Apple> apples = Collections.synchronizedList(new ArrayList<>());
    List<Tree> trees = Collections.synchronizedList(new ArrayList<>());

    boolean animating = false;
    Thread animationThread;
    Timer animationTimer;

    protected void paintComponent(Graphics g){
        g.setColor(fieldColor);
        g.fillRect(0,0,getWidth(), getHeight());
        physicist.draw(g);
        for (Tree t : trees){
            t.draw(g);
        }
        for (Apple a : apples){
            a.draw(g);
        }
    }

    public void setPlayer(Physicist p){
        physicist = p;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (animating && event.getActionCommand().equals("repaint")){
            for (Apple a : apples) {
                a.step();
                detectCollisions(a);
            }
            repaint();
            cullFallenApples();
        }
    }

    public void startTossFromPlayer(Physicist physicist){
        if(!animating){
            System.out.println("Starting animation!");
            animating = true;
            startAnimation();
        }
        if (animating){
            if (physicist.aimingApple != null){
                Apple apple = physicist.takeApple();
                apple.toss(physicist.aimingAngle, physicist.aimingForce);
                apples.add(apple);
                Timer appleLoader = new Timer(800, physicist);
                appleLoader.setActionCommand("New Apple");
                appleLoader.setRepeats(false);
                appleLoader.start();
            }
        }
    }

    void cullFallenApples(){
        Iterator<Apple> iterator = apples.iterator();
        while (iterator.hasNext()){
            Apple a = iterator.next();
            if (a.getCollidedPiece() != null){
                GamePiece otherPiece = a.getCollidedPiece();
                if (otherPiece instanceof Physicist){
                    hitPhysicist((Physicist) otherPiece);
                } else if (otherPiece instanceof Tree) {
                    hitTree((Tree) otherPiece);
                }
                iterator.remove();
            } else if (a.getPositionY() > 600){
                System.out.println("Culling apple");
                iterator.remove();
            }
        }
        if (apples.size() <= 0) {
            animating = false;
            if (animationTimer != null && animationTimer.isRunning()){
                animationTimer.stop();
            }
        }
    }

    void detectCollisions(Apple apple) {
        for (Apple a : apples) {
            if (apple.isTouching(a)){
                System.out.println("Touching another apple!");
                return;
            }
        }
        if (apple.isTouching(physicist)){
            System.out.println("Touching a physicist!");
            return;
        }

        for (Tree t : trees){
            if (apple.isTouching(t)){
                System.out.println("Touching a tree!");
                return;
            }
        }
    }

    void hitPhysicist(Physicist physicist) {
    }

    void hitTree(Tree tree){
        myScore += 10;
        trees.remove(tree);
        setScore(1, String.valueOf(myScore));
    }

    void startAnimation(){
        if (animationTimer == null) {
            animationTimer = new Timer(STEP, this);
            animationTimer.setActionCommand("repaint");
            animationTimer.setRepeats(true);
            animationTimer.start();
        } else if (!animationTimer.isRunning()) {
            animationTimer.restart();
        }
    }

    public String getScore(int playerNumber){
        return scores[playerNumber];
    }

    public void setScore(int playerNumber, String score){
        scores[playerNumber] = score;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                String newScore = " Player " + playerNumber + ": " + score;
                scoreLabels[playerNumber].setText(newScore);
            }
        });
    }

    public String getWinner() {
        int score2 = -1;
        try {
            score2 = Integer.parseInt(scores[2]);
        } catch (NumberFormatException nfe) {
            System.err.println("Couldn't parse the other player's score: " + scores[2]);
        }
        if (myScore == score2){
            return "It's a tie!";
        } else if (myScore > score2) {
            return "You won!";
        } else {
            return "They won.";
        }
    }

    private int goodX() {
        int leftMargin = TREE_WIDTH_IN_PIXELS / 2 + 5;
        int rightMargin = AppleToss.FIELD_WIDTH - leftMargin;

        return leftMargin + random.nextInt(rightMargin - leftMargin);
    }

    private int goodY() {
        int topMargin = TREE_WIDTH_IN_PIXELS / 2 + 5;
        int bottomMargin = AppleToss.FIELD_HEIGHT - TREE_HEIGHT_IN_PIXELS;

        return topMargin + random.nextInt(bottomMargin - topMargin);
    }

    public void setupNewGame() {
        trees.clear();

        for(int i = trees.size(); i < Field.MAX_TREES; i++){
            Tree t = new Tree();
            t.setPosition(goodX(), goodY());

            while (physicist.isTouching(t)){
                t.setPosition(goodX(), goodY());
                System.err.println("Repositioning an intersecting tree...");
            }
            trees.add(t);
        }
        repaint();
    }

    class Animator implements Runnable{

        @Override
        public void run() {
            while (animating) {
                System.out.println("Stepping " + apples.size() + " apples");
                for (Apple a : apples){
                    a.step();
                    detectCollisions(a);
                }
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        Field.this.repaint();
                    }
                });
                cullFallenApples();
                try {
                    Thread.sleep((int)(STEP * 1000));
                } catch (InterruptedException ie){
                    System.err.println("Animation interrupted");
                    animating = false;
                }
            }
        }
    }
}