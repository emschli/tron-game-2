package haw.vs.view.api;

import haw.vs.view.mock.MockPlayerInputHandler;
import haw.vs.view.mock.MockViewFacade;

public class ViewFactory {
    public static IViewFacade getView(){
        //return new ViewFacade();
        return new MockViewFacade();
    }
    public static IPlayerInputHandler getInputHandler(){
        //return new PlayerInputHandler();
        return new MockPlayerInputHandler();
    }
}

