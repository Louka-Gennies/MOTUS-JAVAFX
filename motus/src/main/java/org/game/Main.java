package org.game;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        InitGame initialize = new InitGame(library.words);
        PlayGame game = new PlayGame(initialize.getWordToFind(), initialize.getnumberOfTries());
        System.out.println("The first letter of the word is: " + initialize.getWordToFind().charAt(0));
        game.startGame();
    }
}
