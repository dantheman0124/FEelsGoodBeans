/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication11;

import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;

public class Bullet extends Circle{
    Point2D velocity = new Point2D(0, 0);

    public Bullet(double centerX, double centerY, double radius) {
        super(centerX, centerY, radius);
    }

    public void setVelocity(Point2D velocity) {
        this.velocity = velocity;
    }

    public Point2D getVelocity() {
        return velocity;
    }
    
    public void update () {
        this.setCenterX(this.getCenterX() + velocity.getX());
        this.setCenterY(this.getCenterY()+ velocity.getY());
    }
}

