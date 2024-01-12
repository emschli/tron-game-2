package haw.vs.model.matchmanager.state;

import haw.vs.common.Coordinate;
import haw.vs.common.GameState;
import haw.vs.model.common.Match;
import haw.vs.model.common.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameStateCreator {
    public static GameState createGameState(Match match) {
        Map<String, List<Coordinate>> playerPositionMap = new HashMap<>();

        for (Player player: match.getPlayers()) {
            playerPositionMap.put(player.getColor(), player.getTrace());
        }

        return new GameState(playerPositionMap);
    }
}
