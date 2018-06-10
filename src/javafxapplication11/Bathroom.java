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
 * @author along
 */
public class Bathroom extends Furniture {

    private Image image = null;
    private ImagePattern ip;
    private String bathroom = "";

    public Bathroom(double x, double y, double width, double height, String bathroom) {
        super(x, y, width, height);
        try {
            if (bathroom.equals("sink")) {
                image = new Image(new FileInputStream("src/Sprites/SinkAndCounter.png"), 100, 100, true, true);
            } else if (bathroom.equals("shower")) {
                image = new Image(new FileInputStream("src/Sprites/Shower.png"), 100, 100, true, true);
            } else if (bathroom.equals("toilet")) {
                image = new Image(new FileInputStream("src/Sprites/Toilet.png"), 100, 100, true, true);
            } else if (bathroom.equals("bathtub")) {
                image = new Image(new FileInputStream("src/Sprites/Bathtub.png"), 100, 100, true, true);
            } 
        } catch (IOException e) {
            System.out.println(e);
        }
        ip = new ImagePattern(image);
        this.setFill(ip);
    }
}
