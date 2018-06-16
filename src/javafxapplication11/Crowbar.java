package javafxapplication11;

import java.io.FileInputStream;
import java.io.IOException;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Crowbar extends Interactables {

    private Image image = null;
    private ImagePattern ip;

    public Crowbar(double x, double y, double width, double height) {
        super(x, y, width, height, "crowbar");
        try {
            image = new Image(new FileInputStream("src/Sprites/crowbar.png"), 100, 100, true, true);
        } catch (IOException e) {
        }
        ip = new ImagePattern(image);
        this.setFill(ip);
    }
}
