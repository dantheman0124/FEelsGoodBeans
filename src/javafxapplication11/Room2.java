package javafxapplication11;

import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Room2 extends Room {

    public static boolean nextRoom = false;
    public static boolean haveBattery = false;

    private ArrayList<Node> obj = new ArrayList<>();

    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private ArrayList<Bullet> bullets = new ArrayList<Bullet>();

    private KeyFrame frame = new KeyFrame(Duration.seconds(0.016), e -> {
        getPlayer().update(obj);
        for (Enemy enemy : enemies) {
            enemy.update(obj);
        }
        bullets.forEach(Bullet::update);
        player.getBullets().forEach(Bullet::update);

        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            for (int j = 0; j < roomObjects.getChildren().size(); j++) {
                if (bullet.getBoundsInParent().intersects(roomObjects.getChildren().get(j).getBoundsInParent())) {
                    root.getChildren().remove(bullet);
                    bullets.remove(bullet);
                }
            }

            for (int k = 0; k < walls.getChildren().size(); k++) {
                if (bullet.getBoundsInParent().intersects(walls.getChildren().get(k).getBoundsInParent())) {
                    root.getChildren().remove(bullet);
                    bullets.remove(bullet);
                }
            }

            for (int k = 0; k < doors.getChildren().size(); k++) {
                if (bullet.getBoundsInParent().intersects(doors.getChildren().get(k).getBoundsInParent())) {
                    root.getChildren().remove(bullet);
                    bullets.remove(bullet);
                }
            }

            if (player.isColliding((Node) bullet)) {
                root.getChildren().remove(bullet);
                bullets.remove(bullet);
                player.getHealthBar().loseHealth(1);
                player.getHealthBar().update();
            }
        }

        for (int i = 0; i < player.getBullets().size(); i++) {
            Bullet bullet = player.getBullets().get(i);
            for (int j = 0; j < roomObjects.getChildren().size(); j++) {
                if (bullet.getBoundsInParent().intersects(roomObjects.getChildren().get(j).getBoundsInParent())) {
                    root.getChildren().remove(bullet);
                    player.getBullets().remove(bullet);
                }
            }

            for (int k = 0; k < walls.getChildren().size(); k++) {
                if (bullet.getBoundsInParent().intersects(walls.getChildren().get(k).getBoundsInParent())) {
                    root.getChildren().remove(bullet);
                    player.getBullets().remove(bullet);
                }
            }

            for (int k = 0; k < doors.getChildren().size(); k++) {
                if (bullet.getBoundsInParent().intersects(doors.getChildren().get(k).getBoundsInParent())) {
                    root.getChildren().remove(bullet);
                    player.getBullets().remove(bullet);
                }
            }

            for (int l = 0; l < enemies.size(); l++) {
                Enemy enemy = enemies.get(l);
                if (enemy.isColliding(bullet)) {
                    enemy.getHealthBar().loseHealth(10);
                    enemy.getHealthBar().update();
                    root.getChildren().remove(bullet);
                    player.getBullets().remove(bullet);
                }

                if (enemy.isDead()) {
                    enemies.remove(enemy);
                    root.getChildren().remove(enemy);
                    root.getChildren().remove(enemy.getHealthBar());
                }
            }
        }

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

        if (nextRoom && haveBattery) {
            walls.getChildren().remove(walls.getChildren().size() - 1);
            doors.getChildren().get(doors.getChildren().size() - 1).setTranslateY(doors.getChildren().get(doors.getChildren().size() - 1).getTranslateY() + 16);
            nextRoom = false;
            haveBattery = false;
        }
    }
    );

    public Room2() {
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

        setEnterSpawnX(enterX + getDOOR_W());
        setEnterSpawnY(enterY + getDOOR_H() / 2 - getPLAYER_H() / 2);

        setExitSpawnX(exitX + getDOOR_H() / 2 - getPLAYER_W() / 2 - 35);
        setExitSpawnY(exitY + getDOOR_W() + 35);

        enterSpawnX = getEnterSpawnX();
        enterSpawnY = getEnterSpawnY();

        exitSpawnX = getExitSpawnX();
        exitSpawnY = getExitSpawnY();

        root.getChildren().addAll(floor, walls, roomObjects, inv, doors, interactables);

        scene = new Scene(root, getSCENE_W(), getSCENE_H());

        for (int i = 0; i < 5; i++) {
            Enemy enemy = new Enemy(30, 70, 20, 50);
            enemy.setAction(EnemyAction.MOVE);
            enemy.setRoot(root);
            enemy.setBullets(bullets);
            enemy.setTarget(CPTRewrite.player);
            double startX = this.getWALL_W();
            double startY = this.getHEADER_H() + this.getWALL_W();
            double width = this.getROOM_W() - 2 * this.getWALL_W();
            double height = this.getROOM_H() - 2 * this.getWALL_W();
            enemy.setDestination(enemy.getRandomDestination(startX, startY, width, height, obj));
            enemy.setRoom(this);

            enemies.add(enemy);
            root.getChildren().add(enemy.getHealthBar());
        }
        root.getChildren().addAll(enemies);
        root.getChildren().add(CPTRewrite.player.getHealthBar());

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

        //right wall 
        rect = new Rectangle(getWALL_W(), getROOM_H(), wallsColor);
        rect.setTranslateX(getROOM_W() - getWALL_W());
        rect.setTranslateY(getHEADER_H());

        walls.getChildren().add(rect);

        //in front of door
        rect = new Rectangle(doorExit.getTranslateX(), getWALL_W(), wallsColor);
        rect.setTranslateX(150);
        rect.setTranslateY(getHEADER_H());

        walls.getChildren().add(rect);

        floor = new Group();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 14; j++) {
                Floor tile = new Floor(i * 100, j * 39 + 48, 120, 50, "bedroomWood");
                floor.getChildren().add(tile);
            }
        }
        FloorMat mat = new FloorMat(590, 70, 75, 75);
        floor.getChildren().addAll(mat);
    }

    @Override
    public void createDoors() {
        doors = new Group();

        Rectangle door;

        // door enter (0)
        door = new Rectangle(getDOOR_W(), getDOOR_H(), doorColor);
        door.setTranslateX(0);
        door.setTranslateY(200 + getHEADER_H());

        doors.getChildren().add(door);

        // door exit (1)
        door = new Rectangle(getDOOR_H(), getDOOR_W(), doorColor);
        door.setTranslateX(getROOM_W() - getDOOR_H() - 200);
        door.setTranslateY(getHEADER_H());

        doors.getChildren().add(door);
    }

    @Override
    public void fillRoom() {
        roomObjects = new Group();

        EventHandler findItem = new EventHandler() {
            @Override
            public void handle(Event event) {
                Node source = (Node) event.getSource();
                System.out.println("You found a battery!");
                Battery battery = new Battery(-100, -100, 0, 0);
                CPTRewrite.inventory.add(battery);
                roomObjects.getChildren().remove(source);
                haveBattery = true;
            }
        };

        Crate crate = new Crate(20, 470, 50, 50);
        Crate crate2 = new Crate(20, 500, 50, 50);
        Crate crate3 = new Crate(20, 530, 50, 50);
        Crate crate4 = new Crate(250, 530, 50, 50);
        Crate crate5 = new Crate(300, 530, 50, 50);
        OilDrum bod1 = new OilDrum(805, 505, 70, 70, true);
        OilDrum bod2 = new OilDrum(725, 505, 70, 70, true);
        OilDrum bod3 = new OilDrum(465, 265, 70, 70, true);
        OilDrum od1 = new OilDrum(423, 265, 30, 57, false);
        OilDrum od2 = new OilDrum(383, 265, 30, 57, false);
        
        crate.setOnMouseClicked(findItem);

        roomObjects.getChildren().addAll(crate, crate2, crate3, crate4, crate5, bod1, bod2, bod3, od1, od2);
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
