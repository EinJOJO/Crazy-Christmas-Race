import greenfoot.*;
public class ButtonStartGame extends Button {


    public ButtonStartGame() {
        super("ButtonStartGame.png");
    }
    
    private boolean loading = false;

    @Override
    public void onClick() {
        if (loading ) return;
        loading = true;
        Greenfoot.setWorld(new Winterwelt());
    }
    
}
