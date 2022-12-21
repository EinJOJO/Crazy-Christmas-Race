public class ButtonPause extends Button {

    public ButtonPause() {
        super("ButtonPause.png");
        
    }

    @Override
    public void onClick() {
        Winterwelt world = (Winterwelt) getWorld();
        world.setRunning(!world.isRunning());
    }
   
    
}
