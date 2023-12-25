package haw.vs.view.api;


import java.util.List;

public interface IView {

    public void clear();

    public void draw(List<Integer> bike,String color);

    public void showOverlay(String name);

    public void hideOverlay();

    public void highlightCell(int x, int y);

    public void setWindowSize(int width, int height);

    public void setGridSize(int columns, int rows);
}

