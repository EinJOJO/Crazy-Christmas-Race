import greenfoot.*;
public class ButtonCredits extends Button {


    public ButtonCredits() {
        super("ButtonCredits.png");
    }

    @Override
    public void onClick() {
        
        Greenfoot.setWorld(new CreditsScreen());
    }
    
}
