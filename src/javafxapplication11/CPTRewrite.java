package javafxapplication11;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CPTRewrite extends Application {

    public static Stage stage;
    static int currentRoom = 0;
    static ArrayList<Room> rooms = new ArrayList<Room>();
    static Group mainWalls = new Group();

    public static ArrayList<Interactables> inventory = new ArrayList<>();
    public static Character player = new Character(30, 0, true, 50, 50, inventory);

    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        Group root = new Group();
        Scene scene = new Scene(root, 900, 700);
        scene.setFill(Color.LIGHTBLUE);

        createMainWalls();
        root.getChildren().add(mainWalls);

        Button game = new Button("Play Game");
        game.setTranslateX(400);
        game.setTranslateY(430);
        game.setScaleX(2);
        game.setScaleY(2);

        Text title = new Text("Escape Room !");
        title.setTranslateX(200);
        title.setTranslateY(250);
        title.setFont(Font.font(80));
        title.setFill(Color.WHITE);

        EventHandler clickPlay = new EventHandler() {
            @Override
            public void handle(Event event) {
                Group root = new Group();
                Scene scene = new Scene(root, 900, 700);
                scene.setFill(Color.PURPLE);

                Text instructions = new Text("There are 9 rooms you must go through and exit in order to win.");
                instructions.setTranslateX(20);
                instructions.setTranslateY(100);
                instructions.setFont(Font.font(25));

                Text instructions2 = new Text("You can click on some objects to find items!");
                instructions2.setTranslateX(20);
                instructions2.setTranslateY(200);
                instructions2.setFont(Font.font(25));

                Text instructions3 = new Text("Some objects can be clicked to enter codes!");
                instructions3.setTranslateX(20);
                instructions3.setTranslateY(300);
                instructions3.setFont(Font.font(25));

                Text instructions4 = new Text("You can move your character with the ARROW keys!");
                instructions4.setTranslateX(20);
                instructions4.setTranslateY(400);
                instructions4.setFont(Font.font(25));

                Text instructions5 = new Text("During shooting games, you can shoot with the SPACE key!");
                instructions5.setTranslateX(20);
                instructions5.setTranslateY(500);
                instructions5.setFont(Font.font(25));

                Text instructions6 = new Text("Make sure your healthbar doesn't reach 0%!");
                instructions6.setTranslateX(20);
                instructions6.setTranslateY(600);
                instructions6.setFont(Font.font(25));

                Button play = new Button("Continue to Game");
                play.setTranslateX(750);
                play.setTranslateY(650);

                Crate crate = new Crate(560, 170, 50, 50);
                Lab locker = new Lab(560, 250, 90, 100, "lockers");
                Character character = new Character(650, 360, true, 50, 50, inventory);
                HealthBar health = new HealthBar(1);
                health.setTranslateX(550);
                health.setTranslateY(570);

                EventHandler playGame = new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        rooms.add(new Room5());
                        rooms.add(new Room2());
                        rooms.add(new Room3());
                        rooms.add(new Room4());
                        rooms.add(new Room5());
                        rooms.add(new Room6());
                        rooms.add(new Room7());
                        rooms.add(new Room8());
                        rooms.add(new Room9());

                        player.setRoot(rooms.get(1).getRoot());

                        rooms.get(currentRoom).startEnter(player, currentRoom);
                        stage = primaryStage;

                        stage.setTitle("Room 1!");
                        stage.setScene(rooms.get(currentRoom).getScene());
                        stage.show();
                    }
                };

                play.setOnMouseClicked(playGame);

                root.getChildren().addAll(play, instructions, instructions2, instructions3, instructions4, instructions5, instructions6, crate, locker, character, health);

                primaryStage.setTitle("Instructions :)");
                primaryStage.setScene(scene);
                primaryStage.show();

            }
        };

        game.setOnMouseClicked(clickPlay);

        root.getChildren().addAll(game, title);
        primaryStage.setScene(scene);
        primaryStage.show();

