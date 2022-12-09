import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Schneeball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Schneeball extends Actor
{
    int speed;
    float rotation; 
    Player werfer;
    public void act() 
    {
        // Add your action code here.
    }
    
    public void bewegen( ) {
        y = getY() + Math.cos(_a_);
    }
    
    
    public boolean werferSanta()
    {
            if (Greenfoot.isKeyDown(controlKeys[0]&&Greenfoot.isSpacebardown)) { 

                getY(getObjectsInRange(_radius_, _cls_));
                return getIntersectingObjects(Santa.class).size() > 0;
        
            }
        }
           
        
    public boolean werferRentier()
    {
        return getIntersectingObjects(Rentier.class).size() > 0;
    }
            
        }
