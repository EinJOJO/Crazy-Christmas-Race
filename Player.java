
import greenfoot.*;

/**
 * Die Eigenschaften des Spielers
 */
public abstract class Player extends Actor {
 
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
    private final Zaehler pointsCounter;


    /**
     * 
     * @param controlKeys Bsp: new char[]{'w','a','s','d'} => up, left, right, down
     */
    public Player(String[] controlKeys, int spawnX, int spawnY, Zaehler pointsCounter) {
        //Parameter auf Richtigkeit überprüfen.
        if (controlKeys.length != 4) {
            throw new Error("Parameter controlKeys ungültig.");
        }
        this.controlKeys = controlKeys;
        this.speed = 4;
        this.spawnX = spawnX;
        this.spawnY = spawnY;
        this.pointsCounter = pointsCounter;
        Logger.getInstance().info("Created Player Object of " + getName());
    }

    //Implementieren
    @Override
    public void act() {
        
        handleInput();    
        
        if (isTouchingCar()) {
            respawn();
        }
            
        if (isTouchingHouse()){
            pointsCounter.erhoehe();
            respawn();
        }    
        
        if(isOnSlide()){
            driveOnSlide();
        }

        if (isOnIce()){
            respawn();
        }
      
        
    }
    
    public void respawn () {
        setLocation(spawnX, spawnY);
    }
    

    
    private void handleInput () {
        int oldX = getX();
        int oldY = getY();
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

        if (isTouchingCar()) {
            Auto car = (Auto) getOneIntersectingObject(Auto.class);
            if (car != null ){
                if (car.getSpeed() == 0) {
                    setLocation(oldX, oldY);
                }
            }
            
        }

    }
    
    /**
     * @return Ob der Spieler das Haus berührt.
     */
    private boolean isTouchingHouse() {
        return !(getIntersectingObjects(Haus.class).isEmpty());
    }
    
    //Implementieren
    private boolean isOnIce() {
        int y = getY();
        if(isOnSlide())
        {
            return false;
        }//Ist der Spieler über y1 und unter y2 und nicht auf einem Schlitten?
        
        if (y>60&&y<280)
        {
            return true;
        }
        return false;
    }
    
    
    /**
     * 
     * @return Ob das Objekt ein Auto berührt
     */
    public boolean isTouchingCar()
    {
        //Actor a = getOneIntersectingObject(Auto.class);
        return this.isTouching(Auto.class);
        /* if ( a != null) {
            return ((Auto) a).isSpawned();
        };
        return false; */
    }

    /**
     * 
     * @return Ob das Objekt auf einem Schlitten ist.
     */
    public boolean isOnSlide() {
        //return getIntersectingObjects(Schlitten.class).size() > 0;
        return isTouching(Schlitten.class);
    }

    public void driveOnSlide() {
        if (!isOnSlide()) return;
        Schlitten schlitten = (Schlitten) getOneIntersectingObject(Schlitten.class);
        if (schlitten == null) {
            return;
        }

        int speed = schlitten.getSpeed();
        if (schlitten.isDrivingToLeft()) { 
            setLocation(getX() - speed, getY());
        }   else {
            setLocation(getX() + speed, getY());
        }
    }

    public abstract String getName();
    
}