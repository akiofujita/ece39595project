package game;

import java.util.Random;

public class Monster extends Creature {

    //list of variables for Monster class:

    public Monster(String _name) {
        super(_name);
        if (_name == "Troll") {
            type = 'T';
        }
        if (_name == "Snake") {
            type = 'S';
        }
        if (_name == "Hobgoblin") {
            type = 'H';
        }
    }

    @Override
    public char getType() {
        Dungeon dungeon = Dungeon.getDungeon();
        if (dungeon.getIsHallucinate()) {
            Random rand = new Random();
            String displayChars = dungeon.getDisplayChars();
            return displayChars.charAt(rand.nextInt(displayChars.length())); 
        }
        else {
            return this.type;
        }
    }
}
