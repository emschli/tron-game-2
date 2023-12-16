package haw.vs.controller.impl;

import haw.vs.common.IGameState;
import haw.vs.controller.api.IGameViewUpdate;

public class GameViewUpdate implements IGameViewUpdate {
    private final IViewFacade viewFacade;

    public GameViewUpdate(IViewFacade viewFacade) {
        this.viewFacade = viewFacade;
    }

    @Override
    public void startGame(long playerId, IGameState gameState) {
        viewFacade.startGame(playerId, gameState);
    }

    @Override
    public void updateView(long playerId, IGameState gameState) {
        viewFacade.update(playerId, gameState);
    }

    @Override
    public void playerWon(long playerId, IGameState gameState) {
        viewFacade.playerWon(playerId, gameState);
    }

    @Override
    public void playerLost(long playerId, IGameState gameState) {
        viewFacade.playerLost(playerId, gameState);
    }

    @Override
    public void updatePlayerCountView(long playerId, int playerCount, int targetPlayerCount) {
        viewFacade.updatePlayerCountView(playerId, playerCount, targetPlayerCount);
    }

    @Override
    public void showMainMenu(long playerId) {
        viewFacade.showMainMenu(playerId);
    }

    @Override
    public void setMatchId(long playerId, long matchId) {
        viewFacade.setMatchId(playerId, matchId);
    }
}
