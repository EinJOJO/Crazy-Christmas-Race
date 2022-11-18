import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.Font;
import greenfoot.GreenfootImage;

/**
 * @author Johannes.
 */
public class Card extends Actor{

    private GreenfootImage cardContent;
    private boolean visible;
    
    public Card() {
        setCardContent("example cardContent");
    }

    public Card(String cardContent) {
        setCardContent(cardContent);

    }


    public void setCardContent(String content) {
        
        GreenfootImage cardContent = new GreenfootImage((int)(getWorld().getHeight() * 0.6), (int)(getWorld().getWidth() * 0.4));
        
        cardContent.setFont( new Font( "SANSERIF", true, false, 18 ) );
        cardContent.drawString(content, getX(), getRotation());

        this.cardContent = cardContent;
        setImage(cardContent);
    }
    
    public void toggle() {
        setVisible(!isVisible());
    };

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void fadeIn() {

    }


    public void fadeOut() {

    }

}
