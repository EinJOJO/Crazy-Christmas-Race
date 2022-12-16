import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class IceHole here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IceHole extends Actor
{
    private Timer despawn = new Timer();

    public IceHole(int x, int y) {
        Winterwelt.getInstance().addObject(this, x, y);
        setImage(new GreenfootImage("button-blue.png"));
    }
    
    
   
    public void act()
    {
        if(!Winterwelt.getInstance().getObjects(null).contains(this)) return;
        if (!despawn.isRunning()) {
            despawn.start();
            despawn.setEnd(1 * 1000);
        }
        if (!despawn.isFinished()) return;
        GreenfootImage image = getImage();
        if (image.getTransparency() < 10)  {
            getWorld().removeObject(this);
            return;
        }
        image.setTransparency(image.getTransparency() - 10);
        
        
    }
}
