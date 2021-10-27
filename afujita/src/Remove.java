package src;

public class Remove extends CreatureAction {

    private String name;
    // private Creature owner;
    
    public Remove(String _name, Creature owner){
        super(owner);
        name = _name;
    }
}
