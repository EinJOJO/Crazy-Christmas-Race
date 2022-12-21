import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Startmenü
 * 
 * @author Johannes
 */
public class StartScreen extends World
{

    private final TextSplash textSplash;
    public ButtonDifficulty buttonDifficulty;
    public static GreenfootSound music = new GreenfootSound("StartScreen.mp3");

    /**
     * Constructor for objects of class StartScreen.
     * 
     */
    public StartScreen()
    {    
        
        super(800, 600, 1); 
        
        Logger.getInstance().printNewInstanceInfo(this);
        textSplash = TextSplash.getInstance();
        buttonDifficulty = new ButtonDifficulty();
        setPaintOrder(Button.class);
        textSplash.place();
        textSplash.setRotation(-20);

        setBackground("ScreenStart.png");
        Winterwelt.music.stop();
        music.setVolume(40);
        if (!music.isPlaying() && ButtonMusic.getInstance().isMusicOn()) {
            music.playLoop();
        }

        loadButtons();
        Greenfoot.start();
        Winterwelt.streetSFX.stop();
    }

    @Override
    public void act() {
        String key = Greenfoot.getKey();
        
        if (key != null && key.equals("f3")) {
            Logger logger = Logger.getInstance();
            logger.setPrintLogs(!logger.isPrintLogs());
        }
        
        // super.act();

       
    }

    public void loadButtons() {
        addObject(textSplash, 565 + 30, 455 - 10);

        addObject(new ButtonCredits(),395,417);

        addObject(new ButtonMechanics(),395,333);

        addObject(new ButtonStartGame(),395,253);

        addObject(ButtonMusic.getInstance(), 773, 577);
        addObject(ButtonPlaytime.getInstance(), 435, 210);

        addObject(buttonDifficulty, 315, 210);
    }
}
