package view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import model.Player;
import model.Room;
import model.Monster;

import java.util.HashMap;
import java.util.Objects;

public class GameScreen {

    private final Stage stage;
    private final Scene scene;
    private final Pane root;
    private final HashMap<String, Room> maze;
    private Room currentRoom;
    private String id;
    private final Player player;
    private Rectangle goal;
    private Label hpLabel;
    private boolean returned;

    public GameScreen(Stage stage, Player player, String id, HashMap<String,
            Room> maze, boolean returned) {
        this.stage = stage;
        this.player = player;
        this.maze = Objects.requireNonNullElseGet(maze, HashMap::new);
        this.id = id;
        this.returned = returned;
        this.root = createContent();
        this.scene = new Scene(this.root);
        configureControls();
    }

    private Pane createContent() {
        Pane root = new Pane();
        root.setPrefSize(700, 700);

        Image image = new Image("file:resources/dungeon-floor.jpg");
        ImageView imageView = new ImageView(image);

        Label moneyLabel = new Label("Money: " + this.player.getMoney());
        moneyLabel.setFont(Font.loadFont("file:resources/Seagram.ttf", 20));
        moneyLabel.setTextFill(Color.WHITE);
        moneyLabel.setTranslateX(20);
        moneyLabel.setTranslateY(20);

        this.hpLabel = new Label("HP: " + this.player.getHp());
        this.hpLabel.setFont(Font.loadFont("file:resources/Seagram.ttf", 20));
        this.hpLabel.setId("health");
        this.hpLabel.setTextFill(Color.WHITE);
        this.hpLabel.setTranslateX(600);
        this.hpLabel.setTranslateY(20);

        image = new Image("file:resources/horizwall.jpeg");

        Rectangle topWall = new Rectangle(700, 15);
        topWall.setTranslateX(0);
        topWall.setTranslateY(0);
        topWall.setFill(new ImagePattern(image));

        Rectangle bottomWall = new Rectangle(700, 15);
        bottomWall.setTranslateX(0);
        bottomWall.setTranslateY(685);
        bottomWall.setFill(new ImagePattern(image));

        image = new Image("file:resources/vertwall.jpeg");

        Rectangle rightWall = new Rectangle(15, 700);
        rightWall.setTranslateX(685);
        rightWall.setTranslateY(0);
        rightWall.setFill(new ImagePattern(image));

        Rectangle leftWall = new Rectangle(15, 700);
        leftWall.setTranslateX(0);
        leftWall.setTranslateY(0);
        leftWall.setFill(new ImagePattern(image));

        switch (this.id.charAt(this.id.length() - 1)) {
        case 'W':
            this.player.setTranslateX(320);
            this.player.setTranslateY(550);
            break;
        case 'S':
            this.player.setTranslateX(320);
            this.player.setTranslateY(30);
            break;
        case 'A':
            this.player.setTranslateX(550);
            this.player.setTranslateY(320);
            break;
        case 'D':
            this.player.setTranslateX(30);
            this.player.setTranslateY(320);
            break;
        default:
            break;
        }
        if (goingBack(this.id)) {
            this.id = this.id.substring(0, this.id.length() - 2);
        }
        this.currentRoom = registerRoom(this.id);

        root.getChildren().add(imageView);
        root.getChildren().add(moneyLabel);
        root.getChildren().add(hpLabel);
        root.getChildren().add(topWall);
        root.getChildren().add(bottomWall);
        root.getChildren().add(leftWall);
        root.getChildren().add(rightWall);
        root.getChildren().add(this.player);
        for (Rectangle door : this.currentRoom.getDoors().values()) {
            root.getChildren().add(door);
        }
        if (!this.id.equals("1")) {
            for (Monster monster : this.currentRoom.getMonsters()) {
                root.getChildren().add(monster);
            }
        }
        return root;
    }

