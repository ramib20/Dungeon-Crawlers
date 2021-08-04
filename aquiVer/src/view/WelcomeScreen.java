package view;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;

public class WelcomeScreen {
    private final Scene scene;
    private final Stage stage;

    public WelcomeScreen(Stage stage) {
        this.stage = stage;
        scene = new Scene(createContent());
    }

    private Pane createContent() {
        Pane root = new Pane();
        root.setPrefSize(700, 700);

        Image image = new Image("file:resources/background.jpeg");
        ImageView imageView = new ImageView(image);

        Label welcomeLabel = new Label("Dungeon Crawlers");
        welcomeLabel.setFont(Font.loadFont("file:resources/Seagram.ttf", 70));
        welcomeLabel.setTextFill(Color.WHITE);
        welcomeLabel.setTranslateX(75);
        welcomeLabel.setTranslateY(175);

        Button playButton = new Button("Play");
        playButton.setFont(Font.loadFont("file:resources/Seagram.ttf", 40));
        playButton.setTextFill(Color.WHITE);
        playButton.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
        playButton.setTranslateX(150);
        playButton.setTranslateY(400);
        playButton.setOnAction(e -> initialConfigurationScreen());

        Button exitButton = new Button("Exit");
        exitButton.setFont(Font.loadFont("file:resources/Seagram.ttf", 40));
        exitButton.setTextFill(Color.WHITE);
        exitButton.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
        exitButton.setTranslateX(400);
        exitButton.setTranslateY(400);
        exitButton.setOnAction(e -> stage.close());

        root.getChildren().add(imageView);
        root.getChildren().add(welcomeLabel);
        root.getChildren().add(playButton);
        root.getChildren().add(exitButton);

        return root;
    }

    private void initialConfigurationScreen() {
        InitialConfigurationScreen screen = new InitialConfigurationScreen(stage);
        stage.setScene(screen.getScene());
        stage.show();
    }

    public Scene getScene() {
        return scene;
    }
}