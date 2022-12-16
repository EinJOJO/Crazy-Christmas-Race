import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ButtonHome here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ButtonHome extends Button
{
    public ButtonHome() {
        super("ButtonHome.png");
    }
    
    public void onClick() {
        Greenfoot.setWorld(new StartScreen());
    }
 
}
