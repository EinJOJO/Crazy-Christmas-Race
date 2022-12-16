import greenfoot.World;

public class ButtonMusic extends Button
{

    private boolean musicOn = true;
    private static ButtonMusic instance;

    public ButtonMusic() {
        super("ButtonMusicOn.png");
        instance = this;
        
    }

    @Override
    public void onHover() {
        hoverTransparency();
    }

    @Override
    public void onClick() {
        musicOn = !musicOn;
        playMusic();
    }

    public void playMusic() {
        if (musicOn) {
            setImage("ButtonMusicOn.png");
            World current = getWorld();
            if (current instanceof Winterwelt) {
                Winterwelt.music.playLoop();
                StartScreen.music.stop();
            }
            
            if (current instanceof StartScreen) {
                StartScreen.music.playLoop();
                Winterwelt.music.stop();
            }
        } else {
            setImage("ButtonMusicOff.png");
            StartScreen.music.stop();
            Winterwelt.music.stop();
        }
    }

    public boolean isMusicOn() {
        return musicOn;
    }

    public static ButtonMusic getInstance() {
        if (instance == null) {
            instance = new ButtonMusic();
        }
        return instance;
    }
    
}
