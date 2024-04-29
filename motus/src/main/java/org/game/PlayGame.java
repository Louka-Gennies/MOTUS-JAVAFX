package org.game;
import java.util.Scanner;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PlayGame {
    private final String wordToFind;
    private Integer numberOfTries;
    private Integer timeLeft;

    public PlayGame(String wordToFind, Integer numberOfTries) {
        this.wordToFind = wordToFind;
        this.numberOfTries = numberOfTries;
        this.timeLeft = 300;
        Timer timer = new Timer();
    }

    public void startGame(){
        startTimer();
        boolean findWord = false;
        while (numberOfTries > 0 && !findWord && timeLeft > 0) {
            int minutes = timeLeft / 60;
            int seconds = timeLeft % 60;
            System.out.print("\rTime left: " + minutes + " minutes " + seconds + " seconds\n");
            String word = askUser();
            if (word.equalsIgnoreCase(wordToFind)){
                displayGame(word);
                System.out.println("Congratulations, you found the word!");
                Launcher.main(new String[]{});
                findWord = true;
                break;
            } else {
                numberOfTries--;
                displayGame(word);
            }
        }
        if (numberOfTries == 0){
            System.out.println("You lost, the word to find was: " + wordToFind);
            Launcher.main(new String[]{});
        }
        if (timeLeft <= 0) {
            System.out.println("Time's up! The word to find was: " + wordToFind);
            Launcher.main(new String[]{});
        }
    }

    public void displayGame(String word){
        System.out.println("You put the following word: " + word.toLowerCase());
        System.out.println(generateLine(wordToFind.length()));
        for (int i = 0; i < wordToFind.length(); i++){
            if (word.toLowerCase().charAt(i) == wordToFind.toLowerCase().charAt(i)){
                System.out.print("|" +"\033[42;30m" + " " + word.charAt(i) + " " + "\033[0m");
            } else if ( wordToFind.toLowerCase().contains(String.valueOf(word.toLowerCase().charAt(i)))){
                System.out.print("|" +"\033[43;30m" + " " + word.charAt(i) + " " + "\033[0m");
            }
            else {
                System.out.print("|" +"\033[41;30m" + " " + word.charAt(i) + " " + "\033[0m");
            }
        }
        System.out.println("|");
        System.out.println(generateLine(wordToFind.length()));
        System.out.println("You have " + numberOfTries + " tries left");
    }

    private String generateLine(int length) {
        StringBuilder line = new StringBuilder();
        line.append("----".repeat(Math.max(0, length)));
        line.append("-");
        return line.toString();
    }

    public String askUser(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a word: ");
        String word = scanner.nextLine();
        if (word.length() != wordToFind.length() && !word.matches("[a-zA-Z]")){
            System.out.println("Please enter a valid word");
            return askUser();
        }
        return word;
    }

    private void startTimer() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(() -> {
            timeLeft--;
            if (timeLeft <= 0) {
                executor.shutdown();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }
}
