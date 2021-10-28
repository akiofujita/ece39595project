package game;

public class EndGame extends CreatureAction {

    public String name;
    
    public EndGame(String _name, Creature owner){
        super(owner);
        name = _name;
    }
}