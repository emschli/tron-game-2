package haw.vs.view.api;

import haw.vs.common.GameState;

public interface IViewFacade {

    public void startGame(GameState gameState);

    public void update(GameState gameState);

    public void playerLost(GameState gameState);

    public void playerWon(GameState gameState);

    public void updatePlayerCountView(int playerCount, int targetPlayerCount);

    public void showMainMenu();

    public void setMatchId(long matchId);

}
