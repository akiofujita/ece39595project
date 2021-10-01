package src;

public class Monster extends Creature {

    private String name;
    private int room;
    private String serial;

    public Monster() {
        System.out.println("Monster created");
    }

    public void setName(String _name) {
        System.out.println("Name set");
    }

    public void setID(int _room, int _serial) {
        System.out.println("ID set");
    }
    
}
