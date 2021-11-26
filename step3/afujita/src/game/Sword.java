package game;

public class Sword extends Item {
    
    //list of private variables for Sword class:


    public Sword(String _name, int _serialNum, int _roomNum){
        super(_name, _serialNum, _roomNum);
        type = '|';
    }


    //variable setters:
    public void setName(String _name) {
        name = _name;
    }
}