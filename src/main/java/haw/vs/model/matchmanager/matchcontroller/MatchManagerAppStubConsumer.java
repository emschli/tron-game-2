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
        Object[] args = new Object[] {
                playerId,
                numberOfPlayers,
                configData
        };
        invoke("addPlayerToMatch", args, ModeTypes.ASYNC_TCP);
    }

    @Override
    public void deletePlayerFromMatch(long playerId, long matchId, int numberOfPlayers) {
        Object[] args = new Object[] {
                playerId,
                matchId,
                numberOfPlayers
        };
        invoke("deletePlayerFromMatch", args, ModeTypes.ASYNC_TCP);
    }

    @Override
    public void movePlayer(long playerId, long matchId, Direction direction) {
        Object[] args = new Object[] {
                playerId,
                matchId,
                direction
        };
        invoke("movePlayer", args, ModeTypes.ASYNC_UDP);
    }

    private void invoke(String methodName, Object[] args, int modus) {
        try {
            clientStub.invoke(methodName, args, modus);
        } catch (NameServiceException e) {
            throw new RuntimeException(e);
        } catch (InvokeFailedException e) {
            throw new RuntimeException(e);
        }
    }
}
