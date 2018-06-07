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

public class Room4 extends Room {

    private ArrayList<Node> obj = new ArrayList<>();
    private KeyFrame frame = new KeyFrame(Duration.seconds(0.016), e -> {
        getPlayer().update(obj);

        if (getPlayer().isColliding(doors.getChildren().get(0))) {
            CPTRewrite.prevRoom();
        } else if (getPlayer().isColliding(doors.getChildren().get(1))) {
            CPTRewrite.nextRoom();
        }
    });

    public Room4() {
        super();

        getTimeline().getKeyFrames().add(frame);
        getTimeline().setCycleCount(Timeline.INDEFINITE);

        wallsColor = Color.DARKRED;
        doorColor = Color.BISQUE;

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

        setSpawnX(enterX + getDOOR_W() - 150);
        setSpawnY(enterY + getDOOR_H() / 2 - getPLAYER_H() / 2);
        
//        setSpawnX(enterX - getPLAYER_W());
//        setSpawnY(enterY + getDOOR_H() / 2 - getPLAYER_H() / 2);

        spawnX = getSpawnX();
        spawnY = getSpawnY();


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
        rect = new Rectangle(doorExit.getTranslateX(), getWALL_W(), wallsColor);
        rect.setTranslateX(0);
        rect.setTranslateY(getHEADER_H());

        walls.getChildren().add(rect);

        // top wall right of door
        rect = new Rectangle(getROOM_W() - doorExit.getTranslateX() - getDOOR_H(), getWALL_W(), wallsColor);
        rect.setTranslateX(doorExit.getTranslateX() + getDOOR_H());
        rect.setTranslateY(getHEADER_H());

        walls.getChildren().add(rect);

        // bottom wall
        rect = new Rectangle(getROOM_W(), getWALL_W(), wallsColor);
        rect.setTranslateX(0);
        rect.setTranslateY(getROOM_H() - getWALL_W() + getHEADER_H());

        walls.getChildren().add(rect);

        //left wall 
        rect = new Rectangle(getWALL_W(), getROOM_H(), wallsColor);
        rect.setTranslateX(0);
        rect.setTranslateY(getHEADER_H());

        walls.getChildren().add(rect);

        // right wall above door
        rect = new Rectangle(getWALL_W(), doorEnter.getTranslateY() - getHEADER_H(), wallsColor);
        rect.setTranslateX(getROOM_W() - getWALL_W());
        rect.setTranslateY(getHEADER_H());

        walls.getChildren().add(rect);

        // right wall under door
        rect = new Rectangle(getWALL_W(), getROOM_H() + getHEADER_H() - getDOOR_H() - doorEnter.getTranslateY(), wallsColor);
        rect.setTranslateX(getROOM_W() - getWALL_W());
        rect.setTranslateY(doorEnter.getTranslateY() + getDOOR_H());

        walls.getChildren().add(rect);

        floor = new Group();
        Rectangle bg = new Rectangle(0, 50, 900, 550);
        bg.setFill(Color.KHAKI);
        floor.getChildren().addAll(bg);
    }

    @Override
    public void createDoors() {
        doors = new Group();

        Rectangle door;

        // door enter (0)
        door = new Rectangle(getDOOR_W(), getDOOR_H(), doorColor);
        door.setTranslateX(getROOM_W() - getDOOR_W());
        door.setTranslateY(200 + getHEADER_H());

        doors.getChildren().add(door);

        // door exit (1)
        door = new Rectangle(getDOOR_H(), getDOOR_W(), doorColor);
        door.setTranslateX(50);
        door.setTranslateY(getHEADER_H());

        doors.getChildren().add(door);
    }

    @Override
    public void fillRoom() {
        roomObjects = new Group();

        DiningTable table = new DiningTable(70, 450, 145, 120);
        DiningTable table1 = new DiningTable(685, 450, 145, 120);
        DiningTable table2 = new DiningTable(378, 450, 145, 120);

        Table sideTable = new Table(10, 200, 90, 180, "sideDiningTable");

        Kitchen stoveAndPots = new Kitchen(588, 40, 75, 70, "stoveandpots");
        Kitchen microwave = new Kitchen(493, 18, 100, 101, "microwave");
        Kitchen stoveTeapot = new Kitchen(663, 24, 119, 86, "stoveteapot");
        Kitchen lightCounter = new Kitchen(338, 32, 170, 80, "lightcounter");
        Kitchen longCounter = new Kitchen(311, 35, 35, 160, "longcounter");
        Kitchen cornerCounter = new Kitchen(760, 32, 125, 130, "cornercounter");

        TrashCan trash1 = new TrashCan(290, 165, 20, 25);
        TrashCan trash2 = new TrashCan(290, 75, 20, 25);
        TrashCan trash3 = new TrashCan(290, 105, 20, 25);
        TrashCan trash4 = new TrashCan(290, 135, 20, 25);

        int x = 260;
        Kitchen couchL = new Kitchen(300, x, 60, 120, "couchL");
        Table prettyTable = new Table(405, x, 84, 120, "prettyTable");
        Kitchen couchR = new Kitchen(535, x, 60, 120, "couchR");

        Kitchen plant1 = new Kitchen(270, x+10, 30, 90, "plant");
        Kitchen plant2 = new Kitchen(260, x + 20, 30, 90, "plant");
        Kitchen plant3 = new Kitchen(270, x + 30, 30, 90, "plant");

        Kitchen plant4 = new Kitchen(595, x+10, 30, 90, "plant");
        Kitchen plant5 = new Kitchen(605, x + 20, 30, 90, "plant");
        Kitchen plant6 = new Kitchen(595, x + 30, 30, 90, "plant");

        roomObjects.getChildren().addAll(table, table1, table2, sideTable, stoveTeapot, lightCounter, longCounter, stoveAndPots, microwave, cornerCounter);
        roomObjects.getChildren().addAll(plant1, plant2, plant3, plant4, plant5, plant6, trash1, trash2, trash3, trash4, couchL, couchR, prettyTable);
    }

}
