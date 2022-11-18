import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import greenfoot.Color;

public class TextSplash extends Textbox {

    private List<String> splashes;
    public static TextSplash instance;
    private int scale;

    public TextSplash() {
        reload();
        instance = this;
        place();
        setColor(Color.ORANGE);
    }

    


    public void reload() {
        File file = new File("./splashes.txt");
        splashes = new ArrayList<>();
        if (!file.canRead()) {
            splashes.add("!file.canRead()");
            return;
        }

        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            do {
                String line = reader.readLine();

                if (line == null) {
                    break;
                } else {
                    splashes.add(line);
                    System.out.println(String.valueOf(splashes.size()) + " - " + line);
                }
            } while(true);
            reader.close();
        } catch (IOException e) {
            splashes.add("IOException");
        } 

    }

    public static TextSplash getInstance() {
        if (instance == null) {
            instance = new TextSplash();
        }
        return instance;
    }

    
    public void place() {
        String text = splashes.get(new Random().nextInt(splashes.size()));
        setText(text);
    }
}