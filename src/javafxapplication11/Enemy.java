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
public class Enemy extends Rectangle {

    private EnemyAction action = EnemyAction.NONE;
    private Node target;
    private double speed = 20;
    private HealthBar healthBar = new HealthBar();
    
    private Pane root;
    private ArrayList<Bullet> bullets;

    private double aimSpeed = 1;

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
    }

    public void aim() {
        double x = target.getTranslateX() - this.getX();
        double y = target.getTranslateY() - this.getY();
        this.setRotate(Math.toDegrees(inverseTan(x, -1 * y)));
    }

    public double getAimAngle() {
        double x = target.getTranslateX() - this.getX();
        double y = target.getTranslateY() - this.getY();
        return Math.toDegrees(inverseTan(x, -1 *y));
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
    
    
    
    

}
