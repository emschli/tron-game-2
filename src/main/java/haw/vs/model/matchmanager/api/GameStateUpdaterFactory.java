package haw.vs.model.matchmanager.api;

import haw.vs.common.properties.PropertiesException;
import haw.vs.common.properties.PropertiesHelper;
import haw.vs.model.matchmanager.GameStateUpdater;
import haw.vs.model.matchmanager.mock.MockGameStateUpdater;
import haw.vs.model.matchmanager.state.Matches;

public class GameStateUpdaterFactory {
    public static IGameStateUpdater getGameStateUpdater() throws PropertiesException {
        switch (PropertiesHelper.getAppType()) {
            case STANDALONE -> {
                return new GameStateUpdater(Matches.getInstance());
            }
            default -> {
                return new MockGameStateUpdater();
            }
        }
    }
}
