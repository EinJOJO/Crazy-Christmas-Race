import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Die Spielwelt.
 * @version (a version number or a date)
 */
public class Winterwelt extends World
{
    public static GreenfootSound streetSFX = new GreenfootSound("street.mp3");
    public static Zaehler counterSanta;
    public static Zaehler counterRentier;
    public static Winterwelt instance;
    public static GreenfootSound music = new GreenfootSound("gameMusic.mp3");

    private final Difficulty difficulty;
    private List<Auto> carSpawnQueue = new ArrayList<>();
    private boolean running = true;
    private Random random = new Random();
    private Timer queueDelay = new Timer(); 
    
    /**
     * Constructor for objects of class Winterwelt.
     * 
     */
    public Winterwelt()
    {    
        // Erstelle eine Welt mit 600x400 Zellen und einer Zellgröße von 1x1 Pixeln.
        super(800, 600, 1); 
        this.difficulty = Difficulty.NORMAL;
        setup();
        
    }

    public Winterwelt(Difficulty difficulty) {
        super(800, 600, 1); 
        this.difficulty = difficulty;
        setup();
    }

    private void setup() {
        instance = this;
        Logger.getInstance().printNewInstanceInfo(this);
        Logger.getInstance().info("Difficulty: " + difficulty.toString());
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
        addObject(ButtonMusic.getInstance(), 773, 577);

        spielerUndHausErstellen();
        schlittenZufaelligErstellen();
        spawnRandomCars();

        if (!streetSFX.isPlaying()) {
            streetSFX.setVolume(40);
            streetSFX.play();
        }
        if (!music.isPlaying() && ButtonMusic.getInstance().isMusicOn()) {
            music.setVolume(20);
            music.play();
            StartScreen.music.stop();
        }

        

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
    
    public void addToDelayQueue(Auto auto) {
        this.carSpawnQueue.add(auto);
    }

    private void spawnRandomCars() {
        int[] yLevels = new int[]{328, 374, 423, 475, 526}; // Reihe 1, 2, 3 etc.
        for (int i = 0; i < yLevels.length; i++) { //"Für jede Reihe..." // - von sebastian: tut sie aber nicht. die autos sid in einer reihe teilweise nicht existent (auf einer hoehe) und fahren teils auch nicht //- Johannes "Das hat damit nichts zu tun. Die Anzahl der Autos wird weiter unten bestimmt..."
            int y = yLevels[i]; //Höhe der Reihe
            int speed = random.nextInt(5) + getDifficulty().summand + 1; // Speed = Reihe + Difficulty Geschwindigkeit + 1.
            int carsCount = random.nextInt(5) + 1 + 1 * getDifficulty().summand; // Anzahl der Autos.
            boolean drivingLeft = random.nextBoolean(); // Richtung. 
            
            for (int j = 0; j < carsCount; j++) { // Erstelle |x| Autos.
                Auto auto = new Auto(speed, random.nextBoolean(), drivingLeft);
                int x = random.nextInt(getWidth() - 50);
                addObject(auto, x, y);
                if (auto.isTouchingCar()) { // Wenn ein Auto, ein anderes Auto berührt, wird versucht das Auto woanders zu platzieren.
                    j--;
                    removeObject(auto);
                    continue;
                }   
            }
            Logger.getInstance().info(String.format("Created %d cars{speed: %d, left: %b}, Y-Level %d", carsCount, speed, drivingLeft, y));
        }
    }
    private boolean schlittenRichtung = false;
    public void schlittenZufaelligErstellen()
    {
        int[] yLevels = new int[]{65, 110, 160, 205, 250}; // Reihe 1, 2, 3 etc.
       for (int i = 0; i < yLevels.length; i++) { //"Für jede Reihe..." // - von sebastian: tut sie aber nicht. die autos sid in einer reihe teilweise nicht existent (auf einer hoehe) und fahren teils auch nicht //-~ Johannes: s.o.
            int y = yLevels[i]; //Höhe der Reihe
            int speed = random.nextInt(4) + 1 + getDifficulty().summand; // Speed = Reihe + Difficulty Geschwindigkeit + 1.
            int schlittenCount = random.nextInt(3) + 1 - getDifficulty().summand; // Anzahl der Schlitten.
            if (schlittenCount < 1) {
                schlittenCount = 1;
            }
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
            Logger.getInstance().info(String.format("Created %d slides{speed: %d, left: %b}, Y-Level %d", schlittenCount, speed, drivingLeft, y));
            
            
       }
       
    }
    
    @Override
    public void act() {
        // Listen for keys
        String key = Greenfoot.getKey();
        
        if (key != null && key.equals("f3")) {
            Logger logger = Logger.getInstance();
            logger.setPrintLogs(!logger.isPrintLogs());
        }
        
        if (key != null && key.equals("escape"))  {
            setRunning(!isRunning());    
            Greenfoot.setWorld(new StartScreen());
        }



        // Car Queue spawning
        if (!carSpawnQueue.isEmpty()) {
            // Start neues delay.
            if (!queueDelay.isRunning()) {
                switch (difficulty) {
                    case EASY:
                    case NORMAL:
                        queueDelay.setEnd(random.nextInt(60) * 50); 
                        break;
                    case HARD:
                    case CHALLENGING:
                        queueDelay.setEnd(random.nextInt(30) * 25); 
                        break;
                }
                queueDelay.start();
                return; 
            }
            if (carSpawnQueue.size() > 8 + 2 * difficulty.summand) { // Man vermeidet zu viele Autos in der Schlange
                    queueDelay.setEnd(0);
            }
             
            if (!queueDelay.isFinished()) return; 
        
            // Wenn die random Zeit abgelaufen ist.
            // Nächstes Auto aus der Liste.
            Auto car = carSpawnQueue.get(0); 
            
            car.spawn();
             
            if (car.isTouchingCar()) { // Überlappende Autos vermeiden.
                queueDelay.start();
                queueDelay.setEnd(0);
                car.despawn();
                return;
            }
            carSpawnQueue.remove(0);
            if (random.nextBoolean()) queueDelay.stop();

            
            
        }
        // super.act();
    }


    public List<Auto> getCarSpawnQueue() {
        return carSpawnQueue;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }
  
    public boolean isRunning()
    {
        return running;
    }
    public void setRunning(boolean running) {
        this.running = running;
    }

    public static Winterwelt getInstance() {
        return instance;
    }


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
}