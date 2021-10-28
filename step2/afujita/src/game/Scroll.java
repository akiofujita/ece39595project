package game;

public class Scroll extends Item {
    
    //list of private variables for Scroll class:

    
    public Scroll(String _name, int _serialNum, int _roomNum) {
        super(_name, _serialNum, _roomNum);
        type = ']';
        System.out.println("Scroll created");
    }


    //variable setters:
    public void setName(String _name) {
        name = _name;
        System.out.println("set Scroll name to: " + name);
    }
}