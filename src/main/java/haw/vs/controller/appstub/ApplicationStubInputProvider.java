package haw.vs.controller.appstub;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import haw.vs.common.Direction;
import haw.vs.common.PlayerConfigData;
import haw.vs.controller.api.IInput;
import haw.vs.middleware.serverStub.api.ICallee;

public class ApplicationStubInputProvider implements ICallee {
    private long id;
    IInput iInput;

    public ApplicationStubInputProvider(long id, IInput iInput) {
        this.id = id;
        this.iInput = iInput;
    }

    public ApplicationStubInputProvider(long id) {
        this.id = id;
    }

    @Override
    public void call(String methodName, Object[] args) {
        switch (methodName) {
            case "joinGame":
                long joinGamePlayerId = (long) args[0];
                int noOfPlayers = (int) args[1];
                String configData = (String)  args[2];
                iInput.joinGame(joinGamePlayerId, noOfPlayers, convertPlayerConfigDataToObject(configData));
                break;

            case "cancelWait":
                long cancelWaitPlayerId = (long) args[0];
                long matchId = (long) args[1];
                int cancelWaitNoOfPlayers = (int) args[2];
                iInput.cancelWait(cancelWaitPlayerId, matchId, cancelWaitNoOfPlayers);
                break;

            case "handleGameAction":
                long handleGameActionPlayerId = (long) args[0];
                long handleGameActionMatchId = (long) args[1];
                Direction dir = Direction.valueOf((String)args[2]);
                iInput.handleGameAction(handleGameActionPlayerId, handleGameActionMatchId, dir);
                break;

            default:
                // Fehlerbehandlung
                break;
        }
    }

    private PlayerConfigData convertPlayerConfigDataToObject(String configDataString) {
        ObjectMapper objectMapper = new ObjectMapper();
        PlayerConfigData configData = null;
        try {
            configData = objectMapper.readValue(configDataString, PlayerConfigData.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return configData;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
