package javafxapplication11;

import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Room8 extends Room {

    private ArrayList<Node> obj = new ArrayList<>();
    private KeyFrame frame = new KeyFrame(Duration.seconds(0.016), e -> {
        getPlayer().update(obj);

        if (getPlayer().isColliding(doors.getChildren().get(0))) {
            CPTRewrite.prevRoom();
        } else if (getPlayer().isColliding(doors.getChildren().get(1))) {
            CPTRewrite.nextRoom();
        }
    });

    public Room8() {
        super();

        wallsColor = Color.DARKRED;
        doorColor = Color.BISQUE;

        getTimeline().getKeyFrames().add(frame);
        getTimeline().setCycleCount(Timeline.INDEFINITE);

        createDoors();
        createWalls();
        fillRoom();

        for (int i = 0; i < roomObjects.getChildren().size(); i++) {
            obj.add(roomObjects.getChildren().get(i));
        }

        for (int i = 0; i < walls.getChildren().size(); i++) {
            obj.add(walls.getChildren().get(i));
        }

        int enterX = (int) doors.getChildren().get(0).getTranslateX();
        int enterY = (int) doors.getChildren().get(0).getTranslateY();

        int exitX = (int) doors.getChildren().get(1).getTranslateX();
        int exitY = (int) doors.getChildren().get(1).getTranslateY();

        setEnterSpawnX(enterX + getDOOR_H() / 2 - getPLAYER_H() / 2 - 35);
        setEnterSpawnY(enterY + getDOOR_W() + 15);
        
        setExitSpawnX(exitX + getDOOR_H() / 2 - getPLAYER_H() / 2 - 35);
        setExitSpawnY(exitY - getPLAYER_H() - 35);

        enterSpawnX = getEnterSpawnX();
        enterSpawnY = getEnterSpawnY();

        exitSpawnX = getExitSpawnX();
        exitSpawnY = getExitSpawnY();

        root.getChildren().addAll(floor, walls, roomObjects, doors);

        scene = new Scene(root, getSCENE_W(), getSCENE_H());

        setKeyHandlers();
    }

    @Override
    public void createWalls() {
        walls = new Group();

        Rectangle doorEnter = (Rectangle) doors.getChildren().get(0);
        Rectangle doorExit = (Rectangle) doors.getChildren().get(1);
        Rectangle rect;

        // top wall left of door
        rect = new Rectangle(doorEnter.getTranslateX(), getWALL_W(), wallsColor);
        rect.setTranslateX(0);
        rect.setTranslateY(getHEADER_H());

        walls.getChildren().add(rect);

        // top wall right of door
        rect = new Rectangle(getROOM_W() - doorEnter.getTranslateX() - getDOOR_H(), getWALL_W(), wallsColor);
        rect.setTranslateX(doorEnter.getTranslateX() + getDOOR_H());
        rect.setTranslateY(getHEADER_H());

        walls.getChildren().add(rect);

        // bottom wall left of door
        rect = new Rectangle(doorExit.getTranslateX(), getWALL_W(), wallsColor);
        rect.setTranslateX(0);
        rect.setTranslateY(getHEADER_H() + getROOM_H() - getWALL_W());

        walls.getChildren().add(rect);

        // bottom wall right of door
        rect = new Rectangle(getROOM_W() - doorExit.getTranslateX() - getDOOR_H(), getWALL_W(), wallsColor);
        rect.setTranslateX(doorExit.getTranslateX() + getDOOR_H());
        rect.setTranslateY(getHEADER_H() + getROOM_H() - getWALL_W());

        walls.getChildren().add(rect);

        //left wall 
        rect = new Rectangle(getWALL_W(), getROOM_H(), wallsColor);
        rect.setTranslateX(0);
        rect.setTranslateY(getHEADER_H());
        walls.getChildren().add(rect);

        //right wall 
        rect = new Rectangle(getWALL_W(), getROOM_H(), wallsColor);
        rect.setTranslateX(getROOM_W() - getWALL_W());
        rect.setTranslateY(getHEADER_H());
        walls.getChildren().add(rect);

        //bathroom bottom wall
        rect = new Rectangle(265, getWALL_W(), wallsColor);
        rect.setTranslateX(110);
        rect.setTranslateY(250);
        walls.getChildren().add(rect);

        //bathroom side wall
        rect = new Rectangle(getWALL_W(), 200, wallsColor);
        rect.setTranslateX(355);
        rect.setTranslateY(50);
        walls.getChildren().add(rect);

        //bedroom side wall
        rect = new Rectangle(getWALL_W(), 240, wallsColor);
        rect.setTranslateX(355);
        rect.setTranslateY(360);
        walls.getChildren().add(rect);

        floor = new Group();
        Rectangle bg = new Rectangle(0, 50, 900, 550);
        FloorMat mat = new FloorMat(410, 500, 75, 75);
        bg.setFill(Color.KHAKI);

        floor.getChildren().addAll(bg, mat);

        //bathroom floor
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 4; j++) {
                Floor tile = new Floor(305 - i * 50, 70 + j * 50, 50, 50, "bathroomGrey");
                floor.getChildren().add(tile);
            }
        }

        //bedroom floor
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                Floor tile = new Floor(258 - i * 100, 265 + j * 39, 120, 50, "bedroomWood");
                floor.getChildren().add(tile);
            }
        }

//        for (int i = 0; i < 12; i++) {
//            for (int j = 0; j < 12; j++) {
//                Floor tile = new Floor(374 + i * 39, 70 + j * 39, 40, 40, "whiteTile");
//                floor.getChildren().add(tile);
//            }
//        }

//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < 8; j++) {
//                Floor tile = new Floor(366 + i * 100, 70 + j * 39, 120, 50, "bedroomWood");
//                floor.getChildren().add(tile);
//            }
//        }


    }

    @Override
    public void createDoors() {
        doors = new Group();

        Rectangle door;

        // door enter (0)
        door = new Rectangle(getDOOR_H(), getDOOR_W(), doorColor);
        door.setTranslateX(getROOM_W() - getDOOR_H() - 100);
        door.setTranslateY(getHEADER_H());

        doors.getChildren().add(door);

        // door exit (1)
        door = new Rectangle(getDOOR_H(), getDOOR_W(), doorColor);
        door.setTranslateX(getROOM_W() / 2 - getDOOR_H() / 2);
        door.setTranslateY(getHEADER_H() + getROOM_H() - getDOOR_W());

        doors.getChildren().add(door);
    }

    @Override
    public void fillRoom() {
        roomObjects = new Group();

        Bathroom sink = new Bathroom(185, 40, 115, 70, "sink");
        Bathroom shower = new Bathroom(9, 0, 120, 120, "shower");
        Bathroom toilet = new Bathroom(295, 160, 60, 75, "toilet");
        Bathroom bathtub = new Bathroom(300, 50, 58, 110, "bathtub");

        Bedroom bedsideH = new Bedroom(120, 255, 30, 45, "bedsideH");
        Bedroom bedsideV = new Bedroom(250, 255, 30, 45, "bedsideV");
        Bedroom bed = new Bedroom(150, 240, 100, 140, "bedPink");

        int x = 107, y = 445;
        Bedroom armchairL = new Bedroom(x, y, 60, 70, "armchairL");
        Bedroom armchairR = new Bedroom(x + 120, y, 60, 70, "armchairR");
        Bedroom blackStool = new Bedroom(x + 65, y + 15, 50, 60, "blackstool");

        roomObjects.getChildren().addAll(sink, shower, toilet, bathtub, bed, bedsideH, bedsideV, blackStool, armchairL, armchairR);
    }

}
