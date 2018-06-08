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
public class Office extends Furniture {

    private Image image = null;
    private ImagePattern ip;
    private String officeType = "";

    public Office(double x, double y, double width, double height, String officeType) {
        super(x, y, width, height);
        try {
            if (officeType.equals("redBlue")) {
                image = new Image(new FileInputStream("src/Sprites/bookshelfRedBlue.png"), 100, 100, true, true);
            } else if (officeType.equals("redGreen")) {
                image = new Image(new FileInputStream("src/Sprites/bookshelfRedGreen.png"), 100, 100, true, true);
            } else if (officeType.equals("redGreenBlue")) {
                image = new Image(new FileInputStream("src/Sprites/bookshelfRedGreenBlue.png"), 100, 100, true, true);
            } else if (officeType.equals("greenBlue")) {
                image = new Image(new FileInputStream("src/Sprites/bookshelfGreenBlue.png"), 100, 100, true, true);
            } else if (officeType.equals("redBlueLess")) {
                image = new Image(new FileInputStream("src/Sprites/bookshelfRedBlueLess.png"), 100, 100, true, true);
            } else if (officeType.equals("redBlueDark")) {
                image = new Image(new FileInputStream("src/Sprites/bookshelfRedBlueDark.png"), 100, 100, true, true);
            } else if (officeType.equals("lessDrawers")) {
                image = new Image(new FileInputStream("src/Sprites/bookshelfLessDrawers.png"), 100, 100, true, true);
            } else if (officeType.equals("nineDrawers")) {
                image = new Image(new FileInputStream("src/Sprites/bookshelfNineDrawers.png"), 100, 100, true, true);
            } else if (officeType.equals("cabinet")) {
                image = new Image(new FileInputStream("src/Sprites/bookshelfCabinet.png"), 100, 100, true, true);
            } else if (officeType.equals("television")) {
                image = new Image(new FileInputStream("src/Sprites/Television.png"), 100, 100, true, true);
            } else if (officeType.equals("workDeskYellow")) {
                image = new Image(new FileInputStream("src/Sprites/workDeskYellow.png"), 100, 100, true, true);
            }
        } catch (IOException e) {
        }
        ip = new ImagePattern(image);
        this.setFill(ip);
    }
}
