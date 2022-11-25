import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Auto here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Schlitten extends Actor
{
    private final int speed;
    private final boolean drivingToLeft;
    
    
    public Schlitten()
    {
        speed=3;
        drivingToLeft=false;
               
    }
    
    public Schlitten(int speed, boolean drivesToLeft)
    {
        this.speed = speed;
        this.drivingToLeft = drivesToLeft;
        if (drivesToLeft) {
            setImage("Schlitten2.png");
        }   else    {
            setImage("Schlitten.png");
        }
    }
    
    /**
     * Act - do whatever the Auto wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
        
        
        if (isAtEdge()) {
            if (drivingToLeft) {
                setLocation(getWorld().getWidth(), getY());
                
            } else {
                setLocation(0, getY());
            }
        }
        
        World world = getWorld();
        if (drivingToLeft) {
            setLocation(getX() - speed, getY());
        } else {
            setLocation(getX() + speed, getY());
        }
        
        // Der Schlitten soll sich bewegen und aus der Welt gelöscht werden, wenn er
        // bis zum anderen Rand gefahren ist
    }

    public boolean isDrivingToLeft() {
        return drivingToLeft;
    }

    
    public void loescheMich()
    {
        getWorld().removeObject(this);
    }
        
    // Diese Methode teilt dem aufrufenden Objekt mit, welche Geschwindigkeit der Schlitten hat
    // Dies ist nur wichtig, wenn man auf dem Schlitten fahren können soll
    public int getSpeed()
    {
        return speed;
    }
}
