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

public class Room7 extends Room {

    private ArrayList<Node> obj = new ArrayList<>();
    private KeyFrame frame = new KeyFrame(Duration.seconds(0.016), e -> {
        getPlayer().update(obj);

        //displayInv();
        if (getPlayer().isColliding(doors.getChildren().get(0))) {
            root.getChildren().remove(darkness);
            CPTRewrite.prevRoom();
        } else if (getPlayer().isColliding(doors.getChildren().get(1))) {
            root.getChildren().remove(darkness);
            CPTRewrite.nextRoom();
        }
    });

    public Room7() {
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

        setEnterSpawnX(enterX + getDOOR_W() - 15);
        setEnterSpawnY(enterY + getDOOR_H() / 2 - getPLAYER_H() / 2);

        setExitSpawnX(exitX + getDOOR_H() / 2 - getPLAYER_H() / 2 - 35);
        setExitSpawnY(exitY - getPLAYER_H() - 35);

        enterSpawnX = getEnterSpawnX();
        enterSpawnY = getEnterSpawnY();

        exitSpawnX = getExitSpawnX();
        exitSpawnY = getExitSpawnY();

        root.getChildren().addAll(floor, walls, doors, roomObjects, inv);

        scene = new Scene(root, getSCENE_W(), getSCENE_H());

        setKeyHandlers();
    }

    @Override
    public void createWalls() {
        walls = new Group();

        Rectangle doorEnter = (Rectangle) doors.getChildren().get(0);
        Rectangle doorExit = (Rectangle) doors.getChildren().get(1);
        Rectangle rect;

        // top wall
        rect = new Rectangle(getROOM_W(), getWALL_W(), wallsColor);
        rect.setTranslateX(0);
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

        // left wall above door
        rect = new Rectangle(getWALL_W(), doorEnter.getTranslateY() - getHEADER_H(), wallsColor);
        rect.setTranslateX(0);
        rect.setTranslateY(getHEADER_H());

        walls.getChildren().add(rect);

        // left wall under door
        rect = new Rectangle(getWALL_W(), getROOM_H() + getHEADER_H() - getDOOR_H() - doorEnter.getTranslateY(), wallsColor);
        rect.setTranslateX(0);
        rect.setTranslateY(doorEnter.getTranslateY() + getDOOR_H());

        walls.getChildren().add(rect);

        //right wall 
        rect = new Rectangle(getWALL_W(), getROOM_H(), wallsColor);
        rect.setTranslateX(getROOM_W() - getWALL_W());
        rect.setTranslateY(getHEADER_H());

        walls.getChildren().add(rect);

        floor = new Group();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 14; j++) {
                Floor tile = new Floor(i * 100, j * 39 + 48, 120, 50, "bedroomWood");
                floor.getChildren().add(tile);
            }
        };
        FloorMat mat = new FloorMat(110, 170, 75, 75);
        Bedroom rug = new Bedroom(120, 420, 100, 80, "orangerug");

        Bedroom rug2 = new Bedroom(500, 425, 90, 70, "purplerug");

        floor.getChildren().addAll(mat, rug, rug2);

        //dividing walls
        Rectangle div1 = new Rectangle(20, 330, 790, 20);
        div1.setFill(wallsColor);
        Rectangle div2 = new Rectangle(400, 130, 20, 200);
        div2.setFill(wallsColor);
        Rectangle div3 = new Rectangle(320, 330, 20, 190);
        div3.setFill(wallsColor);

        walls.getChildren().addAll(div1, div2, div3);
    }

    @Override
    public void createDoors() {
        doors = new Group();

        Rectangle door;

        // door exit (0)
        door = new Rectangle(getDOOR_W(), getDOOR_H(), doorColor);
        door.setTranslateX(0);
        door.setTranslateY(getROOM_H() + getHEADER_H() - getDOOR_H() - 100);

        doors.getChildren().add(door);

        // door enter (1)
        door = new Rectangle(getDOOR_H(), getDOOR_W(), doorColor);
        door.setTranslateX(getROOM_W() - getDOOR_H() - 100);
        door.setTranslateY(getHEADER_H() + getROOM_H() - getDOOR_W());

        doors.getChildren().add(door);
    }

    @Override
    public void fillRoom() {
        roomObjects = new Group();

        Bedroom bed = new Bedroom(595, 35, 125, 125, "doublebed");
        Bedroom bed2 = new Bedroom(80, 35, 125, 125, "doublebed");
        Bedroom couchL = new Bedroom(420, 230, 60, 100, "leftcouch");
        Bedroom couchR = new Bedroom(340, 230, 60, 100, "rightcouch");
        Bedroom tableWBook = new Bedroom(25, 55, 60, 60, "bookontable");
        Bedroom emptyTable = new Bedroom(205, 55, 60, 60, "bedsidetable");
        Bedroom cabinet = new Bedroom(780, 10, 100, 100, "bedcabinet");
        Bedroom bookTable = new Bedroom(545, 55, 60, 60, "bookontable");
        Table aTable = new Table(275, 255, 70, 70, "prettyTable");
        Table aTable2 = new Table(475, 255, 70, 70, "prettyTable");
        Kitchen plant = new Kitchen(20, 528, 50, 60, "plant");
        Kitchen plant2 = new Kitchen(50, 528, 50, 60, "plant");
        Bedroom emptyTable2 = new Bedroom(254, 336, 60, 60, "bedsidetable");
        Bedroom couchL2 = new Bedroom(420, 410, 60, 100, "leftcouch");
        Bedroom couchR2 = new Bedroom(600, 410, 60, 100, "rightcouch");
        Kitchen plant3 = new Kitchen(830, 528, 50, 60, "plant");
        Kitchen plant4 = new Kitchen(800, 528, 50, 60, "plant");

        roomObjects.getChildren().addAll(bed, bed2, couchR, couchL, tableWBook, emptyTable, cabinet, bookTable, aTable, aTable2, plant, plant2, emptyTable2, couchL2, couchR2, plant3, plant4);
    }

    public void displayInv() {
        for (int i = 0; i < player.getInteractables().size(); i++) {
            Rectangle rect = new Rectangle(20 + i * 80, 620, 70, 70);
            inv.getChildren().add(rect);
            if (player.getInteractables().get(i).getName().equals("battery")) {
                Battery battery = new Battery(25 + i * 80, 640, 60, 30);
                inv.getChildren().add(battery);
            }
            if (player.getInteractables().get(i).getName().equals("crowbar")) {
                Crowbar crowbar = new Crowbar(25 + i * 80, 640, 65, 35);
                inv.getChildren().add(crowbar);
            }
            if (player.getInteractables().get(i).getName().equals("flashlight")) {
                Flashlight flashlight = new Flashlight(45 + i * 80, 640, 20, 40, false);
                inv.getChildren().add(flashlight);
            }
            if (player.getInteractables().get(i).getName().equals("key")) {
                Key key = new Key(45 + i * 80, 640, 20, 40);
                inv.getChildren().add(key);
            }
        }
    }
}
