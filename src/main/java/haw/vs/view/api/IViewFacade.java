package haw.vs.view.api;


import haw.vs.common.IGameState;

public interface IViewFacade {

    public void startGame(IGameState gameState);

    public void update(IGameState gameState);

    public void playerLost(IGameState gameState);

    public void playerWon(IGameState gameState);

    public void updatePlayerCountView(int playerCount, int targetPlayerCount);

    public void showMainMenu();

    public void setMatchId(long matchId);


}
