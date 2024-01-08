package haw.vs.model.matchmanager.matchcontroller;

import haw.vs.common.Direction;
import haw.vs.common.PlayerConfigData;
import haw.vs.middleware.ModeTypes;
import haw.vs.middleware.clientStub.api.IClientStub;
import haw.vs.middleware.common.exceptions.InvokeFailedException;
import haw.vs.middleware.nameService.impl.exception.NameServiceException;
import haw.vs.model.matchmanager.api.IMatchController;

public class MatchManagerAppStubConsumer implements IMatchController {
    private final IClientStub clientStub;

    public MatchManagerAppStubConsumer(IClientStub clientStub) {
        this.clientStub = clientStub;
    }

    @Override
    public void addPlayerToMatch(long playerId, int numberOfPlayers, PlayerConfigData configData) {
        invoke("addPlayerToMatch", ModeTypes.ASYNC_TCP, playerId, numberOfPlayers, configData);
    }

    @Override
    public void deletePlayerFromMatch(long playerId, long matchId, int numberOfPlayers) {
        invoke("deletePlayerFromMatch", ModeTypes.ASYNC_TCP, playerId, matchId, numberOfPlayers);
    }

    @Override
    public void movePlayer(long playerId, long matchId, Direction direction) {
        invoke("movePlayer", ModeTypes.ASYNC_UDP, playerId, matchId, direction);
    }

    private void invoke(String methodName, int modus, Object... args) {
        try {
            clientStub.invoke(methodName, modus, args);
        } catch (NameServiceException e) {
            throw new RuntimeException(e);
        } catch (InvokeFailedException e) {
            throw new RuntimeException(e);
        }
    }
}
