package game;

public class DropPack extends CreatureAction {

    private String name;
    
    public DropPack(String _name, Creature owner) {
        super(owner);
        name = _name;
    }
}