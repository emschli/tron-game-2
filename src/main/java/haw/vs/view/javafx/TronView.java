package haw.vs.view.javafx;

import edu.cads.bai5.vsp.tron.view.ITronView;

import java.io.IOException;

public class TronView extends edu.cads.bai5.vsp.tron.view.TronView implements ITronView {
    //TODO Singleton Umsetzung korrekt?
    public static TronView tronView;
//TODO in Klassendiagramm updaten Konstruktor:
    private TronView() throws IOException {
        //super(); ??
        tronView = new TronView();
    }

    public static ITronView getInstance() throws IOException {
        if(tronView == null){
            tronView = new TronView();
        }
        return tronView;
    }
}
