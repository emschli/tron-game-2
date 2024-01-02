package haw.vs.view.javafx;

import edu.cads.bai5.vsp.tron.view.ITronView;
import haw.vs.view.api.IPlayerInputHandler;
import haw.vs.view.api.IViewFacade;
import haw.vs.view.api.ViewFactory;
import haw.vs.view.overlay.Looser;
import haw.vs.view.overlay.MainMenu;
import haw.vs.view.overlay.MainMenuNew;
import haw.vs.view.overlay.Winner;

import java.io.IOException;

public class TronView extends edu.cads.bai5.vsp.tron.view.TronView implements ITronView {
    public static ITronView tronView;
    public static IPlayerInputHandler inputHandler;
    public static  IViewFacade viewFacade;
    public final static String VIEW_CONFIG_FILE = "view_custom.properties";

    public static MainMenu mainMenu;
    private TronView(String configFile) throws IOException {
        this.inputHandler = ViewFactory.getInputHandler();
        this.viewFacade = ViewFactory.getView();
        //super(); ??
        tronView = new edu.cads.bai5.vsp.tron.view.TronView(configFile);

        // Build and register main menu to put player count in the form
        MainMenuNew mainMenu = new MainMenuNew("menu.css", tronView);
        tronView.registerOverlay("main", mainMenu);

        // Build and register winner menu to put player count in the form
        Winner winnerMenu = new Winner("menu.css", tronView);
        tronView.registerOverlay("main", winnerMenu);

        // Build and register looser menu to put player count in the form
        Looser looserMenu = new Looser("menu.css", tronView);
        tronView.registerOverlay("main", looserMenu);



     //   tronView.registerOverlay();
    //    tronView.getScene().setOnKeyPressed((event) -> {
    //        inputHandler.onKeyPressed();
    //    });

    }


    public static ITronView getInstance() throws IOException {
        if(tronView == null){
            tronView = new TronView(VIEW_CONFIG_FILE);
        }
        return tronView;
    }

}
