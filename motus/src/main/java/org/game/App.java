package org.game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/game/menu.fxml"));
        Parent root = loader.load();
        MenuController menuController = loader.getController();
        menuController.setPrimaryStage(primaryStage);
        menuController.setWords(loadWordsFromFile());
        primaryStage.setTitle("Game Menu");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private ArrayList<Object> loadWordsFromFile() {
        ArrayList<Object> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/org/game/words.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lineWords = line.split("\n");
                words.addAll(Arrays.asList(lineWords));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    public static void main(String[] args) {
        launch(args);
    }
}