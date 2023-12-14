package haw.vs.common;

import java.util.List;
import java.util.Map;

public interface IGameState {

    /**
     * Get a Map which contains an Entry for each Player. Each Entry consists of the Players Color and a List of Positions,
     * that are occupied by the players Trace.
     */
    Map<String, List<Coordinate>> getPlayerPositionMap();
}
