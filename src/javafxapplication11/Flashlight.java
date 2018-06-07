package javafxapplication11;

import java.io.FileInputStream;
import java.io.IOException;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Flashlight extends Interactables {

    
    private Image image = null;
    private ImagePattern ip;
    private boolean on = false;

    public Flashlight(double x, double y, double width, double height, boolean on) {
        super(x, y, width, height);
        try {
            if (on){
            image = new Image(new FileInputStream("src/Sprites/flashlight_on.png"), 100, 100, true, true);
            } else {
            image = new Image(new FileInputStream("src/Sprites/flashlight_off.png"), 100, 100, true, true);
            }   
        } catch (IOException e) {
        }
        ip = new ImagePattern(image);
        this.setFill(ip);
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }
    
    
}
