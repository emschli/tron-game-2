package haw.vs.controller.appstub;

import haw.vs.common.IGameState;
import haw.vs.controller.api.IGameViewUpdate;
import haw.vs.middleware.clientStub.api.IClientStub;
import haw.vs.middleware.common.exceptions.InvokeFailedException;
import haw.vs.middleware.nameService.impl.exception.NameServiceException;

public class GameViewUpdateAppStubConsumer implements IGameViewUpdate {
    private IClientStub clientStub;

    public GameViewUpdateAppStubConsumer(IClientStub clientStub) {
        this.clientStub = clientStub;
    }

    public GameViewUpdateAppStubConsumer() {
    }

    @Override
    public void startGame(long playerId, IGameState gameState) {
        Object[] args = new Object[] {
                playerId,
                gameState
        };
        invoke("startGame", args, ModeTypes.ASYNC_UDP);
    }

    @Override
    public void updateView(long playerId, IGameState gameState) {
        Object[] args = new Object[] {
                playerId,
                gameState
        };
        invoke("updateView", args, ModeTypes.ASYNC_UDP);
    }

    @Override
    public void playerWon(long playerId, IGameState gameState) {
        Object[] args = new Object[] {
                playerId,
                gameState
        };
        invoke("playerWon", args, ModeTypes.ASYNC_UDP);
    }

    @Override
    public void playerLost(long playerId, IGameState gameState) {
        Object[] args = new Object[] {
                playerId,
                gameState
        };
        invoke("playerLost", args, ModeTypes.ASYNC_UDP);
    }

    @Override
    public void updatePlayerCountView(long playerId, int playerCount, int targetPlayerCount) {
        Object[] args = new Object[] {
                playerId,
                playerCount,
                targetPlayerCount
        };
        invoke("updatePlayerCountView", args, ModeTypes.ASYNC_UDP);
    }

    @Override
    public void showMainMenu(long playerId) {
        Object[] args = new Object[] {
                playerId
        };
        invoke("showMainMenu", args, ModeTypes.ASYNC_UDP);
    }

    @Override
    public void setMatchId(long playerId, long matchId) {
        Object[] args = new Object[] {
                playerId,
                matchId
        };
        invoke("setMatchId", args, ModeTypes.SYNC_TCP);
    }

    private void invoke(String methodName, Object[] args, int mode) {
        try {
            clientStub.invoke(methodName, args, mode);
        } catch (NameServiceException e) {
            throw new RuntimeException(e);
        } catch (InvokeFailedException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {

/*        GameViewUpdateAppStubConsumer apc = new GameViewUpdateAppStubConsumer();

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
