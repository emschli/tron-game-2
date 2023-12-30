package haw.vs.view.api;


import edu.cads.bai5.vsp.tron.view.ITronView;
import haw.vs.common.PlayerConfigData;
import haw.vs.controller.api.IInput;

import static haw.vs.view.api.PlayerInfo.getPlayerId;

/**
 * Klasse die die Player Inputs verarbeitet und dem COntroller Bescheid gibt
 *
 */
public class PlayerInputHandler implements IPlayerInputHandler{

    private ITronView tronView;

    private IInput inputController;

    private PlayerInfo playerInfo;


    /**
     *
     */
    @Override
    public void onGameStart() {
        PlayerConfigData configData = new PlayerConfigData(600,600);
        //TODO Frage: wie komme ich an die noOfPlayers die der Player eingegeben hat? übergeben?
        inputController.joinGame(getPlayerId(), noOfPlayers, configData);
        System.out.println("onGameStart pressed");
    }

    @Override
    public void onKeyPressed() {
        //TODO Frage: wie komme ich hier an den Key / Key Event ran? müsste mit übergeben werden, oder?
        System.out.println("onKey pressed");
    }

    @Override
    public void onCancel() {
        inputController.cancelWait(getPlayerId());
        System.out.println("onCancel pressed");
    }

    @Override
    public void onBackToMain() {

        System.out.println("BackToMain");
//TODO
    }
}