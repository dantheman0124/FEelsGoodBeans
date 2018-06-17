package javafxapplication11;

import java.util.ArrayList;
import javafxapplication11.CPTRewrite;
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
import javafxapplication11.CPTRewrite;

public class Room1 extends Room {

    private ArrayList<Node> obj = new ArrayList<>();
    private KeyFrame frame = new KeyFrame(Duration.seconds(0.016), e -> {
        getPlayer().update(obj);

        displayInv();
        for (int i = 0; i < interactables.getChildren().size(); i++) {;
            if (getPlayer().getBoundsInParent().intersects(interactables.getChildren().get(i).getBoundsInParent())) {
                player.getInteractables().add((Interactables) interactables.getChildren().get(i));
                interactables.getChildren().remove(interactables.getChildren().get(i));
                break;
            }
        }

        if (getPlayer().isColliding(doors.getChildren().get(0))) {
        } else if (getPlayer().isColliding(doors.getChildren().get(1))) {
            CPTRewrite.nextRoom();
        }
    });

    public Room1() {
        super();

        getTimeline().getKeyFrames().add(frame);
        getTimeline().setCycleCount(Timeline.INDEFINITE);

        wallsColor = Color.DARKRED;
        doorColor = Color.BISQUE;

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

        setEnterSpawnX(enterX - getDOOR_W());
        setEnterSpawnY(enterY + getDOOR_H() / 2 - getPLAYER_H() / 2);

        setExitSpawnX(exitX - getPLAYER_W() - 50);
        setExitSpawnY(exitY + getDOOR_H() / 2 - getPLAYER_H() / 2);

        enterSpawnX = getEnterSpawnX();
        enterSpawnY = getEnterSpawnY();

        exitSpawnX = getExitSpawnX();
        exitSpawnY = getExitSpawnY();

        root.getChildren().addAll(floor, walls, doors, roomObjects, inv, interactables);

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

        // bottom wall
        rect = new Rectangle(getROOM_W(), getWALL_W(), wallsColor);
        rect.setTranslateX(0);
        rect.setTranslateY(getROOM_H() - getWALL_W() + getHEADER_H());
        walls.getChildren().add(rect);

        // left wall above door
        rect = new Rectangle(getWALL_W(), doorEnter.getTranslateY() - getHEADER_H(), wallsColor);
        rect.setTranslateX(0);
        rect.setTranslateY(getHEADER_H());
        walls.getChildren().add(rect);

        rect = new Rectangle(getDOOR_W(), getDOOR_H(), wallsColor);
        rect.setTranslateX(0);
        rect.setTranslateY(getROOM_H() - getDOOR_H() - 50 + getHEADER_H());
        walls.getChildren().add(rect);

        // horizontal wall right of that^^ 
        rect = new Rectangle(150, getWALL_W(), wallsColor);
        rect.setTranslateX(getWALL_W());
        rect.setTranslateY(doorEnter.getTranslateY() - getWALL_W());
        walls.getChildren().add(rect);

        // another horizontal wall right of that^^ 
        rect = new Rectangle(40, getWALL_W(), wallsColor);
        rect.setTranslateX(getWALL_W() + 280);
        rect.setTranslateY(doorEnter.getTranslateY() - getWALL_W());
        walls.getChildren().add(rect);

        // left wall under door
        rect = new Rectangle(getWALL_W(), getROOM_H() + getHEADER_H() - getDOOR_H() - doorEnter.getTranslateY(), wallsColor);
        rect.setTranslateX(0);
        rect.setTranslateY(doorEnter.getTranslateY() + getDOOR_H());
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

        // wall under shelves
        rect = new Rectangle(560, getWALL_W(), wallsColor);
        rect.setTranslateX(345 - getWALL_W());
        rect.setTranslateY(doorExit.getTranslateY() - getWALL_W());
        walls.getChildren().add(rect);

        // wall left of shelves
        rect = new Rectangle(getWALL_W(), 60, wallsColor);
        rect.setTranslateX(345 - getWALL_W());
        rect.setTranslateY(getHEADER_H() + getWALL_W());
        walls.getChildren().add(rect);

        // wall below that^^ 
        rect = new Rectangle(getWALL_W(), doorEnter.getTranslateY() - doorExit.getTranslateY(), wallsColor);
        rect.setTranslateX(345 - getWALL_W());
        rect.setTranslateY(doorExit.getTranslateY());
        walls.getChildren().add(rect);

        floor = new Group();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 14; j++) {
                Floor tile = new Floor(i * 100, j * 39 + 48, 120, 50, "bedroomWood");
                floor.getChildren().add(tile);
            }
        }
        FloorMat mat = new FloorMat(800, 290, 75, 75);
        floor.getChildren().addAll(mat);
    }

    @Override
    public void createDoors() {
        doors = new Group();

        Rectangle door;

        // door enter (0)
        door = new Rectangle(getDOOR_W(), getDOOR_H(), doorColor);
        door.setTranslateX(0);
        door.setTranslateY(getROOM_H() - getDOOR_H() - 50 + getHEADER_H());

        doors.getChildren().add(door);

        // door exit (1)
        door = new Rectangle(getDOOR_W(), getDOOR_H(), doorColor);
        door.setTranslateX(getROOM_W() - 10);
        door.setTranslateY(200 + getHEADER_H());

        doors.getChildren().add(door);
    }

    @Override
    public void fillRoom() {
        roomObjects = new Group();

        Crate crate = new Crate(780, 40, 50, 50);
        Crate crate2 = new Crate(830, 40, 50, 50);
        Crate crate3 = new Crate(830, 68, 50, 50);
        Crate crate4 = new Crate(250, 530, 50, 50);
        Crate crate5 = new Crate(300, 530, 50, 50);

        // group of tables and chairs
        int x = 430, y = 310;
        for (int i = 0; i < 2; i++) {
            Chair chairL = new Chair(x + i * 230, y + 35 + i * 110, 30, 30, false);
            Table table = new Table(x + 30 + i * 230, y + i * 110, 100, 100, "prettyTable");
            Chair chairR = new Chair(x + 120 + i * 230, y + 35 + i * 110, 30, 30, true);
            roomObjects.getChildren().addAll(table, chairL, chairR);
        }

        // bookshelves and desk
        Office bookcase = new Office(345, 0, 170, 115, "cabinet");
        Office bookcase3 = new Office(615, -7, 90, 127, "lessDrawers");
        Office bookcase4 = new Office(688, 24, 85, 85, "redGreen");
        Table desk = new Table(9, 200, 90, 180, "sideDiningTable");
        Office workDesk = new Office(500, 20, 120, 95, "workDeskYellow");

        // cabinets in top left corner
        Bedroom cabinetClosed1 = new Bedroom(30, 20, 65, 110, "cabinetsClosed");
        Bedroom cabinetClosed2 = new Bedroom(95, 20, 65, 110, "cabinetsClosed");
        Bedroom cabinetOpen = new Bedroom(160, 21, 65, 113, "cabinetsOpen");
        Bedroom cabinetClosed4 = new Bedroom(225, 20, 65, 110, "cabinetsClosed");

        EventHandler objClick = new EventHandler() {
            @Override
            public void handle(Event event) {
                Node source = (Node) event.getSource();
                System.out.println("There is nothing in here.");
            }
        };

        EventHandler findItem = new EventHandler() {
            @Override
            public void handle(Event event) {
                Node source = (Node) event.getSource();
                System.out.println("You found a flashlight!");
                Flashlight flashlight = new Flashlight(-100, -100, 0, 0, false);
                CPTRewrite.player.getInteractables().add(flashlight);
                System.out.println(CPTRewrite.player.getInteractables().size());
                roomObjects.getChildren().remove(source);
            }
        };

        crate.setOnMouseClicked(findItem);
        crate2.setOnMouseClicked(objClick);
        crate3.setOnMouseClicked(objClick);
        crate4.setOnMouseClicked(objClick);
        crate5.setOnMouseClicked(objClick);

        roomObjects.getChildren().addAll(crate, crate2, crate3, crate4, crate5, bookcase, bookcase3, bookcase4, desk, workDesk);
        roomObjects.getChildren().addAll(cabinetClosed1, cabinetClosed2, cabinetOpen, cabinetClosed4);
    }

    @Override
    public void createInteractables() {
        interactables = new Group();
    }

    @Override
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
