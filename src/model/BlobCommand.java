package model;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class BlobCommand extends Sprite implements Serializable {

    public static final int initialBlobSize = 10;
    public static final int firstEvolutionSizeThreshold = 60;
    public static final int startingX = (BGame.WIDTH / 2);
    public static final int startingY = (BGame.HEIGHT / 2);
    public static final Color startingColor = new Color(255, 84, 0);
    public static final Color firstEvolutionColor = new Color(0, 242, 255);
    public int blobSize;

    public BlobCommand(int x1, int y1, int radius) {
        super(x1, y1, radius);
        this.blobSize = initialBlobSize;
        this.radius = blobSize;
        this.color = startingColor;
        this.x1 = startingX;
        this.y1 = startingY;
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

    public Color getColor() {
        return color;
    }

    public void moveBlobRight() {
        x1 = x1 + 1;
    }

    public void moveBlobLeft() {
        x1 = x1 - 1;
    }

    public void moveBlobUp() {
        y1 = y1 + 1;
    }

    public void moveBlobDown() {
        y1 = y1 - 1;
    }

    public void eatFoodPiece(FoodPiece f) {
        radius += f.getRadius();
    }

    public boolean evolveBlob() {
        if (radius >= firstEvolutionSizeThreshold) {
            color = firstEvolutionColor;
            return true;
        } else {
            color = startingColor;
            return false;
        }
    }


    public void save(BlobCommand blob) {
        Path path = Paths.get("data/blobCommandSaveData.txt");
        String contents = "" + blob.x1 + blob.y1 + blob.radius;
        byte[] bytes = contents.getBytes();
        try {
            Files.write(path, bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        public void load(BlobCommand blob) {
            try {
                File loadData = new File("data/blobCommandSaveData.txt");
                Scanner myReader = new Scanner(loadData);
                String data = myReader.nextLine();
                blob.x1 = Integer.parseInt(data.substring(0, 3));
                blob.y1 = Integer.parseInt(data.substring(3, 6));
                blob.radius = Integer.parseInt(data.substring(6));
                } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    @Override
    public void draw(Graphics g) {
        Color savedCol = g.getColor();
        g.setColor(color);
        g.fillOval(getX1() - radius / 2, getY1() - radius / 2, radius, radius);
        g.setColor(savedCol);
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

}
