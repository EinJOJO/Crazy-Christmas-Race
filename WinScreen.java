import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class WinScreen extends World
{

    
    private final TYPE type; 
    

    public WinScreen() {
        super(800, 600, 1); 
        type = TYPE.DRAW;
        init();
    }
 
    public WinScreen(TYPE type)
    {    
        super(800, 600, 1); 
        this.type = type;
        init();
        
    }

    private void init() {
        switch(type) {
            case DRAW:
                break;
            case SANTA:
                break;
            case RUDOLF: 
                break;
        }

        setBackground(type.image);
        addObject(new ButtonHome(), 413, 50);

        Winterwelt.music.stop();
        if (!StartScreen.music.isPlaying() && ButtonMusic.getInstance().isMusicOn()) {
            StartScreen.music.playLoop();
        }
    }
    public enum TYPE {
        DRAW(new GreenfootImage("win_draw.png")),
        SANTA(new GreenfootImage("win_santa.png")),
        RUDOLF(new GreenfootImage("win_rudolf.png"));

        TYPE(GreenfootImage image) {
            this.image = image;
        }

        public final GreenfootImage image;
    }
}
