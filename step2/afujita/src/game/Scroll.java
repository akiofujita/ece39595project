package game;

public class Scroll extends Item {

    private int serial;
    private int room;
    private String name;
    
    public Scroll(String _name){
        name = _name;
        System.out.println("Scroll: " + name);
    }

    public void setId(int _room, int _serial){
        room = _room;
        serial = _serial;
        System.out.println("room: " + room + "\n" + "serial: " + serial);
    }
}