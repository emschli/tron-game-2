package haw.vs.view.javafx;

import edu.cads.bai5.vsp.tron.view.ITronView;
import haw.vs.common.Coordinate;
import haw.vs.common.GameState;
import haw.vs.view.api.IPlayerInputHandler;
import haw.vs.view.api.IViewFacade;
import haw.vs.view.api.ViewFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TronView extends edu.cads.bai5.vsp.tron.view.TronView implements ITronView {
    public static ITronView tronView;
    public static IPlayerInputHandler inputHandler;
    public static  IViewFacade viewFacade;
    public final static String VIEW_CONFIG_FILE = "view_custom.properties";

    private TronView(String configFile) throws IOException {
        this.inputHandler = ViewFactory.getInputHandler();
        this.viewFacade = ViewFactory.getView();
        //super(); ??
        tronView = new edu.cads.bai5.vsp.tron.view.TronView(configFile);




    }


    public static ITronView getInstance() throws IOException {
        if(tronView == null){
            tronView = new TronView(VIEW_CONFIG_FILE);
        }
        return tronView;
    }

}
