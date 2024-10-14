package org.example.spellingbee;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GameController {
    private static final int MIN_WORD_SIZE = 4;
    private Set<String> wordDictionary;

    public GameController() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = SpellingBeeApplication.class.getResourceAsStream("words_dictionary.json");
            Map<String, Integer> dictionary = objectMapper.readValue(inputStream, new TypeReference<>() {});
            wordDictionary = dictionary.keySet();
        } catch (IOException exception) {
            wordDictionary = new HashSet<>();
        }
    }

    @FXML
    private TextField wordInput;

    @FXML
    private ListView<String> wordsFound;

    @FXML
    private Text scoreText;

    @FXML
    protected void onGoHome(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(SpellingBeeApplication.class.getResource("home.fxml"));
        Scene home = new Scene(loader.load(), 1000, 1000);
        stage.setScene(home);
    }

    @FXML
    protected void onWordComplete(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            ObservableList<String> currentItems = wordsFound.getItems();
            String word = wordInput.getText();
            if (!currentItems.contains(word) && word.length() >= MIN_WORD_SIZE
                    && wordDictionary.contains(word.toLowerCase())) {
                currentItems.add(word);
                wordsFound.setItems(currentItems);
                int score = currentItems.stream().mapToInt(String::length).sum();
                scoreText.setText("Score: " + score);
            }

            wordInput.setText("");
        }
    }

    @FXML
    protected void onLetterClicked(ActionEvent event) {
        Button button = (Button) event.getSource();
        String letter = button.getText();
        wordInput.setText(wordInput.getText() + letter);
        wordInput.requestFocus();
        wordInput.positionCaret(wordInput.getLength());
    }
}
