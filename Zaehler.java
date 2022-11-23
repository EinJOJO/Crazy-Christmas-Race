import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Default
 */
public class Zaehler extends Textbox
{
    public GreenfootImage counter;
    public int punkte;
    public String name;

    public Zaehler(String pName)
    {
        
        name = pName;
        setPunkte(0);
    }
 
    public void erhoehe()
    {
        setPunkte(punkte + 1);
    }
    
    public void verringere()
    {
        setPunkte(punkte -1);
    }

    public void setPunkte(int punkte) {
        this.punkte = punkte;
        setText(name +  " " + String.valueOf(punkte));
    }
}