package haw.vs.view.api;

import haw.vs.view.mock.MockPlayerInputHandler;
import haw.vs.view.mock.MockViewFacade;

import java.io.IOException;

public class ViewFactory {
    public static IViewFacade getView() throws IOException {
        //return new ViewFacade();
        return new MockViewFacade();
    }
    public static IPlayerInputHandler getInputHandler(){
        //return new PlayerInputHandler();
        return new MockPlayerInputHandler();
    }
}

