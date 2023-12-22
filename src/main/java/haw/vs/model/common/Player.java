package haw.vs.model.common;

import haw.vs.common.Coordinate;
import haw.vs.common.Direction;
import haw.vs.common.PlayerConfigData;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player {
    private long playerId;
    private PlayerState state;
    private String color;
    private List<Coordinate> trace = new ArrayList<>();
    private PlayerConfigData configData;
    private Direction direction;

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

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
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

    @Override
    public String toString() {
        return "Player{" +
                "playerId=" + playerId +
                ", state=" + state +
                ", color='" + color + '\'' +
                ", trace=" + trace +
                ", configData=" + configData +
                ", direction=" + direction +
                '}';
    }

    public Player copy() {
        Player player = new Player();
        player.setPlayerId(this.playerId);
        player.setState(this.state);
        player.setColor(this.color);
        List<Coordinate> trace = new ArrayList<>();
        for (Coordinate coordinate : this.trace) {
            trace.add(coordinate.copy());
        }
        player.setTrace(trace);
        player.setConfigData(this.configData.copy());
        player.setDirection(this.direction);
        return player;
    }
}
