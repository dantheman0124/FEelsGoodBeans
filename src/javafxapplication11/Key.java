package javafxapplication11;

import java.io.FileInputStream;
import java.io.IOException;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Key extends Interactables{
    
    private Image image = null;
    private ImagePattern ip;

    public Key(double x, double y, double width, double height) {
        super(x, y, width, height, "key");
        try {
            image = new Image(new FileInputStream("src/Sprites/key.png"), 100, 100, true, true);
        } catch (IOException e) {
        }
        ip = new ImagePattern(image);
        this.setFill(ip);
    }
}
