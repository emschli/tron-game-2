package haw.vs.model.gamelogic.api;

import haw.vs.model.gamelogic.mock.MockGameStateProcessor;

public class GameLogicFactory {
    public static IGameStateProcessor getGameStateProcessor() {
        return new MockGameStateProcessor();
    }
}
