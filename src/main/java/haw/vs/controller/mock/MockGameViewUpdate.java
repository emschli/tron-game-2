package haw.vs.controller.mock;

import haw.vs.common.IGameState;
import haw.vs.controller.api.IGameViewUpdate;

public class MockGameViewUpdate implements IGameViewUpdate {
    @Override
    public void startGame(long playerId, IGameState gameState) {
        System.out.printf("GameViewUpdate:   startGame(%s, %s)\n", playerId, gameState);
    }

    @Override
    public void updateView(long playerId, IGameState gameState) {
        System.out.printf("GameViewUpdate:   updateView(%s, %s)\n", playerId, gameState);
    }

    @Override
    public void playerWon(long playerId, IGameState gameState) {
        System.out.printf("GameViewUpdate:   playerWon(%s, %s)\n", playerId, gameState);


    }

    @Override
    public void playerLost(long playerId, IGameState gameState) {
        System.out.printf("GameViewUpdate:   playerLost(%s, %s)\n", playerId, gameState);


    }

    @Override
    public void updatePlayerCountView(long playerId, int playerCount, int targetPlayerCount) {
        System.out.printf("GameViewUpdate:  updatePlayerCountView(%s, %s, %s)\n", playerId, playerCount, targetPlayerCount);


    }

    @Override
    public void showMainMenu(long playerId) {
        System.out.printf("GameViewUpdate:  showMainMenu(%s)\n", playerId);
    }

    @Override
    public void setMatchId(long playerId, long matchId) {
        System.out.printf("GameViewUpdate:  setMatchId(%s, %s)\n", playerId, matchId);
    }
}
