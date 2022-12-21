import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PauseCard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PauseCard extends Actor
{
    private boolean loaded = false; 
    public ButtonEndGame endButton = new ButtonEndGame();
    public ButtonResumeGame resumeButton = new ButtonResumeGame();
    public PauseCard() {
        setImage(new GreenfootImage("CardGamePaused.png"));
    }

}
