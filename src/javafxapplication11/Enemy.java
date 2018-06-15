/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication11;

import java.util.ArrayList;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Payam
 */
public class Enemy extends Rectangle{

    private EnemyAction action = EnemyAction.NONE;
    private Node target;
    private double speed = 0.5;
    private HealthBar healthBar = new HealthBar(0.3);
    private boolean alive = true;

    private Pane root;
    private ArrayList<Bullet> bullets;

    private double aimSpeed = 10;

    private Point2D destination, velocity = new Point2D(1, 0);

    public Enemy(double x, double y, double width, double height) {
        super(x, y, width, height);
        healthBar.setColor(Color.BLUE);
    }

    public void setAction(EnemyAction action) {
        this.action = action;
    }

    public EnemyAction getAction() {
        return action;
    }

    public void setTarget(Node target) {
        this.target = target;
    }

    public Node getTarget() {
        return target;
    }

    public void setDestination(Point2D destination) {
        this.destination = destination;
    }

    public Point2D getDestination() {
        return destination;
    }

    public void update() {
        switch (action) {
            case AIM:
                if (Math.abs(this.getRotate() - this.getAimAngle()) < aimSpeed) {
                    this.setAction(EnemyAction.SHOOT);
                    this.setDestination(getRandomDestination(CPTRewrite.rooms.get(1).getROOM_W(), CPTRewrite.rooms.get(1).getROOM_H()));

                } else {
                    this.setRotate(this.getRotate() + (this.getRotate() > this.getAimAngle() ? -1 * aimSpeed : aimSpeed));
                    //aim();
                    //System.out.println(this.getRotate());
                }
                break;
            case MOVE:
                if (Math.abs(this.getX() - destination.getX()) >= speed) {
                    this.setX(this.getX() + (this.getX() < destination.getX() ? speed : -1 * speed));
                }

                if (Math.abs(this.getY() - destination.getY()) >= speed) {
                    this.setY(this.getY() + (this.getY() < destination.getY() ? speed : -1 * speed));
                }

                if (Math.abs(this.getX() - destination.getX()) < speed && Math.abs(this.getY() - destination.getY()) < speed) {
                    this.setAction(EnemyAction.AIM);
                }
                break;
            case SHOOT:
                Bullet bullet = new Bullet(this.getX(), this.getY(), 5);
                double x = Math.sin(Math.toRadians(this.getRotate()));
                double y = -1 * Math.cos(Math.toRadians(this.getRotate()));
                bullet.setVelocity(new Point2D(15 * x, 15 * y));
                root.getChildren().add(bullet);
                bullets.add(bullet);

                this.setAction(EnemyAction.MOVE);
                break;
            case NONE:
                break;

        }

        healthBar.relocate(this.getX(), this.getY() - 20);
        
        if (healthBar.getHealth() < 0) {
            alive = false;
        }
    }

    public void update(ArrayList<Node> all) {
        switch (action) {
            case AIM:
                if (Math.abs(this.getRotate() - this.getAimAngle()) < aimSpeed) {
                    this.setAction(EnemyAction.SHOOT);
                    this.setDestination(getRandomDestination(CPTRewrite.rooms.get(1).getROOM_W(), CPTRewrite.rooms.get(1).getROOM_H(), all));

                } else {
                    this.setRotate(this.getRotate() + (this.getRotate() > this.getAimAngle() ? -1 * aimSpeed : aimSpeed));
                    //aim();
                    //System.out.println(this.getRotate());
                }
                break;
            case MOVE:
                if (Math.abs(this.getX() - destination.getX()) >= speed && checkMove(Move.HORIZONTAL, all)) {
                    this.setX(this.getX() + (this.getX() < destination.getX() ? speed : -1 * speed));
                }

                if (Math.abs(this.getY() - destination.getY()) >= speed && checkMove(Move.VERTICAL, all)) {
                    this.setY(this.getY() + (this.getY() < destination.getY() ? speed : -1 * speed));
                }

                if (Math.abs(this.getX() - destination.getX()) < speed && Math.abs(this.getY() - destination.getY()) < speed) {
                    this.setAction(EnemyAction.AIM);
                }

                if (!checkMove(Move.HORIZONTAL, all) && !checkMove(Move.VERTICAL, all)) {
                    this.setAction(EnemyAction.AIM);
                }

                if (Math.abs(this.getX() - destination.getX()) <= speed && !checkMove(Move.VERTICAL, all)) {
                    this.setAction(EnemyAction.AIM);
                }

                if (Math.abs(this.getY() - destination.getY()) <= speed && !checkMove(Move.HORIZONTAL, all)) {
                    this.setAction(EnemyAction.AIM);
                }

                break;
            case SHOOT:
                Bullet bullet = new Bullet(this.getX(), this.getY(), 5);
                double x = Math.sin(Math.toRadians(this.getRotate()));
                double y = -1 * Math.cos(Math.toRadians(this.getRotate()));
                bullet.setVelocity(new Point2D(15 * x, 15 * y));
                root.getChildren().add(bullet);
                bullets.add(bullet);

                this.setAction(EnemyAction.MOVE);
                break;
            case NONE:
                break;

        }

        healthBar.relocate(this.getX(), this.getY() - 20);
        
        if (healthBar.getHealth() < 0) {
            alive = false;
        }

    }

