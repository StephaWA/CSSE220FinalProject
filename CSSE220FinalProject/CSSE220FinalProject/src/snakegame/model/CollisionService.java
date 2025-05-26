package snakegame.model;

import java.awt.*;

public class CollisionService {
    public boolean isCollision(Snake snake) {
        Point head = snake.getHead();

        if (head.x < 0 || head.y < 0 || head.x >= 20 || head.y >= 20) {
            return true;
        }

        for (int i = 1; i < snake.getBody().size(); i++) {
            if (head.equals(snake.getBody().get(i))) {
                return true;
            }
        }

        return false;
    }
}
