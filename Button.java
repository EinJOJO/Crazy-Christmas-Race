import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author Johannes
 */
public abstract class Button extends Actor
{
    private String imageLink;
    boolean hovering;

    private int defaultX = -1;
    private int defaultY = -1;


    private int oldMouseX = 0;
    private int oldMouseY = 0;

    public Button(String imageLink) {

        if (imageLink == null || imageLink.length() == 0) {
            setImageLink("button.png");
        } else {
            setImageLink(imageLink);
        }   
    }


    public void act() 
    {
        MouseInfo mouseInfo = Greenfoot.getMouseInfo();
        defineDefaultPosition();

        //Is Hovering?
        if (mouseInfo == null) {
            return;
        }
        if (Greenfoot.mouseMoved(this)) {
            hovering = true;
            oldMouseX = mouseInfo.getX();
            oldMouseY = mouseInfo.getY();
        } else {
            if (isHovering()) {
                boolean movedX = oldMouseX != mouseInfo.getX();
                boolean movedY = oldMouseY != mouseInfo.getY();

                if(!movedX && !movedY) {
                    hovering = true;
                } else {
                    hovering = false;
                }
            }
        }
        //Hover function
        onHover();
        //Check for clicks.
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
        hoverPosition();
        GreenfootImage image = getImage();
        if (isHovering()) {
            
            image.setTransparency(0xf0);
        } else {
            image.setTransparency(0xff);
        }
        
        setImage(image);
    }
    
    /**
     * Damit das Hovern möglich ist, muss der Knopf wissen,
     * wo die Ursprüngliche Position war.
     * -1 existiert nicht im Koordinatensystem, weshalb sich
     * der Wert super eignet, um zu sagen "Noch nicht initiiert"
     */
    private void defineDefaultPosition() {
        if (getDefaultX() == -1) {
            defaultX = getX(); 
        }
        if (getDefaultY() == -1) { 
            defaultY = getY();
        }
    }


    public void hoverPosition() {
        hoverPosition(true);
    }

    public void hoverPosition(boolean animated) {
        int hoverDistance = 5;

        if (isHovering()) {
            if (getY() != getDefaultY() + hoverDistance) {
                if (animated) {
                    setLocation(getX(), getY() + 1);
                } else {
                    setLocation(getX(), hoverDistance);
                }
            } 
        } else {
            if (getY() != getDefaultY()) {
                if (animated) {
                    setLocation(getX(), getY() - 1);
                } else {
                    setLocation(getX(), hoverDistance);
                }
            }
        }
    }

    public abstract void onClick();
    
   
}
