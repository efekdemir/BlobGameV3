package model;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static javax.sound.sampled.AudioSystem.getAudioInputStream;
import static javax.sound.sampled.AudioSystem.getClip;


public class BGame {
    public static final int WIDTH = 900;
    public static final int HEIGHT = 900;
    public static final int maxFoodPieces = 20;
    public static final Random RND = new Random();
    public static final int spawnPointX = BlobGUI.startingX;
    public static final int spawnPointY = BlobGUI.startingY;
    final String zaWarudo = "memes/zawarudo.wav";
    final String josukeThemeSong = "memes/josuke.wav";
    final String giornoThemeSong = "memes/giorno.wav";
    private final List<FoodPiece> foodPieces;
    private final List<RottenFoodPiece> rottenFoodPieces;
    private final List<Sprite> sprites;
    public BlobGUI blobGUI;
    public Clip clip;
    private boolean isGameOver;


    public BGame() {
        sprites = new ArrayList<>();
        foodPieces = new ArrayList<>();
        rottenFoodPieces = new ArrayList<>();
        setUpGame();
        soundPlayer(josukeThemeSong);
    }

    public void clearSprites() {
        sprites.clear();
    }

    public void clearFoodPieces() {
        foodPieces.clear();
    }

    public void clearRottenFoodPieces() {
        rottenFoodPieces.clear();
    }

    public void setUpGame() {
        clearSprites();
        clearFoodPieces();
        clearRottenFoodPieces();
        blobGUI = new BlobGUI(spawnPointX, spawnPointY, BlobGUI.initialBlobSize);
        sprites.add(blobGUI);
        for (int i = 0; i < maxFoodPieces; i++) {
            FoodPiece fp = new FoodPiece(RND.nextInt(BGame.WIDTH), RND.nextInt(BGame.HEIGHT),
                    RND.nextInt(10) + FoodPiece.foodCommandSize);
            foodPieces.add(fp);
            sprites.add(fp);
        }
        for (int i = 0; i < maxFoodPieces; i++) {
            RottenFoodPiece rfp = new RottenFoodPiece(RND.nextInt(BGame.WIDTH), RND.nextInt(BGame.HEIGHT),
                    RND.nextInt(10) + FoodPiece.foodCommandSize);
            rottenFoodPieces.add(rfp);
            sprites.add(rfp);

            isGameOver = false;
        }
    }

    public void update() {
        moveBlob();
        if (checkEatenRottenFoodPiece(blobGUI)) {
            blobGUI.radius -= 5;
        }

        if (checkEatenFoodPiece(blobGUI)) {
            blobGUI.radius += 5;
            setGiornoThemeSong();
        }

        blobGUI.evolveBlob();
        foodPiecesRespawn();
        checkGameOver();
    }

    private Boolean checkEatenFoodPiece(BlobGUI blobGUI) {
        FoodPiece tmpFoodPiece = null;

        for (FoodPiece target : foodPieces) {
            if (checkFoodPieceEaten(target, blobGUI)) {
                tmpFoodPiece = target;
            }
        }

        if (tmpFoodPiece != null) {
            foodPieces.remove(tmpFoodPiece);
            sprites.remove(tmpFoodPiece);
            return true;
        }

        return false;

    }

    private Boolean checkEatenRottenFoodPiece(BlobGUI blobGUI) {
        RottenFoodPiece tmpRottenFoodPiece = null;

        for (RottenFoodPiece target : rottenFoodPieces) {
            if (checkRottenFoodPieceEaten(target, blobGUI)) {
                tmpRottenFoodPiece = target;
            }
        }

        if (tmpRottenFoodPiece != null) {
            rottenFoodPieces.remove(tmpRottenFoodPiece);
            sprites.remove(tmpRottenFoodPiece);
            return true;
        }

        return false;

    }

    private boolean checkFoodPieceEaten(FoodPiece target, BlobGUI blobGUI) {
        return target.collidedWith(blobGUI) && (target.radius < blobGUI.radius);
    }

