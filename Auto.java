import greenfoot.*;

/**
 * Auto macht brumm bruumm.
 * @author Johannes

 */
public class Auto extends Actor
{    
    private final boolean blue;
    private final int speed;
    private final boolean drivingLeft;

    /**
     * Konstruktor 1 von 2. Leere Parameter:
     * Erstellt ein Blaues Auto, welches mit einer Geschwindigkeit von 2 nach rechts fährt.
     */
    public Auto() {
        this.blue = true;
        this.speed = 2;
        this.drivingLeft = false;
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
        if (autoBlau) {
            setImage("car01.png");
        } else {
            setImage("car02.png");
        }
        
        //Durch die Symmetrie kann man die Autos einfach drehen.
        if (drivesToLeft) {
            setRotation(180);
        }
    }


    public void act() 
    {
        //Auto bewegen.
        if (drivingLeft) {
            setLocation(getX() - speed, getY());
        } else {
            setLocation(getX() + speed, getY());
        }

        //Auto zurücksetzen
        if (isAtEdge()) {
            if (drivingLeft) {
                setLocation(getWorld().getWidth(), getY());
            } else {
                setLocation(0, getY());
            }
        }
        
    }

    
    public boolean isDrivingLeft() {
        return drivingLeft;
    }

    public boolean isBlue() {
        return blue;
    }

    public void loescheMich()
    {
        getWorld().removeObject(this);
    }
}
