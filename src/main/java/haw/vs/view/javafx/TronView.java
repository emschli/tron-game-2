package haw.vs.view.javafx;

import edu.cads.bai5.vsp.tron.view.Coordinate;
import edu.cads.bai5.vsp.tron.view.ITronView;
import haw.vs.view.api.IPlayerInputHandler;
import haw.vs.view.api.IViewFacade;
import haw.vs.view.api.ViewFactory;
import haw.vs.view.overlay.Looser;
import haw.vs.view.overlay.MainMenuNew;
import haw.vs.view.overlay.PlayerCountViewNew;
import haw.vs.view.overlay.Winner;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class TronView implements ITronView {
    //theirs:
    private Scene scene;
    private Canvas gameBoard;
    final int WIDTH;
    final int HEIGHT;
    final int ROWS;
    final int COLUMNS;
    private Rectangle fog;
    private StackPane base;
    private Map<String, Node> overlays;
    private Color gameBoardBackgroundColor;
    //ours:
    public static ITronView tronView;
    public static IPlayerInputHandler inputHandler;
    public static  IViewFacade viewFacade;
    public final static String VIEW_CONFIG_FILE = "view_custom.properties";

    //theirs:
    private TronView() throws IOException, NumberFormatException {
        this("view.properties", Color.BLUEVIOLET.darker().darker().darker().desaturate());
    }

    private TronView(String configFile) throws IOException, NumberFormatException {
        this(configFile, Color.BLUEVIOLET.darker().darker().darker().desaturate());
    }

    private TronView(String configFile, Color gameBoardBackgroundColor) throws IOException {
        //ours:
        this.inputHandler = ViewFactory.getInputHandler();
        this.viewFacade = ViewFactory.getView();
        //theirs:
        this.gameBoardBackgroundColor = gameBoardBackgroundColor;
        Properties prop = new Properties();
        prop.load(getFileFromResourceAsStream(configFile));

        this.WIDTH = Integer.parseInt(prop.getProperty("width"));
        this.HEIGHT = Integer.parseInt(prop.getProperty("height"));
        this.ROWS = Integer.parseInt(prop.getProperty("rows"));
        this.COLUMNS = Integer.parseInt(prop.getProperty("columns"));

        this.overlays = new HashMap<>();
        base = new StackPane();

        gameBoard = new Canvas(WIDTH,HEIGHT);
        base.getChildren().add(gameBoard);

        fog = new Rectangle(WIDTH, HEIGHT, Color.gray(0.2,0.8));
        overlays.put("fog", fog);
        base.getChildren().add(fog);

        this.scene = new Scene(base);
    }


    public static ITronView getInstance() throws IOException {
        if(tronView == null){
            tronView = new TronView(VIEW_CONFIG_FILE);

            // Build and register main menu to put player count in the form
            MainMenuNew mainMenu = new MainMenuNew("menu.css", tronView);
            tronView.registerOverlay("main", mainMenu);

            // Build and register main menu to put player count in the form
            PlayerCountViewNew playerCountView = new PlayerCountViewNew("menu.css", tronView);
            tronView.registerOverlay("playerCount", playerCountView);

            // Build and register winner menu to put player count in the form
            Winner winnerMenu = new Winner("menu.css", tronView);
            tronView.registerOverlay("winner", winnerMenu);

            // Build and register looser menu to put player count in the form
            Looser looserMenu = new Looser("menu.css", tronView);
            tronView.registerOverlay("looser", looserMenu);

            //init view
            tronView.init();

            //set event handlers for user input
            tronView.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    System.out.println("HANDLE");
                    inputHandler.onKeyPressed(event.getCode().toString());
                }
            });

        }
        return tronView;
    }

    @Override
    public Scene getScene() {
        return scene;
    }

    @Override
    public void init() {
        clear();
        hideOverlays();
    }

    @Override
    public void clear() {
        // Paint game board background
        GraphicsContext g = gameBoard.getGraphicsContext2D();
        g.setFill(gameBoardBackgroundColor);
        g.fillRect(0, 0, gameBoard.getWidth(), gameBoard.getHeight());
    }

    @Override
    public void draw(List<Coordinate> bike, Color color) {
        if(bike == null || color == null){
            throw new NullPointerException();
        }
        for(Coordinate pos : bike){
            if(pos.x < 0 || pos.x >= COLUMNS){
                throw new IllegalArgumentException("x value out of bounds: x is " + pos.x + ", but should be 0 <= x < " + COLUMNS);
            }
            if(pos.y < 0 || pos.y >= ROWS) {
                throw new IllegalArgumentException("y value out of bounds: y is " + pos.y + ", but should be 0 <= y < " + ROWS);
            }

            // paint new bike position
            GraphicsContext g = gameBoard.getGraphicsContext2D();
            g.setFill(color); //Color.PAPAYAWHIP);
            g.fillRect(pos.x*WIDTH/COLUMNS, pos.y*HEIGHT/ROWS, WIDTH/COLUMNS, HEIGHT/ROWS);
        }
    }

    @Override
    public <T extends Node> void registerOverlay(String name, T overlay) {
        if(name == null || overlay == null){
            throw new NullPointerException();
        }

        overlays.put(name, overlay);
        base.getChildren().add(overlay);
    }

    @Override
    public void showOverlay(String name) {
        if(!overlays.keySet().contains(name)){
            throw new IllegalArgumentException("An overlay mapped to " + name + " does not exist. Registered are " + overlays.keySet());
        }

        overlays.get("fog").setVisible(true);
        overlays.get(name).setVisible(true);
    }

    @Override
    public void hideOverlays() {
        for(Map.Entry<String,Node> entry : overlays.entrySet()){
            entry.getValue().setVisible(false);
        }
    }

    @Override
    public void highlightCell(Coordinate cell) {
        // highlight last bike position
        GraphicsContext g = gameBoard.getGraphicsContext2D();
        g.setFill(Color.RED.darker());
        g.fillRect(cell.x*WIDTH/COLUMNS, cell.y*HEIGHT/ROWS, WIDTH/COLUMNS, HEIGHT/ROWS);
    }

    //Gets File from resources-Folder (works from within jar as well)
    private InputStream getFileFromResourceAsStream(String fileName) throws IOException{
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IOException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }


}
