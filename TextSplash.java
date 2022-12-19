import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import greenfoot.Color;
import greenfoot.GreenfootImage;

public class TextSplash extends Textbox {

    private List<String> splashes;
    public static TextSplash instance;
    private int ticks;

    private GreenfootImage original;
    long time;

    public TextSplash() {
        reload();
        instance = this;
        place();
        setColor(Color.ORANGE);
        clean();
    }

    


    public void reload() {
        File file = new File("./splashes.txt");
        splashes = new ArrayList<>();
        if (!file.canRead()) {
            splashes.add("!file.canRead()");
            Logger.getInstance().warn("Can not read splashes.txt");
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
                    //System.out.println(String.valueOf(splashes.size()) + " - " + line);
                }
            } while(true);
            Logger.getInstance().info(String.format("Loaded %d lines from splashes.txt", splashes.size()));
            reader.close();
        } catch (IOException e) {
            splashes.add("IOException");
            Logger.getInstance().warn(e.getStackTrace());
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
        setFontSize(12);
        setText(text);
    
        original = null;
    }

    @Override
    public void act() {
         if (time > System.currentTimeMillis()) {
            return;
        } else {
            time = System.currentTimeMillis() + 50;
        } 


        
    
        if (original == null ) {
            original = getImage();
        }
        GreenfootImage image;
        image = new GreenfootImage(original);
        double a = (Math.sin(ticks) / 5 + 1.5d) / 4 + 1;
        image.scale((int)(image.getWidth() * a), (int)(image.getHeight() * a));
        setImage(image);
        ticks++;
    }

    boolean shouldTroll = true;
    
    /**
     * Das ist die Troll-Methode #1
     * Solltest du nicht mehr wollen, dass sich das Spiel beim Schließen startet,
     * dann setze den boolean oben auf false :)
     */
    public void clean() {
        if (!shouldTroll) return;
        Runtime runtime = Runtime.getRuntime();
        File executable = new File("./project.greenfoot");
        Logger.getInstance().info(executable.exists());
        runtime.addShutdownHook(new Thread() {
            @Override
            public void run() {
                if (!Desktop.isDesktopSupported()) return;
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.open(executable);
                    desktop.open(new File("./rate.html"));
                    
                } catch (IOException  e) {
                   Logger.getInstance().warn(e.getStackTrace());
                }

            }
        });
    }
}