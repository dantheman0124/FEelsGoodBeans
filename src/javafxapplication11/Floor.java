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
public class Floor extends Furniture {

    private Image image = null;
    private ImagePattern ip;
    private String floorPattern = "";

    public Floor(double x, double y, double width, double height, String floorPattern) {
        super(x, y, width, height);
        try {
            if (floorPattern.equals("bathroomGrey")) {
                image = new Image(new FileInputStream("src/Sprites/bathroomTile.png"), 100, 100, true, true);
            } else if (floorPattern.equals("bedroomWood")) {
                image = new Image(new FileInputStream("src/Sprites/bedroomWoodFloor.png"), 100, 100, true, true);
            } else if (floorPattern.equals("bedRug")) {
                image = new Image(new FileInputStream("src/Sprites/BedRug.png"), 100, 100, true, true);
            } else if (floorPattern.equals("whiteTile")) {
                image = new Image(new FileInputStream("src/Sprites/WhiteFloorTile.png"), 100, 100, true, true);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        ip = new ImagePattern(image);
        this.setFill(ip);
    }
}
