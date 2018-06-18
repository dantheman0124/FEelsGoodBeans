package javafxapplication11;

import java.util.ArrayList;
import java.util.Scanner;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
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

        //displayInv();
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

        // vertical wall right of door
        rect = new Rectangle(getWALL_W(), 110, wallsColor);
        rect.setTranslateX(doorEnter.getTranslateX() + getDOOR_H());
        rect.setTranslateY(490);
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

        // vertical wall right of lockers
        rect = new Rectangle(getWALL_W(), 150, wallsColor);
        rect.setTranslateX(310);
        rect.setTranslateY(getHEADER_H() + getWALL_W());
        walls.getChildren().add(rect);

        // horizontal wall below of lockers
        rect = new Rectangle(160, getWALL_W(), wallsColor);
        rect.setTranslateX(20);
        rect.setTranslateY(doorExit.getTranslateY() + 50);
        walls.getChildren().add(rect);

        // horizontal wall bottom right of lockers
        rect = new Rectangle(120, getWALL_W(), wallsColor);
        rect.setTranslateX(280);
        rect.setTranslateY(doorExit.getTranslateY() + 50);
        walls.getChildren().add(rect);

        // horizontal wall below computers
        rect = new Rectangle(200, getWALL_W(), wallsColor);
        rect.setTranslateX(500);
        rect.setTranslateY(doorExit.getTranslateY() + 50);
        walls.getChildren().add(rect);

        // wall right of computers
        rect = new Rectangle(getWALL_W(), doorExit.getTranslateY() - getHEADER_H() - getWALL_W(), wallsColor);
        rect.setTranslateX(750);
        rect.setTranslateY(getHEADER_H() + getWALL_W());
        walls.getChildren().add(rect);

        // wall diagonal near computers
        for (int i = 0; i < 70; i++) {
            rect = new Rectangle(getWALL_W(), getWALL_W() - 10, wallsColor);
            rect.setTranslateX(750 - i);
            rect.setTranslateY(getHEADER_H() + getWALL_W() + i + 70);
            walls.getChildren().add(rect);
        }

        floor = new Group();
        Rectangle bg = new Rectangle(0, 50, 900, 550);
        FloorMat mat = new FloorMat(800, 190, 75, 75);
        bg.setFill(Color.KHAKI);            // background khaki colour
        floor.getChildren().addAll(bg);

        // tile floor
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 6; j++) {
                Floor tile = new Floor(i * 95, 43 + j * 90, 110, 107, "blueTile");
                tile.setOpacity(.6);
                floor.getChildren().add(tile);
            }
        }

        floor.getChildren().addAll(mat);    //mat
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

        // computers and chairs
        int x = 360, y = 30;
        for (int i = 0; i < 4; i++) {
            Lab desk = new Lab(x + i * 101, y, 66, 66, "scienceDesk");
            Chair chair = new Chair(x + 15 + i * 101, y + 60, 25, 30, "deskChair");
            roomObjects.getChildren().addAll(desk, chair);
        }

        x = 778;
        y = 50;
        Lab machine = new Lab(x, y, 48, 61, "healingMachine");
        Lab machine2 = new Lab(x + 48, y, 48, 61, "healingMachine");
        Lab machine3 = new Lab(x, y + 460, 48, 61, "healingMachine");
        Lab machine4 = new Lab(x + 48, y + 460, 48, 61, "healingMachine");

        // display shelves
        Lab shelf;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                shelf = new Lab(130 + i * 200, 285 + j * 22, 40, 44, "displayShelf");
                roomObjects.getChildren().addAll(shelf);
            }
        }

        // table and chairs at the bottom
        for (int j = 260; j < 451; j += 170) {
            for (int i = 0; i < 2; i++) {
                Chair chairL = new Chair(j, 500 + i * 40, 25, 30, false);
                Chair chairR = new Chair(j + 113, 500 + i * 40, 25, 30, true);
                roomObjects.getChildren().addAll(chairL, chairR);
            }
            Table desk = new Table(j + 25, 490, 88, 88, "blueTable");
            roomObjects.getChildren().addAll(desk);
        }

        Lab beakers = new Lab(595, 500, 150, 60, "beakers");

        x = 120;
        Lab lockers = new Lab(x, 30, 90, 100, "lockers");
        Lab lockers1 = new Lab(x + 90, 30, 90, 100, "lockers");
        Lab lockers2 = new Lab(x - 90, 30, 90, 100, "lockers");

        EventHandler enterCode = new EventHandler() {
            @Override
            public void handle(Event event) {
                Scanner input = new Scanner(System.in);
                Node source = (Node) event.getSource();
                System.out.print("Enter a 3-Digit passcode: ");
                String passcode = input.next();
                if (passcode.equals("918")) {
                    System.out.println("'tUtTiFrUtTi.'");
                    System.out.println(" - Some Cool Dude");
                    source.setOnMouseClicked(null);
                } else {
                    System.out.println("Wrong code.");
                }
            }
        };

        EventHandler colour = new EventHandler() {
            @Override
            public void handle(Event event) {
                Node source = (Node) event.getSource();
                System.out.println("Wow these colours look important. Maybe they're used for something?");
            }
        };

        beakers.setOnMouseClicked(colour);
        lockers.setOnMouseClicked(enterCode);

        roomObjects.getChildren().addAll(machine, machine2, machine3, machine4);
        //roomObjects.getChildren().addAll(desk4, desk5, chair4, chair5, chair6, chair7, chair8, chair9, chair10, chair11);
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
