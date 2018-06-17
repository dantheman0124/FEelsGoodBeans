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

public class Room6 extends Room {

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

    public Room6() {
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

        setEnterSpawnX(enterX + getDOOR_W() - 10);
        setEnterSpawnY(enterY + getDOOR_H() / 2 - getPLAYER_H() / 2);

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

        // left small room walls
        // horizontal wall right and under door
        rect = new Rectangle(290, getWALL_W(), wallsColor);
        rect.setTranslateX(getWALL_W());
        rect.setTranslateY(doorEnter.getTranslateY() + getDOOR_H());
        walls.getChildren().add(rect);

        // vertical right wall of small room
        rect = new Rectangle(getWALL_W(), 120, wallsColor);
        rect.setTranslateX(290);
        rect.setTranslateY(doorEnter.getTranslateY() + getDOOR_H() + getWALL_W());
        walls.getChildren().add(rect);

        // horizontal wall above right-small room
        rect = new Rectangle(350, getWALL_W(), wallsColor);
        rect.setTranslateX(410);
        rect.setTranslateY(doorEnter.getTranslateY() + getDOOR_H() + 120);
        walls.getChildren().add(rect);

        // vertical wall right of right-small room
        rect = new Rectangle(getWALL_W(), 100, wallsColor);
        rect.setTranslateX(760 - getWALL_W());
        rect.setTranslateY(doorEnter.getTranslateY() + getDOOR_H() + 120);
        walls.getChildren().add(rect);

        floor = new Group();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 14; j++) {
                Floor tile = new Floor(i * 100, j * 39 + 48, 120, 50, "bedroomWood");
                floor.getChildren().add(tile);
            }
        };
        FloorMat mat = new FloorMat(800, 390, 75, 75);
        floor.getChildren().addAll(mat);
    }

    @Override
    public void createDoors() {
        doors = new Group();

        Rectangle door;

        // door enter (0)
        door = new Rectangle(getDOOR_W(), getDOOR_H(), doorColor);
        door.setTranslateX(0);
        door.setTranslateY(100 + getHEADER_H());

        doors.getChildren().add(door);

        // door exit (1)
        door = new Rectangle(getDOOR_W(), getDOOR_H(), doorColor);
        door.setTranslateX(getROOM_W() - 10);
        door.setTranslateY(getROOM_H() + getHEADER_H() - getDOOR_H() - 100);

        doors.getChildren().add(door);
    }

    @Override
    public void fillRoom() {
        roomObjects = new Group();
        int y = 18;
        Office bookshelf = new Office(10, y, 97, 103, "redBlue");
        Office bookshelf2 = new Office(95, y + 12, 85, 85, "redGreen");
        Office bookshelf3 = new Office(170, y + 10, 87, 87, "greenBlue");
        Office bookshelf4 = new Office(250, y + 7, 85, 90, "redGreenBlue");
        Office bookshelf5 = new Office(332, y + 3, 90, 97, "nineDrawers");
        Office bookshelf6 = new Office(406, y + 11, 85, 85, "redGreen");
        Office bookshelf7 = new Office(485, y - 20, 90, 127, "lessDrawers");
        Office bookshelf8 = new Office(560, y - 14, 170, 115, "cabinet");
        Office bookshelf9 = new Office(714, y + 9, 87, 87, "greenBlue");
        Office bookshelf10 = new Office(795, y + 11, 85, 85, "redGreen");

        y = 190;
        Office bookshelf11 = new Office(435, y, 90, 95, "redBlueLess");
        Office bookshelf12 = new Office(520, y, 78, 95, "television");
        Office bookshelf13 = new Office(594, y, 78, 95, "redBlueDark");
        Office bookshelf14 = new Office(670, y, 85, 95, "redGreen");

        Office workDesk1 = new Office(747, 195, 120, 95, "workDeskYellow");

        int x = 95;
        y = 405;
        Table table1 = new Table(x, y - 10, 110, 110, "brownWChairs");
        Table table2 = new Table(x + 13, y + 75, 89, 89, "brownWOChairs");
        Chair chairDown = new Chair(x + 40, y - 20, 30, 55, "brownChairDown");
        Chair chairUp = new Chair(x + 40, y + 130, 30, 45, "brownChairUp");
        Chair chairR = new Chair(x + 3, y + 70, 35, 60, "brownChairRight");
        Chair chairR2 = new Chair(x - 40, y + 95, 35, 60, "brownChairRight");
        Chair chairL = new Chair(x + 79, y + 70, 35, 60, "brownChairLeft");
        Chair chairL2 = new Chair(x + 79, y + 95, 35, 60, "brownChairLeft");

        x = 350;
        y = 520;
        int diff = 190;
        for (int i = 0; i < 2; i++) {
            Table tableH = new Table(x + i * diff, y, 120, 55, "brownTableH");
            Chair chairD = new Chair(x + 20 + i * diff, y - 20, 30, 55, "brownChairDown");
            Chair chairD2 = new Chair(x + 67 + i * diff, y - 20, 30, 55, "brownChairDown");
            Chair chairL3 = new Chair(x + 95 + i * diff, y - 10, 35, 60, "brownChairLeft");
            Chair chairR3 = new Chair(x - 13 + i * diff, y - 10, 35, 60, "brownChairRight");
            roomObjects.getChildren().addAll(chairD, chairD2, chairL3, chairR3, tableH);
        }

        roomObjects.getChildren().addAll(bookshelf, bookshelf2, bookshelf3, bookshelf4, bookshelf5, bookshelf6, bookshelf7, bookshelf8, bookshelf9, bookshelf10, workDesk1);
        roomObjects.getChildren().addAll(bookshelf11, bookshelf12, bookshelf13, bookshelf14);
        roomObjects.getChildren().addAll(chairDown, table1, chairR, chairR2, chairL, chairL2, table2, chairUp);

//        Table table = new Table(190, 225, 100, 100, "prettyTable");
//        Table table6 = new Table(190, 295, 100, 100, "prettyTable");
//        Chair deskChair = new Chair(280, 260, 30, 30, true);
//        Chair deskChair2 = new Chair(280, 320, 30, 30, true);
//        Chair deskChair3 = new Chair(155, 260, 30, 30, false);
//        Chair deskChair4 = new Chair(155, 320, 30, 30, false);
//
//        Table table2 = new Table(465, 420, 100, 100, "prettyTable");
//        Table table3 = new Table(540, 420, 100, 100, "prettyTable");
//        Table table4 = new Table(465, 490, 100, 100, "prettyTable");
//        Table table5 = new Table(540, 490, 100, 100, "prettyTable");
//        Chair deskChair7 = new Chair(630, 455, 30, 30, true);
//        Chair deskChair8 = new Chair(630, 525, 30, 30, true);
//        Chair deskChair9 = new Chair(440, 455, 30, 30, false);
//        Chair deskChair10 = new Chair(440, 525, 30, 30, false);
//        roomObjects.getChildren().addAll(table, deskChair, deskChair2, deskChair3, deskChair4, table2, table3, table4, table5, deskChair7, deskChair8, deskChair9, deskChair10, table6);
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
