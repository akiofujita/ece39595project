package src;

public class Armor extends Item {

    //list of private variables for Armor class:
    private int serial;
    private String name;
    private int room;

    public Armor(String _name) {
        name = _name;
        System.out.println("Armor created");
    }


    //variable setters:
    public void setName(String _name) {
        name = _name;
        System.out.println("set Armor name to: " + name);
    }

    public void setID(int _room, int _serial) {
        room = _room;
        serial = _serial;
        System.out.println("room: " + room + "\n" + "serial: " + serial);
    }


    //variable getters:
    public String getName() {
        return name;
    }

    public int getIDRoom() {
        return room;
    }

    public int getIDSerial() {
        return serial;
    }
}