package afujita.src;

public class Monster extends Creature {

    private String name;
    private int serial;
    private String type;

    public Monster() {
        System.out.println("Monster created");
    }

    public void setName(String _name) {
        System.out.println("Name set");
    }

    public void setID(int _room, int _seriel) {
        System.out.println("ID set");
    }
    
}
