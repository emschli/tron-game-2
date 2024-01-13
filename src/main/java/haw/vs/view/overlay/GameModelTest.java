package haw.vs.view.overlay;

import haw.vs.common.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class GameModelTest {
    private static Coordinate RIGHT = new Coordinate(1, 0);
    private static Coordinate LEFT = new Coordinate(-1, 0);
    private static Coordinate UP = new Coordinate(0, -1);
    private static Coordinate DOWN = new Coordinate(0, 1);

    private String pressedKey;

    //Hier wäre dann später für jeden Spieler ein Liste von Coordinates
    private Coordinate currentPosition;

    private int maxX;
    private int maxY;

    public GameModelTest(int maxX, int maxY) {
        this.maxX = maxX-1;
        this.maxY = maxY-1;

        currentPosition = new Coordinate(maxX/2, maxY/2);
    }

    public void update() {
        if (pressedKey == null) {
            return;
        }
        switch (pressedKey){
            case "LEFT":
                moveLeft();
                break;
            case "RIGHT":
                moveRight();
                break;
            case "UP":
                moveUp();
                break;
            case "DOWN":
                moveDown();
                break;
        }
    }

    private void moveRight() {
        if (currentPosition.x < maxX) {
            currentPosition = currentPosition.add(RIGHT);
        }
    }

    private void moveLeft() {
        if (currentPosition.x > 0) {
            currentPosition = currentPosition.add(LEFT);
        }
    }

    private void moveUp() {
        if (currentPosition.y > 0) {
            currentPosition = currentPosition.add(UP);
        }
    }

    private void moveDown() {
        if (currentPosition.y < maxY) {
            currentPosition = currentPosition.add(DOWN);
        }
    }

    public void onKeyPressed(String pressedKey) {
        this.pressedKey = pressedKey;
    }

    public void onKeyReleased(String releasedKey) {
        if (this.pressedKey != null && this.pressedKey.equals(releasedKey)) {
            this.pressedKey = null;
        }
    }

    public List<Coordinate> getCurrentPosition() {
        List<Coordinate> result = new ArrayList<>();
        result.add(currentPosition);
        return result;
    }
}
