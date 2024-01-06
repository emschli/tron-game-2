package haw.vs.controller.appstub;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import haw.vs.common.GameState;
import haw.vs.common.IGameState;
import haw.vs.controller.api.IGameViewUpdate;
import haw.vs.middleware.clientStub.api.IClientStub;
import haw.vs.middleware.common.exceptions.InvokeFailedException;
import haw.vs.middleware.nameService.impl.exception.NameServiceException;

public class ApplicationStubGameViewUpdateConsumer implements IGameViewUpdate {
    private IClientStub clientStub;

    public ApplicationStubGameViewUpdateConsumer(IClientStub clientStub) {
        this.clientStub = clientStub;
    }

    public ApplicationStubGameViewUpdateConsumer() {
    }

    @Override
    public void startGame(long playerId, IGameState gameState) {
        try {
            clientStub.invoke("startGame", new Object[]{playerId, convertGamaStateToString(gameState)}, 0);
        } catch (NameServiceException e) {
            throw new RuntimeException(e);
        } catch (InvokeFailedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateView(long playerId, IGameState gameState) {
        try {
            clientStub.invoke("updateView", new Object[]{playerId, convertGamaStateToString(gameState)}, 0);
        } catch (NameServiceException e) {
            throw new RuntimeException(e);
        } catch (InvokeFailedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void playerWon(long playerId, IGameState gameState) {
        try {
            clientStub.invoke("playerWon",new Object[]{ playerId, convertGamaStateToString(gameState)}, 0);
        } catch (NameServiceException e) {
            throw new RuntimeException(e);
        } catch (InvokeFailedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void playerLost(long playerId, IGameState gameState)  {
        try {
            clientStub.invoke("playerLost", new Object[]{playerId, convertGamaStateToString(gameState)}, 0);
        } catch (NameServiceException e) {
            throw new RuntimeException(e);
        } catch (InvokeFailedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updatePlayerCountView(long playerId, int playerCount, int targetPlayerCount) {
        try {
            clientStub.invoke("updatePlayerCountView", new Object[]{playerId, playerCount, targetPlayerCount}, 0);
        } catch (NameServiceException e) {
            throw new RuntimeException(e);
        } catch (InvokeFailedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void showMainMenu(long playerId) {
        try {
            clientStub.invoke("showMainMenu", new Object[]{playerId}, 0 );
        } catch (NameServiceException e) {
            throw new RuntimeException(e);
        } catch (InvokeFailedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void setMatchId(long playerId, long matchId) {
        try {
            clientStub.invoke("setMatchId", new Object[]{playerId, matchId},  0);
        } catch (NameServiceException e) {
            throw new RuntimeException(e);
        } catch (InvokeFailedException e) {
            e.printStackTrace();
        }

    }

    private String convertGamaStateToString(IGameState gameState){
        ObjectMapper objectMapper = new ObjectMapper();
        String gameStateJson = null;
        try {
            gameStateJson = objectMapper.writeValueAsString(gameState);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return gameStateJson;

    }


    public static void main(String[] args) {

/*        ApplicationStubGameViewUpdateConsumer apc = new ApplicationStubGameViewUpdateConsumer();

        List<Coordinate> player1Coordinates = new ArrayList<>();
        player1Coordinates.add(new Coordinate(1, 1));
        player1Coordinates.add(new Coordinate(2, 2));

        // Erstelle einige Koordinaten für Spieler 2
        List<Coordinate> player2Coordinates = new ArrayList<>();
        player2Coordinates.add(new Coordinate(3, 3));
        player2Coordinates.add(new Coordinate(4, 4));

        // Erstelle die Spielerpositionen
        Map<String, List<Coordinate>> playerPositionMap = new HashMap<>();
        playerPositionMap.put("Player1", player1Coordinates);
        playerPositionMap.put("Player2", player2Coordinates);


        IGameState gameState = new GameState(playerPositionMap);
        String s = apc.convertGamaStateToString(gameState);
        System.out.println(s);

        Object[] argsi = {1234L, s};


        //long playerId = (long) argsi[0];
        //String gameStateString = (String) argsi[1];
        //IGameState gameStateConverted = apc.convertGamaStateToObject(gameStateString);
        //System.out.println(gameStateConverted);


        System.out.println("----------------------------------");

        Marshaller cs = new Marshaller();
        byte[] bytes = cs.marshall(argsi, "testMethod");

        ServerStub ss = new ServerStub();
        JsonRequest unmarshalled = ss.unmarshall(bytes);

        Object[] params =  unmarshalled.getParams();

        long playerId = (long) argsi[0];
        String gameStateString = (String) argsi[1];
        IGameState gameStateConverted = apc.convertGamaStateToObject(gameStateString);

        System.out.println(gameStateConverted);*/


    }

}
