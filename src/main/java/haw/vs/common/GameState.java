package haw.vs.common;

import java.util.List;
import java.util.Map;

public class GameState {

    public GameState() {
    }

    private Map<String, List<Coordinate>> playerPositionMap;

    public GameState(Map<String, List<Coordinate>> playerPositionMap) {
        this.playerPositionMap = playerPositionMap;
    }

    public Map<String, List<Coordinate>> getPlayerPositionMap() {
        return playerPositionMap;
    }

    public void setPlayerPositionMap(Map<String, List<Coordinate>> playerPositionMap) {
        this.playerPositionMap = playerPositionMap;
    }

    @Override
    public String toString() {
        return "GameState{" +
                "playerPositionMap=" + playerPositionMap +
                '}';
    }
}
