import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

/**
 * Write a description of class RandomTeleportItem here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RandomTeleportItem extends Item
{
    public void onPickUp(Actor actor) {
        System.out.println("Picked up");
        Random random = new Random();
        actor.setLocation(random.nextInt(getWorld().getWidth()), random.nextInt(getWorld().getHeight()));
    }
}
