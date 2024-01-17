package haw.vs.view.api;


import haw.vs.common.GameState;

public interface IViewFacade {

    void setPlayerId(long playerId);
    void setPlayerColor(String playerColor);
    void startGameView(GameState gameState);
    void updateView(GameState gameState);
    void playerLostView(GameState gameState);
    void playerWonView(GameState gameState);
    void updatePlayerCountViewView(Integer playerCount, Integer targetPlayerCount, String color);
    void showMainMenuView();
    void setMatchIdView(Long matchId);

}
