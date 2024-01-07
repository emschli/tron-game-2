package haw.vs.model.matchmanager.gamestateupdater;

import haw.vs.middleware.MethodTypes;
import haw.vs.middleware.nameService.impl.exception.NameServiceException;
import haw.vs.middleware.serverStub.api.ICallee;
import haw.vs.middleware.serverStub.api.IServerStub;
import haw.vs.model.common.Match;
import haw.vs.model.matchmanager.MatchManagerInfo;
import haw.vs.model.matchmanager.api.IGameStateUpdater;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class GameStateUpdaterAppStubProvider implements IGameStateUpdater, ICallee {
    private final IServerStub serverStub;

    private final IGameStateUpdater gameStateUpdater;

    public GameStateUpdaterAppStubProvider(IServerStub serverStub, IGameStateUpdater gameStateUpdater) {
        this.serverStub = serverStub;
        this.gameStateUpdater = gameStateUpdater;
    }

    @Override
    public void register() throws NameServiceException {
        List<Method> methods = new ArrayList<>();
        try {
            methods.add(this.getClass().getMethod("update", Match.class));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        serverStub.register(methods, this, MethodTypes.SPECIFIC);
    }

    @Override
    public void setId(long id) {
        MatchManagerInfo.setMatchManagerId(id);
    }

    @Override
    public void update(Match match) {
        gameStateUpdater.update(match);
    }
}