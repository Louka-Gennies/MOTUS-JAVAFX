package org.game;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private ArrayList<String> words;
    private Scanner scanner = new Scanner(System.in);

    public void setWords(ArrayList<String> words) {
        this.words = words;
    }

    public String findWord(int wordLength) {
        int randomIndex = (int) (Math.random() * words.size());
        String wordToGuess = (String) words.get(randomIndex);
        while (wordToGuess.length() != wordLength) {
            randomIndex = (int) (Math.random() * words.size());
            wordToGuess = (String) words.get(randomIndex);
        }
        return wordToGuess;
    }

    public void startGame() throws Exception {
        System.out.println("Enter the number of attempts:");
        int attempts = scanner.nextInt();
        System.out.println("Enter the length of the word:");
        int wordLength = scanner.nextInt();
        Game game = new Game();
        game.setAttempts(attempts);
        game.setWordToGuess(findWord(wordLength));
        game.submitWord();
    }
}