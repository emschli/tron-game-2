package haw.vs.model.matchmanager.api;

import haw.vs.common.Direction;
import haw.vs.common.PlayerConfigData;

public interface IMatchController {
    void addPlayerToMatchMatchManager(Long playerId, Integer numberOfPlayers, PlayerConfigData configData);
    void deletePlayerFromMatchMatchManager(Long playerId, Long matchId, Integer numberOfPlayers);
    void movePlayerMatchManager(Long playerId, Long matchId, Direction direction);
}
