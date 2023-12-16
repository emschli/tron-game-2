package haw.vs.controller.api;

import haw.vs.common.IGameState;

public interface IGameViewUpdate {
    void startGame(long playerId, IGameState gameState);
    void updateView(long playerId, IGameState gameState);
    void playerWon(long playerId, IGameState gameState);
    void playerLost(long playerId, IGameState gameState);
    void updatePlayerCountView(long playerId, int playerCount, int targetPlayerCount);
    void showMainMenu(long playerId);
    void setMatchId(long playerId, long matchId);
}
