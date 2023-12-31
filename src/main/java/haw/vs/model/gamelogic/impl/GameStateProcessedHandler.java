package haw.vs.model.gamelogic.impl;

import haw.vs.model.common.Match;
import haw.vs.model.matchmanager.api.IGameStateUpdater;

public class GameStateProcessedHandler implements IGameStateProcessedHandler {

    private IGameStateUpdater gameStateUpdater;

    public GameStateProcessedHandler(IGameStateUpdater gameStateUpdater) {
        this.gameStateUpdater = gameStateUpdater;
    }

    @Override
    public void updateMatch(Match match) {
        gameStateUpdater.update(match);
    }
}
