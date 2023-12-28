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
    public void joinGame(long playerId, int noOfPlayers, PlayerConfigData configData) {
        matchController.addPlayerToMatch(playerId, noOfPlayers, configData);
    }

    @Override
    public void cancelWait(long playerId, long matchId) {

        //brauchen wir hier überhaupt noOfPlayers?
        int noOfPlayers = 4;
        matchController.deletePlayerFromMatch(playerId, matchId, noOfPlayers );
    }

    @Override
    public void handleGameAction(long playerId, long matchId, Direction dir) {
        matchController.movePlayer(playerId, matchId, dir);
    }
}
