
package javafxapplication11;

import javafx.scene.shape.Rectangle;

public class Interactables extends Rectangle {

    private String name = "";
    public Interactables(double x, double y, double width, double height, String name) {
       super(x, y, width, height);
       this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
