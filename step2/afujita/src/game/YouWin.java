package src;

public class Teleport extends CreatureAction {

    private String name;
    //private Creature owner
    
    public YouWin(String _name, Creature owner){
        super(owner);
        name = _name;
    }
}