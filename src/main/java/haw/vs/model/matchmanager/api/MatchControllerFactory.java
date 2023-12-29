package haw.vs.model.matchmanager.api;

import haw.vs.common.properties.ComponentType;
import haw.vs.common.properties.PropertiesException;
import haw.vs.common.properties.PropertiesHelper;
import haw.vs.model.matchmanager.MatchManager;
import haw.vs.model.matchmanager.mock.MockMatchController;
import haw.vs.model.matchmanager.state.Matches;

public class MatchControllerFactory {
    public static IMatchController getMatchController() throws PropertiesException {
        if (PropertiesHelper.isTest(ComponentType.MATCH_MANAGER)) {
            return new MockMatchController();
        }
        switch (PropertiesHelper.getAppType()) {
            case STANDALONE -> {
                return new MatchManager(Matches.getInstance());
            }
            default -> {
                return new MockMatchController();
            }
        }

    }
}
