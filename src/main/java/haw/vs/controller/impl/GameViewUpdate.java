package haw.vs.controller.impl;

import haw.vs.common.GameState;
import haw.vs.controller.api.IGameViewUpdate;
import haw.vs.view.api.IViewFacade;

public class GameViewUpdate implements IGameViewUpdate {
    private final IViewFacade viewFacade;

    public GameViewUpdate(IViewFacade viewFacade) {
        this.viewFacade = viewFacade;
    }

    @Override
    public void startGameController(long playerId, GameState gameState) {
        viewFacade.startGameView(gameState);
    }

    @Override
    public void updateController(long playerId, GameState gameState) {
        viewFacade.updateView(gameState);

    }

    @Override
    public void playerWonController(long playerId, GameState gameState) {
        viewFacade.playerWonView(gameState);

    }

    @Override
    public void playerLostController(long playerId, GameState gameState) {
        viewFacade.playerLostView(gameState);

    }

    @Override
    public void updatePlayerCountViewController(long playerId, int playerCount, int targetPlayerCount) {
        viewFacade.updatePlayerCountViewView(playerCount, targetPlayerCount);

    }

    @Override
    public void showMainMenuController(long playerId) {
        viewFacade.showMainMenuView();

    }

    @Override
    public void setMatchIdController(long playerId, long matchId) {
        viewFacade.setMatchIdView(matchId);
    }
}