package haw.vs.model.matchmanager.api;

import haw.vs.common.Direction;
import haw.vs.common.PlayerConfigData;

public interface IMatchController {
    void addPlayerToMatch(long playerId, int numberOfPlayers, PlayerConfigData configData);
    void deletePlayerFromMatch(long playerId, long matchId, int numberOfPlayers);
    void movePlayer(long playerId, long matchId, Direction direction);
}
