package org.game;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Launcher {
    private ArrayList<String> loadWordsFromFile() {
        ArrayList<String> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/org/game/words.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose the mode:");
        System.out.println("1. Console mode");
        System.out.println("2. GUI mode");
        System.out.print("3. Exit game");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                Menu menu = new Menu();
                menu.setWords(new Launcher().loadWordsFromFile());
                try {
                    menu.startGame();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                App.main(args);
                break;
            case 3:
                System.out.println("Exiting game...");
                break;
            default:
                System.out.println("Invalid choice. Please choose 1 for console mode or 2 for GUI mode.");
                break;
        }
    }
}