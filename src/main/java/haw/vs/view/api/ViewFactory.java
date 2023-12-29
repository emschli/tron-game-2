package haw.vs.view.api;

public class ViewFactory {
    public static IViewFacade getView(){
        return new ViewFacade();
    }
    public static IPlayerInputHandler getInputHandler(){
        return new PlayerInputHandler();
    }
}

