package game;

import java.util.ArrayList;
import java.util.Stack;

public class Dungeon extends Displayable {

    private static Dungeon myDungeon = null;
    private String name;
    private int width;
    private int topHeight;
    private int gameHeight;
    private int bottomHeight;

    private ArrayList<Room> rooms;
    private ArrayList<Creature> creatures;
    private ArrayList<Item> items;
    private ArrayList<Passage> passages;
    private Player player;
    private int maxRooms = 0;
    private int roomCount = 0;

    private Dungeon(String _name, int _width, int _topHeight, int _gameHeight, int _bottomHeight) {
        name = _name;
        width = _width;
        topHeight = _topHeight;
        gameHeight = _gameHeight;
        bottomHeight = _bottomHeight;
        rooms = new ArrayList<Room>();
        creatures = new ArrayList<Creature>();
        items = new ArrayList<Item>();
        passages = new ArrayList<Passage>();
        player = new Player();
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
        System.out.println("Dungeon add Room");
        rooms.add(_room);
    }

    public void addCreature(Creature _creature) {
        System.out.println("Dungeon add Creature");
        creatures.add(_creature);
    }

    public void addItem(Item _item) {
        System.out.println("Dungeon add Item");
        items.add(_item);
    }

    public void addPassage(Passage _passage) {
        System.out.println("Dungeon add Passage");
        passages.add(_passage);
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Room> getRooms() {
        System.out.println("Dungeon getRooms");
        return rooms;
    }

    public ArrayList<Creature> getCreatures() {
        System.out.println("Dungeon add Room");
        return creatures;
    }

    public ArrayList<Item> getItems() {
        System.out.println("Dungeon add Creature");
        return items;
    }

    public ArrayList<Passage> getPassages() {
        System.out.println("Dungeon add Passage");
        return passages;
    }

    public int getWidth() {
        return width;
    }

    public int getTopHeight() {
        return topHeight;
    }

    public int getGameHeight() {
        return gameHeight;
    }

    public int getBottomHeight() {
        return bottomHeight;
    }

    public void move(ObjectDisplayGrid displayGrid, int moveX, int moveY) {
        int oldPlayerX = player.getPosX() + rooms.get(player.getRoomNum() - 1).getPosX();
        int oldPlayerY = player.getPosY() + rooms.get(player.getRoomNum() - 1).getPosY() + topHeight;
        int newPlayerX = oldPlayerX + moveX;
        int newPlayerY = oldPlayerY + moveY;
        // System.out.println(player.getRoomNum());
        // System.out.println(rooms);
        // System.out.println(player.getPosX() + ", " + player.getPosY() + ", " + rooms.get(player.getRoomNum()).getPosX() + ", " + rooms.get(player.getRoomNum()).getPosY());
        // System.out.println(oldPlayerX + ", " + oldPlayerY + ", " + newPlayerX + ", " + newPlayerY);

        Stack<Displayable>[][] objectGrid = displayGrid.getObjectGrid();
        Displayable newObject = objectGrid[newPlayerX][newPlayerY].peek();

        System.out.println(newObject);
        if (newObject instanceof RoomWall == false && newObject.getType() != ' ') {
            player.setPosX(player.getPosX() + moveX);
            player.setPosY(player.getPosY() + moveY);
            newObject = displayGrid.removeObjectFromDisplay(oldPlayerX, oldPlayerY);
            displayGrid.addObjectToDisplay(player, newPlayerX, newPlayerY);
        }
    }
}
