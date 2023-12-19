package haw.vs.controller.mock;

import haw.vs.common.IGameState;
import haw.vs.controller.api.IGameViewUpdate;

public class MockGameViewUpdate implements IGameViewUpdate {
    @Override
    public void startGame(long playerId, IGameState gameState) {

    }

    @Override
    public void updateView(long playerId, IGameState gameState) {

    }

    @Override
    public void playerWon(long playerId, IGameState gameState) {

    }

    @Override
    public void playerLost(long playerId, IGameState gameState) {

    }

    @Override
    public void updatePlayerCountView(long playerId, int playerCount, int targetPlayerCount) {

    }

    @Override
    public void showMainMenu(long playerId) {

    }

    @Override
    public void setMatchId(long playerId, long matchId) {

    }
}
