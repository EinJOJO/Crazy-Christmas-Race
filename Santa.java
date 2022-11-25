
public class Santa extends Player
{
    public Santa()
    {
        super(new String[]{"w","a","s","d"}, 266, 580, Winterwelt.counterSanta );       
    }

    @Override
    public String getName() {
        return "Santa";
    }
}
