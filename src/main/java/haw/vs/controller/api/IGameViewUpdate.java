package haw.vs.controller.api;

import haw.vs.common.GameState;

public interface IGameViewUpdate {
    // Methode, um das Spiel für einen Spieler zu starten
    void startGameController(long playerId, GameState gameState);

    // Methode zur Aktualisierung der Ansicht für einen Spieler
    void updateController(long playerId, GameState gameState);

    // Methode, um einem Spieler mitzuteilen, dass er gewonnen hat
    void playerWonController(long playerId, GameState gameState);

    // Methode, um einem Spieler mitzuteilen, dass er verloren hat
    void playerLostController(long playerId, GameState gameState);

    // Methode zur Aktualisierung der Spieleranzahl in der Ansicht für einen Spieler
    void updatePlayerCountViewController(long playerId, int playerCount, int targetPlayerCount);

    // Methode, um das Hauptmenü für einen Spieler anzuzeigen
    void showMainMenuController(long playerId);

    // Methode zur Festlegung der Match-ID für einen Spieler
    void setMatchIdController(long playerId, long matchId);
}
