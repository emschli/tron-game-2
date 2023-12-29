package haw.vs.view.api;

import edu.cads.bai5.vsp.tron.view.Coordinate;
import edu.cads.bai5.vsp.tron.view.ITronView;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.List;

/**
 * Unser Adapter, der die Methoden des Interfaces ITronView benutzt um unsere Methoden umzusetzen
 */
public class TronViewAdapter implements IView {

    private ITronView tronView;

    @Override
    public void clear() {
        tronView.clear();
    }

    @Override
    public void draw(List<haw.vs.common.Coordinate> bike, String color) {
        List<Coordinate> coordinates = new LinkedList<>();
        Color bikeColor = Color.valueOf(color);
        tronView.draw(coordinates, bikeColor);
    }

    @Override
    public void showOverlay(String name) {
        tronView.showOverlay(name);
    }

    @Override
    public void hideOverlay() {
        tronView.hideOverlays();
    }

    @Override
    public void highlightCell(int x, int y) {
        Coordinate coordinate = new Coordinate(x, y);
        tronView.highlightCell(coordinate);
    }

    @Override
    public void setWindowSize(int width, int height) {
        tronView.getScene().getWindow().setHeight(width);
        tronView.getScene().getWindow().setHeight(height);
    }

    @Override
    public void setGridSize(int columns, int rows) {
        tronView.getScene().getWindow().setX(columns);
        tronView.getScene().getWindow().setY(rows);
        //TODO ist das wirklich das Grid?
    }
}
