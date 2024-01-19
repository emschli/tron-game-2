package haw.vs.model.gamelogic.impl.mock;

import haw.vs.model.common.Match;
import haw.vs.model.matchmanager.api.IGameStateUpdater;

public class MockGameStateUpdater implements IGameStateUpdater {

    @Override
    public void updateMatchManager(Match match) {
        System.err.println("updateMatch mit " + match.getMatchId() + " auf MockGameStateUpdater aufgerufen.");
    }
}
