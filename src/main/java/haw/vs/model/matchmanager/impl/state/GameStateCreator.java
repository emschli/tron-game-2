package haw.vs.model.matchmanager.impl.state;

import haw.vs.common.Coordinate;
import haw.vs.common.GameState;
import haw.vs.model.common.Match;
import haw.vs.model.common.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameStateCreator {

    private static int SIZE_TO_SEND = 5;
    public static GameState createGameState(Match match) {
        Map<String, List<Coordinate>> playerPositionMap = new HashMap<>();

        for (Player player: match.getPlayers()) {
            int traceLength = player.getTrace().size();

            if (traceLength < SIZE_TO_SEND) {
                playerPositionMap.put(player.getColor(), player.getTrace());

            } else {
                List<Coordinate> lastFew = player.getTrace().subList(traceLength - SIZE_TO_SEND, traceLength);
                playerPositionMap.put(player.getColor(), lastFew);
            }

        }

        return new GameState(playerPositionMap);
    }
}
