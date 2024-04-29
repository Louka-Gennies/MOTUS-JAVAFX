package org.game;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private ArrayList<String> words;
    private Scanner scanner = new Scanner(System.in);

    public void setWords(ArrayList<String> words) {
        this.words = words;
    }


    public void startGame() throws Exception {
        Library library = new Library();
        InitGame initialize = new InitGame(library.words);
        PlayGame game = new PlayGame(initialize.getWordToFind(), initialize.getnumberOfTries());
        System.out.println("The first letter of the word is: " + initialize.getWordToFind().charAt(0));
        game.startGame();
    }
}