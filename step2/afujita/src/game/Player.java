package game;

public class Player extends Creature {
    
    private String name;
    private int room;
    private int serial;

    public Player() {
        System.out.println("Player created");
    }

    public void setName(String _name) {
        System.out.println("Name set");
    }
    
    public void setID(int room, int serial) {
        System.out.println("ID set");
    }

    // public void setWeapon(Item _sword) {
    //     System.out.println("Weapon set");
    // }

    // public void setArmor(Item _armor) {
    //     System.out.println("Armor set");
    // }
}
