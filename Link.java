import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;

import greenfoot.Greenfoot;
public class Link extends Textbox {

    private final URI uri;
    private final String displayText;
    private Timer timer;

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
        if (timer != null) {
            if (timer.isRunning()) {
                if (timer.isFinished()) {
                    setText(uri.toASCIIString());
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
