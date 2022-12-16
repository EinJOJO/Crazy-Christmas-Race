import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;

import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.MouseInfo;
public class Link extends Textbox {

    private final URI uri;
    private final String displayText;
    private Timer timer;

    private boolean hovering;
    private int oldMouseX;
    private int oldMouseY;

    public Link(String url, String text) throws URISyntaxException {
        uri = new URI(url);
        this.displayText = text;
        setText(displayText);
    }

    public Link(String url ) throws URISyntaxException {
        this.displayText = url;
        uri = new URI(url);
        setText(displayText);
    }

    @Override
    public void act() {
        hoverCheck();
        hoverTransparency();
        if (timer != null) {
            if (timer.isRunning()) {
                if (timer.isFinished()) {
                    setText(uri.toASCIIString());
                    timer.stop();
                }
            }
        }

        if (Greenfoot.mouseClicked(this)) {
            if (!openWebpage()) {
                setText("Link konnte nicht geöffnet werden.");
                if (timer == null) {
                    timer = new Timer();
                    timer.setEnd(1000 * 2);
                }
                timer.start();
                
            }
        }
    }
    public void hoverTransparency() {
        GreenfootImage image = getImage();
        if (hovering) {
            
            image.setTransparency(0xaa);
        } else {
            image.setTransparency(0xff);
        }
        
        // setImage(image);
    }
    private void hoverCheck() {
        MouseInfo mouseInfo = Greenfoot.getMouseInfo();
        if (mouseInfo == null) {
            return;
        }
        if (Greenfoot.mouseMoved(this)) { // #1 - Es ergibt true, wenn Maus auf dem Objekt ist && sich bewegt hat.
            hovering = true;
            oldMouseX = mouseInfo.getX();
            oldMouseY = mouseInfo.getY();
        } else {
            if (hovering) {
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
    }

    public boolean openWebpage() {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                
                desktop.browse(uri);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    
    
}
