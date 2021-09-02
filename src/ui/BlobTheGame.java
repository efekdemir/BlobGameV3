package ui;

import model.BGame;
import ui.GamePanel;
import ui.ScorePanel;

import javax.swing.*;
import java.awt.*;

public class BlobTheGame extends JFrame {

    private static final int INTERVAL = 30;
    private BGame game;
    private GamePanel gp;
    private ScorePanel sp;
    private Timer timer;


    public BlobTheGame() {
        super("Blob The Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game = new BGame();
        gp = new GamePanel(game);
        getGraphics();
        sp = new ScorePanel(game);
        add(gp);
        add(sp, BorderLayout.NORTH);
        pack();
        centreOnScreen();
        setVisible(true);
        addTimer();
        timer.start();
    }


    private void addTimer() {
        timer = new Timer(INTERVAL, ae -> {
            game.update();
            gp.repaint();
            sp.update();

        });
    }

    private void centreOnScreen() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);
    }

}
