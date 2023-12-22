package haw.vs.model.matchmanager.api;

import haw.vs.model.matchmanager.MatchManager;
import haw.vs.model.matchmanager.mock.MockMatchController;
import haw.vs.model.matchmanager.state.Matches;

public class MatchControllerFactory {
    public static IMatchController getMatchController() {
//        return new MockMatchController();

        //TODO: make dependent on Properties-File
        return new MatchManager(Matches.getInstance());
    }
}
