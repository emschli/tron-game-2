package haw.vs.controller.api;

import haw.vs.common.Direction;
import haw.vs.common.PlayerConfigData;

public interface IInput {
    void joinGame(long playerId, int noOfPlayers, PlayerConfigData configData);
    void cancelWait(long playerId);
    void handleGameAction(long playerId, Direction dir);
}
