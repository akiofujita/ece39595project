package game;

import java.util.ArrayList;

public class Passage extends Structure {
    
    private int room1;
    private int room2;
    private ArrayList<Integer> posXs;
    private ArrayList<Integer> posYs;
    // May make Coordinate class later

    public Passage(int _room1, int _room2) {
        System.out.println("Passage created");
        room1 = _room1;
        room2 = _room2;
        posXs = new ArrayList<Integer>();
        posYs = new ArrayList<Integer>();
    }

    public void setRooms(int _room1, int _room2) {
        System.out.println("ID set for room");
        room1 = _room1;
        room2 = _room2;
    }

    @Override
    public void setPosX(int _x){
        posXs.add(_x);
    }

    @Override
    public void setPosY(int _y){
        posYs.add(_y);
    }

    public int getRoom1() {
        return room1;
    }

    public int getRoom2() {
        return room2;
    }

    public ArrayList<Integer> getPosXs(){
        return posXs;
    }

    public ArrayList<Integer> getPosYs(){
        return posYs;
    }
}
