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
        if (musicOn) {
            setImage("ButtonMusicOn.png");
            StartScreen.music.playLoop();
        } else {
            setImage("ButtonMusicOff.png");
            StartScreen.music.stop();
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
