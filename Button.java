import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author Johannes
 */
public abstract class Button extends Actor
{
    private String imageLink;
    private boolean hovering;
    public int hoverDistance = 5;

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


    public final void act() 
    {
        MouseInfo mouseInfo = Greenfoot.getMouseInfo();
        defineDefaultPosition();

        //Is Hovering?
        if (mouseInfo == null) {
            return;
        }
        if (Greenfoot.mouseMoved(this)) { // #1 - Es ergibt true, wenn Maus auf dem Objekt ist && sich bewegt hat.
            hovering = true;
            oldMouseX = mouseInfo.getX();
            oldMouseY = mouseInfo.getY();
        } else {
            if (isHovering()) {
                // #2 Überprüfung ob die Maus sich bewegt hat, weil die vorherige Abfrage false ergab.
                boolean hasMovedX = oldMouseX != mouseInfo.getX();
                boolean hasMovedY = oldMouseY != mouseInfo.getY();

                
                if(!hasMovedX && !hasMovedY) {
                    // Sollte sie sich nicht bewegt haben, ist sie immer noch auf dem Knopf
                    hovering = true;
                } else {
                    hovering = false;
                    // Sollte sie immer noch auf dem Knopf befinden wird #1 true ergeben.
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
    public void onHover()  {
        hoverPosition();
        hoverTransparency();
    }
    public void hoverTransparency() {
        GreenfootImage image = getImage();
        if (isHovering()) {
            
            image.setTransparency(0xf0);
        } else {
            image.setTransparency(0xff);
        }
        
        setImage(image);
    }
    
    public void hoverPosition(boolean animated) {
        
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

    

    public abstract void onClick();
    
   
}
