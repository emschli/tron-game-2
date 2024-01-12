package haw.vs.view;

import haw.vs.common.ICallee;
import haw.vs.common.GameState;
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
            methods.add(this.getClass().getMethod("startGameView", GameState.class));
            methods.add(this.getClass().getMethod("updateView", GameState.class));
            methods.add(this.getClass().getMethod("playerLostView", GameState.class));
            methods.add(this.getClass().getMethod("playerWonView", GameState.class));
            methods.add(this.getClass().getMethod("updatePlayerCountViewView", Integer.class, Integer.class));
            methods.add(this.getClass().getMethod("setMatchIdView", Long.class));
            methods.add(this.getClass().getMethod("showMainMenuView"));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        serverStub.register(methods, this, MethodTypes.SPECIFIC); //TODO: hier kommt playerID zurück und muss in PlayerInfo übernommen werden
    }

    @Override
    public void startGameView(GameState gameState) {
        viewFacade.startGameView(gameState);
    }

    @Override
    public void updateView(GameState gameState) {
        viewFacade.updateView(gameState);
    }

    @Override
    public void playerLostView(GameState gameState) {
        viewFacade.playerLostView(gameState);
    }

    @Override
    public void playerWonView(GameState gameState) {
        viewFacade.playerWonView(gameState);
    }

    @Override
    public void updatePlayerCountViewView(Integer playerCount, Integer targetPlayerCount) {
        viewFacade.updatePlayerCountViewView(playerCount, targetPlayerCount);
    }

    @Override
    public void showMainMenuView() {
        viewFacade.showMainMenuView();
    }

    @Override
    public void setMatchIdView(Long matchId) {
        viewFacade.setMatchIdView(matchId);
    }
}
