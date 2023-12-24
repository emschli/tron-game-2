package haw.vs.model.gamelogic.api;

import haw.vs.model.gamelogic.IGameStateProcessedHandler;
import haw.vs.model.gamelogic.IGameStateProcessor;
import haw.vs.model.gamelogic.impl.GameStateProcessedHandler;
import haw.vs.model.gamelogic.mock.MockGameStateProcessor;

public class GameLogicFactory {

    public IGameStateProcessor getGameStateProcessor(){
        return new MockGameStateProcessor();
    }


    public IGameStateProcessedHandler getGameStateProcessedHandler() {
        return new GameStateProcessedHandler();
    }

}
