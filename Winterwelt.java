        import java.util.Random;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Die Spielwelt.
 * @version (a version number or a date)
 */
public class Winterwelt extends World
{
    public static Zaehler counterSanta;
    public static Zaehler counterRentier;

    private final Difficulty difficulty;
    private boolean running = true;
    Random random = new Random();

    /**
     * How much speed on top to the base speed.
     */
    public enum Difficulty {
        EASY(-1, 0),
        NORMAL(0 , 1),
        HARD(1,  2),
        CHALLENGING(2, 3); 

        public final int summand;
        public final int index;
        private Difficulty(int summand, int index) {
            this.summand = summand;
            this.index = index;
        }
    }

    /**
     * Constructor for objects of class Winterwelt.
     * 
     */
    public Winterwelt()
    {    
        // Erstelle eine Welt mit 600x400 Zellen und einer Zellgröße von 1x1 Pixeln.
        super(800, 600, 1); 
        this.difficulty = Difficulty.EASY;
        setup();
    }

    public Winterwelt(Difficulty difficulty) {
        super(800, 600, 1); 
        this.difficulty = difficulty;

        setup();
    }

    private void setup() {
        GreenfootImage background = new GreenfootImage("Winterwelt.jpg");
        setBackground(background);
        setPaintOrder(Card.class, Button.class ,Santa.class, Rentier.class, Zaehler.class, Haus.class, Auto.class, Schlitten.class);

        Greenfoot.setSpeed(50);

        counterSanta = new Zaehler("Punkte: ");
        addObject(counterSanta, 200, 580);

        counterRentier = new Zaehler("Punkte: ");
        addObject(counterRentier, 645, 580);

        ButtonDifficulty buttonDifficulty = new ButtonDifficulty(difficulty);
        addObject(buttonDifficulty, 763, 17);

        spielerUndHausErstellen();
        schlittenZufaelligErstellen();
        spawnRandomCars();
    }


    public void setRunning(boolean running) {
        this.running = running;
    }

    

    private void spielerUndHausErstellen()
    {
        Santa santa = new Santa();
        addObject(santa,0,0);
        santa.respawn();

        Rentier rentier = new Rentier();
        addObject(rentier,0,0);
        rentier.respawn();

        Haus haus = new Haus();
        addObject(haus,400,20);

    }

    private void spawnRandomCars() {
        int[] yLevels = new int[]{328, 374, 423, 475, 526}; // Reihe 1, 2, 3 etc.
        for (int i = 0; i < yLevels.length; i++) { //"Für jede Reihe..." // - von sebastian: tut sie aber nicht. die autos sid in einer reihe teilweise nicht existent (auf einer hoehe) und fahren teils auch nicht
            int y = yLevels[i]; //Höhe der Reihe
            int speed = random.nextInt(5) + getDifficulty().summand + 1; // Speed = Reihe + Difficulty Geschwindigkeit + 1.
            int carsCount = random.nextInt(3) + 1 + getDifficulty().summand; // Anzahl der Autos.
            boolean drivingLeft = random.nextBoolean(); // Richtung. 
            
            for (int j = 0; j < carsCount; j++) {
                Auto auto = new Auto(speed, random.nextBoolean(), drivingLeft);
                int x = random.nextInt(getWidth() - 50);
                addObject(auto, x, y);
                if (auto.isTouchingCar()) {
                    j--;
                    removeObject(auto);
                    continue;
                }
                
            }
        }
    }

    public void schlittenZufaelligErstellen()
    {
        int[] yLevels = new int[]{65, 110, 160, 205, 250}; // Reihe 1, 2, 3 etc.
       for (int i = 0; i < yLevels.length; i++) { //"Für jede Reihe..." // - von sebastian: tut sie aber nicht. die autos sid in einer reihe teilweise nicht existent (auf einer hoehe) und fahren teils auch nicht
            int y = yLevels[i]; //Höhe der Reihe
            int speed = random.nextInt(4) + 2 + getDifficulty().summand; // Speed = Reihe + Difficulty Geschwindigkeit + 1.
            int schlittenCount = random.nextInt(3) + 1 - getDifficulty().summand; // Anzahl der Schlitten.
            schlittenRichtung = !schlittenRichtung;
            boolean drivingLeft = schlittenRichtung;
            
            for (int j = 0; j < schlittenCount; j++) {
                Schlitten schlitten = new Schlitten(speed, drivingLeft);
                int x = random.nextInt(getWidth() - 50);
                addObject(schlitten, x, y);
                
                if (schlitten.isOnSlide()) {
                    j--;    
                    removeObject(schlitten);
                    continue;
                    
                }
                
            }
            
       }
       
    }
    public boolean schlittenRichtung = false;
    @Override
    public void act() {
        if (Greenfoot.isKeyDown("escape")) {
            setRunning(!isRunning());    
            Greenfoot.setWorld(new StartScreen());
        }
        // super.act();
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }
  


    
    
    public boolean isRunning()
    {
        return running;

    }

}