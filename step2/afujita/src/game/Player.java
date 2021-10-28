package game;

public class Player extends Creature {

    public Player(String _name) {
        name = _name;
        type = '@';
        System.out.println("Player created");
    }

    // public void setWeapon(Item _sword) {
    //     System.out.println("Weapon set");
    // }

    // public void setArmor(Item _armor) {
    //     System.out.println("Armor set");
    // }
}
