import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.Font;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

/**
 * @author Johannes.
 */
public class Card extends Actor{

    private String cardContent;
    private GreenfootImage cardImage; 
    private boolean visible = true;
    
    public Card() {
        setCardContent("example cardContent");
    }

    public Card(String cardContent) {
        setCardContent(cardContent);

    }

    public void setCardContent(String content) {
        this.cardContent = content;
         int width = (int)(800 * 0.6);
         int height = (int)(600 * 0.4);

        GreenfootImage cardImage = new GreenfootImage(width, height);
        
    
        cardImage.setColor(Color.WHITE);
        cardImage.fillRect(0, 0, width, height);
        
        cardImage.setColor(Color.BLACK);
        cardImage.setFont( new Font( "SANSERIF", true, false, 18 ) );
        cardImage.drawString(content, 30, 30);

        
        this.cardImage = cardImage;
    }

    public void toggle() {
        setVisible(!isVisible());
    };

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;

        if (visible) {
            setCardContent(cardContent);
        } else {
            setImage(new GreenfootImage(1, 1));
        }
    }

    public void fadeIn() {
        while(!visible) {
            GreenfootImage image = getImage();
            if (image.getTransparency() == 0xff) { //0xff -> 255 aber in cooler.
                toggle();
                return;
            }
            image.setTransparency(image.getTransparency() + 1);
            setImage(image);
            Greenfoot.delay(1);

        }
    }


    public void fadeOut() {
        while(visible) {
            GreenfootImage image = getImage();
            if (image.getTransparency() == 0) {
                toggle();
                return;
            }
            image.setTransparency(image.getTransparency() - 1);
            setImage(image);
            Greenfoot.delay(1);
        }
    }

}
