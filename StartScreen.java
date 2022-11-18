import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Startmenü
 * 
 * @author Johannes
 */
public class StartScreen extends World
{
    /**
     * Constructor for objects of class StartScreen.
     * 
     */
    public StartScreen()
    {    

        super(800, 600, 1); 

        prepare();
    }

    @Override
    public void act() {    
        super.act();
    }

    public void loadButtons() {

    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {

        addObject(new ButtonCredits(),395,417);

        addObject(new ButtonMechanics(),395,333);

        addObject(new ButtonStartGame(),395,253);
    }
}
