package org.game;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

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
    private void submitWord() throws InterruptedException {
        System.out.println("The word to guess is: " + wordToGuess);
        String word = wordInput.getText().toLowerCase();
        List<HBox> coloredTexts = colorLetters(wordToGuess, word);
        if (word.equals(wordToGuess)) {
            previousAttemptsLabel.getChildren().addAll(0, coloredTexts);
            previousAttemptsLabel.getChildren().addFirst(new Text("\n"));
            winOrLoseOrErrorLabel.setText("Congratulations! You guessed the word!" );
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

    public List<HBox> colorLetters(String wordToGuess, String userInput) {
        List<HBox> coloredBoxes = new ArrayList<>();
        for (int i = 0; i < userInput.length(); i++) {
            char c = userInput.charAt(i);
            Label text = new Label(String.valueOf(c));
            HBox box = new HBox();
            box.getChildren().add(text);

            if (i < wordToGuess.length() && c == wordToGuess.charAt(i)) {
                box.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
                text.setFont(javafx.scene.text.Font.font(30));
            } else if (wordToGuess.contains(String.valueOf(c))) {
                box.setBackground(new Background(new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY)));
                text.setFont(javafx.scene.text.Font.font(30));
            } else {
                box.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                text.setFont(javafx.scene.text.Font.font(30));
            }

            coloredBoxes.add(box);
        }
        return coloredBoxes;
    }

}