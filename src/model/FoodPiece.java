package model;

import javafx.scene.shape.Circle;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import static java.lang.Math.pow;

public class FoodPiece extends Sprite {

    public static final int foodCommandSize = 5;
    public static final Color startingColor = new Color(200, 254, 15);
    public static final Random RND = new Random();
    public BufferedImage food = null;


    public FoodPiece(int x1, int y1, int radius) {
        super(x1, y1, radius);
        this.radius = RND.nextInt(20) + foodCommandSize;
        this.color = startingColor;
        this.x1 = RND.nextInt(BGame.WIDTH);
        this.y1 = RND.nextInt(BGame.HEIGHT);

    }

    public int getRadius() {
        return radius;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }


    public boolean collidedWith(BlobGUI b) {
        Circle foodPieceBoundingCircle = new Circle(getX1(), getY1(), radius);
        Circle blobBoundingCircle = new Circle(b.getX1(), b.getY1(), b.radius);
        return ((pow(foodPieceBoundingCircle.getCenterX() - (blobBoundingCircle.getCenterX()), 2)
                + (pow(foodPieceBoundingCircle.getCenterY() - (blobBoundingCircle.getCenterY()), 2))
                <= pow((radius + b.radius), 2)));
    }


    @Override
    public void draw(Graphics g) {
        Color savedCol = g.getColor();
        g.setColor(startingColor);
        g.fillOval(getX1() - radius / 2, getY1() - radius / 2, radius, radius);
        g.setColor(Color.black);
        g.drawOval(getX1() - radius / 2, getY1() - radius / 2, radius, radius);
        g.setColor(savedCol);
//
//        try {
//            food = ImageIO.read(new File("/Users/Efe/IdeaProjects/BlobGameV2/data/food.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        g.drawImage(food, getX1() - radius / 2, getY1() - radius / 2, radius, radius, null);

    }

}


