package haw.vs.model.matchmanager.impl.mock;

import haw.vs.common.Direction;
import haw.vs.common.PlayerConfigData;
import haw.vs.model.matchmanager.api.IMatchController;

public class MockMatchController implements IMatchController {
    @Override
    public void addPlayerToMatchMatchManager(Long playerId, Integer numberOfPlayers, PlayerConfigData configData) {

    }

    @Override
    public void deletePlayerFromMatchMatchManager(Long playerId, Long matchId, Integer numberOfPlayers) {

    }

    @Override
    public void movePlayerMatchManager(Long playerId, Long matchId, Direction direction) {

    }
}
