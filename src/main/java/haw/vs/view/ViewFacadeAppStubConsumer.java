package haw.vs.view;

import haw.vs.common.GameState;
import haw.vs.middleware.ModeTypes;
import haw.vs.middleware.clientStub.api.IClientStub;
import haw.vs.middleware.common.exceptions.InvokeFailedException;
import haw.vs.view.api.IViewFacade;

public class ViewFacadeAppStubConsumer implements IViewFacade {

    private long playerId = 1;

    private IClientStub clientStub;
    private long playerId;

    public ViewFacadeAppStubConsumer(IClientStub clientStub) {
        this.clientStub = clientStub;
        this.playerId = 1;
    }

    private void invoke(String methodName, int mod, Object... args) {
        try {
            clientStub.invokeSpecific(playerId, methodName, mod, args);
        } catch (InvokeFailedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    @Override
    public void startGameView (GameState gameState) {
        invoke("startGameView", ModeTypes.ASYNC_TCP, gameState);
    }

    @Override
    public void updateView (GameState gameState) {
        invoke("updateView", ModeTypes.ASYNC_UDP, gameState);
    }

    @Override
    public void playerLostView(GameState gameState) {
        invoke("playerLostView", ModeTypes.ASYNC_UDP, gameState);

    }

    @Override
    public void playerWonView(GameState gameState) {
        invoke("playerWonView", ModeTypes.ASYNC_UDP, gameState);

    }

    @Override
    public void updatePlayerCountViewView(Integer playerCount, Integer targetPlayerCount) {
        invoke("updatePlayerCountViewView", ModeTypes.ASYNC_UDP, playerCount, targetPlayerCount);
    }

    @Override
    public void showMainMenuView() {
        invoke("showMainMenuView", ModeTypes.ASYNC_UDP);
    }

    @Override
    public void setMatchIdView(Long matchId) {
        invoke("setMatchIdView", ModeTypes.SYNC_TCP, matchId);
    }
}
