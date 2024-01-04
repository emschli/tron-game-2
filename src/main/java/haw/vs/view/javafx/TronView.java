package haw.vs.view.javafx;

import edu.cads.bai5.vsp.tron.view.ITronView;
import haw.vs.view.api.IPlayerInputHandler;
import haw.vs.view.api.IViewFacade;
import haw.vs.view.api.ViewFactory;
import haw.vs.view.overlay.MainMenu;

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





/**
        tronView.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                System.out.println("huhu");
                inputHandler.onKeyPressed(keyEvent.getText());
            }
        });
*/

    }


    public static ITronView getInstance() throws IOException {
        if(tronView == null){
            tronView = new TronView(VIEW_CONFIG_FILE);
        }
        return tronView;
    }

}
