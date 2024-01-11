package haw.vs.model.gamelogic.impl;

import haw.vs.middleware.ModeTypes;
import haw.vs.middleware.clientStub.api.IClientStub;
import haw.vs.middleware.common.exceptions.InvokeFailedException;
import haw.vs.middleware.nameService.impl.exception.NameServiceException;
import haw.vs.model.common.Match;
import haw.vs.model.gamelogic.api.IGameStateProcessor;

public class GameStateProcessorAppStubConsumer implements IGameStateProcessor {

    private final IClientStub clientStub;

    public GameStateProcessorAppStubConsumer(IClientStub clientStub) {
        this.clientStub = clientStub;
    }

    @Override
    public void addTask(Match match) {
        try {
            clientStub.invoke("addTask", ModeTypes.ASYNC_UDP, match);
        } catch (NameServiceException e) {
            throw new RuntimeException(e);
        } catch (InvokeFailedException e) {
            throw new RuntimeException(e);
        }
    }
}
