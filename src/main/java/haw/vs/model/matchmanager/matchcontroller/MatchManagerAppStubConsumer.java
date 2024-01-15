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
    public void addPlayerToMatchMatchManager(Long playerId, Integer numberOfPlayers, PlayerConfigData configData) {
        invoke("addPlayerToMatchMatchManager", ModeTypes.ASYNC_TCP,playerId, playerId, numberOfPlayers, configData);
    }

    @Override
    public void deletePlayerFromMatchMatchManager(Long playerId, Long matchId, Integer numberOfPlayers) {
        invoke("deletePlayerFromMatchMatchManager", ModeTypes.ASYNC_TCP,playerId, playerId, matchId, numberOfPlayers);
    }

    @Override
    public void movePlayerMatchManager(Long playerId, Long matchId, Direction direction) {
        invoke("movePlayerMatchManager", ModeTypes.ASYNC_UDP,playerId, playerId, matchId, direction);
    }

    private void invoke(String methodName, int modus, long stateId, Object... args) {
        try {
            clientStub.invoke(methodName, modus, stateId, args);
        } catch (NameServiceException e) {
            throw new RuntimeException(e); //✅
        } catch (InvokeFailedException e) { //should only happen with syncTCP calls
            //throw new RuntimeException(e);
        }
    }
}
