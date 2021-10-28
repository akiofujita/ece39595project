package game;

public class Sword extends Item {
    
    //list of private variables for Sword class:


    public Sword(String _name, int _serialNum, int _roomNum){
        super(_name, _serialNum, _roomNum);
        type = '|';
        System.out.println("Sword created");
    }


    //variable setters:
    public void setName(String _name) {
        name = _name;
        System.out.println("set Sword name to: " + name);
    }
}