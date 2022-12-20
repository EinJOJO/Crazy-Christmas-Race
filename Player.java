
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

import greenfoot.*;

/**
 * Die Eigenschaften des Spielers
 */
public abstract class Player extends Actor implements Logger.Loggable {
 
    /*
     * Index:
     * 0: up
     * 1: left
     * 2: right
     * 3: down
     */
    private final String[] controlKeys; 
    private int deaths;
    private int speed;
    private final int spawnX;
    private final int spawnY;
    private final Zaehler pointsCounter;
    private boolean cancelRespawn = false;
    private boolean hitByCar = false;
    
    private static GreenfootSound s1 = new GreenfootSound("humanImpact.mp3");
    private static GreenfootSound s2 = new GreenfootSound("splash.mp3");
    static {
        s1.setVolume(20);
        s2.setVolume(30);
    }


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
        this.speed = 2;
        this.spawnX = spawnX;
        this.spawnY = spawnY;
        this.pointsCounter = pointsCounter;
        Logger.getInstance().printNewInstanceInfo(this);
        Logger.getInstance().info(this);
    }

    //Implementieren
    @Override
    public void act() {
        
        handleInput();    
        
        /**
         * hitByCar()
         */
        if (isHitByCar()) { 
            hitByCar = false;
            deaths++;
            s1.play();
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
            deaths++;
            s2.play();
            new IceHole(getX(), getY());
            respawn();
        }
      
        
    }
    
    public void respawn () {
        if (cancelRespawn) return;
        setLocation(spawnX, spawnY);
    }
    
    /**
     * Is only called by {@link Auto.class#playerCollision()}
     */
    public void hitByCar() {
        hitByCar = true;
    }
    /**
     * Boolean changes only when {@link Player.class#hitByCar()} is called by {@link Auto.class#playerCollision()}
     */
    public boolean isHitByCar() {
        return hitByCar;
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

 
    public int getDeaths() {
        return deaths;
    }
    public abstract String getName();



    public void setCancelRespawn(boolean cancelRespawn) {
        this.cancelRespawn = cancelRespawn;
    }

    @Override
    public Map<String, String> getLogInfo() {
        Map<String, String> map = new HashMap<>();

        map.put("name", getName());
        map.put("controls", Arrays.toString(controlKeys));
        map.put("speed", String.valueOf(speed));
        map.put("spawnX", String.valueOf(spawnX));
        map.put("spawnY", String.valueOf(spawnY));
        map.put("punkte", String.valueOf(pointsCounter.punkte));
        map.put("deaths", String.valueOf(getDeaths()));
        map.put("cancelRespawn", String.valueOf(cancelRespawn));
        return map;
    }
    
}