package haw.vs.view;

import haw.vs.common.IGameState;
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
    public void startGame(IGameState gameState) {
        invoke("startGame", ModeTypes.SYNC_TCP, gameState);
    }

    @Override
    public void update(IGameState gameState) {
        invoke("update", ModeTypes.ASYNC_UDP, gameState);
    }

    @Override
    public void playerLost(IGameState gameState) {
        invoke("playerLost", ModeTypes.ASYNC_UDP, gameState);

    }

    @Override
    public void playerWon(IGameState gameState) {
        invoke("playerWon", ModeTypes.ASYNC_UDP, gameState);

    }

    @Override
    public void updatePlayerCountView(int playerCount, int targetPlayerCount) {
        invoke("updatePlayerCountView", ModeTypes.ASYNC_UDP, playerCount, targetPlayerCount);
    }

    @Override
    public void showMainMenu() {
        invoke("showMainMenu", ModeTypes.ASYNC_UDP);
    }

    @Override
    public void setMatchId(long matchId) {
        invoke("setMatchId", ModeTypes.SYNC_TCP, matchId);
    }
}
