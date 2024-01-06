package haw.vs.controller.appstub;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import haw.vs.common.Direction;
import haw.vs.common.PlayerConfigData;
import haw.vs.controller.api.IInput;
import haw.vs.middleware.clientStub.api.IClientStub;
import haw.vs.middleware.common.exceptions.InvokeFailedException;
import haw.vs.middleware.nameService.impl.exception.NameServiceException;

public class ApplicationStubInputConsumer implements IInput {

    private IClientStub clientStub;

    public ApplicationStubInputConsumer(IClientStub clientStub) {
        this.clientStub = clientStub;
    }
    public ApplicationStubInputConsumer() {
    }

    @Override
    public void joinGame(long playerId, int noOfPlayers, PlayerConfigData configData) {
        try {
            clientStub.invoke("joinGame", new Object[]{playerId, noOfPlayers, convertPlayerConfigDataToString(configData)}, 0);
        } catch (NameServiceException e) {
            throw new RuntimeException(e);
        } catch (InvokeFailedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cancelWait(long playerId, long matchId, int noOfPlayers) {
        try {
            clientStub.invoke("cancelWait", new Object[]{playerId, matchId, noOfPlayers}, 0);
        } catch (NameServiceException e) {
            throw new RuntimeException(e);
        } catch (InvokeFailedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleGameAction(long playerId, long matchId, Direction dir) {
        try {
            clientStub.invoke("handleGameAction", new Object[]{playerId, matchId, dir.name()}, 0);
        } catch (NameServiceException e) {
            throw new RuntimeException(e);
        } catch (InvokeFailedException e) {
            e.printStackTrace();
        }
    }

    private String convertPlayerConfigDataToString(PlayerConfigData configData) {
        ObjectMapper objectMapper = new ObjectMapper();
        String configDataJson = null;
        try {
            configDataJson = objectMapper.writeValueAsString(configData);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return configDataJson;
    }

}
