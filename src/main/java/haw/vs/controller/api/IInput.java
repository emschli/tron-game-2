package haw.vs.controller.api;

import haw.vs.common.Direction;
import haw.vs.common.PlayerConfigData;

public interface IInput {
    // Ein Spieler tritt dem Spiel bei
    void joinGameController(Long playerId, Integer noOfPlayers, PlayerConfigData configData);

    // Ein Spieler bricht das Warten ab
    void cancelWaitController(Long playerId, Long matchId, Integer noOfPlayers);

    // Verarbeitet die Spielaktion eines Spielers
    void handleGameActionController(Long playerId, Long matchId, Direction dir);
}
