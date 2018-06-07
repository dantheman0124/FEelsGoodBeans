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
 * @author Daniel Bassani
 */
public class Table extends Furniture {

    private Image image = null;
    private ImagePattern ip;
    private String tableType = "";

    public Table(double x, double y, double width, double height) {
        super(x, y, width, height);
        try {
            image = new Image(new FileInputStream("src/Sprites/Table.png"), 100, 100, true, true);
        } catch (IOException e) {
        }
        ip = new ImagePattern(image);
        this.setFill(ip);
    }

    public Table(double x, double y, double width, double height, String tableType) {
        super(x, y, width, height);
        this.tableType = tableType;
        try {
            if (tableType.equals("counter")) {
                image = new Image(new FileInputStream("src/Sprites/Counter.png"), 100, 100, true, true);
            } else if (tableType.equals("prettyTable")) {
                image = new Image(new FileInputStream("src/Sprites/prettyTable.png"), 100, 100, true, true);
            } else if (tableType.equals("sideDiningTable")) {
                image = new Image(new FileInputStream("src/Sprites/sideDiningTable.png"), 100, 100, true, true);
            }
        } catch (IOException e) {
        }
        ip = new ImagePattern(image);
        this.setFill(ip);
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public String getTableType() {
        return tableType;
    }

}
