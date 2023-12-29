package haw.vs.view.api;


import edu.cads.bai5.vsp.tron.view.ITronView;
import haw.vs.controller.api.IInput;

/**
 * Klasse die die Player Inputs verarbeitet
 */
public class PlayerInputHandler implements IPlayerInputHandler{

    private ITronView tronView;

    private IInput inputController;

    /**
     *
     */
    @Override
    public void onGameStart() {
//

        System.out.println("onGameStart pressed");
    }

    @Override
    public void onKeyPressed() {
//TODO
        System.out.println("onKeyPressPressed");

    }

    @Override
    public void onCancel() {
//TODO
        System.out.println("onCancel pressed");

    }

    @Override
    public void onBackToMain() {

        System.out.println("BackToMain");
//TODO
    }



}
