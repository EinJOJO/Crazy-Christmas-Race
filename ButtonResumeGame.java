public class ButtonResumeGame extends Button
{
    
    public ButtonResumeGame()
    {
        super("ButtonResumeGame.png");
    }

    @Override
    public void onClick() {
        if (getWorld() == null) return;
        if (!(getWorld() instanceof Winterwelt)) return;
        Winterwelt winterwelt = (Winterwelt) getWorld();
        winterwelt.setRunning(true);
    }

    @Override
    public void onHover() {
        hoverTransparency();
    }
}

