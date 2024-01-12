package haw.vs.controller.mock;

import haw.vs.common.Direction;
import haw.vs.common.PlayerConfigData;
import haw.vs.controller.api.IInput;

public class MockInput implements IInput {
    @Override
    public void joinGameController(long playerId, int noOfPlayers, PlayerConfigData configData) {
        System.out.printf("InputHandler:   joinGame(%s, %s, %s)\n", playerId, noOfPlayers, configData);
    }

    @Override
    public void cancelWaitController(long playerId, long matchId, int noOfPlayers) {
        System.out.printf("InputHandler:   cancelWait(%s, %s, %s)\n", playerId, matchId, noOfPlayers);
    }

    @Override
    public void handleGameActionController(long playerId, long matchId, Direction dir) {
        System.out.printf("InputHandler:   handleGameAction(%s, %s, %s)\n", playerId, matchId, dir);

    }

}
