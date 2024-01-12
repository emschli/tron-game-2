package haw.vs.view.api;


import haw.vs.common.Direction;
import haw.vs.common.PlayerConfigData;
import haw.vs.controller.api.IInput;

import static haw.vs.view.api.PlayerInfo.*;

/**
 * Klasse die die Player Inputs verarbeitet und dem Controller Bescheid gibt
 */
public class PlayerInputHandler implements IPlayerInputHandler {

    private IInput inputController;

    public PlayerInputHandler(IInput inputController) {
        this.inputController = inputController;
    }

    @Override
    public void onGameStart(int numOfPlayers) {
        //TODO ConfigData? muss ggfls noch festgelegt werden
        PlayerConfigData configData = new PlayerConfigData(600, 600);
        inputController.joinGameController(getPlayerId(), numOfPlayers, configData);
    }

    @Override
    public void onKeyPressed(String pressedKey) {
                System.out.println("Hilfsausgabe im PlayerInputHandler. Pressed Key: "+ pressedKey );
        inputController.handleGameActionController(getPlayerId(), getMatchId() , getDirectionFromString(pressedKey));
    }

    @Override
    public void onCancel() {
        inputController.cancelWaitController(getPlayerId(), getMatchId(), getNoOfPlayers());
    }

    private Direction getDirectionFromString(String string) {
        Direction direction = null;

        switch (string) {
            case "LEFT":
                direction = Direction.LEFT;
                break;
            case "RIGHT":
                direction = Direction.RIGHT;
                break;
            case "UP":
                direction = Direction.UP;
                break;
            case "DOWN":
                direction = Direction.DOWN;
                break;
        }
        if(direction.equals(null)){
            throw new RuntimeException("The direction is null");
        }
        return direction;
    }
}