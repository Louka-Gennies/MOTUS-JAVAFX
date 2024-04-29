package org.game;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InitGame {
    private Integer numberOfTries;
    private Integer numberOfLetters;
    private String wordToFind;
    private List<String> library;

    public InitGame(List<String> words) {
        if (words == null) {
            throw new IllegalArgumentException("Words list cannot be null");
        }
        this.library = words;
        this.numberOfLetters = askNumberOfLetters();
        this.wordToFind = generateWord(this.numberOfLetters);
        this.numberOfTries = askNumberOfTries();

    }

    private Integer askNumberOfLetters() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many letters should the word contain?");
        String numberOfLetters = scanner.nextLine();
        if(!numberOfLetters.matches("[0-9]+")) {
            System.out.println("Please enter a valid number");
            return askNumberOfLetters();
        }
        try {
            if (Integer.valueOf(numberOfLetters) < 2) {
                throw new IllegalArgumentException("The number of letters should be greater than 0");
            } else if (Integer.valueOf(numberOfLetters) > 14) {
                throw new IllegalArgumentException("The number of letters should be less than 14");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return askNumberOfLetters();
        }
        return Integer.valueOf(numberOfLetters);
    }

    private Integer askNumberOfTries() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many tries do you want ?");
        String numberOfTries = scanner.nextLine();
        if(!numberOfTries.matches("[0-9]+")) {
            System.out.println("Please enter a valid number");
            return askNumberOfTries();
        }
        try {
            if (Integer.valueOf(numberOfTries) < 1) {
                throw new IllegalArgumentException("The number of tries should be greater than 1");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return askNumberOfTries();
        }
        return Integer.valueOf(numberOfTries);
    }

    private String generateWord(Integer numberOfLetters) {

        String word = "";

        do {
            word = library.get((int) (Math.random() * library.size()));
        } while (word.length() != numberOfLetters); {}
        return word;
    }

    public Integer getnumberOfTries() {
        return this.numberOfTries;
    }

    public String getWordToFind() {
        return this.wordToFind;
    }
}
