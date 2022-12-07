import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Schneeball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Schneeball extends Actor
{
    private final int speed;
    
    public Schneeball(int speed) {
        this.speed = speed;
    }
    
    private Player werfer;
    
    
    public void act() 
    {
        bewegen();
    }
    
    public void bewegen( ) {
        setLocation(getX() + speed, getY() + speed);
    }
    

}
