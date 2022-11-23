public class ButtonPause extends Button {

    public ButtonPause() {
        super("button.png");
        
    }

    @Override
    public void onClick() {
        Winterwelt world = (Winterwelt) getWorld();
        world.setRunning(!world.isRunning());
    }
    @Override
    public void act() {
        super.act();
    }
    
}
