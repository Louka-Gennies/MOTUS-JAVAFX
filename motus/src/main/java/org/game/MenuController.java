package org.game;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MenuController {

    @FXML
    private Spinner<Integer> attemptsSpinner;

    @FXML
    private Spinner<Integer> wordLengthSpinner;

    @FXML
    private Button startGameButton;

    private Stage primaryStage;
    private ArrayList<Object> words;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setWords(ArrayList<Object> words) {
        this.words = words;
    }

    @FXML
    public void initialize() {
        attemptsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 5));
        wordLengthSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 14, 6));
    }

    public String findWord() {
        int randomIndex = (int) (Math.random() * words.size());
        String wordToGuess = (String) words.get(randomIndex);
        while (wordToGuess.length() != wordLengthSpinner.getValue()) {
            randomIndex = (int) (Math.random() * words.size());
            wordToGuess = (String) words.get(randomIndex);
        }
        return wordToGuess;
    }

    @FXML
    private void startGame() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/game/game.fxml"));
        primaryStage.setScene(new Scene(loader.load()));
        GameController gameController = loader.getController();
        gameController.setAttempts(attemptsSpinner.getValue());
        gameController.setWordToGuess(findWord());
        gameController.setPrimaryStage(primaryStage);
    }
}