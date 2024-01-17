package haw.vs.view.javafx;

import haw.vs.common.Coordinate;
import haw.vs.view.api.IView;
import javafx.scene.paint.Color;

import java.util.List;

/**
 * Unser Adapter, der die Methoden des Interfaces ITronView benutzt um unsere Methoden umzusetzen
 */
public class TronViewAdapter implements IView {

    private final ITronView tronView;

    public TronViewAdapter(){
        this.tronView = TronView.getInstance();
    }

    @Override
    public void clear() {
        tronView.clear();
    }

    @Override
    public void draw(List<Coordinate> bike, String color) {
        //convert String to Color
        Color bikeColor = Color.valueOf(color);
        //draw the bikes:
        tronView.draw(bike, bikeColor);
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
    }

}
