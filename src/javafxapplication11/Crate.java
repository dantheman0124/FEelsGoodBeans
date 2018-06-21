/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication11;

import javafxapplication11.Furniture;
import java.io.FileInputStream;
import java.io.IOException;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author Daniel Bassani
 */
public class Crate extends Furniture {

    private Image image = null;
    private ImagePattern ip;

    public Crate(double x, double y, double width, double height) {
        super(x, y, width, height);
        try {
            image = new Image(new FileInputStream("src/Sprites/Crate.png"), 100, 100, true, true);
        } catch (IOException e) {
        }
        ip = new ImagePattern(image);
        this.setFill(ip);
    }
    
    
}