    private void configureControls() {
        this.scene.setOnKeyPressed(e -> {
            Monster monster = checkMonsterCollision();
            if (e.getCode() == KeyCode.SPACE) {
                if (monster != null) {
                    monster.takeDmg(this.player.getDmg());
                    this.player.didDmg(this.player.getDmg());
                    if (monster.getHp() <= 0) {
                        this.root.getChildren().remove(monster);
                        this.currentRoom.killMonster(monster);
                        this.player.killedMonster();
                        if (this.currentRoom.getMonsters().size() == 0 && this.id.length() == 8) {
                            this.goal = new Rectangle(80, 80);
                            this.goal.setTranslateX(310);
                            this.goal.setTranslateY(310);
                            Image image = new Image("file:resources/trophy.png");
                            this.goal.setFill(new ImagePattern(image));
                            this.root.getChildren().add(this.goal);
                        }
                    }
                }
            } else {
                if (checkWallCollision(e.getCode().getChar().charAt(0))) {
                    switch (e.getCode()) {
                    case A:
                        this.player.moveLeft();
                        break;
                    case S:
                        this.player.moveDown();
                        break;
                    case D:
                        this.player.moveRight();
                        break;
                    case W:
                        this.player.moveUp();
                        break;
                    default:
                        break;
                    }
                }
                if (monster != null) {
                    this.player.takeDmg(monster.getDmg());
                    this.hpLabel.setText("HP: " + this.player.getHp());
                    if (this.player.isDead()) {
                        losingScreen();
                    }
                }
                if (this.goal != null && this.player.getBoundsInParent().intersects(
                        this.goal.getBoundsInParent())) {
                    winningScreen();
                }
                if (enterDoor(e.getCode().getChar())) {
                    newRoomScreen(this.id + e.getCode().getChar());
                }
            }
        });
    }

    private boolean enterDoor(String button) {
        if (this.currentRoom.getDoors().get(button) != null
                && this.player.getBoundsInParent().intersects(
                        this.currentRoom.getDoors().get(button).getBoundsInParent())) {
            String nextRoom = this.id + button;
            if (returned) {
                if (this.maze.get(nextRoom) != null) {
                    this.returned = false;
                    return true;
                } else {
                    return false;
                }
            } else {
                if (goingBack(nextRoom)) {
                    nextRoom = nextRoom.substring(0, nextRoom.length() - 2);
                }
                if (this.currentRoom.getMonsters().size() > 0) {
                    if (this.maze.get(nextRoom) != null) {
                        this.returned = true;
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return true;
                }
            }
        } else {
            return false;
        }
    }

    private boolean checkWallCollision(char direction) {
        switch (direction) {
        case 'A':
            return !(this.player.getTranslateX() - 15 < 0);
        case 'D':
            return !(this.player.getTranslateX() + 15 > 630);
        case 'W':
            return !(this.player.getTranslateY() - 15 < 0);
        case 'S':
            return !(this.player.getTranslateY() + 15 > 610);
        default:
            return true;
        }
    }

    private Monster checkMonsterCollision() {
        if (!this.id.equals("1")) {
            for (Monster monster : this.currentRoom.getMonsters()) {
                if (this.player.getBoundsInParent().intersects(monster.getBoundsInParent())) {
                    return monster;
                }
            }
        }
        return null;
    }

    private boolean goingBack(String id) {
        if (id.length() < 3) {
            return false;
        } else if (id.charAt(id.length() - 2) == 'S'
                && id.charAt(id.length() - 1) == 'W') {
            return true;
        } else if (id.charAt(id.length() - 2) == 'W'
                && id.charAt(id.length() - 1) == 'S') {
            return true;
        } else if (id.charAt(id.length() - 2) == 'A'
                && id.charAt(id.length() - 1) == 'D') {
            return true;
        } else {
            return id.charAt(id.length() - 2) == 'D'
                    && id.charAt(id.length() - 1) == 'A';
        }
    }

    public Room registerRoom(String id) {
        Room room;
        if (this.maze.containsKey(id)) {
            room = this.maze.get(id);
        } else {
            room = new Room(id);
        }
        this.maze.put(id, room);
        return room;
    }

    private void newRoomScreen(String id) {
        GameScreen gameScreen =
                new GameScreen(this.stage, this.player, id, this.maze, this.returned);
        this.stage.setScene(gameScreen.getScene());
        this.stage.show();
    }

    private void winningScreen() {
        WinningScreen screen = new WinningScreen(this.stage, this.player);
        this.stage.setScene(screen.getScene());
        this.stage.show();
    }

    private void losingScreen() {
        LosingScreen screen = new LosingScreen(this.stage, this.player);
        this.stage.setScene(screen.getScene());
        this.stage.show();
    }

    public Scene getScene() {
        return this.scene;
    }
}