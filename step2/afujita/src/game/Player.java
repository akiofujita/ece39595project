package game;

import java.util.ArrayList;

public class Player extends Creature {

    //list of variables for Player class:
    private Sword sword;
    private Armor armor;

    private ArrayList<Item> items;

    public Player() {
        type = '@';
        items = new ArrayList<Item>();
        System.out.println("Player created");
    }

    //variable setters
    public void addArmor(Armor _armor) {
        items.add(_armor);
        System.out.println("Armor added");
    }

    public void addScroll(Scroll _scroll) {
        items.add(_scroll);
        System.out.println("Scroll added");
    }

    public void addSword(Sword _sword) {
        items.add(_sword);
        System.out.println("Sword added");
    }


    // variable getters
    public ArrayList<Item> getItems() {
        return items;
    }
}