    private boolean checkRottenFoodPieceEaten(RottenFoodPiece target, BlobGUI blobGUI) {
        return target.hitBlob(blobGUI) && (target.radius < blobGUI.radius);
    }


    private void foodPiecesRespawn() {
        if (!isGameOver) {
            while (foodPieces.size() < maxFoodPieces) {
                FoodPiece f = new FoodPiece(RND.nextInt(BGame.WIDTH), RND.nextInt(BGame.HEIGHT),
                        FoodPiece.foodCommandSize);
                foodPieces.add(f);
                sprites.add(f);
            }
            while (rottenFoodPieces.size() < maxFoodPieces) {
                RottenFoodPiece rf = new RottenFoodPiece(RND.nextInt(BGame.WIDTH), RND.nextInt(BGame.HEIGHT),
                        FoodPiece.foodCommandSize);
                rottenFoodPieces.add(rf);
                sprites.add(rf);
            }
        }
    }

    public boolean isOver() {
        return isGameOver;
    }

    private void checkGameOver() {
        if (blobGUI.radius >= 200) {
            isGameOver = true;
            foodPieces.clear();
            rottenFoodPieces.clear();
            clip.stop();
        }
    }

    public void moveBlob() {
        blobGUI.move();
    }

    public void draw(Graphics g) {
        if (!isGameOver) {
            for (Sprite sprite1 : sprites) {
                sprite1.draw(g);
            }
        } else {
            blobGUI.draw(g);
        }

    }

    public int getNumFoodPiecesEaten() {
        return (blobGUI.getBlobSize() - BlobGUI.initialBlobSize) / FoodPiece.foodCommandSize;
    }

    public int returnBlobSize() {
        return blobGUI.getBlobSize();
    }