    public void aim() {
        double x = target.getTranslateX() - this.getX();
        double y = target.getTranslateY() - this.getY();
        this.setRotate(Math.toDegrees(inverseTan(x, -1 * y)));
    }

    public double getAimAngle() {
        double x = target.getTranslateX() - this.getX();
        double y = target.getTranslateY() - this.getY();
        return Math.toDegrees(inverseTan(x, -1 * y));
    }

    public double inverseTan(double x, double y) {
        double X = Math.abs(x);
        double Y = Math.abs(y);
        if (x >= 0) {
            if (y >= 0) {
                return Math.atan(X / Y);
            } else {
                return Math.atan(Y / X) + (Math.PI / 2);
            }
        } else {
            if (y >= 0) {
                return Math.atan(Y / X) + ((3 * Math.PI) / 2);
            } else {
                return Math.atan(X / Y) + (Math.PI);
            }
        }
    }

    public void setRoot(Pane root) {
        this.root = root;
    }

    public Pane getRoot() {
        return root;
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public HealthBar getHealthBar() {
        return healthBar;
    }

    public void setHealthBar(HealthBar healthBar) {
        this.healthBar = healthBar;
    }

    public boolean checkDestination(Point2D destination, ArrayList<Node> objects) {
        boolean result;

        Enemy temp = new Enemy(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        temp.setTranslateX(destination.getX());
        temp.setTranslateY(destination.getY());
        temp.setRotate(this.getRotate());

        for (Node object : objects) {
            if (temp.isColliding(object)) {
                return false;
            }
        }

        return true;
    }

    public boolean isColliding(Node node) {
        return this.getBoundsInParent().intersects(node.getBoundsInParent());
    }

    public boolean isColliding(ArrayList<Node> all) {
        for (Node node : all) {
            if (this.isColliding(node)) {
                return true;
            }
        }

        return false;
    }

    private enum Move {
        HORIZONTAL, VERTICAL;
    }

    public boolean checkMove(Move move, ArrayList<Node> objects) {
        boolean result = true;
        Enemy temp = new Enemy(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        temp.setTranslateX(this.getTranslateX());
        temp.setTranslateY(this.getTranslateY());
        temp.setRotate(this.getRotate());

        switch (move) {
            case HORIZONTAL:
                temp.setX(temp.getX() + (temp.getX() < destination.getX() ? speed : -1 * speed));
                break;
            case VERTICAL:
                temp.setY(temp.getY() + (temp.getY() < destination.getY() ? speed : -1 * speed));
                break;
        }

        for (Node object : objects) {
            if (temp.isColliding(object)) {
                result = false;
                break;
            }
        }

        return result;
    }

    public boolean checkLocation(Point2D location, ArrayList<Node> all) {
        Enemy enemy = new Enemy(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        enemy.setTranslateX(location.getX());
        enemy.setTranslateY(location.getY());
        enemy.setRotate(this.getRotate());

        return !enemy.isColliding(all);
    }

    public Point2D getRandomDestination(double width, double height) {
        return new Point2D(Math.random() * width, Math.random() * height);
    }

    public Point2D getRandomDestination(double width, double height, ArrayList<Node> all) {
        Point2D output;
        do {
            output = getRandomDestination(width, height);
        } while (!checkLocation(output, all));

        return output;
    }
    
    public boolean isDead() {
        return !alive;
    }
    
    

}
