/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication11;

import java.io.FileInputStream;
import java.io.IOException;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 *
 * @author Daniel Bassani
 */
public class Chair extends Furniture {

    private Image image = null;
    private ImagePattern ip;
    private boolean left;

    public Chair(double x, double y, double width, double height, boolean left) {
        super(x, y, width, height);
        try {
            if (left) {
                image = new Image(new FileInputStream("src/Sprites/Chair_left.png"), 100, 100, true, true);
            } else {
                image = new Image(new FileInputStream("src/Sprites/Chair_right.png"), 100, 100, true, true);
            }
        } catch (IOException e) {
        }
        ip = new ImagePattern(image);
        this.setFill(ip);
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }
    
    
}
