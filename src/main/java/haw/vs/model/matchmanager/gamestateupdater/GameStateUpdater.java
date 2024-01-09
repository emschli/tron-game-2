package haw.vs.model.matchmanager.gamestateupdater;

import haw.vs.model.common.Match;
import haw.vs.model.matchmanager.api.IGameStateUpdater;
import haw.vs.model.matchmanager.state.Matches;

public class GameStateUpdater implements IGameStateUpdater {
    private final Matches matches;

    public GameStateUpdater(Matches matches) {
        this.matches = matches;
    }

    @Override
    public void update(Match match) {
        matches.updateMatch(match);
    }
}
