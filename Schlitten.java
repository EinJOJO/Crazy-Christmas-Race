import java.util.HashMap;
import java.util.Map;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Schlitten extends Actor implements Logger.Loggable
{
    private final int speed;
    private final boolean drivingToLeft;
    private int respawnProgress = -1;
    
    
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
        if (respawnProgress != -1) {
            respawnWithFading();
            return;
        }
        if (drivingToLeft) {
            setLocation(getX() - speed, getY());
        } else {
            setLocation(getX() + speed, getY());
        }
        if (isAtEdge()) {
            if (respawnProgress >= 20 || respawnProgress == -1) {
                respawnProgress = 0;
            }
        }
        
    }

    public void respawnWithFading() {
        GreenfootImage image = getImage();
        int cOpacity = image.getTransparency();
        try {
            if (respawnProgress < 5) {
                image.setTransparency(cOpacity - 255 / 5);
                respawnProgress++;
                return;
            } 
            if (respawnProgress == 5) {
                if (drivingToLeft) {
                    setLocation(getWorld().getWidth(), getY());
                } else {
                    setLocation(0, getY());
                }
            }
            if (respawnProgress < 15) {
                image.setTransparency(cOpacity + 25);
                respawnProgress++;
                return;
            }
            respawnProgress = -1;
        } catch(IllegalArgumentException e){
            image.setTransparency(255);
            respawnProgress = -1;
        }
        
        
        
    }

    public boolean isOnSlide() {
        
        return !getIntersectingObjects(Schlitten.class).isEmpty();
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

    

    @Override
    public Map<String, String> getLogInfo() {
        Map<String, String> map = new HashMap<>();
        map.put("speed", String.valueOf(speed));
        map.put("drivingLeft", String.valueOf(drivingToLeft));
        map.put("respawnProgress", String.valueOf(respawnProgress));
        map.put("currentX", String.valueOf(getX()));
        map.put("currentY", String.valueOf(getY()));
        return map;
    }
}
