package haw.vs.view.javafx;

import edu.cads.bai5.vsp.tron.view.ITronView;

import java.io.IOException;

public abstract class TronView implements ITronView {
    //TODO Singleton Umsetzung korrekt?
    public ITronView getInstance() throws IOException {
        return new edu.cads.bai5.vsp.tron.view.TronView();
    }
}
