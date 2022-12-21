import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class ButtonPlaytime extends Button
{
    private static ButtonPlaytime instance;
    private int playTime = 1;

    public ButtonPlaytime() {
        super("ButtonPlaytime1.png");
        instance = this;
    }

    @Override
    public void onClick() {
        GreenfootImage image = null;  
        switch(playTime) {
            case 1:
                playTime = 2;
                image = new GreenfootImage("ButtonPlaytime2.png");
                break;
            case 2:
                playTime = 5;
                image = new GreenfootImage("ButtonPlaytime5.png");
                break;
            case 5:
                playTime = 7;
                image = new GreenfootImage("ButtonPlaytime7.png");
                break;
            case 7: 
                playTime = 1;
                image = new GreenfootImage("ButtonPlaytime1.png");
                break;
            
        }
        if (image == null) return;
        setImage(image);
        
    }

    public int getPlayTime() {
        return playTime;
    }

    public static ButtonPlaytime getInstance() {
        if (instance == null ) {
            instance = new ButtonPlaytime();
        }
        return instance;
    }
 
}
