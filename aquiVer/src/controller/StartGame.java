package controller;

import javafx.application.Application;
import javafx.stage.Stage;
import view.WelcomeScreen;

public class StartGame extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        WelcomeScreen screen = new WelcomeScreen(stage);
        stage.setTitle("Dungeon Crawlers");
        stage.setScene(screen.getScene());
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}