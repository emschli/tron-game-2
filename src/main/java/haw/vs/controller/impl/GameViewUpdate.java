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
        viewFacade.startGameView(gameState);
    }

    @Override
    public void updateController(Long playerId, GameState gameState) {
        viewFacade.updateView(gameState);

    }

    @Override
    public void playerWonController(Long playerId, GameState gameState) {
        viewFacade.playerWonView(gameState);

    }

    @Override
    public void playerLostController(Long playerId, GameState gameState) {
        viewFacade.playerLostView(gameState);

    }

    @Override
    public void updatePlayerCountViewController(Long playerId, Integer playerCount, Integer targetPlayerCount) {
        viewFacade.updatePlayerCountViewView(playerCount, targetPlayerCount);

    }

    @Override
    public void showMainMenuController(Long playerId) {
        viewFacade.showMainMenuView();

    }

    @Override
    public void setMatchIdController(Long playerId, Long matchId) {
        viewFacade.setMatchIdView(matchId);
    }
}