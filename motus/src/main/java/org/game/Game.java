package org.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private int attempts;
    private String wordToGuess;
    private Scanner scanner = new Scanner(System.in);


    public void setAttempts(int attempts) {
        this.attempts = attempts;
        System.out.println("Attempts remaining: " + attempts);
    }

    public void setWordToGuess(String wordToGuess) {
        this.wordToGuess = wordToGuess;
    }

    public void submitWord() {
        System.out.println("The word to guess is: " + wordToGuess);
        String word = scanner.nextLine().toLowerCase();
        List<String> coloredTexts = colorLetters(wordToGuess, word);
        if (word.equals(wordToGuess)) {
            System.out.println("Congratulations! You guessed the word!");
        } else {
            if (word.length() != wordToGuess.length()) {
                System.out.println("The word must have " + wordToGuess.length() + " characters.");
            } else {
                attempts--;
                System.out.println("Attempts remaining: " + attempts);
                if (attempts == 0) {
                    System.out.println("You lost! The word was: " + wordToGuess);
                } else {
                    System.out.println("Try again!");
                }
            }
        }
    }

    public List<String> colorLetters(String wordToGuess, String userInput) {
        List<String> coloredTexts = new ArrayList<>();
        for (int i = 0; i < userInput.length(); i++) {
            char c = userInput.charAt(i);
            String text = String.valueOf(c);

            if (i < wordToGuess.length() && c == wordToGuess.charAt(i)) {
                text = "[GREEN]" + text + "[RESET]";
            } else if (wordToGuess.contains(String.valueOf(c))) {
                text = "[ORANGE]" + text + "[RESET]";
            } else {
                text = "[RED]" + text + "[RESET]";
            }

            coloredTexts.add(text);
        }
        return coloredTexts;
    }
}