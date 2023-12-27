package haw.vs.view.api;

import edu.cads.bai5.vsp.tron.view.ITronView;

import java.util.List;

/**
 * Unser Adapter, der die Methoden des Interfaces ITronView benutzt um unsere Methoden umzusetzen
 */
public class TronViewAdapter implements IView{

    private ITronView tronView;

    @Override
    public void clear() {
        //TODO
        tronView.clear();
    }

    @Override
    public void draw(List<Integer> bike, String color) {
//TODO
    }

    @Override
    public void showOverlay(String name) {
//TODO
    }

    @Override
    public void hideOverlay() {
//TODO
    }

    @Override
    public void highlightCell(int x, int y) {
//TODO
    }

    @Override
    public void setWindowSize(int width, int height) {
//TODO
    }

    @Override
    public void setGridSize(int columns, int rows) {
//TODO
    }
}
