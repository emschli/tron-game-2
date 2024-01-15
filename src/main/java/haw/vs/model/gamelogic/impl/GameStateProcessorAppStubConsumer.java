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
            throw new RuntimeException(e); //✅ we need the nameservice for lookup
        } catch (InvokeFailedException e) {
            e.printStackTrace();
            //throw new RuntimeException(e); //✅ shouldn't happen, since this is no syncTCP call, maybe the asyncModes should not have to deal with this?
        }
    }
}
