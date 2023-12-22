package haw.vs.model.matchmanager.viewupdate;

import haw.vs.model.matchmanager.state.Matches;
import haw.vs.model.matchmanager.state.MenuEvent;

public class MenuUpdateThread implements Runnable {
    private final Matches matches;
    private final IMatchUpdateHandler matchUpdateHandler;

    public MenuUpdateThread(Matches matches, IMatchUpdateHandler matchUpdateHandler) {
        this.matches = matches;
        this.matchUpdateHandler = matchUpdateHandler;
    }

    @Override
    public void run() {
        while (true) {
            try {
                MenuEvent event = matches.getNextMenuEvent();
                matchUpdateHandler.updateView(event);
            } catch (InterruptedException e) {

            }
        }
    }
}
