package haw.vs.controller.impl;


import haw.vs.common.Direction;
import haw.vs.common.PlayerConfigData;
import haw.vs.controller.api.IInput;
import haw.vs.model.matchmanager.api.IMatchController;

public class InputHandler implements IInput {
    private final IMatchController matchController;

    public InputHandler(IMatchController matchController) {
        this.matchController = matchController;
    }

    @Override
    public void joinGameController(long playerId, int noOfPlayers, PlayerConfigData configData) {
        matchController.addPlayerToMatchMatchManager(playerId, noOfPlayers, configData);
    }

    @Override
    public void cancelWaitController(long playerId, long matchId, int noOfPlayers) {

        //brauchen wir hier überhaupt noOfPlayers?
        //int noOfPlayers = 4;
        matchController.deletePlayerFromMatchMatchManager(playerId, matchId, noOfPlayers );
    }

    @Override
    public void handleGameActionController(long playerId, long matchId, Direction dir) {
        matchController.movePlayerMatchManager(playerId, matchId, dir);
    }
}
