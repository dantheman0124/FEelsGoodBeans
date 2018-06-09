package javafxapplication11;

import java.io.FileInputStream;
import java.io.IOException;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Bedroom extends Furniture {

    private Image image = null;
    private ImagePattern ip;
    private String bedroomType = "";

    public Bedroom(double x, double y, double width, double height, String bedroomType) {
        super(x, y, width, height);
        try {
            if (bedroomType.equals("doublebed")) {
                image = new Image(new FileInputStream("src/Sprites/doubleBed.png"), 100, 100, true, true);
            } else if (bedroomType.equals("bookontable")) {
                image = new Image(new FileInputStream("src/Sprites/bedsideTableBook.png"), 100, 100, true, true);
            } else if (bedroomType.equals("bedsidetable")) {
                image = new Image(new FileInputStream("src/Sprites/bedsideTableEmpty.png"), 100, 100, true, true);
            } else if (bedroomType.equals("vaseontable")) {
                image = new Image(new FileInputStream("src/Sprites/bedsideTableVase.png"), 100, 100, true, true);
            } else if (bedroomType.equals("leftcouch")) {
                image = new Image(new FileInputStream("src/Sprites/CouchL.png"), 100, 100, true, true);
            } else if (bedroomType.equals("rightcouch")) {
                image = new Image(new FileInputStream("src/Sprites/CouchR.png"), 100, 100, true, true);
            } else if (bedroomType.equals("bedcabinet")) {
                image = new Image(new FileInputStream("src/Sprites/cabinetBedroom.png"), 100, 100, true, true);
            } else if (bedroomType.equals("TV")) {
                image = new Image(new FileInputStream("src/Sprites/TV.png"), 100, 100, true, true);
            } else if (bedroomType.equals("bedPink")) {
                image = new Image(new FileInputStream("src/Sprites/bedPink.png"), 100, 100, true, true);
            } else if (bedroomType.equals("bedsideH")) {
                image = new Image(new FileInputStream("src/Sprites/BedsideTableH.png"), 100, 100, true, true);
            } else if (bedroomType.equals("bedsideV")) {
                image = new Image(new FileInputStream("src/Sprites/BedsideTableV.png"), 100, 100, true, true);
            } else if (bedroomType.equals("blackstool")) {
                image = new Image(new FileInputStream("src/Sprites/BlackStool.png"), 100, 100, true, true);
            } else if (bedroomType.equals("armchairL")) {
                image = new Image(new FileInputStream("src/Sprites/ArmChairL.png"), 100, 100, true, true);
            } else if (bedroomType.equals("armchairR")) {
                image = new Image(new FileInputStream("src/Sprites/ArmChairR.png"), 100, 100, true, true);
            } 
        } catch (IOException e) {
        }
        ip = new ImagePattern(image);
        this.setFill(ip);
    }

    public String getBedroomType() {
        return bedroomType;
    }

    public void setBedroomType(String kitchenType) {
        this.bedroomType = kitchenType;
    }

}
