/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication11;

import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;

public class Bullet extends Circle {

    private Point2D velocity;
    private double speed;
    private int damage;

    public void setVelocity(Point2D velocity) {
        this.velocity = new Point2D(speed * velocity.normalize().getX(), speed * velocity.normalize().getY());
    }

    public Point2D getVelocity() {
        return velocity;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void update() {
        this.setTranslateX(this.getTranslateX() + velocity.getX());
        this.setTranslateY(this.getTranslateY() + velocity.getY());
    }

}
