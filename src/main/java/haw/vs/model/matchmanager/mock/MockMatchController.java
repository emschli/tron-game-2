package haw.vs.model.matchmanager.mock;

import haw.vs.common.Direction;
import haw.vs.common.PlayerConfigData;
import haw.vs.model.matchmanager.api.IMatchController;

public class MockMatchController implements IMatchController {
    @Override
    public void addPlayerToMatchMatchManager(long playerId, int numberOfPlayers, PlayerConfigData configData) {

    }

    @Override
    public void deletePlayerFromMatchMatchManager(long playerId, long matchId, int numberOfPlayers) {

    }

    @Override
    public void movePlayerMatchManager(long playerId, long matchId, Direction direction) {

    }
}
