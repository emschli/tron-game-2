package haw.vs.controller.api;

import haw.vs.common.GameState;

public interface IGameViewUpdate {
    // Methode, um das Spiel für einen Spieler zu starten
    void startGameController(Long playerId, GameState gameState);

    // Methode zur Aktualisierung der Ansicht für einen Spieler
    void updateController(Long playerId, GameState gameState);

    // Methode, um einem Spieler mitzuteilen, dass er gewonnen hat
    void playerWonController(Long playerId, GameState gameState);

    // Methode, um einem Spieler mitzuteilen, dass er verloren hat
    void playerLostController(Long playerId, GameState gameState);

    // Methode zur Aktualisierung der Spieleranzahl in der Ansicht für einen Spieler
    void updatePlayerCountViewController(Long playerId, Integer playerCount, Integer targetPlayerCount, String color);

    // Methode, um das Hauptmenü für einen Spieler anzuzeigen
    void showMainMenuController(Long playerId);

    // Methode zur Festlegung der Match-ID für einen Spieler
    void setMatchIdController(Long playerId, Long matchId);
}
