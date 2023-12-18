package haw.vs.model.matchmanager.api;

import haw.vs.model.matchmanager.mock.MockMatchController;

public class MatchControllerFactory {
    public static IMatchController getMatchController() {
        return new MockMatchController();
    }
}
