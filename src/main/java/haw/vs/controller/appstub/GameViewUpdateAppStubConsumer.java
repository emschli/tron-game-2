package haw.vs.controller.appstub;

import haw.vs.common.GameState;
import haw.vs.controller.api.IGameViewUpdate;
import haw.vs.middleware.ModeTypes;
import haw.vs.middleware.clientStub.api.IClientStub;
import haw.vs.middleware.common.exceptions.InvokeFailedException;
import haw.vs.middleware.nameService.impl.exception.NameServiceException;

public class GameViewUpdateAppStubConsumer implements IGameViewUpdate {
    private IClientStub clientStub;

    public GameViewUpdateAppStubConsumer(IClientStub clientStub) {
        this.clientStub = clientStub;
    }

    public GameViewUpdateAppStubConsumer() {
    }

    @Override
    public void startGameController(long playerId, GameState gameState) {
        invoke("startGame", ModeTypes.ASYNC_TCP, playerId, gameState);
    }

    @Override
    public void updateController(long playerId, GameState gameState) {
        invoke("updateController", ModeTypes.ASYNC_UDP, playerId, gameState);
    }

    @Override
    public void playerWonController(long playerId, GameState gameState) {
        invoke("playerWon", ModeTypes.ASYNC_TCP, playerId, gameState);
    }

    @Override
    public void playerLostController(long playerId, GameState gameState) {
        invoke("playerLost",ModeTypes.ASYNC_TCP, playerId, gameState);
    }

    @Override
    public void updatePlayerCountViewController(long playerId, int playerCount, int targetPlayerCount) {
        invoke("updatePlayerCountView", ModeTypes.ASYNC_TCP, playerId, playerCount, targetPlayerCount);
    }

    @Override
    public void showMainMenuController(long playerId) {
        invoke("showMainMenu", ModeTypes.ASYNC_TCP, playerId);
    }

    @Override
    public void setMatchIdController(long playerId, long matchId) {
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

}
