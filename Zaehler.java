import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Default
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
        update();
    }
    
    public void verringere()
    {
        punkte -= 1;
        update();
    }

    public void setPunkte(int punkte) {
        this.punkte = punkte;
        update();
    }
}