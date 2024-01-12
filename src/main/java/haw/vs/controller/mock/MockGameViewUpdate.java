package haw.vs.controller.mock;

import haw.vs.common.GameState;
import haw.vs.controller.api.IGameViewUpdate;

public class MockGameViewUpdate implements IGameViewUpdate {
    @Override
    public void startGameController(long playerId, GameState gameState) {
        System.out.printf("GameViewUpdate:   startGame(%s, %s)\n", playerId, gameState);
    }

    @Override
    public void updateController(long playerId, GameState gameState) {
        System.out.printf("GameViewUpdate:   updateView(%s, %s)\n", playerId, gameState);
    }

    @Override
    public void playerWonController(long playerId, GameState gameState) {
        System.out.printf("GameViewUpdate:   playerWon(%s, %s)\n", playerId, gameState);
    }

    @Override
    public void playerLostController(long playerId, GameState gameState) {
        System.out.printf("GameViewUpdate:   playerLost(%s, %s)\n", playerId, gameState);
    }

    @Override
    public void updatePlayerCountViewController(long playerId, int playerCount, int targetPlayerCount) {
        System.out.printf("GameViewUpdate:  updatePlayerCountView(%s, %s, %s)\n", playerId, playerCount, targetPlayerCount);

    }

    @Override
    public void showMainMenuController(long playerId) {
        System.out.printf("GameViewUpdate:  showMainMenu(%s)\n", playerId);

    }

    @Override
    public void setMatchIdController(long playerId, long matchId) {
        System.out.printf("GameViewUpdate:  setMatchId(%s, %s)\n", playerId, matchId);
    }
}
