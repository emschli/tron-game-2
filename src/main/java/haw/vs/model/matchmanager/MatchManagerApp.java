package haw.vs.model.matchmanager;

import haw.vs.common.properties.PropertiesException;
import haw.vs.common.properties.PropertiesHelper;
import haw.vs.model.matchmanager.state.Matches;
import haw.vs.model.matchmanager.tick.TickHandlerFactory;
import haw.vs.model.matchmanager.tick.TickThread;
import haw.vs.model.matchmanager.viewupdate.GameUpdateThread;
import haw.vs.model.matchmanager.viewupdate.MatchUpdateHandlerFactory;
import haw.vs.model.matchmanager.viewupdate.MenuUpdateThread;

public class MatchManagerApp {
    public void startApp() throws PropertiesException {
        switch (PropertiesHelper.getAppType()) {
            case STANDALONE ->  startStandalone();
            case DISTRIBUTED -> startDistributed();
        }
    }

    private void startStandalone() {
        MenuUpdateThread menuUpdateThread = new MenuUpdateThread(Matches.getInstance(), MatchUpdateHandlerFactory.getMatchUpdateHandler());
        Thread menuUpdateThreadThread = new Thread(menuUpdateThread, "MenuUpdateThread");

        GameUpdateThread gameUpdateThread = new GameUpdateThread(MatchUpdateHandlerFactory.getMatchUpdateHandler(), Matches.getInstance());
        Thread gameUpdateThreadThread = new Thread(gameUpdateThread, "GameUpdateThread");

        TickThread tickThread = new TickThread(TickHandlerFactory.getTickHandler(), gameUpdateThreadThread, Matches.getInstance());
        Thread tickThreadThread = new Thread(tickThread, "TickThread");

        menuUpdateThreadThread.start();
        gameUpdateThreadThread.start();
        tickThreadThread.start();
    }

    private void startDistributed() throws PropertiesException {

    }
}
