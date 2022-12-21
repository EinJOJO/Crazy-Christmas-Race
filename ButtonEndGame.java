public class ButtonEndGame extends Button
{
    public ButtonEndGame() {
        super("ButtonEndGame.png");
    }

    @Override
    public void onClick() {
        if (getWorld() == null) return;
        if (!(getWorld() instanceof Winterwelt)) return;
        Winterwelt winterwelt = (Winterwelt) getWorld();
        winterwelt.endGame();
    }

    @Override
    public void onHover() {
        hoverTransparency();
    }
}
