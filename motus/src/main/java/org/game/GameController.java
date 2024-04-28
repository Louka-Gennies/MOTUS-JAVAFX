package org.game;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class GameController {

    @FXML
    private Label attemptsLabel;

    @FXML
    private TextField wordInput;

    @FXML
    private TextFlow previousAttemptsLabel;

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
        List<Text> coloredTexts = colorLetters(wordToGuess, word);
        for (Text text : coloredTexts) {
            text.setFont(new Font(30));
        }
        if (word.equals(wordToGuess)) {
            previousAttemptsLabel.getChildren().addAll(0, coloredTexts);
            previousAttemptsLabel.getChildren().addFirst(new Text("\n"));
            winOrLoseOrErrorLabel.setText("Congratulations! You guessed the word!");
            wordInput.clear();
        } else {
            if (word.length() != wordToGuess.length()) {
                winOrLoseOrErrorLabel.setText("The word must have " + wordToGuess.length() + " characters.");
                wordInput.clear();
            } else {
                attempts--;
                attemptsLabel.setText("Attempts remaining: " + attempts);
                if (attempts == 0) {
                    winOrLoseOrErrorLabel.setText("You lost! The word was: " + wordToGuess);
                    previousAttemptsLabel.getChildren().addAll(0, coloredTexts);
                    previousAttemptsLabel.getChildren().addFirst(new Text("\n"));
                    wordInput.clear();
                } else {
                    winOrLoseOrErrorLabel.setText("Try again!");
                    previousAttemptsLabel.getChildren().addAll(0, coloredTexts);
                    previousAttemptsLabel.getChildren().addFirst(new Text("\n"));
                    wordInput.clear();
                }
            }
        }
    }

    public List<Text> colorLetters(String wordToGuess, String userInput) {
        List<Text> coloredTexts = new ArrayList<>();
        for (int i = 0; i < userInput.length(); i++) {
            char c = userInput.charAt(i);
            Text text = new Text(String.valueOf(c));

            if (i < wordToGuess.length() && c == wordToGuess.charAt(i)) {
                text.setFill(Color.GREEN);
            } else if (wordToGuess.contains(String.valueOf(c))) {
                text.setFill(Color.ORANGE);
            } else {
                text.setFill(Color.RED);
            }

            coloredTexts.add(text);
        }
        return coloredTexts;
    }

}