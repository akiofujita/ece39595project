package src;

public class Armor extends Item {

    private int serial;
    private String name;
    private int room;

    public Armor(String _name) {
        name = _name;
        System.out.println("Armor created");
    }

    public void setName(String _name) {
        name = _name;
        System.out.println("set Armor name to: " + name);
    }

    public void setID(int _room, int _serial) {
        room = _room;
        serial = _serial;
        System.out.println("room: " + room + "\n" + "serial: " + serial);
    }
}