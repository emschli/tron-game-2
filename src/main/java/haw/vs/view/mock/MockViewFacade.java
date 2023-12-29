package haw.vs.view.mock;

import haw.vs.common.IGameState;
import haw.vs.view.api.IViewFacade;

public class MockViewFacade implements IViewFacade {
    @Override
    public void startGame(IGameState gameState) {
        System.out.printf("ViewFacade:   startGame(%s)\n", gameState);
    }

    @Override
    public void update(IGameState gameState) {
        System.out.printf("ViewFacade:   update(%s)\n", gameState);
    }

    @Override
    public void playerLost(IGameState gameState) {
        System.out.printf("ViewFacade:   playerLost(%s)\n", gameState);
    }

    @Override
    public void playerWon(IGameState gameState) {
        System.out.printf("ViewFacade:   playerWon(%s)\n", gameState);
    }

    @Override
    public void updatePlayerCountView(int playerCount, int targetPlayerCount) {
        System.out.printf("ViewFacade:   updatePlayerCountView(%s, %s)\n", playerCount, targetPlayerCount);
    }

    @Override
    public void showMainMenu() {
        System.out.print("ViewFacade:   showMainMenu()\n");
    }

    @Override
    public void setMatchId(long matchId) {
        System.out.printf("ViewFacade:   setMatchId(%s)\n", matchId);
    }

}
