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

    /**
     * How much speed on top to the base speed.
     */
    public enum Difficulty {
        NOOB(-2),
        EASY(1),
        NORMAL(0),
        HARD(1),
        CHALLENGING(2);

        public final int summand;
        private Difficulty(int summand) {
            this.summand = summand;
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
    }

    private void setup() {
        GreenfootImage background = new GreenfootImage("Winterwelt.jpg");
        setBackground(background);
        setPaintOrder(Santa.class, Rentier.class, Zaehler.class, Haus.class, Auto.class, Schlitten.class);

        Greenfoot.setSpeed(50);

        counterSanta = new Zaehler("Punkte: ");
        addObject(counterSanta, 200, 580);

        counterRentier = new Zaehler("Punkte: ");
        addObject(counterRentier, 780, 580);

        spielerUndHausErstellen();
        autosErstellen();
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

    private void autosErstellen() {
        //Reihe 1
        int speed1 = 2 + getDifficulty().summand;
        addObject(new Auto(speed1, false, false),138, 322);
        addObject(new Auto(speed1, true, false),418, 322);
        addObject(new Auto(speed1, false, false),700, 322);
        //Reihe 2
        addObject(new Auto(4, true, true),41,374);
        addObject(new Auto(4, false, true),400,374);
        addObject(new Auto(4, false, true),494,374);
        //Reihe 3
        addObject(new Auto(3, false, true),275,423);
        addObject(new Auto(3, true, true),670,423);
        // Reihe 4
        addObject(new Auto(4, true, true),114,475);
        addObject(new Auto(4, true, true),423,475);

        addObject(new Auto(),49,526);
    } 



    public Difficulty getDifficulty() {
        return difficulty;
    }

}
