package game;

public class Room extends Structure {

    //list of private variables for Room class
    private int room;
    private Creature Monster;

    public Room(int _room) {
        System.out.println("Room created");
    }

    @Override
    public String toString() {
        String outputStr = "";
        outputStr += "ROOM INFO:";
        outputStr += "\nisVisible:      " + isVisible;
        outputStr += "\nmaxHit:         " + maxHit;
        outputStr += "\nhitPointMoves:  " + hitPointMoves;
        outputStr += "\nhitPoint:       " + hitPoint;
        outputStr += "\ntype:           " + type;
        outputStr += "\nintValue:       " + intValue;
        outputStr += "\nposX:           " + posX;
        outputStr += "\nposY:           " + posY;
        outputStr += "\nwidth:          " + width;
        outputStr += "\nheight:         " + height;
        return outputStr;
    }
    
    //variable setters
    public void setId(int _room) {
        System.out.println("id set");
        room = _room;
    }

    public void setCreature(Creature _Monster) {
        System.out.println("creature set");
        Monster = _Monster;
    }


    //variable getters
    public int getId() {
        return room;
    }

    public Creature getCreature(Creature _Monster) {
        return Monster;
    }
}
