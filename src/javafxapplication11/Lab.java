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
public class Lab extends Furniture {

    private Image image = null;
    private ImagePattern ip;
    private String labType = "";

    public Lab(double x, double y, double width, double height, String labType) {
        super(x, y, width, height);
        try {
            if (labType.equals("beakers")) {
                image = new Image(new FileInputStream("src/Sprites/beakers.png"), 100, 100, true, true);
            } else if (labType.equals("lockers")) {
                image = new Image(new FileInputStream("src/Sprites/lockers.png"), 100, 100, true, true);
            } else if (labType.equals("healingMachine")) {
                image = new Image(new FileInputStream("src/Sprites/Healing_Machine.png"), 100, 100, true, true);
            } else if (labType.equals("scienceDesk")) {
                image = new Image(new FileInputStream("src/Sprites/Science_Computer_Desk.png"), 100, 100, true, true);
            } else if (labType.equals("displayShelf")) {
                image = new Image(new FileInputStream("src/Sprites/Display_Shelf.png"), 100, 100, true, true);
            } else if (labType.equals("researchShelf")) {
                image = new Image(new FileInputStream("src/Sprites/Research_Shelf.png"), 100, 100, true, true);
            }

        } catch (IOException e) {
        }
        ip = new ImagePattern(image);
        this.setFill(ip);

    }
}
