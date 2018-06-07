package javafxapplication11;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class Room { 

    static ArrayList rooms = new ArrayList<Room>();
    private final int ROOM_W = 900;
    private final int ROOM_H = 550;

    static final int SCENE_W = 900;
    static final int SCENE_H = 700;

    private final int WALL_W = 20;

    private final int DOOR_W = 10;
    private final int DOOR_H = 150;

    private final int PLAYER_W = 40;
    private final int PLAYER_H = 40;

    private final int HEADER_H = 50;
    private final int FOOTER_H = 100;

    int spawnX;
    int spawnY;
    Color wallsColor;
    Color doorColor;
    private Image image = null;
    private ImagePattern ip;

    Group walls;
    Group doors;
    Group floor;
    Group roomObjects;
    Group interactables;
    Pane root;
    Scene scene;

    private Timeline timeline = new Timeline();
    public Character player;

    public Room() {
        this.root = new Pane();
    }

    public void createWalls() {
    }

    public void createDoors() {
    }

    public void fillRoom() {

    }

    public void createInteractables() {

    }

    public void spawnPlayer() {
        player.setTranslateX(spawnX);
        player.setTranslateY(spawnY);
        root.getChildren().add(player);
    }

    public void spawnPlayer(int x, int y) {
        player.setTranslateX(x);
        player.setTranslateY(y);
        root.getChildren().add(player);
    }

    public int getDOOR_W() {
        return DOOR_W;
    }

    public int getDOOR_H() {
        return DOOR_H;
    }

    public int getPLAYER_H() {
        return PLAYER_H;
    }

    public int getPLAYER_W() {
        return PLAYER_W;
    }

    public static int getSCENE_H() {
        return SCENE_H;
    }

    public static int getSCENE_W() {
        return SCENE_W;
    }

    public int getWALL_W() {
        return WALL_W;
    }

    public int getROOM_W() {
        return ROOM_W;
    }

    public int getROOM_H() {
        return ROOM_H;
    }

    public int getHEADER_H() {
        return HEADER_H;
    }

    public int getSpawnX() {
        return spawnX;
    }

    public int getSpawnY() {
        return spawnY;
    }

    public void setSpawnX(int X) {
        spawnX = X;
    }

    public void setSpawnY(int Y) { 
        spawnY = Y;
    }

    public Character getPlayer() {
        return player;
    }

    public Scene getScene() {
        return scene;
    }

    public void setPlayer(Character player) {
        this.player = player;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setKeyHandlers() {

        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case LEFT:
                    try {
                        image = new Image(new FileInputStream("src/Sprites/Player_left.png"), 100, 100, true, true);
                    } catch (IOException f) {
                    }
                    ip = new ImagePattern(image);
                    player.setFill(ip);
                    player.setAction(characterAction.LEFT);
                    break;
                case RIGHT:
                    try {
                        image = new Image(new FileInputStream("src/Sprites/Player_right.png"), 100, 100, true, true);
                    } catch (IOException f) {
                    }
                    ip = new ImagePattern(image);
                    player.setFill(ip);
                    player.setAction(characterAction.RIGHT);
                    break;
                case UP:
                    try {
                        image = new Image(new FileInputStream("src/Sprites/Player_up.png"), 100, 100, true, true);
                    } catch (IOException f) {
                    }
                    ip = new ImagePattern(image);
                    player.setFill(ip);
                    player.setAction(characterAction.UP);
                    break;
                case DOWN:
                    try {
                        image = new Image(new FileInputStream("src/Sprites/Player_down.png"), 100, 100, true, true);
                    } catch (IOException f) {
                    }
                    ip = new ImagePattern(image);
                    player.setFill(ip);
                    player.setAction(characterAction.DOWN);
                    break;
                default:
            }
        });

        scene.setOnKeyReleased(e -> {
            player.setAction(characterAction.NONE);
        });
    }

    public void start(Character player) {
        this.player = player;
        spawnPlayer();
        timeline.play();
    }

    public void stop() {
        timeline.stop();
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }
}