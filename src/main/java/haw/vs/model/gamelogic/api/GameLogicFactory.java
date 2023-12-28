package haw.vs.model.gamelogic.api;

import haw.vs.model.gamelogic.IGameStateProcessedHandler;
import haw.vs.model.gamelogic.impl.GameStateProcessedHandler;
import haw.vs.model.gamelogic.impl.GameStateProcessor;

public class GameLogicFactory {

    public IGameStateProcessor getGameStateProcessor(){
        return new GameStateProcessor();
    }

    public IGameStateProcessedHandler getGameStateProcessedHandler() {
        return new GameStateProcessedHandler();
    }

}
