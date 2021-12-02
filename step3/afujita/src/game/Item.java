package game;

import java.util.Random;

public class Item extends Displayable {

    //list of private variables for Item class:
    protected String name;
    protected int roomNum;
    protected int serialNum;
    private ItemAction itemAction;

    public Item(String _name, int _roomNum, int _serialNum) {
        name = _name;
        serialNum = _serialNum;
        roomNum = _roomNum;
        itemAction = null;
    }

    @Override
    public String toString() {
        String outputStr = "";

        outputStr += "ITEM INFO:";
        // outputStr += "\nisVisible:      " + isVisible;
        // outputStr += "\nmaxHit:         " + maxHit;
        // outputStr += "\nhitPointMoves:  " + hitPointMoves;
        // outputStr += "\nhitPoint:       " + hitPoint;
        outputStr += "\ntype:           " + type;
        // outputStr += "\nintValue:       " + intValue;
        outputStr += "\nposX:           " + posX;
        outputStr += "\nposY:           " + posY;
        return outputStr;
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
    
    //variable setters
    public void setName(String _name) {
        name = _name;
    }

    public void setRoomNum(int _roomNum) {
        roomNum = _roomNum;
    }

    public void setId(int _roomNum, int _serialNum){
        roomNum = _roomNum;
        serialNum = _serialNum;
    }

    public void setItemAction(ItemAction _itemAction) {
        itemAction = _itemAction;
    }


    //variable getters:
    public String getName() {
        return name;
    }

    public int getRoomNum() {
        return roomNum;
    }

    public int getIDSerial() {
        return serialNum;
    }

    public ItemAction getItemAction() {
        return itemAction;
    }


}