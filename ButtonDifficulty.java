import greenfoot.Greenfoot;

public class ButtonDifficulty extends Button{

    private Winterwelt.Difficulty difficulty;
    

    public ButtonDifficulty() {
        super("ButtonNormal.png");
        this.difficulty = Winterwelt.Difficulty.NORMAL;
        update();
        hoverDistance = 2;
    }

    public ButtonDifficulty(Winterwelt.Difficulty difficulty) {
        
        super("ButtonNormal.png");
        this.difficulty = difficulty;
        hoverDistance = 2;
        update();
    }

    public void update() {
        switch(difficulty) {
            case EASY:
                setImageLink("ButtonEinfach.png");
                break;
            case CHALLENGING:
                setImageLink("ButtonChallenge.png");
                break;
            case HARD:
                setImageLink("ButtonSchwer.png");
                break;
            case NORMAL:
                setImageLink("ButtonNormal.png");
                break;
        }
    }

    @Override
    public void onClick() {
        Winterwelt.Difficulty[] a = Winterwelt.Difficulty.values();
        int b = a.length;

        difficulty = a[(difficulty.index+1) % b];
        update();
        
        if (getWorld() == null || (!(getWorld() instanceof Winterwelt))) {
            return;
        }
        Winterwelt world = (Winterwelt) getWorld();

        Greenfoot.setWorld(new Winterwelt(difficulty, gameMinutes()));
        
    }
    
    private int gameMinutes() {
        return ButtonPlaytime.getInstance().getPlayTime();
    }

    public Winterwelt.Difficulty getDifficulty() {
        return difficulty;
    }
}
