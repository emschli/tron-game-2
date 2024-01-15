package haw.vs.model.matchmanager.gamestateupdater;

import haw.vs.middleware.MethodTypes;
import haw.vs.middleware.common.exceptions.MethodNameAlreadyExistsException;
import haw.vs.middleware.nameService.impl.exception.NameServiceException;
import haw.vs.common.ICallee;
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
    public void register() throws NameServiceException, MethodNameAlreadyExistsException {
        List<Method> methods = new ArrayList<>();
        try {
            methods.add(this.getClass().getMethod("updateMatchManager", Match.class));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e); //✅
        }
        long id = serverStub.register(methods, this, MethodTypes.SPECIFIC);
        MatchManagerInfo.setMatchManagerId(id);
    }

    @Override
    public void updateMatchManager(Match match) {
        gameStateUpdater.updateMatchManager(match);
    }
}
