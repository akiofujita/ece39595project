package game;

public class Teleport extends CreatureAction {

    private String name;
    //private Creature owner
    
    public Teleport(String _name, Creature owner){
        super(owner);
        name = _name;
    }
}