package haw.vs.controller.impl.mock;

import haw.vs.common.Direction;
import haw.vs.common.PlayerConfigData;
import haw.vs.controller.api.IInput;

public class MockInput implements IInput {
    @Override
    public void joinGameController(Long playerId, Integer noOfPlayers, PlayerConfigData configData) {
        System.out.printf("InputHandler:   joinGame(%s, %s, %s)\n", playerId, noOfPlayers, configData);
    }

    @Override
    public void cancelWaitController(Long playerId, Long matchId, Integer noOfPlayers) {
        System.out.printf("InputHandler:   cancelWait(%s, %s, %s)\n", playerId, matchId, noOfPlayers);
    }

    @Override
    public void handleGameActionController(Long playerId, Long matchId, Direction dir) {
        System.out.printf("InputHandler:   handleGameAction(%s, %s, %s)\n", playerId, matchId, dir);

    }

}
