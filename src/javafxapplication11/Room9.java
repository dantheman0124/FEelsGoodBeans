package javafxapplication11;

import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Room9 extends Room {

    private ArrayList<Node> obj = new ArrayList<>();
    private KeyFrame frame = new KeyFrame(Duration.seconds(0.016), e -> {
        getPlayer().update(obj);

        //displayInv();
        if (getPlayer().isColliding(doors.getChildren().get(0))) {
            CPTRewrite.prevRoom();
        }
        
        if (player.getHealthBar().getHealth() <= 0) {
            this.stop();
            root.getChildren().removeAll(root.getChildren());
            Rectangle bg = new Rectangle(0, 0, 900, 700);
            bg.setFill(Color.BLACK);
            player.getHealthBar().setHealth(1);
            Text display = new Text("GAME OVER");
            display.setFill(Color.RED);
            display.setTranslateX(325);
            display.setTranslateY(350);
            display.setFont(Font.font(40));
            root.getChildren().addAll(bg, display);
        }
    });

    public Room9() {
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

        setEnterSpawnX(enterX + getDOOR_H() / 2 - getPLAYER_H() / 2 - 35);
        setEnterSpawnY(enterY + getDOOR_W() + 20);

        enterSpawnX = getEnterSpawnX();
        enterSpawnY = getEnterSpawnY();

        exitSpawnX = getExitSpawnX();
        exitSpawnY = getExitSpawnY();

        root.getChildren().addAll(floor, walls, doors, inv);

        scene = new Scene(root, getSCENE_W(), getSCENE_H());

        setKeyHandlers();
    }

    @Override
    public void createWalls() {
        walls = new Group();

        Rectangle doorEnter = (Rectangle) doors.getChildren().get(0);
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

        //right wall 
        rect = new Rectangle(getWALL_W(), getROOM_H(), wallsColor);
        rect.setTranslateX(getROOM_W() - getWALL_W());
        rect.setTranslateY(getHEADER_H());
        walls.getChildren().add(rect);

        //divider walls
        
        //left vertical divider wall 
        rect = new Rectangle(getWALL_W(), getROOM_H() / 4, wallsColor);
        rect.setTranslateX(getROOM_W() / 4 - getWALL_W());
        rect.setTranslateY(getROOM_H() / 4 + getHEADER_H());
        walls.getChildren().add(rect);

        //right vertical divider wall 
        rect = new Rectangle(getWALL_W(), getROOM_H() / 4, wallsColor);
        rect.setTranslateX(getROOM_W() / 4 * 3);
        rect.setTranslateY(getROOM_H() / 4 * 2 + getHEADER_H() + getWALL_W());
        walls.getChildren().add(rect);

        //right  horizontal divider wall 
        rect = new Rectangle(getROOM_W() / 4, getWALL_W(), wallsColor);
        rect.setTranslateX(getROOM_W() / 2);
        rect.setTranslateY(getROOM_H() / 4 + getWALL_W() * 2);
        walls.getChildren().add(rect);

        //left horizontal divider wall 
        rect = new Rectangle(getROOM_W() / 4, getWALL_W(), wallsColor);
        rect.setTranslateX(getROOM_W() / 2 +getWALL_W());
        rect.setTranslateY(getROOM_H() / 4 * 3 + getWALL_W() * 3);
        walls.getChildren().add(rect);

        floor = new Group();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 14; j++) {
                Floor tile = new Floor(i * 100, j * 39 + 48, 120, 50, "bedroomWood");
                floor.getChildren().add(tile);
            }
        }
    }

    @Override
    public void createDoors() {
        doors = new Group();

        Rectangle door;

        // door enter (0)
        door = new Rectangle(getDOOR_H(), getDOOR_W(), doorColor);
        door.setTranslateX(getROOM_W() / 2 - getDOOR_H() / 2);
        door.setTranslateY(getHEADER_H());

        doors.getChildren().add(door);

    }

    @Override
    public void fillRoom() {
        roomObjects = new Group();
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
