package game;

import java.util.Random;

public class TraversableStructure extends Structure {
    public TraversableStructure() {

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