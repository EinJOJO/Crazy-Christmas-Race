import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import greenfoot.*;

/**
 * Auto brummt brutulm.
 * @author Johannes
 * 
 * TODO:
 * - Random Respawn Delay
 */
public class Auto extends Actor implements Logger.Loggable
{    
    private final boolean blue;
    private int speed;
    private int originalSpeed;
    private int targetSpeed; 
    private final boolean drivingLeft;
    private boolean spawned = false; 
    private Random random = new Random();
    private static GreenfootSound s1 = new GreenfootSound("car1.mp3");
    private static GreenfootSound s2 = new GreenfootSound("car2.mp3");
    private static GreenfootSound s3 = new GreenfootSound("honk1.mp3");
    private static GreenfootSound s4 = new GreenfootSound("honk2.mp3");
    private static GreenfootSound[] crashSounds = new GreenfootSound[]{
        new GreenfootSound("crash1.mp3"),
        new GreenfootSound("crash2.mp3"),
        new GreenfootSound("crash3.mp3")
    };

    private Timer brakeTimer = new Timer();

    


    /**
     * Konstruktor 1 von 2. Leere Parameter:
     * Erstellt ein Blaues Auto, welches mit einer Geschwindigkeit von 2 nach rechts fährt.
     */
    public Auto() {
        this.blue = true;
        this.speed = 2;
        targetSpeed = 2;
        this.drivingLeft = false;
        spawn();

    }

    /**
    * Konstruktor 2 von 2. Dieser nimmt alle Parameter, sodass alles eingestellt werden kann.
    */
    public Auto(int speed, boolean autoBlau, boolean drivesToLeft)

    {
        this.speed = speed;
        this.targetSpeed = speed;
        this.originalSpeed = speed;
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
        if (brakeTimer.isRunning()) {
            if (brakeTimer.isFinished()) {
                targetSpeed = originalSpeed;
                brakeTimer.stop();
            }
        }

        accelerate();
        
        if (!playerCollision() && !carCollision()) {
            if (drivingLeft) {
                setLocation(getX() - speed, getY());
            } else {
                setLocation(getX() + speed, getY());
            }
        };
        //Auto bewegen.
        

        // Sound
        playDriveSounds();
        
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
            if (!Winterwelt.getInstance().getCarSpawnQueue().contains(this))  {
                Winterwelt.getInstance().addToDelayQueue(this);
            }
            return;
        }
        
        if (wirdSchneeballBeruehrt()){
            speed=0;
        
        }
        
    }

    public void accelerate() {
        if (targetSpeed == speed) return;
        if (targetSpeed > speed) {
            speed++;
        } else {
            speed--;
        }
    }
    
    
    public boolean carCollision() {
        if (getHittedCar() != null) { 
            brake();
            crashSounds[2].setVolume(30);
            crashSounds[2].play();
            return true;
        } 
        return false;
    }
    public boolean playerCollision() {
        if (!spawned) return false;
        if (speed == 0) return false;
        if (!getIntersectingObjects(Player.class).isEmpty()) {
            // PLAY SOUNDS
            int r = random.nextInt(3);
            switch (r) {
                case 0:
                    s3.setVolume(30);
                    crashSounds[0].setVolume(20);
                    crashSounds[0].play();
                    s3.play();  
                    break;
                case 2:
                    crashSounds[1].setVolume(20);
                    crashSounds[1].play();
                case 1:
                    s4.setVolume(30);
                    s4.play();  
                    break;

            } 
            brake();
            return true;
        }
        return false;
    }

    private void brake() {
        targetSpeed = 0;
            brakeTimer.setEnd(500);
            brakeTimer.start();
    }

    public void playDriveSounds() {
        int i = getObjectsInRange(60, Player.class).size();
        
        boolean shouldPlaySounds = i != 0;
        if (!shouldPlaySounds) return;
        int r = random.nextInt(2);
        
        switch (r) {
            case 0:
                 s1.setVolume(30);
                 s1.play();  
                break;
            case 1:
                 s2.setVolume(30);
                 s2.play();  
                break;
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


    private Auto getHittedCar() {
        if (!isTouchingCar()) return null;

        List<Auto> list = getIntersectingObjects(Auto.class);
        Auto hittedCar = null;
        
        if (drivingLeft) {
            for (Auto a : list) {
                if (a.getX() < getX()) { // Wenn das Auto vor einem ist:
                    hittedCar = a;
                }
            }
        } else {
            for (Auto a : list) {
                if (a.getX() > getX()) { // Wenn das Auto vor einem ist:
                    hittedCar = a;
                }
            }
        }
        return hittedCar;
    }

    public boolean isTouchingCar() {
        if (!spawned) return false;
        List<Auto> list = getIntersectingObjects(Auto.class);
        if (list.isEmpty()) return false;
        
        for (Auto auto : list) {
            if (!auto.isSpawned()) {
                continue;
            }
            return true;    
        }
        return false;
        
    }
    
    public int getSpeed() {
        return speed;
    }

    @Override
    public Map<String, String> getLogInfo() {
        Map<String, String> map = new HashMap<>();

        map.put("isBlue", String.valueOf(blue));
        map.put("currentSpeed", String.valueOf(speed));
        map.put("originalSpeed", String.valueOf(originalSpeed));
        map.put("targetSpeed", String.valueOf(targetSpeed));
        map.put("drivingLeft", String.valueOf(drivingLeft));
        map.put("spawned", String.valueOf(spawned));
        map.put("currentX", String.valueOf(getX()));
        map.put("currentY", String.valueOf(getY()));
        brakeTimer.getLogInfo().forEach((k,v) -> map.put("brakeTimer."+k, v));


        return map;
    }
}
