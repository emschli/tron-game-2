package haw.vs.view;

import haw.vs.common.GameState;
import haw.vs.middleware.ModeTypes;
import haw.vs.middleware.clientStub.api.IClientStub;
import haw.vs.middleware.common.exceptions.InvokeFailedException;
import haw.vs.middleware.nameService.impl.exception.NameServiceException;
import haw.vs.view.api.IViewFacade;

public class ViewFacadeAppStubConsumer implements IViewFacade {

    private IClientStub clientStub;

    public ViewFacadeAppStubConsumer(IClientStub clientStub) {
        this.clientStub = clientStub;
    }

    private void invoke(String methodName, int mod, Object... args) {
        try {
            clientStub.invoke(methodName, mod, args);
        } catch (NameServiceException e) {
            throw new RuntimeException(e);
        } catch (InvokeFailedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void startGameView (GameState gameState) {
        invoke("startGameView", ModeTypes.SYNC_TCP, gameState);
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
    public void updatePlayerCountViewView(int playerCount, int targetPlayerCount) {
        invoke("updatePlayerCountViewView", ModeTypes.ASYNC_UDP, playerCount, targetPlayerCount);
    }

    @Override
    public void showMainMenuView() {
        invoke("showMainMenuView", ModeTypes.ASYNC_UDP);
    }

    @Override
    public void setMatchIdView(long matchId) {
        invoke("setMatchIdView", ModeTypes.ASYNC_TCP, matchId);
    }
}
