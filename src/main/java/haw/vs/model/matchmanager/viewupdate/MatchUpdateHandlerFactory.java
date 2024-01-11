package haw.vs.model.matchmanager.viewupdate;

import haw.vs.common.properties.ComponentType;
import haw.vs.controller.api.GameViewUpdateFactory;
import haw.vs.model.matchmanager.state.Matches;

public class MatchUpdateHandlerFactory {
    public static IMatchUpdateHandler getMatchUpdateHandler() {
        return new MatchUpdateHandler(GameViewUpdateFactory.getGameViewUpdate(ComponentType.MATCH_MANAGER), Matches.getInstance());
    }
}
