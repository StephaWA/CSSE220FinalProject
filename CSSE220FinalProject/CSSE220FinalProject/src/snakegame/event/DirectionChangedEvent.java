package snakegame.event;

public class DirectionChangedEvent {
    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    private Direction direction;

    public DirectionChangedEvent(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }
}
