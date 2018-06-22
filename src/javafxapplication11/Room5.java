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

public class Room5 extends Room {

    private ArrayList<Node> obj = new ArrayList<>();
    private Group buttons = new Group();
    private String passcode = "";
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

        EventHandler clickLab = new EventHandler() {
            @Override
            public void handle(Event event) {
                Rectangle coverUp = new Rectangle(0, 600, 900, 100);
                coverUp.setFill(Color.WHITE);
                Text enterMessage = new Text("You probably shouldn't be touching this...");
                enterMessage.setX(20);
                enterMessage.setY(660);
                enterMessage.setFont(new Font(20));

                root.getChildren().addAll(coverUp, enterMessage);
            }

        };
        machine.setOnMouseClicked(clickLab);
        machine2.setOnMouseClicked(clickLab);
        machine3.setOnMouseClicked(clickLab);
        machine4.setOnMouseClicked(clickLab);

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

        EventHandler colour = new EventHandler() {
            @Override
            public void handle(Event event) {
                Node source = (Node) event.getSource();
                //System.out.println("Wow these colours look important. Maybe they're used for something?");
                Rectangle coverUp = new Rectangle(0, 600, 900, 100);
                coverUp.setFill(Color.WHITE);
                Text message = new Text("Wow these colours look important. Maybe they're used for something?");
                message.setX(20);
                message.setY(660);
                message.setFont(new Font(20));
                root.getChildren().addAll(coverUp, message);
            }
        };

        beakers.setOnMouseClicked(colour);

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
                if (passcode.equals("918")) {
                    Rectangle coverUp = new Rectangle(0, 600, 900, 100);
                    coverUp.setFill(Color.WHITE);
                    Text message = new Text("tuttifrutti");
                    message.setX(20);
                    message.setY(660);
                    message.setFont(new Font(20));
                    root.getChildren().addAll(coverUp, message);

                } else {
                    Rectangle coverUp = new Rectangle(0, 600, 900, 100);
                    coverUp.setFill(Color.WHITE);
                    Text message = new Text("Wrong code.");
                    message.setX(20);
                    message.setY(660);
                    message.setFont(new Font(20));
                    root.getChildren().addAll(coverUp, message);
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
