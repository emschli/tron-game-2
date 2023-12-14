package haw.vs.model.common;

import haw.vs.common.Coordinate;
import haw.vs.common.PlayerConfigData;

import java.util.List;
import java.util.Objects;

public class Player {
    private long playerId;
    private PlayerState state;
    private String color;
    private List<Coordinate> trace;
    private PlayerConfigData configData;

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
