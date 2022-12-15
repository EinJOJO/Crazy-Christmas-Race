import java.util.Random;

import greenfoot.*;

/**
 * Auto brummt brutulm.
 * @author Johannes
 * 
 * TODO:
 * - Random Respawn Delay
 */
public class Auto extends Actor
{    
    private final boolean blue;
    private int speed;
    private final boolean drivingLeft;
    private boolean spawned = false; 

    private Timer respawnTimer = new Timer(); 

    /**
     * Konstruktor 1 von 2. Leere Parameter:
     * Erstellt ein Blaues Auto, welches mit einer Geschwindigkeit von 2 nach rechts fährt.
     */
    public Auto() {
        this.blue = true;
        this.speed = 2;
        this.drivingLeft = false;
        spawn();
    }

    /**
    * Konstruktor 2 von 2. Dieser nimmt alle Parameter, sodass alles eingestellt werden kann.
    */
    public Auto(int speed, boolean autoBlau, boolean drivesToLeft)

    {
        this.speed = speed;
        this.blue = autoBlau;
        this.drivingLeft = drivesToLeft;
    
        //Durch die Symmetrie kann man die Autos einfach drehen.
        if (drivesToLeft) {
            setRotation(180);
        }
        spawn();
    }


    public void act() 
    {
        if(!spawned) return;

        //Auto bewegen.
        if (drivingLeft) {
            setLocation(getX() - speed, getY());
        } else {
            setLocation(getX() + speed, getY());
        }

        //Auto zurücksetzen
        if (isAtEdge()) {
            despawn(); 
            // Positioniere...
            if (drivingLeft) { 
                setLocation(getWorld().getWidth(), getY());
            } else {
                setLocation(0, getY());
            }
            // Respawn
            ((Winterwelt) getWorld()).addToDelayQueue(this);
            return;
        }
        
        if (wirdSchneeballBeruehrt()){
            speed=0;
        
        }
        
    }
    
    public boolean wirdSchneeballBeruehrt()
    {
        
        Schneeball schneeball = (Schneeball) getOneIntersectingObject(Schneeball.class);
        if (schneeball==null)
        {
            return false;
        }
        
        return true;
        
    }
    
    public void spawn() {
        if (!spawned) {
            if (isBlue()) {
                setImage("car01.png");
            } else {
                setImage("car02.png");
            }

            spawned = true;
        }
    }

    public void despawn() {
        if (spawned) {
            spawned = false;
            setImage(new GreenfootImage(1,1));
            
        }
    }

    public boolean isDrivingLeft() {
        return drivingLeft;
    }

    public boolean isBlue() {
        return blue;
    }
    
    public boolean isSpawned() {
        return spawned;
    }

    public boolean isTouchingCar() {
        return !getIntersectingObjects(Auto.class).isEmpty();
    }
    
    public int getSpeed() {
        return speed;
    }
}
