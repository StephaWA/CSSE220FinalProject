package snakegame.model;

import java.awt.*;

public class Food {
    private Point position;

    public Food() {
        position = new Point(0, 0);
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }
}
