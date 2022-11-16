import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Button extends Actor
{
    private String imageLink;
    boolean hovering;
    public Button(String imageLink) {

        if (imageLink == null || imageLink.length() == 0) {
            setImageLink("button.png");
        } else {
            setImageLink(imageLink);
        }   
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
        setImage(this.imageLink);
    }



    public void onHover() {
        this.setLocation(getX(), getY() + 5);
    }

    public abstract void onClick();
    
    public void act() 
    {
        if (Greenfoot.mouseMoved(this)) {
            this.hovering = true;
            onHover();
        } else {
            this.hovering = false;
        }

        if (Greenfoot.mouseClicked(this)) {
            onClick();
        }
    }    
}
