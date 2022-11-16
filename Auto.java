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
    private final boolean drivesToLeft;

    public Auto() {
        this.blau = true;
        this.speed = 2;
        this.drivesToLeft = false;
    }

        public Auto(int speed, boolean autoBlau, boolean drivesToLeft)
    {
        this.speed = speed;
        this.blau = autoBlau;
        if (autoBlau) {
            setImage("car01.png");
        } else {
            setImage("car02.png");
        }
        
        this.drivesToLeft = drivesToLeft;
        if (drivesToLeft) {
            setRotation(180);
        }
    }

    
    
    /**
     * Act - do whatever the Auto wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
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
        
    }
    
    public void loescheMich()
    {
        getWorld().removeObject(this);
    }
}
