package org.example.spellingbee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class HomeController {
    @FXML
    protected void onPlayGame(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(SpellingBeeApplication.class.getResource("game.fxml"));
        Scene game = new Scene(loader.load(), 1000, 1000);
        stage.setScene(game);
    }
}