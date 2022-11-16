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
    private final boolean drivesToLeft;
    
    public Schlitten(int speed, boolean drivesToLeft)
    {
        this.speed = 3;
        this.drivesToLeft = drivesToLeft;
        if (drivesToLeft) {
            setRotation(180);
            setImage("Schlitten2");
        }   else    {
            setImage("Schlitten");
        }
    }
    
    /**
     * Act - do whatever the Auto wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (isAtEdge())
        {
            
            setLocation(0, getY());

            }
            
        
        if (drivesToLeft) {
            setLocation(getX() - speed, getY());
        } else {
            setLocation(getX() + speed, getY());
        }
        if (isAtEdge()) {
            if (drivesToLeft) {
                setLocation(getWorld().getWidth(), getY());
            } else {
                setLocation(0, getY());
            }
        }
        
        World world = getWorld();
        if (drivesToLeft) {
            setLocation(getX() - speed, getY());
        } else {
            setLocation(getX() + speed, getY());
        }
        
        if (isAtEdge()) {
            if (drivesToLeft) {
                setLocation(getWorld().getWidth(), getY());
            } else {
                setLocation(0, getY());
            }
        }
        // Der Schlitten soll sich bewegen und aus der Welt gelöscht werden, wenn er
        // bis zum anderen Rand gefahren ist
    }

    public void aufSchlittenFahren()
    {
        Schlitten schlitten= (Schlitten)getIntersectingObjects(Schlitten.class).get(0);
        setLocation(getX()+schlitten.getSpeed(),   getY());
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
