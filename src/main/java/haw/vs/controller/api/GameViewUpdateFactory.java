package haw.vs.controller.api;

import haw.vs.controller.mock.MockGameViewUpdate;

public class GameViewUpdateFactory {
    public static IGameViewUpdate getGameViewUpdate(){
        return new MockGameViewUpdate();
    }
}
