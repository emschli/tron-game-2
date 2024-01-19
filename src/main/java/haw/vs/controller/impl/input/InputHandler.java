package haw.vs.controller.impl.input;


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
    public void joinGameController(Long playerId, Integer noOfPlayers, PlayerConfigData configData) {
        matchController.addPlayerToMatchMatchManager(playerId, noOfPlayers, configData);
    }

    @Override
    public void cancelWaitController(Long playerId, Long matchId, Integer noOfPlayers) {

        //brauchen wir hier überhaupt noOfPlayers?
        //int noOfPlayers = 4;
        matchController.deletePlayerFromMatchMatchManager(playerId, matchId, noOfPlayers );
    }

    @Override
    public void handleGameActionController(Long playerId, Long matchId, Direction dir) {
        matchController.movePlayerMatchManager(playerId, matchId, dir);
    }
}
