package haw.vs.controller.mock;

import haw.vs.common.Direction;
import haw.vs.common.PlayerConfigData;
import haw.vs.controller.api.IInput;

public class MockInput implements IInput {
    @Override
    public void joinGame(long playerId, int noOfPlayers, PlayerConfigData configData) {

    }

    @Override
    public void cancelWait(long playerId) {

    }

    @Override
    public void handleGameAction(long playerId, Direction dir) {

    }
}
