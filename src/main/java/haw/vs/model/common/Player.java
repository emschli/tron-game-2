package haw.vs.model.common;

import haw.vs.common.Coordinate;
import haw.vs.common.Direction;
import haw.vs.common.PlayerConfigData;

import java.util.List;
import java.util.Objects;

public class Player {
    private long playerId;
    private PlayerState state;
    private String color;
    private List<Coordinate> trace;
    private PlayerConfigData configData;

    // The direction, the player is currently heading to
    // (which is the same direction the player made a step into in the last tick).
    private Direction currentDirection;

    // The direction the player wants to move into - by default,
    // this is the current direction, if the player made an input during the tick,
    // it is changed
    private Direction nextDirection;

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    public Direction getNextDirection() {
        return nextDirection;
    }

    public void setNextDirection(Direction nextDirection) {
        this.nextDirection = nextDirection;
    }





    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public PlayerState getState() {
        return state;
    }

    public void setState(PlayerState state) {
        this.state = state;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<Coordinate> getTrace() {
        return trace;
    }

    public void setTrace(List<Coordinate> trace) {
        this.trace = trace;
    }

    public PlayerConfigData getConfigData() {
        return configData;
    }

    public void setConfigData(PlayerConfigData configData) {
        this.configData = configData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return playerId == player.playerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId);
    }
}
