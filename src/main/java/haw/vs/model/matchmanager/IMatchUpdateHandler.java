package haw.vs.model.matchmanager;

import haw.vs.model.common.Match;

public interface IMatchUpdateHandler {
    void updateView(Match match);
    void updateView(MenuEvent event);
}
