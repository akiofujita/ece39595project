package game;

public class Sword extends Item {

    private int serial;
    private int room;
    private String name;
    
    public Sword(String _name){
        type = '|';
        name = _name;
        System.out.println("Sword: " + name);
    }

    public void setId(int _room, int _serial){
        room = _room;
        serial = _serial;
        System.out.println("room: " + room + "\n" + "serial: " + serial);
    }

    public String toString() {
        return "Sword";
    }
}