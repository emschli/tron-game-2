package haw.vs.model.gamelogic.impl;

import haw.vs.model.common.Match;
import haw.vs.model.gamelogic.IGameLogic;
import haw.vs.model.gamelogic.IGameStateProcessedHandler;
import haw.vs.model.gamelogic.api.GameLogicFactory;

public class GameLogic implements IGameLogic {

    private IGameStateProcessedHandler gameStateProcessedHandler;
    private GameLogicFactory glf;

    public GameLogic() {
        this.glf = new GameLogicFactory();
        this.gameStateProcessedHandler = glf.getGameStateProcessedHandler();
    }

    @Override
    public void processMatch(Match match) {
        // process Game
        gameStateProcessedHandler.updateMatch(match);
    }


}
