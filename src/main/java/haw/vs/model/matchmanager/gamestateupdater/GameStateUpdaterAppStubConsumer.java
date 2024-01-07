package haw.vs.model.matchmanager.gamestateupdater;

import haw.vs.middleware.ModeTypes;
import haw.vs.middleware.clientStub.api.IClientStub;
import haw.vs.middleware.common.exceptions.InvokeFailedException;
import haw.vs.model.common.Match;
import haw.vs.model.matchmanager.api.IGameStateUpdater;

public class GameStateUpdaterAppStubConsumer implements IGameStateUpdater {
    private final IClientStub clientStub;

    public GameStateUpdaterAppStubConsumer(IClientStub clientStub) {
        this.clientStub = clientStub;
    }

    @Override
    public void update(Match match) {
        Object[] args = new Object[] {
                match
        };
        try {
            clientStub.invokeSpecific(match.getMatchManagerId(), "update", args, ModeTypes.ASYNC_UDP);
        } catch (InvokeFailedException e) {
            throw new RuntimeException(e);
        }
    }
}
