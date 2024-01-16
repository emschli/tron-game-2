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

    /**
     * Sets the target number of players with whom the user wants to play and sets the actual players to 1 (the user theirself)
     * The configdata is created and the inputController is informed about this action and the data is directed to it.
     * @param numOfPlayers number of players with whom the user wants to play
     */
    @Override
    public void onGameStart(int numOfPlayers) {
        PlayerConfigData configData = new PlayerConfigData(600, 600);
        PlayerInfo.setNoOfPlayers(numOfPlayers);
        PlayerInfo.setActualNoOfPlayers(1);
        inputController.joinGameController(getPlayerId(), numOfPlayers, configData);
    }

    /**
     * Sends the pressedKey to the input controller to handle the game action (direction changes)
     * @param pressedKey
     */
    @Override
    public void onKeyPressed(String pressedKey) {
                System.out.println("Hilfsausgabe im PlayerInputHandler. Pressed Key: "+ pressedKey );
        inputController.handleGameActionController(getPlayerId(), getMatchId() , getDirectionFromString(pressedKey));
    }

    /**
     * Deletes the player from the waitingroom for the match for which they registered
     */
    @Override
    public void onCancel() {
        inputController.cancelWaitController(getPlayerId(), getMatchId(), getNoOfPlayers());
    }

    /**
     * Converts a string to get the direction
     * @param string to be converted
     * @return direction
     */
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