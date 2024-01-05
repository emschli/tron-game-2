package haw.vs.model.gamelogic.mock;

import haw.vs.model.common.Match;
import haw.vs.model.matchmanager.api.IGameStateUpdater;

public class MockGameStateUpdater implements IGameStateUpdater {

    @Override
    public void update(Match match) {
        System.err.println("updateMatch mit " + match.getMatchId() + " auf MockGameStateUpdater aufgerufen.");
    }
}