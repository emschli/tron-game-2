package haw.vs.controller.api;

import haw.vs.common.Direction;
import haw.vs.common.PlayerConfigData;

public interface IInput {
    // Ein Spieler tritt dem Spiel bei
    void joinGameController(long playerId, int noOfPlayers, PlayerConfigData configData);

    // Ein Spieler bricht das Warten ab
    void cancelWaitController(long playerId, long matchId, int noOfPlayers);

    // Verarbeitet die Spielaktion eines Spielers
    void handleGameActionController(long playerId, long matchId, Direction dir);
}
