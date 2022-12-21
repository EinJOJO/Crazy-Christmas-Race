import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


 /**
  * Not implemented
  */
public abstract class Item extends Actor
{
    /**
     * Act - do whatever the Item wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        Actor actor = getOneIntersectingObject(Actor.class);
        
        if (actor == null){
            return;
        }
        
        if (!(actor instanceof Player)) {
            return;
        }
        
        onPickUp(actor);
    }    
    
    public abstract void onPickUp(Actor actor);
}
