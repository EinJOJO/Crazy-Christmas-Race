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
    private final int speed;
    private final boolean drivingLeft;
    private boolean spawned = false; 
    private int secureX = 0;
    private int secureY = 0;

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

        if (speed <= 0) {
            getWorld().removeObject(this);
            return;
        }

        //Textur setzten: Blaues oder Rotes Auto
        
        
        //Durch die Symmetrie kann man die Autos einfach drehen.
        if (drivesToLeft) {
            setRotation(180);
        }
        spawn();
    }


    public void act() 
    {

        if(!spawned) return;

        secureX = getX();
        secureY = getY();

        //Auto bewegen.
        if (drivingLeft) {
            setLocation(getX() - speed, getY());
        } else {
            setLocation(getX() + speed, getY());
        }

        //Auto zurücksetzen
        if (isAtEdge()) {
            despawn();
            if (drivingLeft) {
                setLocation(getWorld().getWidth(), getY());
            } else {
                setLocation(0, getY());
            }
            spawn();
        }
        
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

    /**
     * Alternative to getX() method by actor which throws illegal state exception
     * @return last X coordinate
     */
    public int getSecureX() {
        return secureX;
    }
    /**
     * Alternative to getY() method by actor which throws illegal state exception
     * @return last Y coordinate
     */
    public int getSecureY() {
        return secureY;
    }
}
