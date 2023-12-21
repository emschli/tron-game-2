package haw.vs.model.matchmanager;

public class MenuUpdateThread implements Runnable {
    private Matches matches;
    private IMatchUpdateHandler matchUpdateHandler;

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
