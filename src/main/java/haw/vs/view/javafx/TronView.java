package haw.vs.view.javafx;

import edu.cads.bai5.vsp.tron.view.ITronView;
import haw.vs.view.api.IPlayerInputHandler;
import haw.vs.view.api.IViewFacade;
import haw.vs.view.api.ViewFactory;
import haw.vs.view.overlay.MainMenu;

import java.io.IOException;

public class TronView extends edu.cads.bai5.vsp.tron.view.TronView implements ITronView {
    public static ITronView tronView;
    public final static String VIEW_CONFIG_FILE = "view_custom.properties";

    public static MainMenu mainMenu;
    private TronView(String configFile) throws IOException {
        IPlayerInputHandler inputHandler = ViewFactory.getInputHandler();
        IViewFacade viewFacade = ViewFactory.getView();
        //super(); ??
        tronView = new edu.cads.bai5.vsp.tron.view.TronView(configFile);


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
