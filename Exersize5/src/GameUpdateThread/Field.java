package GameUpdateThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Field extends JComponent implements ActionListener {
    public static final float GRAVITY = 9.8f;
    public static final int STEP = 40;
    public static final int APPLE_SIZE_IN_PIXELS = 30;
    public static final int TREE_WIDTH_IN_PIXELS = 60;
    public static final int TREE_HEIGHT_IN_PIXELS = 2 * TREE_WIDTH_IN_PIXELS;
    public static final int PHYSICIST_SIZE_IN_PIXELS = 75;

    public static final int MAX_PHYSICISTS = 5;
    public static final int MAX_APPLES_PER_PHYSICIST = 3;
    public static final int MAX_TREES = 12;

    Color fieldColor = Color.GRAY;

    List<Physicist> physicists = Collections.synchronizedList(new ArrayList<>());
    List<Apple> apples = Collections.synchronizedList(new ArrayList<>());
    List<Tree> trees = Collections.synchronizedList(new ArrayList<>());

    boolean animating = false;
    Thread animationThread;
    Timer animationTimer;

    protected void paintComponent(Graphics g) {
        g.setColor(fieldColor);
        g.fillRect(0,0, getWidth(), getHeight());
        for (Physicist p : physicists) {
            p.draw(g);
        }
        for (Tree t : trees) {
            t.draw(g);
        }
        for (Apple a : apples) {
            a.draw(g);
        }
    }

    public void actionPerformed(ActionEvent event) {
        if (animating && event.getActionCommand().equals("repaint")) {
            System.out.println("Timer stepping " + apples.size() + " apples");
            for (Apple a : apples) {
                a.step();
                detectCollisions(a);
            }
            repaint();
            cullFallenApples();
        }
    }

    public void startTossFromPlayer(Physicist physicist) {
        if (!animating) {
            System.out.println("Starting animation!");
            animating = true;
            startAnimation();
        }
        if (animating) {
            if (physicist.aimingApple != null) {
                Apple apple = physicist.takeApple();
                apple.toss(physicist.aimingAngle, physicist.aimingForce);
                apples.add(apple);
            }
        }
    }

    void cullFallenApples() {
        Iterator<Apple> iterator = apples.iterator();
        while (iterator.hasNext()) {
            Apple a = iterator.next();
            if (a.getPositionY() > 600) {
                System.out.println("Culling apple");
                iterator.remove();
            }
        }
        if (apples.size() <= 0) {
            animating = false;
            if (animationTimer != null && animationTimer.isRunning()) {
                animationTimer.stop();
            }
        }
    }

    void detectCollisions(Apple apple) {
        for (Apple a : apples) {
            if (apple.isTouching(a)) {
                System.out.println("Touching another apple!");
                return;
            }
        }
        for (Physicist p : physicists) {
            if (apple.isTouching(p)) {
                System.out.println("Touching a physicist!");
                return;
            }
        }
        for (Tree t : trees) {
            if (apple.isTouching(t)) {
                System.out.println("Touching a tree!");
                return;
            }
        }
    }

    void hitPhysicist(Physicist physicist) {
        physicists.remove(physicist);
    }

    void hitTree(Tree tree) {
        trees.remove(tree);
    }

    void startAnimation() {
        if (animationTimer == null) {
            animationTimer = new Timer(STEP, this);
            animationTimer.setActionCommand("repaint");
            animationTimer.setRepeats(true);
            animationTimer.start();
        } else if (!animationTimer.isRunning()) {
            animationTimer.restart();
        }
    }

    class Animator implements Runnable {
        public void run() {
            while (animating) {
                System.out.println("Stepping " + apples.size() + " apples");
                for (Apple a : apples) {
                    a.step();
                    detectCollisions(a);
                }
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        Field.this.repaint();
                    }
                });
                cullFallenApples();
                try {
                    Thread.sleep((int)(STEP * 1000));
                } catch (InterruptedException ie) {
                    System.err.println("Animation interrupted");
                    animating = false;
                }
            }
        }
    }
}