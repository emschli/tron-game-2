package haw.vs.view.impl.mock;

import haw.vs.common.GameState;
import haw.vs.view.api.IViewFacade;

public class MockViewFacade implements IViewFacade {
    @Override
    public void setPlayerId(long playerId) {

    }
    @Override
    public void setPlayerColor(String playerColor) {

    }

    @Override
    public void startGameView(GameState gameState) {
        System.out.printf("ViewFacade:   startGame(%s)\n", gameState);
    }

    @Override
    public void updateView(GameState gameState) {
        System.out.printf("ViewFacade:   update(%s)\n", gameState);
    }

    @Override
    public void playerLostView(GameState gameState) {
        System.out.printf("ViewFacade:   playerLost(%s)\n", gameState);
    }

    @Override
    public void playerWonView(GameState gameState) {
        System.out.printf("ViewFacade:   playerWon(%s)\n", gameState);
    }

    @Override
    public void updatePlayerCountViewView(Integer playerCount, Integer targetPlayerCount, String color) {
        System.out.printf("ViewFacade:   updatePlayerCountView(%s, %s, %s)\n", playerCount, targetPlayerCount, color);
    }

    @Override
    public void showMainMenuView() {
        System.out.print("ViewFacade:   showMainMenu()\n");
    }

    @Override
    public void setMatchIdView(Long matchId) {
        System.out.printf("ViewFacade:   setMatchId(%s)\n", matchId);
    }

}
