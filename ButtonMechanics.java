import greenfoot.*;

public class ButtonMechanics extends Button {


    public ButtonMechanics() {
        super("ButtonMechanics.png");
    }

    @Override
    public void onClick() {
        Greenfoot.setWorld(new MechanicsScreen());
    }
    
}
