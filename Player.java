import greenfoot.*;

/**
 * Die Eigenschaften des Spielers
 */
public class Player extends Actor {
 
    /*
     * Index:
     * 0: up
     * 1: left
     * 2: right
     * 3: down
     */
    private final String[] controlKeys; 
    private int speed;
    private final int spawnX;
    private final int spawnY;

    /**
     * 
     * @param controlKeys Bsp: new char[]{'w','a','s','d'} => up, left, right, down
     */
    public Player(String[] controlKeys, int spawnX, int spawnY) {
        //Parameter auf richtigkeit überprüfen.
        if (controlKeys.length != 4) {
            throw new Error("Parameter controlKeys ungültig.");
        }
        this.controlKeys = controlKeys;
        this.speed = 2;
        this.spawnX = spawnX;
        this.spawnY = spawnY;
    }

    //Implementieren
    @Override
    public void act() {
        handleInput();
        
        //Wird das Haus berührt?
        //Wird ein Auto berührt?
        //Wird Eis berührt?
        //Fährt er Schlitten?
        
    }
    
    public void respawn () {
        setLocation(spawnX, spawnY);
    }
    

    
    private void handleInput () {
        if (Greenfoot.isKeyDown(controlKeys[0])) { 
            setLocation(getX() , getY() - speed);
            
        }
        if (Greenfoot.isKeyDown(controlKeys[1])) { 
            setLocation(getX() - speed, getY());
        }
        if (Greenfoot.isKeyDown(controlKeys[2])) { 
            setLocation(getX(), getY() + speed);
        }
        if (Greenfoot.isKeyDown(controlKeys[3])) { 
            setLocation(getX() + speed, getY());
        }

    }

    /**
     * Erhöht die Punkte
     * @param zaehler Zöhlerobject des Spielers. (Beispiel: Winterwelt.counterSanta)
     * 
     */
    public void increasePoints(Zaehler zaehler) {
        zaehler.erhoehe();
        zaehler.update();
    }        
    
    /**
     * @return Ob der Spieler das Haus berührt.
     */
    private boolean isTouchingHouse() {
        return !(getIntersectingObjects(Haus.class).isEmpty());
    }
    
    //Implementieren
    private boolean isOnIce() {
        //Ist der Spieler über y1 und unter y2 und nicht auf einem Schlitten?
        return false;
    }
    
    
    /**
     * 
     * @return Ob das Objekt ein Auto berührt
     */
    public boolean isColliding()
    {
        return (getIntersectingObjects(Auto.class).size() > 0);
    }

    /**
     * 
     * @return Ob das Objekt auf einem Schlitten ist.
     */
    public boolean isOnSlide() {
        return getIntersectingObjects(Schlitten.class).size() > 0;
    }
    
}