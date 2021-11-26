package game;

public class Item extends Displayable {

    //list of private variables for Item class:
    private Creature owner;
    protected String name;
    protected int roomNum;
    protected int serialNum;

    public Item(String _name, int _roomNum, int _serialNum) {
        name = _name;
        serialNum = _serialNum;
        roomNum = _roomNum;
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
    
    //variable setters
    public void setOwner(Creature _owner) {
        owner = _owner;
    }

    public void setName(String _name) {
        name = _name;
    }

    public void setId(int _roomNum, int _serialNum){
        roomNum = _roomNum;
        serialNum = _serialNum;
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

}