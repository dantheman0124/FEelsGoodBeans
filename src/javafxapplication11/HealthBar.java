package javafxapplication11;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HealthBar extends StackPane {

    Rectangle back, front;

    private static final int HEIGHT = 30;
    private static final int WIDTH = 300;

   private static Color backColor = Color.BLACK, frontColor = new Color( 0.8431,  166.0 / 255,  252.0 / 255, 1);

    private int otherWidth = 250;

    public int getOtherWidth() {
        return otherWidth;
    }

    public void setOtherWidth(int otherWidth) {
        this.otherWidth = otherWidth;
    }

    public HealthBar() {
        back = new Rectangle(WIDTH + 10, HEIGHT + 10, backColor);
        front = new Rectangle(otherWidth, HEIGHT, frontColor);
        
        back.setTranslateX(0);
        back.setTranslateY(0);
        front.setTranslateX(0);
        front.setTranslateY(0);

        getChildren().add(back);
        getChildren().add(front);
        
    }

}
