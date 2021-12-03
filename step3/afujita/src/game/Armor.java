package game;

public class Armor extends Item {

    //list of private variables for Armor class:

    
    public Armor(String _name, int _serialNum, int _roomNum) {
        super(_name, _serialNum, _roomNum);
        type = ']';
    }


    //variable setters:
    public void setName(String _name) {
        name = _name;
    }

    @Override
    public String getName() {
        return "+" + this.intValue + " Armor";
    }
}