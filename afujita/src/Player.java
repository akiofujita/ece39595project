package afujita.src;

public class Player extends Creature {
    
    private String name;
    private int room;
    private int serial;
    private String type;

    public Player(String name, int room, int serial, String type) {
        System.out.println("Player created")
    }

    public void setWeapon(Item _sword) {
        System.out.println("Weapon set");
    }

    public void setArmor(Item _armor) {
        System.out.println("Armor set");
    }
}
