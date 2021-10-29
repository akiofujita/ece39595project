package game;

public class Player extends Creature {

    //list of variables for Player class:
    private Sword sword;
    private Armor armor;

    public Player(String _name) {
        super(_name);
        type = '@';
        System.out.println("Player created");
    }

    //variable setters
                    //??: type sword or type Item?
    public void setWeapon(Sword _sword) {
        sword = _sword;
        System.out.println("Weapon set");
    }

    public void setArmor(Armor _armor) {
        armor = _armor;
        System.out.println("Armor set");
    }


    // variable getters
    public Sword getWeapon() {
        return sword;
    }

    public Armor getArmor() {
        return armor;
    }
}
