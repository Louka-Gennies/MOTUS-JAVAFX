package org.game;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.ArrayList;

public class EndGameController {

    @FXML
    private Button restartButton;

    @FXML
    private Button quitButton;

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    public void initialize() {
        restartButton.setOnAction(event -> restartGame());
        quitButton.setOnAction(event -> quitGame());
    }

    private void restartGame() {
        primaryStage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/game/menu.fxml"));
        try {
            Parent root = loader.load();
            MenuController menuController = loader.getController();
            menuController.setPrimaryStage(primaryStage);
            menuController.setWords(App.loadWordsFromFile());
            primaryStage.setTitle("MOTUS");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void quitGame() {
        primaryStage.close();
    }
}