import greenfoot.*;
public class ButtonStartGame extends Button {


    public ButtonStartGame() {
        super("ButtonStartGame.png");
    }
    

    @Override
    public void onClick() {
        if (!(getWorld() instanceof StartScreen)) return;
        StartScreen world = (StartScreen) getWorld();
        
        Greenfoot.setWorld(new Winterwelt(world.buttonDifficulty.getDifficulty(), minutes()));
        StartScreen.music.stop();
    }
    
    private int minutes() {
        return ButtonPlaytime.getInstance().getPlayTime();
    }
    
}
