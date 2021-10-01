package afujita.src;

public class Passage extends Structure {
    
    private String name;
    private int room1;
    private int room2;

    public Passage(String name, int room1, int room2) {
        System.out.println("Passage created");
    }

    public void setName(String _name) {
        System.out.println("Name set");
    }

    public void setID(int _room1, int _room2) {
        System.out.println("ID set for room");
    }
}
