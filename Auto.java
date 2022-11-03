import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Auto here.
 * 
 * @author Johannes

 */
public class Auto extends Actor
{

    private final boolean blau;
    private final int speed;

    public Auto() {
        this.blau = true;
        this.speed = 2;
    }

    public Auto(int speed, boolean autoBlau)
    {
        this.speed = speed;
        this.blau = autoBlau;
        if (autoBlau) {
            setImage("car01.png");
        } else {
            setImage("car02.png");
        }
    }

    
    
    /**
     * Act - do whatever the Auto wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        move(speed);
        if (isAtEdge()) {
            setLocation(0, getY());
        }
        
    }
    
    public void loescheMich()
    {
        getWorld().removeObject(this);
    }
}
