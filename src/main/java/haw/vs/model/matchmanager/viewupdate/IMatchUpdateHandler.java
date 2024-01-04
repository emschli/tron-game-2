package haw.vs.model.matchmanager.viewupdate;

import haw.vs.model.common.Match;
import haw.vs.model.matchmanager.state.MenuEvent;

public interface IMatchUpdateHandler {
    void updateView(Match match);
    void updateView(MenuEvent event);
}
