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
public class DiningTable extends Furniture {
    private Image image = null;
    private ImagePattern ip;

    public DiningTable(double x, double y, double width, double height) {
        super(x, y, width, height);
        try {
            image = new Image(new FileInputStream("src/Sprites/Dining_Table.png"), 100, 100, true, true);
        } catch (IOException e) {
        }
        ip = new ImagePattern(image);
        this.setFill(ip);
    }
}
