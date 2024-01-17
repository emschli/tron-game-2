package haw.vs.controller.mock;

import haw.vs.common.GameState;
import haw.vs.controller.api.IGameViewUpdate;

public class MockGameViewUpdate implements IGameViewUpdate {
    @Override
    public void startGameController(Long playerId, GameState gameState) {
        System.out.printf("GameViewUpdate:   startGame(%s, %s)\n", playerId, gameState);
    }

    @Override
    public void updateController(Long playerId, GameState gameState) {
        System.out.printf("GameViewUpdate:   updateView(%s, %s)\n", playerId, gameState);
    }

    @Override
    public void playerWonController(Long playerId, GameState gameState) {
        System.out.printf("GameViewUpdate:   playerWon(%s, %s)\n", playerId, gameState);
    }

    @Override
    public void playerLostController(Long playerId, GameState gameState) {
        System.out.printf("GameViewUpdate:   playerLost(%s, %s)\n", playerId, gameState);
    }

    @Override
    public void updatePlayerCountViewController(Long playerId, Integer playerCount, Integer targetPlayerCount, String color) {
        System.out.printf("GameViewUpdate:  updatePlayerCountView(%s, %s, %s, %s)\n", playerId, playerCount, targetPlayerCount, color);

    }

    @Override
    public void showMainMenuController(Long playerId) {
        System.out.printf("GameViewUpdate:  showMainMenu(%s)\n", playerId);

    }

    @Override
    public void setMatchIdController(Long playerId, Long matchId) {
        System.out.printf("GameViewUpdate:  setMatchId(%s, %s)\n", playerId, matchId);
    }
}
