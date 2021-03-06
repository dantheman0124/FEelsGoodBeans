package javafxapplication11;

import java.io.FileInputStream;
import java.io.IOException;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Character extends Rectangle {

    //private int x, y;
    private boolean alive;
    private double speed;
    private Image image = null;
    private ImagePattern ip;
    private ArrayList<Interactables> interactables = new ArrayList<>();
    private ArrayList<Bullet> bullets = new ArrayList<>();

    private characterAction lastAction = characterAction.NONE;

    private Pane root = new Pane();
    private boolean shoot = false;

    private HealthBar healthBar = new HealthBar(1);

    characterAction action = characterAction.NONE;

    public Character(double x, double y, boolean alive, double width, double height, ArrayList<Interactables> interactables) {
        super(width, height);
        //this.x = x;
        //this.y = y;
        this.setX(x);
        this.setY(y);
        //this.setWidth(width);
        //this.setHeight(height);
        this.interactables = interactables;
        this.alive = alive;
        this.speed = 5;
        try {
            image = new Image(new FileInputStream("src/Sprites/Player_down.png"), 100, 100, true, true);
        } catch (IOException f) {
            System.out.println(f);
        }
        ip = new ImagePattern(image);
        this.setFill(ip);
        healthBar.setColor(Color.YELLOW);
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAction(characterAction action) {
        this.action = action;
    }

    public characterAction getAction() {
        return action;
    }

    public void setX(int x) {
        //this.x = x;
        super.setX(x);
    }

    public void setY(int y) {
        //this.y = y;
        super.setY(y);
    }

    public void setAlive(boolean b) {
        this.alive = b;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void update() {
        switch (action) {
            case LEFT:
                setTranslateX(getTranslateX() - speed);
                break;
            case RIGHT:
                setTranslateX(getTranslateX() + speed);
                break;
            case UP:
                setTranslateY(getTranslateY() - speed);
                break;
            case DOWN:
                setTranslateY(getTranslateY() + speed);
                break;
            case NONE:
                break;
        }

        if (action != characterAction.NONE) {
            lastAction = action;
        }
    }

    public void update(ArrayList<Node> objects) {
        if (this.checkObstacle(objects)) {
            switch (action) {
                case LEFT:
                    setTranslateX(getTranslateX() - speed);
                    break;
                case RIGHT:
                    setTranslateX(getTranslateX() + speed);
                    break;
                case UP:
                    setTranslateY(getTranslateY() - speed);
                    break;
                case DOWN:
                    setTranslateY(getTranslateY() + speed);
                    break;
                case NONE:
                    break;
            }
        }

        if (action != characterAction.NONE) {
            lastAction = action;
        }

        if (shoot) {
            shootBullet();
            shoot = false;
        }
    }

//    public int checkInRange(ArrayList<Node> interactables) {
//
//        double playerX = CPTRewrite.player.getTranslateX() + CPTRewrite.player.getWidth() / 2;
//        double playerY = CPTRewrite.player.getTranslateY() + CPTRewrite.player.getHeight() / 2;
//        double objectX;
//        double objectY;
//        double closest = 1000;
//        double distance;
//        int index = -1;
//
//        for (int i = 0; i < interactables.size(); i++) {
//            Rectangle object = (Rectangle) interactables.get(i);
//
//            objectX = object.getX() + object.getWidth() / 2;
//            objectY = object.getY() + object.getHeight() / 2;
//
//            if (playerX >= (object.getX() - 100) && playerX <= (object.getX() + object.getWidth() + 100)) {
//                if (playerY >= (object.getY() - 100) && playerY <= (object.getY() + object.getHeight() + 100)) {
//                    distance = Math.sqrt((objectX - playerX) * (objectX - playerX) + (objectY - playerY) * (objectY - playerY));
//                    if (distance < closest) {
//                        closest = distance;
//                        index = i;
//                    }
//                    return index;
//                }
//            }
//        }
//        return index;
//    }
    public boolean checkInRange(ArrayList<Node> interactables) {

        double playerX = CPTRewrite.player.getTranslateX() + CPTRewrite.player.getWidth() / 2;
        double playerY = CPTRewrite.player.getTranslateY() + CPTRewrite.player.getHeight() / 2;

        for (int i = 0; i < interactables.size(); i++) {
            Rectangle object = (Rectangle) interactables.get(i);
            int j = 0;

            //Furniture thing = (Furniture) interactable;
            if (playerX >= (object.getX() - 50) && playerX <= (object.getX() + object.getWidth() + 50)) {
                if (playerY >= (object.getY() - 50) && playerY <= (object.getY() + object.getHeight() + 50)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isColliding(Node other) {
        return getBoundsInParent().intersects(other.getBoundsInParent());

    }

    public boolean checkObstacle(ArrayList<Node> objects) {
        Character temp = new Character(getX(), getY(), isAlive(), getWidth(), getHeight(), interactables);
        temp.setTranslateX(getTranslateX());
        temp.setTranslateY(getTranslateY());
        temp.setAction(this.getAction());
        temp.update();

        for (Node object : objects) {
            if (temp.isColliding(object)) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Interactables> getInteractables() {
        return interactables;
    }

    public HealthBar getHealthBar() {
        return healthBar;
    }

    public void setHealthBar(HealthBar healthBar) {
        this.healthBar = healthBar;
    }

    public boolean canShoot() {
        return shoot;
    }

    public void setShoot(boolean shoot) {
        this.shoot = shoot;
    }

    public void shootBullet() {
        Bullet bullet = new Bullet(this.getX(), this.getY(), 5);
        bullet.setTranslateX(this.getTranslateX() + (this.getWidth() / 2));
        bullet.setTranslateY(this.getTranslateY() + (this.getHeight() / 2));
        switch (lastAction) {
            case RIGHT:
                bullet.setVelocity(new Point2D(speed, 0));
                break;
            case LEFT:
                bullet.setVelocity(new Point2D(-1 * speed, 0));
                break;
            case UP:
                bullet.setVelocity(new Point2D(0, -1 * speed));
                break;
            case DOWN:
                bullet.setVelocity(new Point2D(0, speed));
                break;
        }

        root.getChildren().add(bullet);
        bullets.add(bullet);
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }

    public void setRoot(Pane root) {
        this.root = root;
    }

}
