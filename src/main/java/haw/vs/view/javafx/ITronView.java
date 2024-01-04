package haw.vs.view.javafx;

import edu.cads.bai5.vsp.tron.view.Coordinate;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

import java.util.List;

/**
 * A simple view interface for TRON-like games. It provides methods to draw on a canvas and to register, show and hide
 * overlays such as menus.
 *
 * @author Daniel Sarnow (daniel.sarnow@haw-hamburg.de)
 * @version 0.1
 */
public interface ITronView {
    /**
     * Returns the scene
     * @return scene
     */
    Scene getScene();

    /**
     * Initializes the view
     */
    void init();

    /**
     * Clears the view
     */
    void clear();

    /**
     * Draws a bike in the given color
     *
     * @param bike list of coordinates to cells. First element of the list is the bike, rest is the tail.
     * @param color color of the bike
     *
     * @throws IllegalArgumentException - if at least one of the coordinates is outside of the canvas, i.e. game board
     */
    void draw(List<Coordinate> bike, Color color);

    /**
     * Registers a new overlay, e.g. a menu. If a mapping for the name already exists, the old value is replaced by
     * the specified value.
     *
     * @param name name of the overlay
     * @param overlay overlay to be registered. It must be a subtype of javafx.scene.Node
     * @param <O> subtype of javafx.scene.Node
     */
    <O extends Node>
    void registerOverlay(String name, O overlay);

    /**
     * Displays the overlay associated with the specified name.
     *
     * @param name name of the overlay to be displayed
     * @throws IllegalArgumentException if name has not been registered
     */
    void showOverlay(String name);

    /**
     * Hides all Overlays
     */
    void hideOverlays();

    /**
     * Highlights a specified cell in the color red
     *
     * @param cell the cell to be highlighted
     */
    void highlightCell(Coordinate cell);
}
