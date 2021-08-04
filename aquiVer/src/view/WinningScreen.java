package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import model.Player;

public class WinningScreen {
    private final Scene scene;
    private final Stage stage;
    private final Player player;

    public WinningScreen(Stage stage, Player player) {
        this.stage = stage;
        this.player = player;
        scene = new Scene(createContent());
    }

    private Pane createContent() {
        Pane root = new Pane();
        root.setPrefSize(700, 700);

        Image image = new Image("file:resources/background.jpeg");
        ImageView imageView = new ImageView(image);

        Label victoryLabel = new Label("You WIN!");
        victoryLabel.setFont(Font.loadFont("file:resources/Seagram.ttf", 75));
        victoryLabel.setTextFill(Color.WHITE);
        victoryLabel.setTranslateX(138);
        victoryLabel.setTranslateY(100);

        Label subTextVictoryLabel = new Label("Congratulations, "
                + this.player.getName() + ", you've escaped the dungeon!");
        subTextVictoryLabel.setFont(Font.loadFont("file:resources/Seagram.ttf", 25));
        subTextVictoryLabel.setTextFill(Color.WHITE);
        subTextVictoryLabel.setTranslateX(105);
        subTextVictoryLabel.setTranslateY(225);

        Label statisticsLabel = new Label("Monsters Killed: "
                + this.player.getMonstersKilled()
                + "\nDamage Dealt: " + this.player.getDmgDealt()
                + "\nDamage Taken: " + this.player.getDmgTaken());
        statisticsLabel.setFont(Font.loadFont("file:resources/Seagram.ttf", 25));
        statisticsLabel.setTextFill(Color.WHITE);
        statisticsLabel.setTranslateX(105);
        statisticsLabel.setTranslateY(350);

        Button playAgainButton = new Button("Play Again");
        playAgainButton.setFont(Font.loadFont("file:resources/Seagram.ttf", 40));
        playAgainButton.setTextFill(Color.WHITE);
        playAgainButton.setBackground(new Background(
                new BackgroundFill(Color.TRANSPARENT, null, null)));
        playAgainButton.setTranslateX(100);
        playAgainButton.setTranslateY(500);
        playAgainButton.setOnAction(e -> initialConfigurationScreen());

        Button exitButton = new Button("Exit");
        exitButton.setFont(Font.loadFont("file:resources/Seagram.ttf", 40));
        exitButton.setTextFill(Color.WHITE);
        exitButton.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
        exitButton.setTranslateX(400);
        exitButton.setTranslateY(500);
        exitButton.setOnAction(e -> stage.close());

        root.getChildren().add(imageView);
        root.getChildren().add(victoryLabel);
        root.getChildren().add(subTextVictoryLabel);
        root.getChildren().add(statisticsLabel);
        root.getChildren().add(playAgainButton);
        root.getChildren().add(exitButton);

        return root;
    }

    public void initialConfigurationScreen() {
        InitialConfigurationScreen screen = new InitialConfigurationScreen(stage);
        stage.setScene(screen.getScene());
        stage.show();
    }

    public Scene getScene() {
        return scene;
    }
}
