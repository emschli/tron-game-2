package haw.vs.view.api;

import edu.cads.bai5.vsp.tron.view.Coordinate;
import edu.cads.bai5.vsp.tron.view.ITronView;
import javafx.scene.paint.Color;

import java.util.LinkedList;
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
        //TODO Liste von Integers???

        List<Coordinate> coordinates = new LinkedList<>();
   //TODO klappt das?
    Color bikeColor = Color.valueOf(color);
    tronView.draw(coordinates, bikeColor);
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
//TODO testen
        Coordinate coordinate = new Coordinate(x,y);
        tronView.highlightCell(coordinate);
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
