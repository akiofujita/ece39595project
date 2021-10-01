package afujita.src;

public class Dungeon extends Displayable {

    private static Dungeon myDungeon = null;
    private String name;
    private int width;
    private int topHeight;
    private int gameHeight;
    private int bottomHeight;

    private Dungeon(String _name, int _width, int _topHeight, int _gameHeight, int _bottomHeight) {
        name = _name;
        width = _width;
        topHeight = _topHeight;
        gameHeight = _gameHeight;
        bottomHeight = _bottomHeight;
    }

    public static Dungeon buildDungeon(String _name, int _width, int _topHeight, int _gameHeight, int _bottomHeight) {
        if (myDungeon == null) {
            myDungeon = new Dungeon(_name, _width, _topHeight, _gameHeight, _bottomHeight);
            System.out.println("Dungeon Room created");
        }
        else {
            System.out.println("Dungeon Room created already");
        }
        return myDungeon;
    }

    public void addRoom(Room _room) {
        System.out.println("Dungeon addRoom");
    }

    public void addCreate(Creature _creature) {
        System.out.println("Dungeon addCreate");
    }

    public void addPassage(Passage _passage) {
        System.out.println("Dungeon addPassage");
    }

    public void addItem(Item _item) {
        System.out.println("Dungeon addItem");
    }
}
