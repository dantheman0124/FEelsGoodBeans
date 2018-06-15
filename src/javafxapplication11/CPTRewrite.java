package javafxapplication11;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class CPTRewrite extends Application {

    public static Stage stage;
    static int currentRoom = 0;
    static ArrayList<Room> rooms = new ArrayList<Room>();

    public static ArrayList<Interactables> inventory = new ArrayList<>();
    public static Character player = new Character(30, 0, true, 50, 50, inventory);

    @Override
    public void start(Stage primaryStage) throws InterruptedException {

        rooms.add(new Room6());
        rooms.add(new Room2());
        rooms.add(new Room3());
        rooms.add(new Room4());
        rooms.add(new Room5());
        rooms.add(new Room6());
        rooms.add(new Room7());
        rooms.add(new Room8());
        rooms.add(new Room9());

        rooms.get(currentRoom).startEnter(player, currentRoom);
        stage = primaryStage;
//        HealthBar healthBar = new HealthBar();

        stage.setTitle("Room 1!");
        stage.setScene(rooms.get(currentRoom).getScene());
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void nextRoom() {
        rooms.get(currentRoom).stop();
//        displayInv();
        currentRoom++;
        if (currentRoom > 8) {
            currentRoom = 8;
        }
        stage.setScene(rooms.get(currentRoom).getScene());
        stage.setTitle("Room " + (currentRoom + 1) + "!");
        rooms.get(currentRoom).startEnter(player, currentRoom);
    }

    public static void prevRoom() {
        rooms.get(currentRoom).stop();
//        displayInv();
        currentRoom--;
        if (currentRoom < 0) {
            currentRoom = 0;
        }
        stage.setScene(rooms.get(currentRoom).getScene());
        stage.setTitle("Room " + (currentRoom + 1) + "!");
        rooms.get(currentRoom).startExit(player, currentRoom);
    }

//    public static void setRoom(int room) {
//        rooms.get(currentRoom).stop();
//
//        stage.setScene(rooms.get(currentRoom).getScene());
//        stage.setTitle("Room " + (currentRoom + 1) + "!");
//
//        rooms.get(currentRoom).start(player);
//    }
    
//    public static void displayInv() {
//        for (int i = 0; i < player.getInteractables().size(); i++) {
//            Rectangle rect = new Rectangle(20 + i * 80, 620, 70, 70);
//            Room.inv.getChildren().add(rect);
//            if (player.getInteractables().get(i).getName().equals("battery")) {
//                Battery battery = new Battery(25 + i * 80, 640, 60, 30);
//                Room.inv.getChildren().add(battery);
//            }
//            if (player.getInteractables().get(i).getName().equals("crowbar")) {
//                Crowbar crowbar = new Crowbar(25 + i * 80, 640, 65, 35);
//                Room.inv.getChildren().add(crowbar);
//            }
//            if (player.getInteractables().get(i).getName().equals("flashlight")) {
//                Flashlight flashlight = new Flashlight(45 + i * 80, 640, 20, 40, false);
//                Room.inv.getChildren().add(flashlight);
//            }
//            if (player.getInteractables().get(i).getName().equals("key")) {
//                Key key = new Key(45 + i * 80, 640, 20, 40);
//                Room.inv.getChildren().add(key);
//            }
//        }
//    }
}
