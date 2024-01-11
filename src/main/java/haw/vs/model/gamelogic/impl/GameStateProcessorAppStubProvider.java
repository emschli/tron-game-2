package haw.vs.model.gamelogic.impl;

import haw.vs.common.ICallee;
import haw.vs.middleware.MethodTypes;
import haw.vs.middleware.nameService.impl.exception.NameServiceException;
import haw.vs.middleware.serverStub.api.IServerStub;
import haw.vs.model.common.Match;
import haw.vs.model.gamelogic.api.IGameStateProcessor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class GameStateProcessorAppStubProvider implements IGameStateProcessor, ICallee {
    public final IServerStub serverStub;
    public IGameStateProcessor gameStateProcessor;

    public GameStateProcessorAppStubProvider(IServerStub serverStub, IGameStateProcessor gameStateProcessor) {
        this.serverStub = serverStub;
        this.gameStateProcessor = gameStateProcessor;
    }

    @Override
    public void register() throws NameServiceException {
        List<Method> methods = new ArrayList<>();
        try {
            methods.add(this.getClass().getMethod("addTask", Match.class));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    serverStub.register(methods, this, MethodTypes.STATELESS);
    }

    @Override
    public void addTask(Match match) {
        gameStateProcessor.addTask(match);
    }
}
