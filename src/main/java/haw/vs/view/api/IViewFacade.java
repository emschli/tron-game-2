package haw.vs.view.api;


import haw.vs.common.GameState;

public interface IViewFacade {

    void startGameView(GameState gameState);
    void updateView(GameState gameState);
    void playerLostView(GameState gameState);
    void playerWonView(GameState gameState);
    void updatePlayerCountViewView(int playerCount, int targetPlayerCount);
    void showMainMenuView();
    void setMatchIdView(long matchId);

}
