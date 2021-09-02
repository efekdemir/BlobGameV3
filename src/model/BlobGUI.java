package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class BlobGUI extends Sprite implements Serializable {

    public static final int initialBlobSize = 15;
    public static final int firstEvolutionSizeThreshold = 60;
    public static final int startingX = 450;
    public static final int startingY = 450;
    public static final int DX = 5;
    public static final Color c1 = new Color(50, 100, 150);
    public static final Color c2 = new Color(60, 200, 255);
    public int blobSize;
    public Direction direction;
    public boolean evolved;
    public boolean shrunk;
    public BufferedImage img1 = null;
    public BufferedImage img2 = null;


    public BlobGUI(int x1, int y1, int radius) {
        super(x1, y1, radius);
        this.blobSize = initialBlobSize;
        this.radius = blobSize;
        this.color = c1;
        this.x1 = startingX;
        this.y1 = startingY;
        direction = Direction.D0;
        evolved = false;
        shrunk = false;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getBlobSize() {
        return radius;
    }


    public void evolveBlob() {
        if (radius >= firstEvolutionSizeThreshold) {
            color = c2;
            evolved = true;
            shrunk = true;
        } else if (!shrunk) {
            color = c1;
        }
    }


    @Override
    public void draw(Graphics g) {

        Color savedCol = g.getColor();
        g.setColor(color);
        g.fillOval(getX1() - radius / 2, getY1() - radius / 2, radius, radius);
        g.setColor(Color.black);
        g.drawOval(getX1() - radius / 2, getY1() - radius / 2, radius, radius);
        g.setColor(savedCol);

        try {
            img1 = ImageIO.read(new File("/Users/Efe/IdeaProjects/BlobGameV3/data/blob1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            img2 = ImageIO.read(new File("/Users/Efe/IdeaProjects/BlobGameV3/data/blob2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!shrunk) {
            g.drawImage(img1, getX1() - radius / 2, getY1() - radius / 2, radius, radius, null);
        } else {
            g.drawImage(img2, getX1() - radius / 2, getY1() - radius / 2, radius, radius, null);
        }
    }

    protected void handleBoundary() {
        if (x1 < 0) {
            x1 = 0;
        } else if (x1 > BGame.WIDTH) {
            x1 = BGame.WIDTH;
        } else if (y1 > BGame.HEIGHT) {
            y1 = BGame.HEIGHT;
        } else if (y1 < 0) {
            y1 = 0;
        }
    }

    public void move() {
        switch (direction) {
            case D0:
                break;
            case D3:
                y1 = y1 - DX;
                break;
            case D4:
                y1 += DX;
                break;
            case D1:
                x1 -= DX;
                break;
            case D2:
                x1 += DX;
                break;
        }

        handleBoundary();
    }

    public enum Direction {
        D0,
        D1,
        D2,
        D3,
        D4
    }

}
