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
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Daniel Bassani
 */
public class Furniture extends Rectangle {

    private Image image;
    private ImagePattern ip;
    private String text;

    public Furniture(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    public Furniture(Image image, ImagePattern ip, double width, double height) {
        super(width, height);
        this.image = image;
        this.ip = ip;
    }

    public Image getImage() {
        return image;
    }

    public ImagePattern getIp() {
        return ip;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setIp(ImagePattern ip) {
        this.ip = ip;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
