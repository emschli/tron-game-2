package haw.vs.model.matchmanager;

import haw.vs.model.common.Match;
import haw.vs.model.matchmanager.api.IGameStateUpdater;

public class GameStateUpdater implements IGameStateUpdater {
    private Matches matches;
    @Override
    public void update(Match match) {
        matches.updateMatch(match);
    }
}
