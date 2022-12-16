import greenfoot.*;

/**
 * Write a description of class Textbox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Textbox extends Actor
{
    private String text = "";
    private Color color = Color.BLACK;
    private GreenfootImage image = new GreenfootImage(1,1);
    private int fontSize = 18;



    public void setColor(Color color) {
        this.color = color;
        update();
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
        update();
    }


    public Color getColor() {
        return color;
    }

    

    public void setText(String text) {
        this.text = text;
        String[] lines = text.split("\n");
        this.image = new GreenfootImage(9 * text.length() + 20, fontSize * lines.length + 10);
        update();
    }

    public String getText() {
        return text;
    }

    public void update() {
       
        image.clear();
        image.setColor(getColor());
        image.setFont( new Font( "SANSERIF", true, false, fontSize ) );
        
        
        image.drawString(text, 5, fontSize  - 2);
        setImage(image);
    }
}
