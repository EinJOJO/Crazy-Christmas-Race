import greenfoot.*;
public class ButtonStartGame extends Button {


    public ButtonStartGame() {
        super("ButtonStartGame.png");
    }

    @Override
    public void onClick() {
        Greenfoot.setWorld(new Winterwelt());
        StartScreen.music.stop();
    }
    
}
