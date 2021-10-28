package game;

public class Room extends Structure {

    //list of private variables for Room class
    private int room;
    private Creature Monster;

    public Room(int _room) {
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
    public int getId() {
        return room;
    }

    public Creature getCreature(Creature _Monster) {
        return Monster;
    }
}
