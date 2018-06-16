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
 * @author Emma
 */
public class Room8Stuff extends Furniture{
    
     private Image image = null;
    private ImagePattern ip;
    private String room8Type = "";

    public Room8Stuff(double x, double y, double width, double height, String room8Type) {
        super(x, y, width, height);
        try {
            if (room8Type.equals("diningset")) {
                image = new Image(new FileInputStream("src/Sprites/diningSet.png"), 100, 100, true, true);
            } 
            else if (room8Type.equals("piano")) {
                image = new Image (new FileInputStream("src/Sprites/piano.png"), 100, 100, true, true);
            }
        } catch (IOException e) {
        }
        ip = new ImagePattern(image);
        this.setFill(ip);
    }



       
    
}
