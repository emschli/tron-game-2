package haw.vs.controller.impl.gameviewupdate;

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
    public void startGameController(Long playerId, GameState gameState) {
        invoke("startGameController", ModeTypes.ASYNC_TCP, playerId, gameState);
    }

    @Override
    public void updateController(Long playerId, GameState gameState) {
        invoke("updateController", ModeTypes.ASYNC_UDP, playerId, gameState);
    }

    @Override
    public void playerWonController(Long playerId, GameState gameState) {
        invoke("playerWonController", ModeTypes.ASYNC_TCP, playerId, gameState);
    }

    @Override
    public void playerLostController(Long playerId, GameState gameState) {
        invoke("playerLostController",ModeTypes.ASYNC_TCP, playerId, gameState);
    }

    @Override
    public void updatePlayerCountViewController(Long playerId, Integer playerCount, Integer targetPlayerCount, String color) {
        invoke("updatePlayerCountViewController", ModeTypes.ASYNC_TCP, playerId, playerCount, targetPlayerCount, color);
    }

    @Override
    public void showMainMenuController(Long playerId) {
        invoke("showMainMenuController", ModeTypes.ASYNC_TCP, playerId);
    }

    @Override
    public void setMatchIdController(Long playerId, Long matchId) {
        invoke("setMatchIdController", ModeTypes.SYNC_TCP, playerId, matchId);
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
