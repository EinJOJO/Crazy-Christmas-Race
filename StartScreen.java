import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Startmenü
 * 
 * @author Johannes
 */
public class StartScreen extends World
{

    TextSplash textSplash;
    Textbox textbox;
    public static GreenfootSound music = new GreenfootSound("StartScreen.mp3");

    /**
     * Constructor for objects of class StartScreen.
     * 
     */
    public StartScreen()
    {    
        super(800, 600, 1); 
        textSplash = TextSplash.getInstance();
        setPaintOrder(Button.class);
        
        textSplash.place();
        textSplash.setRotation(-20);

        setBackground("ScreenStart.png");
        
        music.setVolume(50);
        if (!music.isPlaying()) {
            music.playLoop();
            
        }

        prepare();
        Greenfoot.start();
    }

    @Override
    public void act() {    
        if (Greenfoot.getKey() == "f3") {
            Logger logger = Logger.getInstance();
            
            logger.setPrintLogs(!logger.isPrintLogs());

            if (logger.isPrintLogs()) {
                textbox.setText("Enabled logger printing.");
                System.out.println("Enabled logger printing");
            } else {
                textbox.setText("Disabled logger printing.");
            };


        }
        
        // super.act();

       
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
