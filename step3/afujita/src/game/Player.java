package game;

import java.util.ArrayList;

public class Player extends Creature {

    //list of variables for Player class:
    private Sword sword;
    private Armor armor;
    private int HPMoves;
    private int accumMoves;

    private ArrayList<Item> items;

    public Player() {
        type = '@';
        items = new ArrayList<Item>();
        HPMoves = 0;
    }

    //variable setters
    public void addItem(Item _item) {
        items.add(_item);
        System.out.println(_item.getClass() + " added");
    }

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

    public void setAccumMoves(int _accumMoves) {
        accumMoves = _accumMoves;
    }

    public void setHpMoves(int _HPMoves) {
        HPMoves = _HPMoves;
    }

    public int getHpMoves() {
        return HPMoves;
    }

    public int getAccumMoves() {
        return accumMoves;
    }

    // variable getters
    public ArrayList<Item> getItems() {
        return items;
    }
}
