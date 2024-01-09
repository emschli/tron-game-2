package haw.vs.controller.appstub;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import haw.vs.common.Coordinate;
import haw.vs.common.GameState;
import haw.vs.common.IGameState;
import haw.vs.common.properties.PropertiesException;
import haw.vs.controller.api.IGameViewUpdate;
import haw.vs.middleware.ModeTypes;
import haw.vs.middleware.clientStub.api.ClientStubFactory;
import haw.vs.middleware.clientStub.api.IClientStub;
import haw.vs.middleware.common.JsonRequest;
import haw.vs.middleware.common.exceptions.InvokeFailedException;
import haw.vs.middleware.nameService.impl.exception.NameServiceException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameViewUpdateAppStubConsumer implements IGameViewUpdate {
    private IClientStub clientStub;

    public GameViewUpdateAppStubConsumer(IClientStub clientStub) {
        this.clientStub = clientStub;
    }

    public GameViewUpdateAppStubConsumer() {
    }

    @Override
    public void startGame(long playerId, IGameState gameState) {
        invoke("startGame", ModeTypes.ASYNC_TCP, playerId, gameState);
    }

    @Override
    public void updateView(long playerId, IGameState gameState) {
        invoke("updateView", ModeTypes.ASYNC_UDP, playerId, gameState);
    }

    @Override
    public void playerWon(long playerId, IGameState gameState) {
        invoke("playerWon", ModeTypes.ASYNC_TCP, playerId, gameState);
    }

    @Override
    public void playerLost(long playerId, IGameState gameState) {
        invoke("playerLost",ModeTypes.ASYNC_TCP, playerId, gameState);
    }

    @Override
    public void updatePlayerCountView(long playerId, int playerCount, int targetPlayerCount) {
        invoke("updatePlayerCountView", ModeTypes.ASYNC_TCP, playerId, playerCount, targetPlayerCount);
    }

    @Override
    public void showMainMenu(long playerId) {
        invoke("showMainMenu", ModeTypes.ASYNC_TCP, playerId);
    }

    @Override
    public void setMatchId(long playerId, long matchId) {
        invoke("setMatchId", ModeTypes.SYNC_TCP, playerId, matchId);
    }

    private void invoke(String methodName, int modus, Object... args) {
        try {
            clientStub.invoke(methodName, modus, args);
        } catch (NameServiceException e) {
            throw new RuntimeException(e);
        } catch (InvokeFailedException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {

    }

}
