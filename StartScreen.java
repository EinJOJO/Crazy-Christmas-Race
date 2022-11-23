import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Startmenü
 * 
 * @author Johannes
 */
public class StartScreen extends World
{

    TextSplash textSplash;

    /**
     * Constructor for objects of class StartScreen.
     * 
     */
    public StartScreen()
    {    
        super(800, 600, 1); 
        textSplash = TextSplash.getInstance();
        setPaintOrder(Button.class);
        prepare();
        textSplash.place();
        textSplash.setRotation(-20);
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


        addObject(textSplash, 565, 455);

        addObject(new ButtonCredits(),395,417);

        addObject(new ButtonMechanics(),395,333);

        addObject(new ButtonStartGame(),395,253);
    }


}
