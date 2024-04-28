package org.game;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GameController {

    @FXML
    private Label attemptsLabel;

    @FXML
    private TextField wordInput;

    @FXML
    private Label previousAttemptsLabel;

    @FXML
    private Label winOrLoseOrErrorLabel;

    private int attempts;
    private String wordToGuess;

    public void setAttempts(int attempts) {
        this.attempts = attempts;
        attemptsLabel.setText("Attempts remaining: " + attempts);
    }

    public void setWordToGuess(String wordToGuess) {
        this.wordToGuess = wordToGuess;
    }

    @FXML
    public void initialize() {
        wordInput.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[a-z]*")) {
                wordInput.setText(newValue.replaceAll("[^a-z]", ""));
            }
        });
        winOrLoseOrErrorLabel.setText("Guess the word!");
    }

    @FXML
    private void submitWord() {
        System.out.println("The word to guess is: " + wordToGuess);
        String word = wordInput.getText().toLowerCase();
        if (word.equals(wordToGuess)) {
            winOrLoseOrErrorLabel.setText("Congratulations! You guessed the word!");
        } else {
            if (word.length() != wordToGuess.length()) {
                winOrLoseOrErrorLabel.setText("The word must have " + wordToGuess.length() + " characters.");
                wordInput.clear();
            } else {
                attempts--;
                attemptsLabel.setText("Attempts remaining: " + attempts);
                if (attempts == 0) {
                    winOrLoseOrErrorLabel.setText("You lost! The word was: " + wordToGuess);
                    wordInput.clear();
                } else {
                    winOrLoseOrErrorLabel.setText("Try again!");
                    previousAttemptsLabel.setText(previousAttemptsLabel.getText() + word + "\n");
                    wordInput.clear();
                }
            }
        }
    }
}