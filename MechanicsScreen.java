import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MechanicsScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MechanicsScreen extends World
{

    /**
     * Constructor for objects of class MechanicsScreen.
     * 
     */
    public MechanicsScreen()
    {    

        super(800, 600, 1); 
        setBackground("ScreenMechanics.png");

        prepare();
    }
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        ButtonHome buttonHome = new ButtonHome();
        addObject(buttonHome,400,500);
        
    }
}
