package game;

import java.util.ArrayList;

public class Player extends Creature {

    //list of variables for Player class:
    private Sword sword;
    private Armor armor;
    private int HPMoves;
    private int accumMoves;
    private int halMoves = 0;
    private int indexSwordHeld;
    private int indexArmorWorn;

    private ArrayList<Item> items;

    public Player() {
        type = '@';
        items = new ArrayList<Item>();
        HPMoves = 0;
        sword = null;
        armor = null;
    }

    //variable setters
    public void addItem(Item _item) {
        items.add(_item);
        System.out.println(_item.getClass() + " added");
    }

    public Item dropItem(int itemNum) {
        Item removedItem = null;
        itemNum--;
        if (itemNum >= 0 && itemNum < items.size()) {
            removedItem = items.remove(itemNum);
            System.out.println(removedItem.getClass() + " removed");
        }
        else {
            System.out.println("No item to remove");
        }
        return removedItem;
    }

    public Armor alterArmor(int changeVal) {
        if (armor != null) {
            armor.setIntValue(armor.getIntValue() + changeVal);
        }
        return armor;
    }

    public Sword alterSword(int changeVal) {
        if (sword != null) {
            sword.setIntValue(sword.getIntValue() + changeVal);
        }
        return sword;
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

    public void setHalMoves(int _halMoves) {
        halMoves = _halMoves;
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

    public int getHalMoves() {
        return halMoves;
    }

    public void setIndexSwordHeld(int _indexSwordHeld) {
        indexSwordHeld = _indexSwordHeld;
    }

    public void setIndexArmorWorn(int _indexArmorWorn) {
        indexArmorWorn = _indexArmorWorn;
    }

    public int getIndexSwordHeld() {
        return indexSwordHeld;
    }

    public int getIndexArmorWorn() {
        return indexArmorWorn;
    }

    public void setArmor(Armor _armor) {
        armor = _armor;
    }

    public void setSword(Sword _sword) {
        sword = _sword;
    }

    public Sword getSword() {
        return sword;
    }

    public Armor getArmor() {
        return armor;
    }

    // variable getters
    public ArrayList<Item> getItems() {
        return items;
    }
}
