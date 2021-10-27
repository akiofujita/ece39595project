package game;

public class Room extends Structure{

    //list of private variables for Room class
    private int room;
    private Creature Monster;

    public Room (String _room) {
        System.out.println("Room created");
    }

    
    //variable setters
    public void setId(int _room) {
        System.out.println("id set");
        room = _room;
    }

    public void setCreature(Creature _Monster) {
        System.out.println("creature set");
        Monster = _Monster;
    }


    //variable getters
    public int setId() {
        return room;
    }

    public Creature setCreature(Creature _Monster) {
        return Monster;
    }
}
