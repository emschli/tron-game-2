package haw.vs.view.javafx;

import edu.cads.bai5.vsp.tron.view.ITronView;
import haw.vs.view.api.IPlayerInputHandler;
import haw.vs.view.api.IViewFacade;
import haw.vs.view.api.ViewFactory;

import java.io.IOException;

public class TronView extends edu.cads.bai5.vsp.tron.view.TronView implements ITronView {
    public static TronView tronView;
    private TronView() throws IOException {
        IPlayerInputHandler inputHandler = ViewFactory.getInputHandler();
        IViewFacade viewFacade = ViewFactory.getView();
        //super(); ??
        tronView = new TronView();
//        tronView.registerOverlay();
    //    tronView.getScene().setOnKeyPressed((event) -> {
    //        inputHandler.onKeyPressed();
    //    });

    }

    public static ITronView getInstance() throws IOException {
        if(tronView == null){
            tronView = new TronView();
        }
        return tronView;
    }
}
