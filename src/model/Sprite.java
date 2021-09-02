package model;

import java.awt.*;

public abstract class Sprite {
    public int x1;
    public int y1;
    public int radius;
    protected Color color;

    public Sprite() {
        super();
    }

    public Sprite(int x, int y, int radius) {
        this.x1 = x;
        this.y1 = y;
        this.radius = radius;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getRadius() {
        return radius;
    }

    public abstract void draw(Graphics g);

    protected void handleBoundary() {
        if (x1 < 0) {
            x1 = 0;
        } else if (x1 > BGame.WIDTH) {
            x1 = BGame.WIDTH;
        }
        // also if p1 x1 = p2 x1, x1+1 or something
    }
}
