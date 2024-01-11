package haw.vs.view;

import haw.vs.common.ICallee;
import haw.vs.common.IGameState;
import haw.vs.middleware.MethodTypes;
import haw.vs.middleware.nameService.impl.exception.NameServiceException;
import haw.vs.middleware.serverStub.api.IServerStub;
import haw.vs.view.api.IViewFacade;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ViewFacadeAppStubProvider implements IViewFacade, ICallee {

    private final IServerStub serverStub;

    private final IViewFacade viewFacade;

    public ViewFacadeAppStubProvider(IServerStub serverStub, IViewFacade viewFacade) {
        this.serverStub = serverStub;
        this.viewFacade = viewFacade;
    }

    @Override
    public void register() throws NameServiceException {
        List<Method> methods = new ArrayList<>();
        try {
            methods.add(this.getClass().getMethod("startGame", IGameState.class));
            methods.add(this.getClass().getMethod("update", IGameState.class));
            methods.add(this.getClass().getMethod("playerLost", IGameState.class));
            methods.add(this.getClass().getMethod("playerWon", IGameState.class));
            methods.add(this.getClass().getMethod("updatePlayerCountView", int.class, int.class));
            methods.add(this.getClass().getMethod("setMatchId", long.class));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        serverStub.register(methods, this, MethodTypes.SPECIFIC);
    }

    @Override
    public void startGame(IGameState gameState) {
        viewFacade.startGame(gameState);
    }

    @Override
    public void update(IGameState gameState) {
        viewFacade.update(gameState);
    }

    @Override
    public void playerLost(IGameState gameState) {
        viewFacade.playerLost(gameState);
    }

    @Override
    public void playerWon(IGameState gameState) {
        viewFacade.playerWon(gameState);
    }

    @Override
    public void updatePlayerCountView(int playerCount, int targetPlayerCount) {
        viewFacade.updatePlayerCountView(playerCount, targetPlayerCount);
    }

    @Override
    public void showMainMenu() {
        viewFacade.showMainMenu();
    }

    @Override
    public void setMatchId(long matchId) {
        viewFacade.setMatchId(matchId);
    }
}
