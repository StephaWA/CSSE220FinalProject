package snakegame.model;

import snakegame.event.DirectionChangedEvent;


import java.awt.*;
import java.util.LinkedList;

public class Snake {
    private LinkedList<Point> body;
    private DirectionChangedEvent.Direction direction;
    private boolean growing;

    public Snake() {
        body = new LinkedList<>();
        body.add(new Point(10, 10));
        direction = DirectionChangedEvent.Direction.RIGHT;
        growing = false;
    }

    public void move() {
        Point head = getHead();
        Point newHead = new Point(head);

        switch (direction) {
            case UP -> newHead.y--;
            case DOWN -> newHead.y++;
            case LEFT -> newHead.x--;
            case RIGHT -> newHead.x++;
        }

        body.addFirst(newHead);
        if (!growing) {
            body.removeLast();
        } else {
            growing = false;
        }
    }

    public void changeDirection(DirectionChangedEvent.Direction newDirection) {
        if ((direction == DirectionChangedEvent.Direction.UP && newDirection != DirectionChangedEvent.Direction.DOWN) ||
            (direction == DirectionChangedEvent.Direction.DOWN && newDirection != DirectionChangedEvent.Direction.UP) ||
            (direction == DirectionChangedEvent.Direction.LEFT && newDirection != DirectionChangedEvent.Direction.RIGHT) ||
            (direction == DirectionChangedEvent.Direction.RIGHT && newDirection != DirectionChangedEvent.Direction.LEFT)) {
            direction = newDirection;
        }
    }

    public void grow() {
        growing = true;
    }

    public Point getHead() {
        return body.getFirst();
    }

    public LinkedList<Point> getBody() {
        return body;
    }
}
