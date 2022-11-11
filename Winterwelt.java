import java.util.Random;
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Winterwelt here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Winterwelt extends World
{
    public static Zaehler counterSanta;
    public static Zaehler counterRentier;

    private Santa santa;
    private Rentier rentier;

    /**
     * Constructor for objects of class Winterwelt.
     * 
     */
    public Winterwelt()
    {    
        // Erstelle eine Welt mit 600x400 Zellen und einer Zellgröße von 1x1 Pixeln.
        super(800, 600, 1); 

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

        addObject(new Auto(),138, 322);
        addObject(new Auto(2, true, false),418, 322);
        addObject(new Auto(),700, 322);
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

}
