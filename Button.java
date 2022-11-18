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

    private int defaultX = -1;
    private int defaultY = -1;
    private int hoverTimeout = 0;

    private MouseInfo mouseInfo = null;

    public Button(String imageLink) {

        if (imageLink == null || imageLink.length() == 0) {
            setImageLink("button.png");
        } else {
            setImageLink(imageLink);
        }   
    }


    public void act() 
    {
        defineDefaultPosition();
        
        if (Greenfoot.mouseMoved(this)) { // Wird nur einmal aufgerufen.
            this.hovering = true;
            hoverTimeout = 10;
            
        } else {
             if ( mouseInfo != null) {
                boolean movedX = mouseInfo.getX() != Greenfoot.getMouseInfo().getX();
                boolean movedY = mouseInfo.getY() != Greenfoot.getMouseInfo().getY();

                if (!movedX && !movedY) {
                    //Falls die Maus nicht bewegt wurde, befindet sie sich logisch noch auf dem Knopf.
                    hoverTimeout = 10;
                } else {
                    mouseInfo = null;
                }

             } else {   
                mouseInfo = Greenfoot.getMouseInfo();
             }
            if (hoverTimeout <= 0) {
                this.hovering = false;
            } else {
                hoverTimeout--;
            }
            
        }

        onHover();

        if (Greenfoot.mouseClicked(this)) {
            onClick();
        }
    }    

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
        setImage(this.imageLink);
    }


    public int getDefaultX() {
        return defaultX;
    }

    public int getDefaultY() {
        return defaultY;
    }

    public boolean isHovering() {
        return hovering;
    }

    public void onHover()  {
        onHoverDefault();
    }


    private void defineDefaultPosition() {
        //Standard Positionen werden gesetzt, sodass der Hovereffekt funktioniert.
        //-1 sagt aus, dass die Position noch nicht definiert wurde.
        if (getDefaultX() == -1) {
            defaultX = getX(); 
        }
        if (getDefaultY() == -1) { 
            defaultY = getY();
        }
    }

    /**
     * Executed when the persons hovers;
     */
    private void onHoverDefault() {
        int hoverDistance = 5;

        if (isHovering()) {
            if (getY() < getDefaultY() + hoverDistance) {
                setLocation(getX(), getY() + 1);
            } 
        } else {
            if (getY() != getDefaultY()) {
                setLocation(getX(), getDefaultY()
                );
            }
        }
    }

    public abstract void onClick();
    
   
}