//        rooms.add(new Room1());
//        rooms.add(new Room2());
//        rooms.add(new Room3());
//        rooms.add(new Room4());
//        rooms.add(new Room5());
//        rooms.add(new Room6());
//        rooms.add(new Room7());
//        rooms.add(new Room8());
//        rooms.add(new Room9());
//
//        player.setRoot(rooms.get(1).getRoot());
//
//        rooms.get(currentRoom).startEnter(player, currentRoom);
//        stage = primaryStage;
////        HealthBar healthBar = new HealthBar();
//
//        stage.setTitle("Room 1!");
//        stage.setScene(rooms.get(currentRoom).getScene());
//        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void createMainWalls() {
        Paint wallsColor = Color.DARKRED;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 14; j++) {
                Floor tile = new Floor(i * 100, j * 39 + 48, 120, 50, "bedroomWood");
                mainWalls.getChildren().add(tile);
            }
        }

//        Rectangle rect = new Rectangle(50, 60, 20, 600);
//        mainWalls.getChildren().add(rect);
//
//        rect = new Rectangle(50, 650, 800, 20);
//        mainWalls.getChildren().addAll(rect);
//
//        rect = new Rectangle(830, 60, 20, 600);
//        mainWalls.getChildren().addAll(rect);
//
//        rect = new Rectangle(50, 50, 800, 20);
//        mainWalls.getChildren().addAll(rect);
        // top wall
        Rectangle rect = new Rectangle(900, 20, wallsColor);
        rect.setTranslateX(0);
        rect.setTranslateY(50);
        mainWalls.getChildren().add(rect);

        // bottom wall
        rect = new Rectangle(900, 20, wallsColor);
        rect.setTranslateX(0);
        rect.setTranslateY(550 - 20 + 50);
        mainWalls.getChildren().add(rect);

        //left wall 
        rect = new Rectangle(20, 550, wallsColor);
        rect.setTranslateX(0);
        rect.setTranslateY(50);
        mainWalls.getChildren().add(rect);

        //right wall 
        rect = new Rectangle(20, 550, wallsColor);
        rect.setTranslateX(900 - 20);
        rect.setTranslateY(50);
        mainWalls.getChildren().add(rect);

//        // horizontal wall right of that^^ 
//        rect = new Rectangle(150, 20, wallsColor);
//        rect.setTranslateX(20);
//        rect.setTranslateY(520);
//        mainWalls.getChildren().add(rect);

        Crate crate = new Crate(780, 40, 50, 50);
        Crate crate2 = new Crate(830, 40, 50, 50);
        Crate crate3 = new Crate(830, 68, 50, 50);
        Crate crate4 = new Crate(250, 530, 50, 50);
        Crate crate5 = new Crate(300, 530, 50, 50);

        // group of tables and chairs
        int x = 430, y = 310;
        for (int i = 0; i < 2; i++) {
            Chair chairL = new Chair(x + i * 230, y + 35 + i * 110, 30, 30, false);
            Table table = new Table(x + 30 + i * 230, y + i * 110, 100, 100, "prettyTable");
            Chair chairR = new Chair(x + 120 + i * 230, y + 35 + i * 110, 30, 30, true);
            mainWalls.getChildren().addAll(table, chairL, chairR);
        }

        // bookshelves and desk
        Office bookcase = new Office(345, 0, 170, 115, "cabinet");
        Office bookcase3 = new Office(615, -7, 90, 127, "lessDrawers");
        Office bookcase4 = new Office(688, 24, 85, 85, "redGreen");
        Table desk = new Table(9, 200, 90, 180, "sideDiningTable");
        Office workDesk = new Office(500, 20, 120, 95, "workDeskYellow");

        // cabinets in top left corner
        Bedroom cabinetClosed1 = new Bedroom(30, 20, 65, 110, "cabinetsClosed");
        Bedroom cabinetClosed2 = new Bedroom(95, 20, 65, 110, "cabinetsClosed");
        Bedroom cabinetOpen = new Bedroom(160, 21, 65, 113, "cabinetsOpen");
        Bedroom cabinetClosed4 = new Bedroom(225, 20, 65, 110, "cabinetsClosed");

        mainWalls.getChildren().addAll(crate, crate2, crate3, crate4, crate5, bookcase, bookcase3, bookcase4, desk, workDesk);
        mainWalls.getChildren().addAll(cabinetClosed1, cabinetClosed2, cabinetOpen, cabinetClosed4);
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
