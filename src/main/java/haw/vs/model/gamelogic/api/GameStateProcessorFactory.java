package haw.vs.model.gamelogic.api;

import haw.vs.model.gamelogic.impl.GameStateProcessor;

public class GameStateProcessorFactory {
    public static IGameStateProcessor getGameStateProcessor() {
        return new GameStateProcessor();
    }
}
