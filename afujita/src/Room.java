package afujita.src;

public class Room extends Structure{
    public Room (String room) {
        System.out.println("Room created");
    }

    public void setId(int room) {
        System.out.println("id set");
    }

    public void setCreature(Creature Monster) {
        System.out.println("creature set");
    }
}
