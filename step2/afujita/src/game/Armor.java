package game;

public class Armor extends Item {

    //list of private variables for Armor class:

    
    public Armor(String _name, int _serialNum, int _roomNum) {
        super(_name, _serialNum, _roomNum);
        type = ']';
        System.out.println("Armor created");
    }


    //variable setters:
    public void setName(String _name) {
        name = _name;
        System.out.println("set Armor name to: " + name);
    }
}