package afujita.src;

public class Room extends Structure {

    public Room(String _room) {
        System.out.println("Room created");
    }

    public void setId(int _room) {
        System.out.println("id set");
    }

    public void setCreature(Creature _Monster) {
        System.out.println("creature set");
    }
}