    public void save(BlobGUI blobGUI) {
        Path path = Paths.get("data/blobGUISaveData.txt");
        String saveString = "" + blobGUI.x1 + blobGUI.y1 + blobGUI.direction + blobGUI.radius;
        String firstHalf = saveString.substring(0, 2);
        String secondHalf = saveString.substring(2);
        if (blobGUI.x1 == 0) {
            saveString = "000" + saveString;
        } else if (blobGUI.x1 < 10 && blobGUI.x1 > 0) {
            saveString = "00" + saveString;
        } else if (blobGUI.x1 < 100 && blobGUI.x1 > 9) {
            saveString = "0" + saveString;
        }
        if (blobGUI.y1 == 0) {
            saveString = firstHalf + "000" + secondHalf;
        } else if (blobGUI.y1 < 10 && blobGUI.y1 > 0) {
            saveString = firstHalf + "00" + secondHalf;
        } else if (blobGUI.y1 < 100 && blobGUI.y1 > 9) {
            saveString = firstHalf + "0" + secondHalf;
        }

        byte[] bytes = saveString.getBytes();
        try {
            Files.write(path, bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(saveString);
        System.out.println(saveString.length());
    }


    public void load(BlobGUI blobGUI) {
        try {
            File loadData = new File("data/blobGUISaveData.txt");
            Scanner myReader = new Scanner(loadData);
            String data = myReader.nextLine();
            data = data.replaceAll("[^A-Za-z0-9]", "");
            if (data.length() >= 10) {
                blobGUI.x1 = Integer.parseInt(data.substring(0, 3));
                System.out.println("Loaded X-coordinate: " + blobGUI.x1);
                blobGUI.y1 = Integer.parseInt(data.substring(3, 6));
                System.out.println("Loaded Y-coordinate: " + blobGUI.y1);
                blobGUI.direction = BlobGUI.Direction.valueOf(data.substring(6, 8));
                System.out.println("Loaded Direction: " + blobGUI.direction);
                blobGUI.radius = Integer.parseInt(data.substring(8));
                System.out.println("Loaded Blob Radius: " + blobGUI.radius);
            } else {
                blobGUI.x1 = Integer.parseInt(data.substring(0, 2));
                System.out.println(blobGUI.x1);
                blobGUI.y1 = Integer.parseInt(data.substring(2, 5));
                System.out.println(blobGUI.y1);
                blobGUI.direction = BlobGUI.Direction.valueOf(data.substring(5, 7));
                System.out.println(blobGUI.direction);
                blobGUI.radius = Integer.parseInt(data.substring(7));
                System.out.println(blobGUI.radius);
            }
        } catch (IOException e) {
            System.out.println("IOException" + e);
        }
    }




    public void clearData() {
        setUpGame();
        save(blobGUI);
        clip.stop();
        soundPlayer(zaWarudo);
    }

    public int getX1() {
        return blobGUI.x1;
    }

    public int getY1() {
        return blobGUI.y1;
    }


    public void soundPlayer(String music) {
        try {
            File jojoOST = new File(music);
            clip = getClip();
            clip.open(getAudioInputStream(jojoOST));
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    public void setGiornoThemeSong() {
        if (BlobGUI.firstEvolutionSizeThreshold == blobGUI.radius && !blobGUI.shrunk) {
            clip.stop();
            soundPlayer(giornoThemeSong);

        }
    }
}


//    public void load(BlobGUI blobGUI) {
//        try {
//            FileInputStream gameDisk = new FileInputStream("data/blobGUISaveData.txt");
//            ObjectInputStream in = new ObjectInputStream(gameDisk);
//            Scanner s = new Scanner(in).useDelimiter("\\A");
//            String result = s.hasNext() ? s.next() : "";
//            result = result.replaceAll("[^A-Za-z0-9]", "");
//            if (result.length() >= 10) {
//                blobGUI.x1 = Integer.parseInt(result.substring(0, 3));
//                System.out.println("Loaded X-coordinate: " + blobGUI.x1);
//                blobGUI.y1 = Integer.parseInt(result.substring(3, 6));
//                System.out.println("Loaded Y-coordinate: " + blobGUI.y1);
//                blobGUI.direction = BlobGUI.Direction.valueOf(result.substring(6, 8));
//                System.out.println("Loaded Direction: " + blobGUI.direction);
//                blobGUI.radius = Integer.parseInt(result.substring(8));
//                System.out.println("Loaded Blob Radius: " + blobGUI.radius);
//            } else {
//                blobGUI.x1 = Integer.parseInt(result.substring(0, 2));
//                System.out.println(blobGUI.x1);
//                blobGUI.y1 = Integer.parseInt(result.substring(2, 5));
//                System.out.println(blobGUI.y1);
//                blobGUI.direction = BlobGUI.Direction.valueOf(result.substring(5, 7));
//                System.out.println(blobGUI.direction);
//                blobGUI.radius = Integer.parseInt(result.substring(7));
//                System.out.println(blobGUI.radius);
//            }
//            in.close();
//        } catch (IOException e) {
//            System.out.println("IOException" + e);
//        }
//    }



//    public void save(BlobGUI blobGUI) {
//        try {
//            FileOutputStream gameDisk = new FileOutputStream("data/blobGUISaveData.txt");
//            ObjectOutputStream out = new ObjectOutputStream(gameDisk);
//            String saveString = "" + blobGUI.x1 + blobGUI.y1 + blobGUI.direction + blobGUI.radius;
//            String firstHalf = saveString.substring(0, 2);
//            String secondHalf = saveString.substring(2);
//            if (blobGUI.x1 == 0) {
//                saveString = "000" + saveString;
//            } else if (blobGUI.x1 < 10 && blobGUI.x1 > 0) {
//                saveString = "00" + saveString;
//            } else if (blobGUI.x1 < 100 && blobGUI.x1 > 9) {
//                saveString = "0" + saveString;
//            }
//            if (blobGUI.y1 == 0) {
//                saveString = firstHalf + "000" + secondHalf;
//            } else if (blobGUI.y1 < 10 && blobGUI.y1 > 0) {
//                saveString = firstHalf + "00" + secondHalf;
//            } else if (blobGUI.y1 < 100 && blobGUI.y1 > 9) {
//                saveString = firstHalf + "0" + secondHalf;
//            }
//            out.writeChars(saveString);
//            out.close();
//            System.out.println(saveString);
//            System.out.println(saveString.length());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

