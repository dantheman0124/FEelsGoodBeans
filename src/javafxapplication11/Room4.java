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
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Room4 extends Room {

    private ArrayList<Node> obj = new ArrayList<>();
    private Group buttons = new Group();
    private String passcode = "";
    private KeyFrame frame = new KeyFrame(Duration.seconds(0.016), e -> {
        getPlayer().update(obj);

        //displayInv();
        for (int i = 0; i < interactables.getChildren().size(); i++) {
            if (getPlayer().getBoundsInParent().intersects(interactables.getChildren().get(i).getBoundsInParent())) {
                player.getInteractables().add((Interactables) interactables.getChildren().get(i));
                interactables.getChildren().remove(interactables.getChildren().get(i));
                break;
            }
        }

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

        setEnterSpawnX(enterX - getDOOR_W() - getPLAYER_W() - 35);
        setEnterSpawnY(enterY + getDOOR_H() / 2 - getPLAYER_H() / 2);

        setExitSpawnX(exitX + getDOOR_H() / 2 - getPLAYER_H() / 2 - 35);
        setExitSpawnY(exitY + getDOOR_W() + 30);

        enterSpawnX = getEnterSpawnX();
        enterSpawnY = getEnterSpawnY();

        exitSpawnX = getExitSpawnX();
        exitSpawnY = getExitSpawnY();

        root.getChildren().addAll(floor, walls, roomObjects, interactables, doors, inv);

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

        //wooden floor
        floor = new Group();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 14; j++) {
                if (i < 3 || j > 3) {
                    Floor tile = new Floor(i * 100, j * 39 + 48, 120, 50, "bedroomWood");
                    floor.getChildren().add(tile);
                }
            }
        }

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 2; j++) {
                Floor tile = new Floor(i * 95 + 305, 43 + j * 88, 110, 105, "blueTile");
                floor.getChildren().add(tile);
            }
        }
        FloorMat mat = new FloorMat(90, 70, 75, 75);
        floor.getChildren().addAll(mat);
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

        Table table = new Table(90, 450, 145, 120, "greenDiningTable");
        Table table1 = new Table(665, 450, 145, 120, "greenDiningTable");
        Table table2 = new Table(368, 450, 145, 120, "greenDiningTable");

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

        int x = 300, y = 260;
        Kitchen couchL = new Kitchen(x, y, 60, 120, "couchL");
        Table prettyTable = new Table(x + 145, y, 84, 120, "prettyTable");
        Kitchen couchR = new Kitchen(x + 305, y, 60, 120, "couchR");

        Kitchen plant1 = new Kitchen(270, y + 10, 30, 90, "plant");
        Kitchen plant2 = new Kitchen(260, y + 20, 30, 90, "plant");
        Kitchen plant3 = new Kitchen(270, y + 30, 30, 90, "plant");

        x = 200;
        Button btn1 = new Button("1");
        btn1.setTranslateX(x);
        btn1.setTranslateY(640);
        btn1.setScaleX(1);
        btn1.setScaleY(1);
        Button btn2 = new Button("2");
        btn2.setTranslateX(x + 40);
        btn2.setTranslateY(640);
        btn2.setScaleX(1);
        btn2.setScaleY(1);
        Button btn3 = new Button("3");
        btn3.setTranslateX(x + 80);
        btn3.setTranslateY(640);
        btn3.setScaleX(1);
        btn3.setScaleY(1);
        Button btn4 = new Button("4");
        btn4.setTranslateX(x + 120);
        btn4.setTranslateY(640);
        btn4.setScaleX(1);
        btn4.setScaleY(1);
        Button btn5 = new Button("5");
        btn5.setTranslateX(x + 160);
        btn5.setTranslateY(640);
        btn5.setScaleX(1);
        btn5.setScaleY(1);
        Button btn6 = new Button("6");
        btn6.setTranslateX(x + 200);
        btn6.setTranslateY(640);
        btn6.setScaleX(1);
        btn6.setScaleY(1);
        Button btn7 = new Button("7");
        btn7.setTranslateX(x + 240);
        btn7.setTranslateY(640);
        btn7.setScaleX(1);
        btn7.setScaleY(1);
        Button btn8 = new Button("8");
        btn8.setTranslateX(x + 280);
        btn8.setTranslateY(640);
        btn8.setScaleX(1);
        btn8.setScaleY(1);
        Button btn9 = new Button("9");
        btn9.setTranslateX(x + 320);
        btn9.setTranslateY(640);
        btn9.setScaleX(1);
        btn9.setScaleY(1);
        Button btn0 = new Button("0");
        btn0.setTranslateX(x + 360);
        btn0.setTranslateY(640);
        btn0.setScaleX(1);
        btn0.setScaleY(1);
        Button enterButton = new Button("Enter");
        enterButton.setTranslateX(x + 400);
        enterButton.setTranslateY(640);
        enterButton.setScaleX(1);
        enterButton.setScaleY(1);
        buttons.getChildren().addAll(btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9);

        for (int i = 0; i < buttons.getChildren().size(); i++) {
            Button btn = (Button) buttons.getChildren().get(i);

            EventHandler click = new EventHandler() {
                @Override
                public void handle(Event event) {
                    passcode += btn.getText();
                }
            };
            btn.setOnMouseClicked(click);
        }

        EventHandler clickEnter = new EventHandler() {
            @Override
            public void handle(Event event) {
                if (passcode.equals("8240")) {
                    Key key = new Key(-100, -100, 0, 0);
                    CPTRewrite.player.getInteractables().add(key);
                    System.out.println("You found a key!");
                    Room6.nextRoom = true;
                } else {
                    System.out.println("Wrong code.");
                }
            }
        };
        
        enterButton.setOnMouseClicked(clickEnter);

        EventHandler enterCode = new EventHandler() {
            @Override
            public void handle(Event event) {
                Scanner input = new Scanner(System.in);
                Node source = (Node) event.getSource();
                Rectangle coverUp = new Rectangle(0, 600, 900, 100);
                coverUp.setFill(Color.WHITE);
                Text nothingMessage = new Text("Enter a passcode: ");
                nothingMessage.setX(20);
                nothingMessage.setY(660);
                nothingMessage.setFont(new Font(20));

                root.getChildren().addAll(coverUp, nothingMessage, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, enterButton);

            }
        };

        EventHandler trashMessage = new EventHandler() {
            @Override
            public void handle(Event event) {
                Node source = (Node) event.getSource();
                Rectangle coverUp = new Rectangle(0, 600, 900, 100);
                coverUp.setFill(Color.WHITE);
                Text comboMessage = new Text("Are these trash cans? Vases? Mario pipes?");
                comboMessage.setX(20);
                comboMessage.setY(660);
                comboMessage.setFont(new Font(20));
                root.getChildren().addAll(coverUp, comboMessage);

            }
        };

        EventHandler stovePotsMessage = new EventHandler() {
            @Override
            public void handle(Event event) {
                Node source = (Node) event.getSource();
                Rectangle coverUp = new Rectangle(0, 600, 900, 100);
                coverUp.setFill(Color.WHITE);
                Text comboMessage = new Text("There's no food in these pots. Why are they even out?");
                comboMessage.setX(20);
                comboMessage.setY(660);
                comboMessage.setFont(new Font(20));
                root.getChildren().addAll(coverUp, comboMessage);
            }
        };

        microwave.setOnMouseClicked(enterCode);
        trash1.setOnMouseClicked(trashMessage);
        trash2.setOnMouseClicked(trashMessage);
        trash3.setOnMouseClicked(trashMessage);
        trash4.setOnMouseClicked(trashMessage);
        stoveAndPots.setOnMouseClicked(stovePotsMessage);

        int n = 667;
        Kitchen plant4 = new Kitchen(n, y + 10, 30, 90, "plant");
        Kitchen plant5 = new Kitchen(n + 10, y + 20, 30, 90, "plant");
        Kitchen plant6 = new Kitchen(n, y + 30, 30, 90, "plant");

        roomObjects.getChildren().addAll(table, table1, table2, sideTable, stoveTeapot, lightCounter, longCounter, stoveAndPots, microwave, cornerCounter);
        roomObjects.getChildren().addAll(plant1, plant2, plant3, plant4, plant5, plant6, trash1, trash2, trash3, trash4, couchL, couchR, prettyTable);
    }

    @Override
    public void createInteractables() {
        interactables = new Group();
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
