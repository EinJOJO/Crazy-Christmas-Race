import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)



/**
 * Write a description of class Zaehler here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Zaehler  extends Actor
{
    public GreenfootImage counter;
    public int punkte;
    public String name;

    public Zaehler(String pName)
    {
        counter = new GreenfootImage(320, 36);
        punkte = 0;
        name = pName;
        update();
        
    }
    
    
    /**
     * Act - do whatever the Zaehler wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
    
    public void update()
    {
        counter.clear();
        counter.setColor(Color.BLACK);
        /** neuen Punktestand in den Counter schreiben */
        counter.setFont( new Font( "SANSERIF", true, false, 18 ) );
        String punktestand =  name + String.valueOf(punkte);
        counter.drawString(punktestand, 24, 24);
        this.setImage(counter);
    }
    
    public void erhoehe()
    {
        punkte += 1;
    }
    
    public void verringere()
    {
        punkte -= 1;
    }
}