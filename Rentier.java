
public class Rentier extends Player
{
    public Rentier()
    {
        //Konstruktor von Player ausführen
        super(new String[]{"up","left","down","right"},536, 580, Winterwelt.counterRentier);
    }

    @Override
    public String getName() {
        return "Rentier";
    }
}
