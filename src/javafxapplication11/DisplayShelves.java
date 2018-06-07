/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication11;

import javafxapplication11.Furniture;
import java.io.FileInputStream;
import java.io.IOException;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 *
 * @author Daniel Bassani
 */
public class DisplayShelves extends Furniture {

    private Image image = null;
    private ImagePattern ip;
    private boolean display;

    public DisplayShelves(double x, double y, double width, double height, boolean display) {
        super(x, y, width, height);
        this.display = display;
        try {
            if (display) {
                image = new Image(new FileInputStream("src/Sprites/Display_Shelf.png"), 100, 100, true, true);
            } else {
                image = new Image(new FileInputStream("src/Sprites/Research_Shelf.png"), 100, 100, true, true);
            }
        } catch (IOException e) {
        }
        ip = new ImagePattern(image);
        this.setFill(ip);
    }

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }
    
    
}
