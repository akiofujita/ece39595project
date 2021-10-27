package game;

public class Passage extends Structure {
    
    private int room1;
    private int room2;
    private int[] posXs;
    private int[] posYs;

    public Passage(int room1, int room2) {
        System.out.println("Passage created");
    }

    public void setName(String _name) {
        System.out.println("Name set");
    }

    public void setID(int _room1, int _room2) {
        System.out.println("ID set for room");
    }

    @Override
    public void setPosX(int _x){
        System.out.println("Passage: PosX added");
    }

    @Override
    public void setPosY(int _y){
        System.out.println("Passage: PosY added");
    }
}
