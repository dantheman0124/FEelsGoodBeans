/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication11;


import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Payam
 */
public class Cell extends Rectangle{
    
    private double x = getX();
    private double y = getY();
    private double width = getWidth();
    private double height = getHeight();
    
    private boolean visited = false;
    
    
    private Line[] walls = new Line[4];

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }


    public Cell(double x, double y, double width, double height) {
        super(x, y, width, height);
        makeWalls(1, Color.WHITE);
    }
    
    private void makeWalls(double stroke, Color color) {
        walls[0] = new Line(x, y, x + width, y);
        walls[0].setStroke(color);
        walls[0].setStrokeWidth(stroke);
        
        walls[1] = new Line(x + width, y, x + width, y + height);
        walls[1].setStroke(color);
        walls[1].setStrokeWidth(stroke);
        
        walls[2] = new Line (x + width, y + height, x, y + height);
        walls[2].setStroke(color);
        walls[2].setStrokeWidth(stroke);
        
        walls[3] = new Line (x, y + height, x, y);
        walls[3].setStroke(color);
        walls[3].setStrokeWidth(stroke);
    }

    public Line[] getWalls() {
        return walls;
    }

    public void setWalls(Line[] walls) {
        this.walls = walls;
    }
    
    public int getGridX() {
        return (int) (getY() / width);
    }
    
    public int getGridY() {
        return (int) (getX() / height);
    }
    
     public int getGridX(double translateX) {
        return (int) ((getY() - translateX) / width);
    }
    
    public int getGridY(double translateY) {
        return (int) ((getX() - translateY) / height);
    }
    
    
}
