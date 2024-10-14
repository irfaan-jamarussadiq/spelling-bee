package org.example.spellingbee;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SpellingBeeApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SpellingBeeApplication.class.getResource("home.fxml"));
        Scene home = new Scene(fxmlLoader.load(), 1000, 1000);
        primaryStage.setTitle("Spelling Bee");
        primaryStage.setScene(home);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}