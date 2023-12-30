package haw.vs.view.api;


import edu.cads.bai5.vsp.tron.view.ITronView;
import haw.vs.common.Direction;
import haw.vs.common.PlayerConfigData;
import haw.vs.controller.api.IInput;

import static haw.vs.view.api.PlayerInfo.getPlayerId;

/**
 * Klasse die die Player Inputs verarbeitet und dem Controller Bescheid gibt
 */
public class PlayerInputHandler implements IPlayerInputHandler {

    private ITronView tronView;

    private IInput inputController;

    @Override
    public void onGameStart(int numOfPlayers) {
        //TODO ConfigData?
        PlayerConfigData configData = new PlayerConfigData(600, 600);
        inputController.joinGame(getPlayerId(), numOfPlayers, configData);
        System.out.println("onGameStart pressed");
    }

    @Override
    public void onKeyPressed(String pressedKey) {
        inputController.handleGameAction(getPlayerId(), getDirectionFromString(pressedKey));
        System.out.println("onKey pressed");
    }

    @Override
    public void onCancel() {
        inputController.cancelWait(getPlayerId());
        System.out.println("onCancel pressed");
    }

    @Override
    public void onBackToMain() {
        //TODO ist das nicht das gleiche wie on cancel?
        System.out.println("BackToMain");
    }

    private Direction getDirectionFromString(String string) {
        //TODO default direction? wie komme ich gegebenenfalls an die direction des players?
        Direction direction = null; //TODO Exception
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
        return direction;
    }
}