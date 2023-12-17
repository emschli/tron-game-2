package haw.vs.controller.api;

import haw.vs.common.IGameState;

public interface IGameViewUpdate {
    // Methode, um das Spiel für einen Spieler zu starten
    void startGame(long playerId, IGameState gameState);

    // Methode zur Aktualisierung der Ansicht für einen Spieler
    void updateView(long playerId, IGameState gameState);

    // Methode, um einem Spieler mitzuteilen, dass er gewonnen hat
    void playerWon(long playerId, IGameState gameState);

    // Methode, um einem Spieler mitzuteilen, dass er verloren hat
    void playerLost(long playerId, IGameState gameState);

    // Methode zur Aktualisierung der Spieleranzahl in der Ansicht für einen Spieler
    void updatePlayerCountView(long playerId, int playerCount, int targetPlayerCount);

    // Methode, um das Hauptmenü für einen Spieler anzuzeigen
    void showMainMenu(long playerId);

    // Methode zur Festlegung der Match-ID für einen Spieler
    void setMatchId(long playerId, long matchId);
}
