package haw.vs.model.matchmanager.viewupdate;

import haw.vs.common.properties.PropertiesException;
import haw.vs.controller.api.GameViewUpdateFactory;
import haw.vs.model.matchmanager.state.Matches;

public class MatchUpdateHandlerFactory {
    public static IMatchUpdateHandler getMatchUpdateHandler() throws PropertiesException {
        return new MatchUpdateHandler(GameViewUpdateFactory.getGameViewUpdate(), Matches.getInstance());
    }
}
