package javafxapplication11;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Room3 extends Room {

    private final int WIDTH = 10, HEIGHT = 6;
    private final double SIZE_W = 86;
    private final double SIZE_H = 86;
    private final double SIZE = 50;
    private Cell[][] grid = new Cell[HEIGHT][WIDTH];

    Stack<Cell> stack = new Stack<Cell>();

    Random random = new Random();

    Cell current, next;

    private ArrayList<Node> obj = new ArrayList<>();
    private KeyFrame frame = new KeyFrame(Duration.seconds(0.016), e -> {
        getPlayer().update(obj);

        //displayInv();
        for (int i = 0; i < interactables.getChildren().size(); i++) {
            if (getPlayer().getBoundsInParent().intersects(interactables.getChildren().get(i).getBoundsInParent())) {
                player.getInteractables().add((Interactables) interactables.getChildren().get(i));
                interactables.getChildren().remove(interactables.getChildren().get(i));
                break;
            }
        }

        if (getPlayer().isColliding(doors.getChildren().get(0))) {
            CPTRewrite.prevRoom();
        } else if (getPlayer().isColliding(doors.getChildren().get(1))) {
            CPTRewrite.nextRoom();
        }
    });

    public Room3() {
        super();

        createGrid(root);

        current = grid[0][0];

        current.setVisited(true);
        current.setFill(Color.KHAKI);

        current.setFill(Color.PURPLE);

        while (getTotalUnvisited() != 0) {

            if (getNumUnvisited(current) > 0) {
                int rand = random.nextInt(4);
                while (getUnvisited(current)[rand] == null) {
                    rand = random.nextInt(4);
                }

                stack.push(current);

                removeWalls(current, rand);

                current = getUnvisited(current)[rand];

                current.setVisited(true);

            } else if (stack.size() != 0) {
                current = stack.pop();
            }

            current.setFill(Color.PURPLE);
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                for (int k = 0; k < 4; k++) {
                    if (grid[i][j].getWalls()[k] != null) {
                        obj.add((Node) grid[i][j].getWalls()[k]);
                    }
                }
            }
        }

        getTimeline().getKeyFrames().add(frame);
        getTimeline().setCycleCount(Timeline.INDEFINITE);

        wallsColor = Color.DARKRED;
        doorColor = Color.BISQUE;

        createDoors();
        createWalls();
        fillRoom();
        createInteractables();

        for (int i = 0; i < roomObjects.getChildren().size(); i++) {
            obj.add(roomObjects.getChildren().get(i));
        }

        for (int i = 0; i < walls.getChildren().size(); i++) {
            obj.add(walls.getChildren().get(i));
        }

        int enterX = (int) doors.getChildren().get(0).getTranslateX();
        int enterY = (int) doors.getChildren().get(0).getTranslateY();

        int exitX = (int) doors.getChildren().get(1).getTranslateX();
        int exitY = (int) doors.getChildren().get(1).getTranslateY();

        setEnterSpawnX(enterX + getDOOR_H() / 2 - getPLAYER_W() / 2 +20);
        setEnterSpawnY(enterY - getPLAYER_H() - 35); // come back and figure out why this is spawning weird without the 30 later, here and in room 2 exit door

        setExitSpawnX(exitX + getDOOR_W());
        setExitSpawnY(exitY + getDOOR_H() / 2 - getPLAYER_H() / 2);

        enterSpawnX = getEnterSpawnX();
        enterSpawnY = getEnterSpawnY();

        exitSpawnX = getExitSpawnX();
        exitSpawnY = getExitSpawnY();

        root.getChildren().addAll(walls, doors, interactables, inv);

        scene = new Scene(root, getSCENE_W(), getSCENE_H());

        setKeyHandlers();
    }

    @Override
    public void createWalls() {
        walls = new Group();

        Rectangle doorEnter = (Rectangle) doors.getChildren().get(0);
        Rectangle doorExit = (Rectangle) doors.getChildren().get(1);
        Rectangle rect;

        // top wall
        rect = new Rectangle(getROOM_W(), getWALL_W(), wallsColor);
        rect.setTranslateX(0);
        rect.setTranslateY(getHEADER_H());

        walls.getChildren().add(rect);

        // bottom wall left of door
        rect = new Rectangle(doorEnter.getTranslateX(), getWALL_W(), wallsColor);
        rect.setTranslateX(0);
        rect.setTranslateY(getHEADER_H() + getROOM_H() - getWALL_W());

        walls.getChildren().add(rect);

        // bottom wall right of door
        rect = new Rectangle(getROOM_W() - doorEnter.getTranslateX() - getDOOR_H(), getWALL_W(), wallsColor);
        rect.setTranslateX(doorEnter.getTranslateX() + getDOOR_H());
        rect.setTranslateY(getHEADER_H() + getROOM_H() - getWALL_W());

        walls.getChildren().add(rect);

        // left wall above door
        rect = new Rectangle(getWALL_W(), doorExit.getTranslateY() - getHEADER_H(), wallsColor);
        rect.setTranslateX(0);
        rect.setTranslateY(getHEADER_H());

        walls.getChildren().add(rect);

        // left wall under door
        rect = new Rectangle(getWALL_W(), getROOM_H() + getHEADER_H() - getDOOR_H() - doorExit.getTranslateY(), wallsColor);
        rect.setTranslateX(0);
        rect.setTranslateY(doorExit.getTranslateY() + getDOOR_H());

        walls.getChildren().add(rect);

        //right wall 
        rect = new Rectangle(getWALL_W(), getROOM_H(), wallsColor);
        rect.setTranslateX(getROOM_W() - getWALL_W());
        rect.setTranslateY(getHEADER_H());

        walls.getChildren().add(rect);
    }

    @Override
    public void createDoors() {
        doors = new Group();

        Rectangle door;

        // door enter (0)
        door = new Rectangle(getDOOR_H(), getDOOR_W(), doorColor);
        door.setTranslateX(getROOM_W() - getDOOR_H() - 200);
        door.setTranslateY(getHEADER_H() + getROOM_H() - getDOOR_W() - 15);

        doors.getChildren().add(door);

        // door exit (1)
        door = new Rectangle(getDOOR_W(), getDOOR_H(), doorColor);
        door.setTranslateX(20);
        door.setTranslateY(200 + getHEADER_H());

        doors.getChildren().add(door);
    }

    @Override
    public void fillRoom() {
        roomObjects = new Group();

        Crate crate = new Crate(780, 40, 50, 50);
        roomObjects.getChildren().addAll(crate);
    }

    @Override
    public void createInteractables() {
        interactables = new Group();

        Key key = new Key(doors.getChildren().get(1).getTranslateX() + 20, doors.getChildren().get(1).getTranslateY() - 20, 50, 50);
        interactables.getChildren().addAll(key);
    }

    private void createGrid(Pane pane) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                Cell cell = new Cell(j * SIZE_W + getWALL_W(), i * SIZE_H + (getWALL_W() + getHEADER_H()), SIZE_W, SIZE_H);
                cell.setFill(Color.BLACK);
                cell.setStroke(null);
                grid[i][j] = cell;

                pane.getChildren().add(cell);
                pane.getChildren().addAll(cell.getWalls());
            }
        }

    }

    public int getTotalUnvisited() {
        int result = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!grid[i][j].isVisited()) {
                    result++;
                }
            }
        }

        return result;
    }

    public Cell[] getUnvisited(Cell cell) {
        int i = cell.getGridX(getWALL_W() + getHEADER_H()), j = cell.getGridY(getWALL_W());
        Cell[] result = new Cell[4];

        //up
        if (i != 0) {
            if (!grid[i - 1][j].isVisited()) {
                result[0] = grid[i - 1][j];
            }
        }

        //right
        if (j != grid[0].length - 1) {
            if (!grid[i][j + 1].isVisited()) {
                result[1] = grid[i][j + 1];
            }
        }

        //down
        if (i != grid.length - 1) {
            if (!grid[i + 1][j].isVisited()) {
                result[2] = grid[i + 1][j];
            }
        }

        //left
        if (j != 0) {
            if (!grid[i][j - 1].isVisited()) {
                result[3] = grid[i][j - 1];
            }
        }

        return result;

    }

    public int getNumUnvisited(Cell cell) {
        int result = 0;

        for (Cell temp : getUnvisited(cell)) {
            if (temp != null) {
                result++;
            }
        }

        return result;
    }

    public void removeWalls(Cell cell, int index) {
        root.getChildren().remove(cell.getWalls()[index]);
        cell.getWalls()[index] = null;

        root.getChildren().remove(getUnvisited(cell)[index].getWalls()[(index + 2) % 4]);
        getUnvisited(cell)[index].getWalls()[(index + 2) % 4] = null;
    }

    public void displayInv() {
        for (int i = 0; i < player.getInteractables().size(); i++) {
            Rectangle rect = new Rectangle(20 + i * 80, 620, 70, 70);
            inv.getChildren().add(rect);
            if (player.getInteractables().get(i).getName().equals("battery")) {
                Battery battery = new Battery(25 + i * 80, 640, 60, 30);
                inv.getChildren().add(battery);
            }
            if (player.getInteractables().get(i).getName().equals("crowbar")) {
                Crowbar crowbar = new Crowbar(25 + i * 80, 640, 65, 35);
                inv.getChildren().add(crowbar);
            }
            if (player.getInteractables().get(i).getName().equals("flashlight")) {
                Flashlight flashlight = new Flashlight(45 + i * 80, 640, 20, 40, false);
                inv.getChildren().add(flashlight);
            }
            if (player.getInteractables().get(i).getName().equals("key")) {
                Key key = new Key(45 + i * 80, 640, 20, 40);
                inv.getChildren().add(key);
            }
        }
    }
}
