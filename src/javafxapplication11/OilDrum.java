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
public class OilDrum extends Furniture {

    private Image image = null;
    private ImagePattern ip;
    private boolean set;

    public OilDrum(double x, double y, double width, double height, boolean set) {
        super(x, y, width, height);
        this.set = set;
        try {
            if(set){
            image = new Image(new FileInputStream("src/Sprites/Big_Oil_Drum.png"), 100, 100, true, true);
            }else{
               image = new Image(new FileInputStream("src/Sprites/Oil_Drum.png"), 100, 100, true, true); 
            }
        } catch (IOException e) {
        }
        ip = new ImagePattern(image);
        this.setFill(ip);
    }

    public boolean isSet() {
        return set;
    }

    public void setSet(boolean set) {
        this.set = set;
    }
    
    
}
