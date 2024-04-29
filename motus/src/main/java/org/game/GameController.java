package org.game;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class GameController {

    private Stage primaryStage;

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

    public void setWinOrLoseOrErrorLabel() {
        winOrLoseOrErrorLabel.setText("Try to guess the word! The first letter is: " + wordToGuess.charAt(0));
    }

    public void setWordToGuess(String wordToGuess) {
        this.wordToGuess = wordToGuess;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    public void initialize() {
        wordInput.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[a-z]*")) {
                wordInput.setText(newValue.replaceAll("[^a-z]", ""));
            }
        });
    }

    @FXML
    private void submitWord() throws InterruptedException {
        System.out.println("The word to guess is: " + wordToGuess);
        String word = wordInput.getText().toLowerCase();
        List<HBox> coloredTexts = colorLetters(wordToGuess, word);
        if (word.equalsIgnoreCase(wordToGuess)) {
            previousAttemptsLabel.getChildren().addAll(0, coloredTexts);
            previousAttemptsLabel.getChildren().addFirst(new Text("\n"));
            winOrLoseOrErrorLabel.setText("Congratulations! You guessed the word!" );
            wordInput.clear();
            PauseTransition pause = new PauseTransition(Duration.seconds(5));
            pause.setOnFinished(event -> {
                try {
                    endGame();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            pause.play();
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
                    PauseTransition pause = new PauseTransition(Duration.seconds(5));
                    pause.setOnFinished(event -> {
                        try {
                            endGame();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                    pause.play();
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
        wordToGuess = wordToGuess.toLowerCase();
        userInput = userInput.toLowerCase();
        List<HBox> coloredBoxes = new ArrayList<>();
        for (int i = 0; i < userInput.length(); i++) {
            char c = userInput.charAt(i);
            Label text = new Label(String.valueOf(c));
            HBox box = new HBox();
            box.getChildren().add(text);

            if (i < wordToGuess.length() && c == wordToGuess.charAt(i)) {
                box.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
                text.setFont(Font.font(30));
            } else if (wordToGuess.contains(String.valueOf(c))) {
                box.setBackground(new Background(new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY)));
                text.setFont(Font.font(30));
            } else {
                box.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                text.setFont(Font.font(30));
            }

            coloredBoxes.add(box);
        }
        return coloredBoxes;
    }

    private void endGame() throws InterruptedException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/game/endGame.fxml"));
            Parent root = loader.load();
            EndGameController endGameController = loader.getController();
            endGameController.setPrimaryStage(primaryStage);
            primaryStage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}