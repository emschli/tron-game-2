package haw.vs.model.matchmanager.gamestateupdater;

import haw.vs.middleware.MethodTypes;
import haw.vs.middleware.nameService.impl.exception.NameServiceException;
import haw.vs.middleware.serverStub.api.ICallee;
import haw.vs.middleware.serverStub.api.IServerStub;
import haw.vs.model.common.Match;
import haw.vs.model.matchmanager.MatchManagerInfo;
import haw.vs.model.matchmanager.api.IGameStateUpdater;

import java.util.ArrayList;
import java.util.List;

public class GameStateUpdaterAppStubProvider implements IGameStateUpdater, ICallee {
    private final IServerStub serverStub;

    private final IGameStateUpdater gameStateUpdater;

    public GameStateUpdaterAppStubProvider(IServerStub serverStub, IGameStateUpdater gameStateUpdater) throws NameServiceException {
        this.serverStub = serverStub;
        this.gameStateUpdater = gameStateUpdater;

        List<String> methodNames = new ArrayList<>();
        methodNames.add("update");
         this.serverStub.register(methodNames, this, MethodTypes.SPECIFIC);
    }

    @Override
    public void call(String methodName, Object[] args) {
        if (methodName.equals("update")) {
            Match match = (Match) args[0]; //TODO: this is not going to work...
            update(match);
        }
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
