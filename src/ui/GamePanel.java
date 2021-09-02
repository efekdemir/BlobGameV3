package ui;

import model.BlobGUI;
import model.BGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GamePanel extends JPanel {

    private static final String OVER = "You Win!";
    private static final String REPLAY = "Press R to Replay";
    private BGame game;

    public GamePanel(BGame g) {
        setPreferredSize(new Dimension(BGame.WIDTH, BGame.HEIGHT));
        setBackground(Color.WHITE);
        this.game = g;

        setKeyInputActions();
    }

    public void setKeyInputActions() {
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("A"), "LeftPressed");
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("W"), "UpPressed");
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("S"), "DownPressed");
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("D"), "RightPressed");
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("R"), "ReplayPressed");
        getActionMap().put("UpPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.blobGUI.direction = BlobGUI.Direction.D3;
            }
        });
        getActionMap().put("LeftPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.blobGUI.direction = BlobGUI.Direction.D1;
            }
        });
        moreActions();
    }

    public void moreActions() {
        getActionMap().put("DownPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.blobGUI.direction = BlobGUI.Direction.D4;
            }
        });
        getActionMap().put("RightPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.blobGUI.direction = BlobGUI.Direction.D2;
            }
        });
        getActionMap().put("ReplayPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.setUpGame();

            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGame(g);
        if (game.isOver()) {
            gameOver(g);
        }
    }

    public void drawGame(Graphics g) {
        game.draw(g);
    }

    private void gameOver(Graphics g) {
        Color saved = g.getColor();
        g.setColor(new Color(0, 0, 0));
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        FontMetrics fm = g.getFontMetrics();
        centreString(OVER, g, fm, BGame.HEIGHT / 2);
        centreString(REPLAY, g, fm, BGame.HEIGHT / 2 + 50);
        g.setColor(saved);
        game.clearFoodPieces();
    }

    private void centreString(String str, Graphics g, FontMetrics fm, int ypos) {
        int width = fm.stringWidth(str);
        g.drawString(str, (BGame.WIDTH - width) / 2, ypos);
    }
}
