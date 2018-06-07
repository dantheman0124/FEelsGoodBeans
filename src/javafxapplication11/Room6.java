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

        setSpawnX(enterX + getDOOR_W());
        setSpawnY(enterY + getDOOR_H() / 2 - getPLAYER_H() / 2);

        spawnX = getSpawnX();
        spawnY = getSpawnY();

        root.getChildren().addAll(walls, roomObjects, doors);

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
        Office bookshelf = new Office(10, 23, 97, 103, "redBlue");
        Office bookshelf2 = new Office(95, 35, 85, 85, "redGreen");
        Office bookshelf3 = new Office(170, 33, 87, 87, "greenBlue");
        Office bookshelf4 = new Office(250, 30, 85, 90, "redGreenBlue");
        Office bookshelf5 = new Office(332, 26, 90, 97, "nineDrawers");
        Office bookshelf6 = new Office(406, 34, 85, 85, "redGreen");
        Office bookshelf7 = new Office(485, 3, 90, 127, "lessDrawers");
        Office bookshelf8 = new Office(560, 9, 170, 115, "cabinet");
        Office bookshelf9 = new Office(714, 32, 87, 87, "greenBlue");
        Office bookshelf10 = new Office(795, 34, 85, 85, "redGreen");
        
        Office bookshelf11 = new Office(435, 157, 90, 95, "redBlueLess");
        Office bookshelf12 = new Office(520, 170, 78, 83, "television");
        Office bookshelf13 = new Office(594, 164, 78, 93, "redBlueDark");
        Office bookshelf14 = new Office(670, 165, 85, 85, "redGreen");
        
        Office workDesk1 = new Office(747, 155, 120, 100, "workDeskYellow");

        Desk desk = new Desk(175, 225, 100, 175);
        DeskChair deskChair = new DeskChair(280, 260, 40, 40);
        DeskChair deskChair2 = new DeskChair(280, 310, 40, 40);
        DeskChair deskChair3 = new DeskChair(130, 260, 40, 40);
        DeskChair deskChair4 = new DeskChair(130, 310, 40, 40);
        DeskChair deskChair5 = new DeskChair(205, 180, 40, 40);
        DeskChair deskChair6 = new DeskChair(205, 390, 40, 40);

        Desk desk2 = new Desk(450, 350, 100, 175);
        Desk desk3 = new Desk(550, 350, 100, 175);
        DeskChair deskChair7 = new DeskChair(655, 385, 40, 40);
        DeskChair deskChair8 = new DeskChair(655, 435, 40, 40);
        DeskChair deskChair9 = new DeskChair(405, 385, 40, 40);
        DeskChair deskChair10 = new DeskChair(405, 435, 40, 40);
        DeskChair deskChair11 = new DeskChair(475, 305, 40, 40);
        DeskChair deskChair12 = new DeskChair(575, 305, 40, 40);
        DeskChair deskChair13 = new DeskChair(525, 305, 40, 40);

        TrashCan trash = new TrashCan(30, 525, 50, 50);
        
        roomObjects.getChildren().addAll(bookshelf, bookshelf2, bookshelf3, bookshelf4, bookshelf5, bookshelf6, bookshelf7, bookshelf8, bookshelf9, bookshelf10, workDesk1, desk, deskChair, deskChair2, deskChair3, deskChair4, desk2, desk3, deskChair5, deskChair6, deskChair7, deskChair8, deskChair9, deskChair10, deskChair11, deskChair12, deskChair13, trash, bookshelf11, bookshelf12, bookshelf13, bookshelf14);
    }
}
