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
import java.math.*;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Room1 extends Room {

    private ArrayList<Node> obj = new ArrayList<>();
    private ArrayList<Node> interactableObjects = new ArrayList<>();

    Bedroom cabinetOpen;
    Crate crate;
    Crate crate2;
    Crate crate3;
    Crate crate4;
    Crate crate5;
    Office bookcase;
    Office bookcase3;
    Office bookcase4;
    Table desk;
    Bedroom cabinetClosed1;
    Bedroom cabinetClosed2;
    Bedroom cabinetClosed4;
    Office workDesk;

    private KeyFrame frame = new KeyFrame(Duration.seconds(0.016), e -> {
        getPlayer().update(obj);

        //displayInv();
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

//        if (CPTRewrite.player.checkInRange(interactableObjects) == 0) {
//            cabinetOpen.setOnMouseClicked(this.findItem);
//            crate.setOnMouseClicked(null);
//            crate2.setOnMouseClicked(null);
//            crate3.setOnMouseClicked(null);
//            crate4.setOnMouseClicked(null);
//            crate5.setOnMouseClicked(null);
//            bookcase.setOnMouseClicked(null);
//            bookcase3.setOnMouseClicked(null);
//            bookcase4.setOnMouseClicked(null);
//            desk.setOnMouseClicked(null);
//            cabinetClosed1.setOnMouseClicked(null);
//            cabinetClosed2.setOnMouseClicked(null);
//            cabinetClosed4.setOnMouseClicked(null);
//            workDesk.setOnMouseClicked(null);
//            System.out.println(CPTRewrite.player.checkInRange(interactableObjects));
//        } else if (CPTRewrite.player.checkInRange(interactableObjects) == 1) {
//            cabinetOpen.setOnMouseClicked(null);
//            crate.setOnMouseClicked(this.objClick);
//            crate2.setOnMouseClicked(null);
//            crate3.setOnMouseClicked(null);
//            crate4.setOnMouseClicked(null);
//            crate5.setOnMouseClicked(null);
//            bookcase.setOnMouseClicked(null);
//            bookcase3.setOnMouseClicked(null);
//            bookcase4.setOnMouseClicked(null);
//            desk.setOnMouseClicked(null);
//            cabinetClosed1.setOnMouseClicked(null);
//            cabinetClosed2.setOnMouseClicked(null);
//            cabinetClosed4.setOnMouseClicked(null);
//            workDesk.setOnMouseClicked(null);
//            System.out.println(CPTRewrite.player.checkInRange(interactableObjects));
//        } else if (CPTRewrite.player.checkInRange(interactableObjects) == 2) {
//            cabinetOpen.setOnMouseClicked(null);
//            crate.setOnMouseClicked(null);
//            crate2.setOnMouseClicked(this.objClick);
//            crate3.setOnMouseClicked(null);
//            crate4.setOnMouseClicked(null);
//            crate5.setOnMouseClicked(null);
//            bookcase.setOnMouseClicked(null);
//            bookcase3.setOnMouseClicked(null);
//            bookcase4.setOnMouseClicked(null);
//            desk.setOnMouseClicked(null);
//            cabinetClosed1.setOnMouseClicked(null);
//            cabinetClosed2.setOnMouseClicked(null);
//            cabinetClosed4.setOnMouseClicked(null);
//            workDesk.setOnMouseClicked(null);
//            System.out.println(CPTRewrite.player.checkInRange(interactableObjects));
//        } else if (CPTRewrite.player.checkInRange(interactableObjects) == 3) {
//            cabinetOpen.setOnMouseClicked(null);
//            crate.setOnMouseClicked(null);
//            crate2.setOnMouseClicked(null);
//            crate3.setOnMouseClicked(this.objClick);
//            crate4.setOnMouseClicked(null);
//            crate5.setOnMouseClicked(null);
//            bookcase.setOnMouseClicked(null);
//            bookcase3.setOnMouseClicked(null);
//            bookcase4.setOnMouseClicked(null);
//            desk.setOnMouseClicked(null);
//            cabinetClosed1.setOnMouseClicked(null);
//            cabinetClosed2.setOnMouseClicked(null);
//            cabinetClosed4.setOnMouseClicked(null);
//            workDesk.setOnMouseClicked(null);
//            System.out.println(CPTRewrite.player.checkInRange(interactableObjects));
//        } else if (CPTRewrite.player.checkInRange(interactableObjects) == 4) {
//            cabinetOpen.setOnMouseClicked(null);
//            crate.setOnMouseClicked(null);
//            crate2.setOnMouseClicked(null);
//            crate3.setOnMouseClicked(null);
//            crate4.setOnMouseClicked(this.objClick);
//            crate5.setOnMouseClicked(null);
//            bookcase.setOnMouseClicked(null);
//            bookcase3.setOnMouseClicked(null);
//            bookcase4.setOnMouseClicked(null);
//            desk.setOnMouseClicked(null);
//            cabinetClosed1.setOnMouseClicked(null);
//            cabinetClosed2.setOnMouseClicked(null);
//            cabinetClosed4.setOnMouseClicked(null);
//            workDesk.setOnMouseClicked(null);
//            System.out.println(CPTRewrite.player.checkInRange(interactableObjects));
//        } else if (CPTRewrite.player.checkInRange(interactableObjects) == 5) {
//            cabinetOpen.setOnMouseClicked(null);
//            crate.setOnMouseClicked(null);
//            crate2.setOnMouseClicked(null);
//            crate3.setOnMouseClicked(null);
//            crate4.setOnMouseClicked(null);
//            crate5.setOnMouseClicked(this.objClick);
//            bookcase.setOnMouseClicked(null);
//            bookcase3.setOnMouseClicked(null);
//            bookcase4.setOnMouseClicked(null);
//            desk.setOnMouseClicked(null);
//            cabinetClosed1.setOnMouseClicked(null);
//            cabinetClosed2.setOnMouseClicked(null);
//            cabinetClosed4.setOnMouseClicked(null);
//            workDesk.setOnMouseClicked(null);
//            System.out.println(CPTRewrite.player.checkInRange(interactableObjects));
//        } else if (CPTRewrite.player.checkInRange(interactableObjects) == 6) {
//            cabinetOpen.setOnMouseClicked(null);
//            crate.setOnMouseClicked(null);
//            crate2.setOnMouseClicked(null);
//            crate3.setOnMouseClicked(null);
//            crate4.setOnMouseClicked(null);
//            crate5.setOnMouseClicked(null);
//            bookcase.setOnMouseClicked(this.bookMessage);
//            bookcase3.setOnMouseClicked(null);
//            bookcase4.setOnMouseClicked(null);
//            desk.setOnMouseClicked(null);
//            cabinetClosed1.setOnMouseClicked(null);
//            cabinetClosed2.setOnMouseClicked(null);
//            cabinetClosed4.setOnMouseClicked(null);
//            workDesk.setOnMouseClicked(null);
//            System.out.println(CPTRewrite.player.checkInRange(interactableObjects));
//        } else if (CPTRewrite.player.checkInRange(interactableObjects) == 7) {
//            cabinetOpen.setOnMouseClicked(null);
//            crate.setOnMouseClicked(null);
//            crate2.setOnMouseClicked(null);
//            crate3.setOnMouseClicked(null);
//            crate4.setOnMouseClicked(null);
//            crate5.setOnMouseClicked(null);
//            bookcase.setOnMouseClicked(null);
//            bookcase3.setOnMouseClicked(this.bookMessage);
//            bookcase4.setOnMouseClicked(null);
//            desk.setOnMouseClicked(null);
//            cabinetClosed1.setOnMouseClicked(null);
//            cabinetClosed2.setOnMouseClicked(null);
//            cabinetClosed4.setOnMouseClicked(null);
//            workDesk.setOnMouseClicked(null);
//            System.out.println(CPTRewrite.player.checkInRange(interactableObjects));
//        } else if (CPTRewrite.player.checkInRange(interactableObjects) == 8) {
//            cabinetOpen.setOnMouseClicked(null);
//            crate.setOnMouseClicked(null);
//            crate2.setOnMouseClicked(null);
//            crate3.setOnMouseClicked(null);
//            crate4.setOnMouseClicked(null);
//            crate5.setOnMouseClicked(null);
//            bookcase.setOnMouseClicked(null);
//            bookcase3.setOnMouseClicked(null);
//            bookcase4.setOnMouseClicked(this.bookMessage);
//            desk.setOnMouseClicked(null);
//            cabinetClosed1.setOnMouseClicked(null);
//            cabinetClosed2.setOnMouseClicked(null);
//            cabinetClosed4.setOnMouseClicked(null);
//            workDesk.setOnMouseClicked(null);
//            System.out.println(CPTRewrite.player.checkInRange(interactableObjects));
//        } else if (CPTRewrite.player.checkInRange(interactableObjects) == 9) {
//            cabinetOpen.setOnMouseClicked(null);
//            crate.setOnMouseClicked(null);
//            crate2.setOnMouseClicked(null);
//            crate3.setOnMouseClicked(null);
//            crate4.setOnMouseClicked(null);
//            crate5.setOnMouseClicked(null);
//            bookcase.setOnMouseClicked(null);
//            bookcase3.setOnMouseClicked(null);
//            bookcase4.setOnMouseClicked(null);
//            desk.setOnMouseClicked(this.objClick);
//            cabinetClosed1.setOnMouseClicked(null);
//            cabinetClosed2.setOnMouseClicked(null);
//            cabinetClosed4.setOnMouseClicked(null);
//            workDesk.setOnMouseClicked(null);
//            System.out.println(CPTRewrite.player.checkInRange(interactableObjects));
//        } else if (CPTRewrite.player.checkInRange(interactableObjects) == 10) {
//            cabinetOpen.setOnMouseClicked(null);
//            crate.setOnMouseClicked(null);
//            crate2.setOnMouseClicked(null);
//            crate3.setOnMouseClicked(null);
//            crate4.setOnMouseClicked(null);
//            crate5.setOnMouseClicked(null);
//            bookcase.setOnMouseClicked(null);
//            bookcase3.setOnMouseClicked(null);
//            bookcase4.setOnMouseClicked(null);
//            desk.setOnMouseClicked(null);
//            cabinetClosed1.setOnMouseClicked(this.objClick);
//            cabinetClosed2.setOnMouseClicked(null);
//            cabinetClosed4.setOnMouseClicked(null);
//            workDesk.setOnMouseClicked(null);
//            System.out.println(CPTRewrite.player.checkInRange(interactableObjects));
//        } else if (CPTRewrite.player.checkInRange(interactableObjects) == 11) {
//            cabinetOpen.setOnMouseClicked(null);
//            crate.setOnMouseClicked(null);
//            crate2.setOnMouseClicked(null);
//            crate3.setOnMouseClicked(null);
//            crate4.setOnMouseClicked(null);
//            crate5.setOnMouseClicked(null);
//            bookcase.setOnMouseClicked(null);
//            bookcase3.setOnMouseClicked(null);
//            bookcase4.setOnMouseClicked(null);
//            desk.setOnMouseClicked(null);
//            cabinetClosed1.setOnMouseClicked(null);
//            cabinetClosed2.setOnMouseClicked(this.objClick);
//            cabinetClosed4.setOnMouseClicked(null);
//            workDesk.setOnMouseClicked(null);
//            System.out.println(CPTRewrite.player.checkInRange(interactableObjects));
//        } else if (CPTRewrite.player.checkInRange(interactableObjects) == 12) {
//            cabinetOpen.setOnMouseClicked(null);
//            crate.setOnMouseClicked(null);
//            crate2.setOnMouseClicked(null);
//            crate3.setOnMouseClicked(null);
//            crate4.setOnMouseClicked(null);
//            crate5.setOnMouseClicked(null);
//            bookcase.setOnMouseClicked(null);
//            bookcase3.setOnMouseClicked(null);
//            bookcase4.setOnMouseClicked(null);
//            desk.setOnMouseClicked(null);
//            cabinetClosed1.setOnMouseClicked(null);
//            cabinetClosed2.setOnMouseClicked(null);
//            cabinetClosed4.setOnMouseClicked(this.objClick);
//            workDesk.setOnMouseClicked(null);
//            System.out.println(CPTRewrite.player.checkInRange(interactableObjects));
//        } else if (CPTRewrite.player.checkInRange(interactableObjects) == 13) {
//            cabinetOpen.setOnMouseClicked(null);
//            crate.setOnMouseClicked(null);
//            crate2.setOnMouseClicked(null);
//            crate3.setOnMouseClicked(null);
//            crate4.setOnMouseClicked(null);
//            crate5.setOnMouseClicked(null);
//            bookcase.setOnMouseClicked(null);
//            bookcase3.setOnMouseClicked(null);
//            bookcase4.setOnMouseClicked(null);
//            desk.setOnMouseClicked(null);
//            cabinetClosed1.setOnMouseClicked(null);
//            cabinetClosed2.setOnMouseClicked(null);
//            cabinetClosed4.setOnMouseClicked(null);
//            workDesk.setOnMouseClicked(this.letter);
//            System.out.println(CPTRewrite.player.checkInRange(interactableObjects));
//        } else {
//            cabinetOpen.setOnMouseClicked(null);
//            crate.setOnMouseClicked(null);
//            crate2.setOnMouseClicked(null);
//            crate3.setOnMouseClicked(null);
//            crate4.setOnMouseClicked(null);
//            crate5.setOnMouseClicked(null);
//            bookcase.setOnMouseClicked(null);
//            bookcase3.setOnMouseClicked(null);
//            bookcase4.setOnMouseClicked(null);
//            desk.setOnMouseClicked(null);
//            cabinetClosed1.setOnMouseClicked(null);
//            cabinetClosed2.setOnMouseClicked(null);
//            cabinetClosed4.setOnMouseClicked(null);
//            workDesk.setOnMouseClicked(null);
//        }

if (CPTRewrite.player.checkInRange(interactableObjects)) {

            cabinetOpen.setOnMouseClicked(this.findItem);
            crate.setOnMouseClicked(this.objClick);
            crate2.setOnMouseClicked(this.objClick);
            crate3.setOnMouseClicked(this.objClick);
            crate4.setOnMouseClicked(this.objClick);
            crate5.setOnMouseClicked(this.objClick);
            bookcase.setOnMouseClicked(this.bookMessage);
            bookcase3.setOnMouseClicked(this.bookMessage);
            bookcase4.setOnMouseClicked(this.bookMessage);
            desk.setOnMouseClicked(this.objClick);
            cabinetClosed1.setOnMouseClicked(this.objClick);
            cabinetClosed2.setOnMouseClicked(this.objClick);
            cabinetClosed4.setOnMouseClicked(this.objClick);
            workDesk.setOnMouseClicked(this.letter);
        }

        if (CPTRewrite.player.checkInRange(interactableObjects)) {

            cabinetOpen.setOnMouseClicked(this.findItem);
            crate.setOnMouseClicked(this.objClick);
            crate2.setOnMouseClicked(this.objClick);
            crate3.setOnMouseClicked(this.objClick);
            crate4.setOnMouseClicked(this.objClick);
            crate5.setOnMouseClicked(this.objClick);
            bookcase.setOnMouseClicked(this.bookMessage);
            bookcase3.setOnMouseClicked(this.bookMessage);
            bookcase4.setOnMouseClicked(this.bookMessage);
            desk.setOnMouseClicked(this.objClick);
            cabinetClosed1.setOnMouseClicked(this.objClick);
            cabinetClosed2.setOnMouseClicked(this.objClick);
            cabinetClosed4.setOnMouseClicked(this.objClick);
            workDesk.setOnMouseClicked(this.letter);
            
        } else {
            cabinetOpen.setOnMouseClicked(null);
            crate.setOnMouseClicked(null);
            crate2.setOnMouseClicked(null);
            crate3.setOnMouseClicked(null);
            crate4.setOnMouseClicked(null);

            crate5.setOnMouseClicked(null);
            bookcase.setOnMouseClicked(null);
            bookcase3.setOnMouseClicked(null);
            bookcase4.setOnMouseClicked(null);
            desk.setOnMouseClicked(null);
            cabinetClosed1.setOnMouseClicked(null);
            cabinetClosed2.setOnMouseClicked(null);
            cabinetClosed4.setOnMouseClicked(null);
            workDesk.setOnMouseClicked(null);
        }

    });

    EventHandler objClick = new EventHandler() {
        @Override
        public void handle(Event event) {
            Node source = (Node) event.getSource();
            Rectangle coverUp = new Rectangle(0, 600, 900, 100);
            coverUp.setFill(Color.WHITE);
            Text nothingMessage = new Text("There's nothing here.");
            nothingMessage.setX(20);
            nothingMessage.setY(660);
            nothingMessage.setFont(new Font(20));
            root.getChildren().addAll(coverUp, nothingMessage);
        }
    };

    EventHandler letter = new EventHandler() {
        @Override
        public void handle(Event event) {
            Node source = (Node) event.getSource();
            Rectangle coverUp = new Rectangle(0, 600, 900, 100);
            coverUp.setFill(Color.WHITE);
            Text comboMessage = new Text("The paper says: 918. Hmmm.... better remember that, it seems like it could be important!");
            comboMessage.setX(20);
            comboMessage.setY(660);
            comboMessage.setFont(new Font(20));
            root.getChildren().addAll(coverUp, comboMessage);

        }
    };

    EventHandler findItem = new EventHandler() {
        @Override
        public void handle(Event event) {
            Node source = (Node) event.getSource();
            Rectangle coverUp = new Rectangle(0, 600, 900, 100);
            coverUp.setFill(Color.WHITE);
            Text foundMessage = new Text("You found a flashlight!");
            foundMessage.setX(20);
            foundMessage.setY(660);
            foundMessage.setFont(new Font(20));
            root.getChildren().addAll(coverUp, foundMessage);
            Flashlight flashlight = new Flashlight(-100, -100, 0, 0, false);
            CPTRewrite.inventory.add(flashlight);
            Room2.nextRoom = true;
        }
    };

    EventHandler bookMessage = new EventHandler() {
        @Override
        public void handle(Event event) {
            Node source = (Node) event.getSource();
            Rectangle coverUp = new Rectangle(0, 600, 900, 100);
            coverUp.setFill(Color.WHITE);
            Text comboMessage = new Text("You don't have time for reading!");
            comboMessage.setX(20);
            comboMessage.setY(660);
            comboMessage.setFont(new Font(20));
            root.getChildren().addAll(coverUp, comboMessage);

        }
    };

    public Room1() {
        super();

        wallsColor = Color.DARKRED;
        doorColor = Color.BISQUE;

        createDoors();
        createWalls();
        fillRoom();
        createInteractables();

        getTimeline().getKeyFrames().add(frame);
        getTimeline().setCycleCount(Timeline.INDEFINITE);

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

        crate = new Crate(780, 40, 50, 50);
        crate2 = new Crate(830, 40, 50, 50);
        crate3 = new Crate(830, 68, 50, 50);
        crate4 = new Crate(250, 530, 50, 50);
        crate5 = new Crate(300, 530, 50, 50);

        // group of tables and chairs
        int x = 430, y = 310;
        for (int i = 0; i < 2; i++) {
            Chair chairL = new Chair(x + i * 230, y + 35 + i * 110, 30, 30, false);
            Table table = new Table(x + 30 + i * 230, y + i * 110, 100, 100, "prettyTable");
            Chair chairR = new Chair(x + 120 + i * 230, y + 35 + i * 110, 30, 30, true);
            roomObjects.getChildren().addAll(table, chairL, chairR);
        }

        // bookshelves and desk
        bookcase = new Office(345, 0, 170, 115, "cabinet");
        bookcase3 = new Office(615, -7, 90, 127, "lessDrawers");
        bookcase4 = new Office(688, 24, 85, 85, "redGreen");
        desk = new Table(9, 200, 90, 180, "sideDiningTable");
        workDesk = new Office(500, 20, 120, 95, "workDeskYellow");

        // cabinets in top left corner
        cabinetClosed1 = new Bedroom(30, 20, 65, 110, "cabinetsClosed");
        cabinetClosed2 = new Bedroom(95, 20, 65, 110, "cabinetsClosed");
        cabinetOpen = new Bedroom(160, 21, 65, 113, "cabinetsOpen");
        cabinetClosed4 = new Bedroom(225, 20, 65, 110, "cabinetsClosed");

        // interactable objects added to interactables array list
        interactableObjects.add(cabinetOpen);
        interactableObjects.add(crate);
        interactableObjects.add(crate2);
        interactableObjects.add(crate3);
        interactableObjects.add(crate4);
        interactableObjects.add(crate5);
        interactableObjects.add(bookcase);
        interactableObjects.add(bookcase3);
        interactableObjects.add(bookcase4);
        interactableObjects.add(desk);
        interactableObjects.add(cabinetClosed1);
        interactableObjects.add(cabinetClosed2);
        interactableObjects.add(cabinetClosed4);
        Bedroom cabinetClosed4 = new Bedroom(225, 20, 65, 110, "cabinetsClosed");

        // distance formula code
        EventHandler objClick = new EventHandler() {
            @Override
            public void handle(Event event) {
                Node source = (Node) event.getSource();
                Rectangle coverUp = new Rectangle(0, 600, 900, 100);
                coverUp.setFill(Color.WHITE);
                Text nothingMessage = new Text("There's nothing here.");
                nothingMessage.setX(20);
                nothingMessage.setY(660);
                nothingMessage.setFont(new Font(20));
                root.getChildren().addAll(coverUp, nothingMessage);
            }
        };

        EventHandler letter = new EventHandler() {
            @Override
            public void handle(Event event) {
                Node source = (Node) event.getSource();
                Rectangle coverUp = new Rectangle(0, 600, 900, 100);
                coverUp.setFill(Color.WHITE);
                Text comboMessage = new Text("The paper says: 918. Hmmm.... better remember that, it seems like it could be important!");
                comboMessage.setX(20);
                comboMessage.setY(660);
                comboMessage.setFont(new Font(20));
                root.getChildren().addAll(coverUp, comboMessage);

            }
        };

        EventHandler findItem = new EventHandler() {
            @Override
            public void handle(Event event) {
                Node source = (Node) event.getSource();
                Rectangle coverUp = new Rectangle(0, 600, 900, 100);
                coverUp.setFill(Color.WHITE);
                Text foundMessage = new Text("You found a flashlight!");
                foundMessage.setX(20);
                foundMessage.setY(660);
                foundMessage.setFont(new Font(20));
                root.getChildren().addAll(coverUp, foundMessage);
                Flashlight flashlight = new Flashlight(-100, -100, 0, 0, false);
                CPTRewrite.inventory.add(flashlight);
                Room2.nextRoom = true;
            }
        };

        EventHandler bookMessage = new EventHandler() {
            @Override
            public void handle(Event event) {
                Node source = (Node) event.getSource();
                Rectangle coverUp = new Rectangle(0, 600, 900, 100);
                coverUp.setFill(Color.WHITE);
                Text comboMessage = new Text("You don't have time for reading!");
                comboMessage.setX(20);
                comboMessage.setY(660);
                comboMessage.setFont(new Font(20));
                root.getChildren().addAll(coverUp, comboMessage);
            }
        };
        
        EventHandler drawersMessage = new EventHandler() {
            @Override
            public void handle(Event event) {
                Node source = (Node) event.getSource();
                Rectangle coverUp = new Rectangle(0, 600, 900, 100);
                coverUp.setFill(Color.WHITE);
                Text comboMessage = new Text("These drawers are empty. How boring.");
                comboMessage.setX(20);
                comboMessage.setY(660);
                comboMessage.setFont(new Font(20));
                root.getChildren().addAll(coverUp, comboMessage);
            }
        };

//        cabinetOpen.setOnMouseClicked(findItem);
//        crate.setOnMouseClicked(objClick);
//        crate2.setOnMouseClicked(objClick);
//        crate3.setOnMouseClicked(objClick);
//        crate4.setOnMouseClicked(objClick);
//        crate5.setOnMouseClicked(objClick);
//        bookcase.setOnMouseClicked(bookMessage);
//        bookcase3.setOnMouseClicked(drawersMessage);
//        bookcase4.setOnMouseClicked(bookMessage);
//        desk.setOnMouseClicked(objClick);
//        cabinetClosed1.setOnMouseClicked(objClick);
//        cabinetClosed2.setOnMouseClicked(objClick);
//        cabinetClosed4.setOnMouseClicked(objClick);
//
//        workDesk.setOnMouseClicked(letter);

        roomObjects.getChildren().addAll(crate, crate2, crate3, crate4, crate5, desk, workDesk, bookcase, bookcase3, bookcase4);
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
