
package javafxapplication11;

import javafx.scene.shape.Rectangle;

public class Door extends Rectangle{
    private boolean previousDoor;
    private String room;
    private int playerX;
    private int playerY;
    

    public Door(boolean previousDoor, String room, int playerX, int playerY, double x, double y, double width, double height) {
        super(x, y, width, height);
        this.playerX = playerX;
        this.playerY = playerY;
        this.previousDoor = previousDoor;
        this.room = room;
    }

    public void setPreviousDoor(boolean previousDoor) {
        this.previousDoor = previousDoor;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getRoom() {
        return room;
    }

    public boolean isPreviousDoor() {
        return previousDoor;
    }
}
