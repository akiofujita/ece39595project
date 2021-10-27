package game;

public class Room extends Structure{

    private int room;
    private Creature Monster;

    public Room (String _room) {
        System.out.println("Room created");
    }

    public void setId(int _room) {
        System.out.println("id set");
        room = _room;
    }

    public void setCreature(Creature _Monster) {
        System.out.println("creature set");
        Monster = _Monster
    }
}
