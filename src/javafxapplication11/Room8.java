package javafxapplication11;

import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Room8 extends Room {

    private ArrayList<Node> obj = new ArrayList<>();
    private KeyFrame frame = new KeyFrame(Duration.seconds(0.016), e -> {
        getPlayer().update(obj);

        this.getHealthBar().setHealth(CPTRewrite.player.getHealthBar().getHealth());
        this.getHealthBar().update();
        
        //displayInv();
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

        root.getChildren().addAll(floor, walls, roomObjects, doors, inv);

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

        //desk dividing wall (horizontal)
        rect = new Rectangle(370, getWALL_W(), wallsColor);
        rect.setTranslateX(525);
        rect.setTranslateY(400);
        walls.getChildren().add(rect);

        //desk dividing wall
        rect = new Rectangle(getWALL_W(), 100, wallsColor);
        rect.setTranslateX(525);
        rect.setTranslateY(400);
        walls.getChildren().add(rect);

        floor = new Group();
        FloorMat mat = new FloorMat(410, 500, 75, 75);
        Bedroom rug2 = new Bedroom(725, 220, 80, 60, "purplerug");
        rug2.setRotate(90);

        //bedroom floor
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 13; j++) {
                Floor tile = new Floor(i * 105, j * 42 + 45, 125, 55, "bedroomWood");
                floor.getChildren().add(tile);
            }
        }

        //bathroom floor
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 4; j++) {
                Floor tile = new Floor(305 - i * 50, 70 + j * 50, 50, 50, "bathroomGrey");
                floor.getChildren().add(tile);
            }
        }
        floor.getChildren().add(mat);
        floor.getChildren().add(rug2);

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
        Bathroom shower = new Bathroom(9, 0, 122, 118, "shower");
        Bathroom toilet = new Bathroom(295, 160, 60, 75, "toilet");
        Bathroom bathtub = new Bathroom(300, 50, 58, 110, "bathtub");
        Bathroom extraCounter = new Bathroom(131, 46, 54, 64, "extraCounter");

        Bedroom bedsideH = new Bedroom(120, 255, 30, 45, "bedsideH");
        Bedroom bedsideV = new Bedroom(250, 255, 30, 45, "bedsideV");
        Bedroom bed = new Bedroom(150, 240, 100, 140, "bedPink");

        int x = 107, y = 445;
        Bedroom armchairL = new Bedroom(x, y, 60, 70, "armchairL");
        Bedroom armchairR = new Bedroom(x + 120, y, 60, 70, "armchairR");
        Bedroom blackStool = new Bedroom(x + 65, y + 15, 50, 60, "blackstool");

        Bedroom piano = new Bedroom(390, 80, 120, 120, "piano");
        Kitchen couch = new Kitchen(670, 200, 60, 100, "couchL");
        Kitchen couch2 = new Kitchen(800, 200, 60, 100, "couchR");
        Kitchen plant = new Kitchen(650, 435, 50, 60, "plant");

        Office workDesk1 = new Office(540, 405, 120, 95, "workDeskYellow");
        Office workDesk2 = new Office(760, 405, 120, 95, "workDeskYellow");

        //fixing photoshop errors
        Rectangle rect = new Rectangle(getWALL_W(), getHEADER_H(), Color.WHITE);
        rect.setTranslateX(0);
        rect.setTranslateY(0);

        EventHandler pianoMessage = new EventHandler() {
            @Override
            public void handle(Event event) {
                Node source = (Node) event.getSource();
                Rectangle coverUp = new Rectangle(0, 600, 900, 100);
                coverUp.setFill(Color.WHITE);
                Text comboMessage = new Text("You quit piano lessons in grade five. Bet you regret that now.");
                comboMessage.setX(20);
                comboMessage.setY(660);
                comboMessage.setFont(new Font(20));
                root.getChildren().addAll(coverUp, comboMessage);
            }
        };

        EventHandler bedMessage = new EventHandler() {
            @Override
            public void handle(Event event) {
                Node source = (Node) event.getSource();
                Rectangle coverUp = new Rectangle(0, 600, 900, 100);
                coverUp.setFill(Color.WHITE);
                Text comboMessage = new Text("This bed is just right. But you can't sleep in someone else's bed, who do you think you are.");
                comboMessage.setX(20);
                comboMessage.setY(660);
                comboMessage.setFont(new Font(20));
                root.getChildren().addAll(coverUp, comboMessage);

            }
        };

        Bedroom chest = new Bedroom(815, 405, 60, 80, "chest");

        piano.setOnMouseClicked(pianoMessage);
        bed.setOnMouseClicked(bedMessage);

        roomObjects.getChildren().addAll(sink, shower, toilet, bathtub, extraCounter, bed, bedsideH, bedsideV, blackStool, armchairL, armchairR, piano, couch, couch2, plant, workDesk1);
        roomObjects.getChildren().addAll(rect, chest);
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
