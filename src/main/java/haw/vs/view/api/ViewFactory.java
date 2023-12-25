package haw.vs.view.api;

import haw.vs.view.impl.PlayerInputHandler;

public class ViewFactory {
    public static IViewFacade getView(){
        //TODO
        return new ViewFacade();
    }
    public static IPlayerInputHandler getInputHandler(){
        //TODO
        return (IPlayerInputHandler) new PlayerInputHandler();
        //??? eigentlich klappt im lokalen Projekt         return new PlayerInputHandler();
    }
}

