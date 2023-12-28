package haw.vs.controller.mock;

import haw.vs.common.Direction;
import haw.vs.common.PlayerConfigData;
import haw.vs.controller.api.IInput;

public class MockInput implements IInput {
    @Override
    public void joinGame(long playerId, int noOfPlayers, PlayerConfigData configData) {

    }

    @Override
    public void cancelWait(long playerId, long matchId) {

    }

    @Override
    public void handleGameAction(long playerId, long matchId, Direction dir) {

    }

}
