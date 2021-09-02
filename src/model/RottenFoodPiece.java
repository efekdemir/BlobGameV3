package model;

import javafx.scene.shape.Circle;

import java.awt.*;
import java.util.Random;

import static java.lang.Math.pow;

public class RottenFoodPiece extends Sprite {

    public static final int foodCommandSize = 5;
    public static final Color originalColor = new Color(255, 0, 15);
    public static final Random RND = new Random();


    public RottenFoodPiece(int x1, int y1, int radius) {
        super(x1, y1, radius);
        this.radius = RND.nextInt(15) + (foodCommandSize * 4);
        this.color = originalColor;
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


    public boolean hitBlob(BlobGUI b) {
        Circle foodPieceBoundingCircle = new Circle(getX1(), getY1(), radius);
        Circle blobBoundingCircle = new Circle(b.getX1(), b.getY1(), b.radius);
        return ((pow(foodPieceBoundingCircle.getCenterX() - (blobBoundingCircle.getCenterX()), 2)
                + (pow(foodPieceBoundingCircle.getCenterY() - (blobBoundingCircle.getCenterY()), 2))
                <= pow((radius + b.radius), 2)));
    }


    @Override
    public void draw(Graphics g) {
        Color savedCol = g.getColor();
        g.setColor(originalColor);
        g.fillOval(getX1() - radius / 2, getY1() - radius / 2, radius, radius);
        g.setColor(Color.black);
        g.drawOval(getX1() - radius / 2, getY1() - radius / 2, radius, radius);
        g.setColor(savedCol);
    }

}


