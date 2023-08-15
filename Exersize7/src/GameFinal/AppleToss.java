package GameFinal;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class AppleToss extends JFrame {
    public static final int SCORE_HEIGHT = 30;
    public static final int CONTROL_WIDTH = 300;
    public static final int CONTROL_HEIGHT = 40;
    public static final int FIELD_WIDTH = 3 * CONTROL_WIDTH;
    public static final int FIELD_HEIGHT = 2 * CONTROL_WIDTH;
    public static final float FORCE_SCALE = 0.7f;

    GridBagLayout gameLayout = new GridBagLayout();
    GridBagConstraints gameConstraints = new GridBagConstraints();
    JPanel gamePane = new JPanel(gameLayout);

    Field field = new Field();
    Physicist player1 = new Physicist();
    Multiplayer multiplayerHelper;

    public AppleToss() {
        super("Apple Toss Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setupFieldForOnePlayer();

        gameLayout.columnWidths = new int[] { CONTROL_WIDTH, CONTROL_WIDTH, CONTROL_WIDTH };
        gameLayout.rowHeights = new int[] { SCORE_HEIGHT, FIELD_HEIGHT, CONTROL_HEIGHT, CONTROL_HEIGHT };

        JLabel player1score = new JLabel(" Player 1: 0");
        field.scoreLabels[1] = player1score;
        JLabel player2score = new JLabel(" Player 2");
        field.scoreLabels[2] = player2score;
        gamePane.add(player1score, buildConstraints(0, 0, 1, 1));
        gamePane.add(player2score, buildConstraints(0, 1, 1, 1));
        gamePane.add(buildRestartButton(), buildConstraints(0, 2, 1, 1));
        gamePane.add(field, buildConstraints(1, 0, 1, 3));
        gamePane.add(buildAngleControl(), buildConstraints(2, 0, 1, 1));
        gamePane.add(buildForceControl(), buildConstraints(2, 1, 1, 1));
        gamePane.add(buildTossButton(), buildConstraints(2, 2, 2, 1));
        gamePane.add(new JLabel("Angle", JLabel.CENTER), buildConstraints(3, 0, 1, 1));
        gamePane.add(new JLabel("Force", JLabel.CENTER), buildConstraints(3, 1, 1, 1));

        gamePane.setPreferredSize(new Dimension(FIELD_WIDTH,SCORE_HEIGHT + FIELD_HEIGHT + (2 * CONTROL_HEIGHT)));
        setContentPane(gamePane);

        setupNetworkMenu();

        pack();
    }

    private GridBagConstraints buildConstraints(int row, int col, int rowspan, int colspan) {
        gameConstraints.fill = GridBagConstraints.BOTH;
        gameConstraints.gridy = row;
        gameConstraints.gridx = col;
        gameConstraints.gridheight = rowspan;
        gameConstraints.gridwidth = colspan;
        return gameConstraints;
    }

    private JButton buildRestartButton() {
        JButton button = new JButton("Play Again");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setupFieldForOnePlayer();
                field.repaint();
            }
        });
        return button;
    }

    private JSlider buildAngleControl() {
        JSlider slider = new JSlider(0,180);
        slider.setInverted(true);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                player1.setAimingAngle((float)slider.getValue());
                field.repaint();
            }
        });
        return slider;
    }

    private JSlider buildForceControl() {
        JSlider slider = new JSlider();
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                player1.setAimingForce(slider.getValue() * FORCE_SCALE);
            }
        });
        return slider;
    }

    private JButton buildTossButton() {
        JButton button = new JButton("Toss");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                field.startTossFromPlayer(player1);
            }
        });
        return button;
    }

    private void setupFieldForOnePlayer() {
        player1.setPosition(Field.PHYSICIST_SIZE_IN_PIXELS, FIELD_HEIGHT - (int) (Field.PHYSICIST_SIZE_IN_PIXELS * 1.5));
        field.setPlayer(player1);
        player1.setField(field);
        field.setupNewGame();
    }

    private void setupNetworkMenu() {
        JMenu netMenu = new JMenu("Multiplayer");
        multiplayerHelper = new Multiplayer(field);

        JMenuItem startItem = new JMenuItem("Start Server");
        startItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                multiplayerHelper.startServer();
            }
        });
        netMenu.add(startItem);

        JMenuItem joinItem = new JMenuItem("Join Game...");
        joinItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String otherServer = JOptionPane.showInputDialog(AppleToss.this, "Enter server name or address:");
                multiplayerHelper.joinGame(otherServer);
            }
        });
        netMenu.add(joinItem);

        JMenuItem quitItem = new JMenuItem("Disconnect");
        quitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                multiplayerHelper.disconnect();
            }
        });
        netMenu.add(quitItem);

        JMenuBar mainBar = new JMenuBar();
        mainBar.add(netMenu);
        setJMenuBar(mainBar);
    }

    public static void main(String args[]) {
        AppleToss game = new AppleToss();
        game.setVisible(true);
    }
}