package haw.vs.controller.api;

import haw.vs.common.Direction;
import haw.vs.common.PlayerConfigData;

public interface IInput {
    // Ein Spieler tritt dem Spiel bei
    void joinGame(long playerId, int noOfPlayers, PlayerConfigData configData);

    // Ein Spieler bricht das Warten ab
    void cancelWait(long playerId);

    // Verarbeitet die Spielaktion eines Spielers
    void handleGameAction(long playerId, Direction dir);
}
