package haw.vs.model.matchmanager.api;

import haw.vs.common.Direction;
import haw.vs.common.PlayerConfigData;

public interface IMatchController {
    void addPlayerToMatchMatchManager(long playerId, int numberOfPlayers, PlayerConfigData configData);
    void deletePlayerFromMatchMatchManager(long playerId, long matchId, int numberOfPlayers);
    void movePlayerMatchManager(long playerId, long matchId, Direction direction);
}
