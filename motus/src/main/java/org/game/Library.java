package org.game;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class Library {
    public List<String> words;

    public Library() {
        this.words = readFile();
    }

    public static List<String> readFile () {
        List<String> words = new ArrayList<String>();

        try {
            File file = new File("src/main/resources/org/game/words.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                words.add(line);
            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }
}
