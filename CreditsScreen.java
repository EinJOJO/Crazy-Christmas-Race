

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CreditsScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CreditsScreen extends World
{

    /**
     * Constructor for objects of class CreditsScreen.
     * 
     */
    public CreditsScreen()
    {    

        super(800, 600, 1); 
        setBackground(new GreenfootImage("ScreenCredits.png"));
        prepare();
    }
    
    
    public void act() {
    
        if (Greenfoot.isKeyDown("escape")) {
            
            Greenfoot.setWorld(new StartScreen());
        }
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        try {
            Link figma = new Link("https://www.figma.com", "Figma");
            figma.setColor(Color.WHITE);
            addObject(figma,400,322 - 10);

            Link twitter = new Link("https://twitter.com/ein_jojo", "Twitter");
            twitter.setColor(Color.CYAN);
            twitter.setFontSize(20);
            addObject(twitter,182, 149 - 20);

            Link github = new Link("https://github.com/EinJOJO/", "Github");
            github.setColor(Color.WHITE);
            github.setFontSize(20);
            addObject(github,178, 177 - 20);

            Link themeMusic = new Link("https://www.epidemicsound.com/track/PvR2BjpAGL/",
             " Trevor Kowalski\nChristmas Begins");
            themeMusic.setColor(Color.WHITE);
            addObject(themeMusic, 718, 317);

            Link gameMusic = new Link("https://www.epidemicsound.com/track/EidLWaZSrp/" ,
            "Roots and Recognition\n          Carols Song"); 
            gameMusic.setColor(Color.WHITE);
            addObject(gameMusic, 740, 372);

            Link epidemic = new Link("https://www.epidemicsound.com", "Epidemic Sound ");
            epidemic.setColor(Color.ORANGE);
            addObject(epidemic, 635, 457 );

        
        } catch (Exception e) {
            //Ignore.
        }
    }
}
