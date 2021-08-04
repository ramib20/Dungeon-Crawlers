package view;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.shape.Rectangle;

import model.Player;

public class InitialConfigurationScreen {

    private final Scene scene;
    private final Stage stage;
    private String name;
    private String difficulty;
    private String item;
    private static ComboBox<String> difficultyMenu;
    private static ComboBox<String> startingItemMenu;

    public InitialConfigurationScreen(Stage stage) {
        this.stage = stage;
        scene = new Scene(createContent());
        difficulty = "Easy";
        item = "Sword";
    }

    public Pane createContent() {
        Pane root = new Pane();
        root.setPrefSize(700, 700);

        Image image = new Image("file:resources/background.jpeg");
        ImageView background = new ImageView(image);

        image = new Image("file:resources/player.png");
        Rectangle imageFrame = new Rectangle(250, 350);
        imageFrame.setFill(new ImagePattern(image));
        imageFrame.setTranslateX(350);
        imageFrame.setTranslateY(40);

        Label nameLabel = new Label("Name:");
        nameLabel.setFont(Font.loadFont("file:resources/Seagram.ttf", 30));
        nameLabel.setTextFill(Color.WHITE);
        nameLabel.setTranslateX(70);
        nameLabel.setTranslateY(50);

        TextField nameTextField = new TextField();
        nameTextField.setTranslateX(70);
        nameTextField.setTranslateY(100);
        nameTextField.setOnAction(e -> saveName(nameTextField));

        Label difficultyLabel = new Label("Difficulty:");
        difficultyLabel.setFont(Font.loadFont("file:resources/Seagram.ttf", 30));
        difficultyLabel.setTextFill(Color.WHITE);
        difficultyLabel.setTranslateX(70);
        difficultyLabel.setTranslateY(180);

        difficultyMenu = new ComboBox<>();
        difficultyMenu.setTranslateX(70);
        difficultyMenu.setTranslateY(230);
        difficultyMenu.getItems().addAll(
            "Easy",
            "Medium",
            "Hard"
        );
        difficultyMenu.setValue("Easy");
        difficultyMenu.setOnAction(e -> saveDifficulty(difficultyMenu));


        Label startingItemLabel = new Label("Starting Item:");
        startingItemLabel.setFont(Font.loadFont("file:resources/Seagram.ttf", 30));
        startingItemLabel.setTextFill(Color.WHITE);
        startingItemLabel.setTranslateX(70);
        startingItemLabel.setTranslateY(310);

        startingItemMenu = new ComboBox<>();
        startingItemMenu.setTranslateX(70);
        startingItemMenu.setTranslateY(360);
        startingItemMenu.getItems().addAll(
            "Sword",
            "Axe",
            "Scythe"
        );
        startingItemMenu.setValue("Sword");
        startingItemMenu.setOnAction(e -> saveItem(startingItemMenu));


        Label errorLabel = new Label();
        errorLabel.setFont(Font.loadFont("file:resources/Seagram.ttf", 20));
        errorLabel.setTextFill(Color.MINTCREAM);
        errorLabel.setTranslateX(83);
        errorLabel.setTranslateY(430);

        Button continueButton = new Button("Continue");
        continueButton.setFont(Font.loadFont("file:resources/Seagram.ttf", 30));
        continueButton.setTextFill(Color.WHITE);
        continueButton.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,
                null, null)));
        continueButton.setTranslateX(470);
        continueButton.setTranslateY(530);
        continueButton.setOnAction(e -> initialGameScreen(errorLabel));

        Button backButton = new Button("Back");
        backButton.setFont(Font.loadFont("file:resources/Seagram.ttf", 30));
        backButton.setTextFill(Color.WHITE);
        backButton.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
        backButton.setTranslateX(50);
        backButton.setTranslateY(530);
        backButton.setOnAction(e -> welcomeScreen());

        root.getChildren().add(background);
        root.getChildren().add(imageFrame);
        root.getChildren().add(nameLabel);
        root.getChildren().add(nameTextField);
        root.getChildren().add(difficultyLabel);
        root.getChildren().add(difficultyMenu);
        root.getChildren().add(startingItemLabel);
        root.getChildren().add(startingItemMenu);
        root.getChildren().add(errorLabel);
        root.getChildren().add(continueButton);
        root.getChildren().add(backButton);
        
        return root;
    }

    private void initialGameScreen(Label errorLabel) {
        if (checkNameOk()) {
            Player player = new Player(320, 310, 70, 90, name, difficulty, item);
            GameScreen screen = new GameScreen(stage, player, "1", null, false);
            stage.setScene(screen.getScene());
            stage.show();
        } else {
            errorLabel.setText("Error: Don't forget to click enter after typing in your name!");
        }
    }

    private boolean checkNameOk() {
        if (name == null || name.length() == 0) {
            return false;
        }
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) != ' ') {
                return true;
            }
        }
        return false;
    }

    private void welcomeScreen() {
        WelcomeScreen screen = new WelcomeScreen(stage);
        stage.setScene(screen.getScene());
        stage.show();
    }

    public Scene getScene() {
        return scene;
    }

    public static String getChosenDifficulty() {
        return difficultyMenu.getValue();
    }

    public static String getChosenStartingItem() {
        return startingItemMenu.getValue();
    }

    private void saveName(TextField nameTextField) {
        name = nameTextField.getText();
    }

    private void saveDifficulty(ComboBox<String> difficultyMenu) {
        difficulty = difficultyMenu.getValue();
    }

    private void saveItem(ComboBox<String> startingItemMenu) {
        item = startingItemMenu.getValue();
    }
}