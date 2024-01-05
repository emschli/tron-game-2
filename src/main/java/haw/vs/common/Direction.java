package haw.vs.common;

public enum Direction {
    UP,
    LEFT,
    RIGHT,
    DOWN;

    public Direction backwards() {
        Direction result;
        switch (this) {
            case UP:
                result = Direction.DOWN;
                break;
            case DOWN:
                result = Direction.UP;
                break;
            case LEFT:
                result = Direction.RIGHT;
                break;
            case RIGHT:
                result = Direction.LEFT;
                break;
            default:
                result = this;
                break;
        }
        return result;

//        return switch (this) {
//            case UP -> DOWN;
//            case DOWN -> UP;
//            case LEFT -> RIGHT;
//            case RIGHT -> LEFT;
//            default ->  this;
//        };
    }

}
