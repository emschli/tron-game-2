package haw.vs.common;

public enum Direction {
    UP,
    LEFT,
    RIGHT,
    DOWN;

    public Direction backwards() {
        return switch (this) {
            case UP -> DOWN;
            case DOWN -> UP;
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
            default ->  this;
        };
    }

}
