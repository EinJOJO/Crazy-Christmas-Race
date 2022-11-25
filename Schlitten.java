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
    
    public void act() 
    {
        if (drivingToLeft) {
            setLocation(getX() - speed, getY());
        } else {
            setLocation(getX() + speed, getY());
        }
        if (isAtEdge()) {
            if (drivingToLeft) {
                setLocation(getWorld().getWidth(), getY());
            } else {
                setLocation(0, getY());
            }
        }
    }

    public boolean isDrivingToLeft() {
        return drivingToLeft;
    }

    // Diese Methode teilt dem aufrufenden Objekt mit, welche Geschwindigkeit der Schlitten hat
    // Dies ist nur wichtig, wenn man auf dem Schlitten fahren können soll
    public int getSpeed()
    {
        return speed;
    }
}
