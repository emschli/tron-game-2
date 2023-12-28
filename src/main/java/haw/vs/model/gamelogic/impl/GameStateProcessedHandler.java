package haw.vs.model.gamelogic.impl;

import haw.vs.model.common.Match;
import haw.vs.model.gamelogic.IGameStateProcessedHandler;
import haw.vs.model.gamelogic.mock.MockGameStateUpdaterFactory;
import haw.vs.model.matchmanager.api.IGameStateUpdater;

public class GameStateProcessedHandler implements IGameStateProcessedHandler {

    private IGameStateUpdater gameStateUpdater;
    private MockGameStateUpdaterFactory gsuf;

    public GameStateProcessedHandler() {
        this.gsuf = new MockGameStateUpdaterFactory();
        this.gameStateUpdater = gsuf.getGameStateUpdater();
    }

    @Override
    public void updateMatch(Match match) {
        gameStateUpdater.update(match);
    }
}
