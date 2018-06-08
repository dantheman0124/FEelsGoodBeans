package javafxapplication11;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class HealthBar extends Pane {

    Rectangle back, front;
    Label percent;

    private static int HEIGHT = 30;
    private static int WIDTH = 300;

    private Color backColor = Color.BLACK, frontColor = new Color( 0.8431,  166.0 / 255,  252.0 / 255, 1);

    private double health = 300;

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }
    
    public void loseHealth (double damage) {
        this.health -= damage;
    }

    public HealthBar() {
        relocate(0,0);
        
        percent = new Label( (int) ((100 * health)/WIDTH) + "%");
        percent.setTextFill(Color.WHITE);
        percent.setFont(new Font(25));
        percent.setTranslateX(WIDTH / 2 - 20);
        percent.setTranslateY(HEIGHT / 2 - 12);
        
        
        back = new Rectangle(WIDTH + 10, HEIGHT + 10, backColor);
        back.setTranslateX(0);
        back.setTranslateY(0);
        front = new Rectangle(health, HEIGHT, frontColor);
        front.setTranslateX(5);
        front.setTranslateY(5);
        
        getChildren().addAll(back, front, percent);
    }
    
    public void update() {
        front.setWidth(health);
        percent.setText((int) ((100 * health)/WIDTH) + "%");
    }
    
    public void setScale(double scale) {
        this.WIDTH /= scale;
        this.
        percent = new Label( (int) ((100 * health)/WIDTH) + "%");
        percent.setTextFill(Color.WHITE);
        percent.setFont(new Font(25));
        percent.setTranslateX(WIDTH / 2 - 20);
        percent.setTranslateY(HEIGHT / 2 - 12);
        
        
        back = new Rectangle(WIDTH + 10, HEIGHT + 10, backColor);
        back.setTranslateX(0);
        back.setTranslateY(0);
        front = new Rectangle(health, HEIGHT, frontColor);
        front.setTranslateX(5);
        front.setTranslateY(5);
    }
    
    public void setColor (Color color ) {
        frontColor = color;
        front.setFill(color);
    }

}
