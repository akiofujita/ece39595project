package game;

public class Item extends Displayable {

    //list of private variables for Item class:
    private Creature owner;
    protected String name;
    protected int serialNum;
    protected int roomNum;

    public Item(String _name, int _serialNum, int _roomNum) {
        name = _name;
        serialNum = _serialNum;
        roomNum = _roomNum;
    }

    
    //variable setters
    public void setOwner(Creature _owner) {
        owner = _owner;
    }

    public void setName(String _name) {
        name = _name;
        System.out.println("set Item name to: " + name);
    }

    public void setId(int _roomNum, int _serialNum){
        roomNum = _roomNum;
        serialNum = _serialNum;
        System.out.println("room: " + roomNum + "\n" + "serial: " + serialNum);
    }


    //variable getters:
    public String getName() {
        return name;
    }

    public int getIDRoom() {
        return roomNum;
    }

    public int getIDSerial() {
        return serialNum;
    }

}