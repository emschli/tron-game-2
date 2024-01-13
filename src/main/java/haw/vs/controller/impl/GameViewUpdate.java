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
    public void startGameController(Long playerId, GameState gameState) {
        viewFacade.setPlayerId(playerId);
        viewFacade.startGameView(gameState);
    }

    @Override
    public void updateController(Long playerId, GameState gameState) {
        viewFacade.setPlayerId(playerId);
        viewFacade.updateView(gameState);
    }

    @Override
    public void playerWonController(Long playerId, GameState gameState) {
        viewFacade.setPlayerId(playerId);
        viewFacade.playerWonView(gameState);
    }

    @Override
    public void playerLostController(Long playerId, GameState gameState) {
        viewFacade.setPlayerId(playerId);
        viewFacade.playerLostView(gameState);
    }

    @Override
    public void updatePlayerCountViewController(Long playerId, Integer playerCount, Integer targetPlayerCount) {
        viewFacade.setPlayerId(playerId);
        viewFacade.updatePlayerCountViewView(playerCount, targetPlayerCount);
    }

    @Override
    public void showMainMenuController(Long playerId) {
        viewFacade.setPlayerId(playerId);
        viewFacade.showMainMenuView();
    }

    @Override
    public void setMatchIdController(Long playerId, Long matchId) {
        viewFacade.setPlayerId(playerId);
        viewFacade.setMatchIdView(matchId);
    }
}