package haw.vs.controller.impl;

import haw.vs.common.IGameState;
import haw.vs.controller.api.IGameViewUpdate;
import haw.vs.view.api.IViewFacade;

public class GameViewUpdate implements IGameViewUpdate {
    private final IViewFacade viewFacade;

    public GameViewUpdate(IViewFacade viewFacade) {
        this.viewFacade = viewFacade;
    }

    @Override
    public void startGame(long playerId, IGameState gameState) {
        viewFacade.startGame(gameState);
    }

    @Override
    public void updateView(long playerId, IGameState gameState) {
        viewFacade.update(gameState);

    }

    @Override
    public void playerWon(long playerId, IGameState gameState) {
        viewFacade.playerWon(gameState);

    }

    @Override
    public void playerLost(long playerId, IGameState gameState) {
        viewFacade.playerLost(gameState);

    }

    @Override
    public void updatePlayerCountView(long playerId, int playerCount, int targetPlayerCount) {
        viewFacade.updatePlayerCountView(playerCount, targetPlayerCount);

    }

    @Override
    public void showMainMenu(long playerId) {
        viewFacade.showMainMenu();

    }

    @Override
    public void setMatchId(long playerId, long matchId) {
        viewFacade.setMatchId(matchId);
    }
}