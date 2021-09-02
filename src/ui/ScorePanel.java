package ui;

import model.BGame;
import model.BlobGUI;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static java.lang.System.exit;
import static javax.sound.sampled.AudioSystem.getAudioInputStream;
import static javax.sound.sampled.AudioSystem.getClip;

public class ScorePanel extends JPanel {
    private static final String EATEN_TXT = "Food Pieces Eaten: ";
    private static final String BLOB_SIZE = "Blob Size: ";
    private static final String COORDINATES = "Blob's X-Y coordinates:";
    private static final int LBL_WIDTH = 200;
    private static final int LBL_HEIGHT = 30;
    final String zaWarudo = "memes/zawarudo.wav";
    JButton quit;
    JButton clearData;
    JButton load;
    Clip clip;
    private BGame game;
    private JLabel foodEatenLbl;
    private JLabel blobLbl;


    public ScorePanel(BGame g) {
        game = g;
        setBackground(new Color(180, 0, 5));
        foodEatenLbl = new JLabel(EATEN_TXT + game.getNumFoodPiecesEaten());
        foodEatenLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        blobLbl = new JLabel(BLOB_SIZE + BlobGUI.initialBlobSize);
        blobLbl = new JLabel(COORDINATES + "" + BlobGUI.startingX + "," + BlobGUI.startingY);
        blobLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        add(foodEatenLbl);
        add(Box.createHorizontalStrut(10));
        add(blobLbl);
        scorePanelButton();
        load = new JButton("Load");
        load.addActionListener(e -> {
                game.load(game.blobGUI);
            System.out.println("Loaded saved data.");
        });
        add(load);
        add(quit);
        add(clearData);
    }

    public void scorePanelButton() {
        quit = new JButton("Quit");
        quit.addActionListener(e -> {
            try {
                Thread.sleep(1200);
                game.save(game.blobGUI);
                soundPlayer(zaWarudo);
            } catch (InterruptedException a) {
                a.printStackTrace();
            }
            exit(0);
        });
        clearData = new JButton("Clear Save Data");
        clearData.addActionListener(e -> {
            game.clearData();
            soundPlayer(zaWarudo);
        });
    }

    public void update() {
        foodEatenLbl.setText(EATEN_TXT + game.getNumFoodPiecesEaten());
        blobLbl.setText(BLOB_SIZE + (game.returnBlobSize()));
        blobLbl.setText(COORDINATES + (game.getX1()) + "," + (game.getY1()));
        repaint();
    }

    public void soundPlayer(String music) {
        try {
            File jojoOST = new File(music);
            clip = getClip();
            clip.open(getAudioInputStream(jojoOST));
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}



