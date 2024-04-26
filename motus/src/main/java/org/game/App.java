package org.game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    private String wordToFind = "motus";
    private int numberOfTries = 10;
    private Label wordLabel;
    private Label triesLabel;
    private GridPane gridPane;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Motus Game");

        VBox vbox = new VBox();
        Scene scene = new Scene(vbox, 300, 200);

        TextField inputField = new TextField();
        wordLabel = new Label();
        triesLabel = new Label("Number of tries left: " + numberOfTries);
        gridPane = new GridPane();

        vbox.getChildren().addAll(inputField, wordLabel, triesLabel, gridPane);

        inputField.setOnAction(event -> {
            String word = inputField.getText();
            if (word.length() != wordToFind.length()) {
                wordLabel.setText("Please enter a word of " + wordToFind.length() + " letters");
            } else {
                checkWord(word);
            }
            inputField.clear();
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void checkWord(String word) {
        if (numberOfTries <= 0) {
            wordLabel.setText("You lost, the word to find was: " + wordToFind);
            return;
        }

        if (word.equalsIgnoreCase(wordToFind)) {
            for (int i = 0; i < word.length(); i++) {
                Label letterLabel = new Label(String.valueOf(word.charAt(i)));
                letterLabel.setStyle("-fx-background-color: green;");
                gridPane.add(letterLabel, i, numberOfTries);
            }
            wordLabel.setText("Congratulations, you found the word!");
            return;
        }

        numberOfTries--;
        triesLabel.setText("Number of tries left: " + numberOfTries);

        for (int i = 0; i < word.length(); i++) {
            Label letterLabel = new Label(String.valueOf(word.charAt(i)));
            if (word.charAt(i) == wordToFind.charAt(i)) {
                letterLabel.setStyle("-fx-background-color: green;");
            } else if (wordToFind.contains(String.valueOf(word.charAt(i)))) {
                letterLabel.setStyle("-fx-background-color: orange;");
            } else {
                letterLabel.setStyle("-fx-background-color: red;");
            }
            gridPane.add(letterLabel, i, numberOfTries);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}