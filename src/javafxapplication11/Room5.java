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

public class Room5 extends Room {

    private ArrayList<Node> obj = new ArrayList<>();
    private KeyFrame frame = new KeyFrame(Duration.seconds(0.016), e -> {
        getPlayer().update(obj);
        
        displayInv();

        if (getPlayer().isColliding(doors.getChildren().get(0))) {
            CPTRewrite.prevRoom();
        } else if (getPlayer().isColliding(doors.getChildren().get(1))) {
            CPTRewrite.nextRoom();
        }
    });

    public Room5() {
        super();

        wallsColor = Color.DARKRED;
        doorColor = Color.BISQUE;

        getTimeline().getKeyFrames().add(frame);
        getTimeline().setCycleCount(Timeline.INDEFINITE);

        createDoors();
        createWalls();
        fillRoom();
        createInteractables();

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
        setEnterSpawnY(enterY - getPLAYER_H() - 35);

        setExitSpawnX(exitX - getDOOR_W() - getPLAYER_W() - 35);
        setExitSpawnY(exitY + getDOOR_H() / 2 - getPLAYER_H() / 2);

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
        rect = new Rectangle(doorEnter.getTranslateX(), getWALL_W(), wallsColor);
        rect.setTranslateX(0);
        rect.setTranslateY(getHEADER_H() + getROOM_H() - getWALL_W());

        walls.getChildren().add(rect);

        // bottom wall right of door
        rect = new Rectangle(getROOM_W() - doorEnter.getTranslateX() - getDOOR_H(), getWALL_W(), wallsColor);
        rect.setTranslateX(doorEnter.getTranslateX() + getDOOR_H());
        rect.setTranslateY(getHEADER_H() + getROOM_H() - getWALL_W());

        walls.getChildren().add(rect);

        //left wall 
        rect = new Rectangle(getWALL_W(), getROOM_H(), wallsColor);
        rect.setTranslateX(0);
        rect.setTranslateY(getHEADER_H());

        walls.getChildren().add(rect);

        // right wall above door
        rect = new Rectangle(getWALL_W(), doorExit.getTranslateY() - getHEADER_H(), wallsColor); // to find how long the vertical wall has to be, make it the length of the x coordinate of the door
        rect.setTranslateX(getROOM_W() - getWALL_W());
        rect.setTranslateY(getHEADER_H());

        walls.getChildren().add(rect);

        // right wall under door
        rect = new Rectangle(getWALL_W(), getROOM_H() + getHEADER_H() - doorExit.getTranslateY() - getDOOR_H(), wallsColor);
        rect.setTranslateX(getROOM_W() - getWALL_W());
        rect.setTranslateY(doorExit.getTranslateY() + getDOOR_H());

        walls.getChildren().add(rect);

        floor = new Group();
        Rectangle bg = new Rectangle(0, 50, 900, 550);
        FloorMat mat = new FloorMat(800, 190, 75, 75);
        bg.setFill(Color.KHAKI);
        floor.getChildren().addAll(bg);

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 6; j++) {
                Floor tile = new Floor(i * 95, 43 + j * 90, 110, 107, "blueTile");
                tile.setOpacity(.6);
                floor.getChildren().add(tile);
            }
        }
        floor.getChildren().addAll(mat);
    }

    @Override
    public void createDoors() {
        doors = new Group();

        Rectangle door;

        // door enter (0)
        door = new Rectangle(getDOOR_H(), getDOOR_W(), doorColor);
        door.setTranslateX(50);
        door.setTranslateY(getHEADER_H() + getROOM_H() - getDOOR_W());

        doors.getChildren().add(door);

        // door exit (1)
        door = new Rectangle(getDOOR_W(), getDOOR_H(), doorColor);
        door.setTranslateX(getROOM_W() - 10);
        door.setTranslateY(100 + getHEADER_H());

        doors.getChildren().add(door);
    }

    @Override
    public void fillRoom() {
        roomObjects = new Group();
        ScienceDesk desk = new ScienceDesk(30, 30, 66, 66);
        ScienceDesk desk2 = new ScienceDesk(146, 30, 66, 66);
        ScienceDesk desk3 = new ScienceDesk(264, 30, 66, 66);
        HealingMachine machine = new HealingMachine(830, 50, 48, 61);
        HealingMachine machine2 = new HealingMachine(782, 50, 48, 61);
        HealingMachine machine3 = new HealingMachine(830, 510, 48, 61);
        HealingMachine machine4 = new HealingMachine(782, 510, 48, 61);

        DisplayShelves shelf = new DisplayShelves(600, 234, 40, 44, true);
        DisplayShelves shelf2 = new DisplayShelves(600, 256, 40, 44, true);
        DisplayShelves shelf3 = new DisplayShelves(600, 278, 40, 44, true);
        DisplayShelves shelf4 = new DisplayShelves(600, 300, 40, 44, true);
        DisplayShelves shelf5 = new DisplayShelves(600, 322, 40, 44, true);
        DisplayShelves shelf6 = new DisplayShelves(600, 344, 40, 44, true);
        DisplayShelves shelf7 = new DisplayShelves(600, 366, 40, 44, true);
        DisplayShelves shelf8 = new DisplayShelves(400, 234, 40, 44, true);
        DisplayShelves shelf9 = new DisplayShelves(400, 256, 40, 44, true);
        DisplayShelves shelf10 = new DisplayShelves(400, 278, 40, 44, true);
        DisplayShelves shelf11 = new DisplayShelves(400, 300, 40, 44, true);
        DisplayShelves shelf12 = new DisplayShelves(400, 322, 40, 44, true);
        DisplayShelves shelf13 = new DisplayShelves(400, 344, 40, 44, true);
        DisplayShelves shelf14 = new DisplayShelves(400, 366, 40, 44, true);
        DisplayShelves shelf15 = new DisplayShelves(200, 234, 40, 44, true);
        DisplayShelves shelf16 = new DisplayShelves(200, 256, 40, 44, true);
        DisplayShelves shelf17 = new DisplayShelves(200, 278, 40, 44, true);
        DisplayShelves shelf18 = new DisplayShelves(200, 300, 40, 44, true);
        DisplayShelves shelf19 = new DisplayShelves(200, 322, 40, 44, true);
        DisplayShelves shelf20 = new DisplayShelves(200, 344, 40, 44, true);
        DisplayShelves shelf21 = new DisplayShelves(200, 366, 40, 44, true);
        TrashCan trash = new TrashCan(100, 60, 20, 25);
        TrashCan trash2 = new TrashCan(216, 60, 20, 25);
        TrashCan trash3 = new TrashCan(334, 60, 20, 25);
        DeskChair chair = new DeskChair(45, 90, 25, 30);
        DeskChair chair2 = new DeskChair(161, 90, 25, 30);
        DeskChair chair3 = new DeskChair(277, 90, 25, 30);
        Desk desk4 = new Desk(220, 490, 88, 88);
        Desk desk5 = new Desk(390, 490, 88, 88);
        Desk desk6 = new Desk(560, 490, 88, 88);
        Chair chair4 = new Chair(195, 500, 25, 30, false);
        Chair chair5 = new Chair(195, 540, 25, 30, false);
        Chair chair6 = new Chair(308, 500, 25, 30, true);
        Chair chair7 = new Chair(308, 540, 25, 30, true);
        Chair chair8 = new Chair(365, 500, 25, 30, false);
        Chair chair9 = new Chair(365, 540, 25, 30, false);
        Chair chair10 = new Chair(478, 500, 25, 30, true);
        Chair chair11 = new Chair(478, 540, 25, 30, true);
        Chair chair12 = new Chair(535, 500, 25, 30, false);
        Chair chair13 = new Chair(535, 540, 25, 30, false);
        Chair chair14 = new Chair(648, 500, 25, 30, true);
        Chair chair15 = new Chair(648, 540, 25, 30, true);

        Lab beakers = new Lab(550, 500, 150, 60, "beakers");
        Lab lockers = new Lab(600, 30, 90, 100, "lockers");
        Lab lockers1 = new Lab(690, 30, 90, 100, "lockers");
        Lab lockers2 = new Lab(510, 30, 90, 100, "lockers");

        roomObjects.getChildren().addAll(desk, desk2, desk3, shelf, shelf2, shelf3, shelf4, shelf5, shelf6, shelf7, shelf8, shelf9, shelf10, shelf11, shelf12, shelf13, shelf14, shelf15, shelf16, shelf17, shelf18, shelf19, shelf20, shelf21);
        //roomObjects.getChildren().addAll(machine, machine2, machine3, machine4, trash, trash2, trash3, 
        roomObjects.getChildren().addAll(chair, chair2, chair3, desk4, desk5, chair4, chair5, chair6, chair7, chair8, chair9, chair10, chair11);
        roomObjects.getChildren().addAll(beakers, lockers, lockers1, lockers2);

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
