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
        rooms.add(_room);
    }

    public void addCreature(Creature _creature) {
        creatures.add(_creature);
    }

    public void addItem(Item _item) {
        items.add(_item);
    }

    public void addPassage(Passage _passage) {
        passages.add(_passage);
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public ArrayList<Creature> getCreatures() {
        return creatures;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public ArrayList<Passage> getPassages() {
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

    /* Move player around dungeon */
    public void move(ObjectDisplayGrid displayGrid, int moveX, int moveY) {
        int oldPlayerX = player.getPosX() + rooms.get(player.getRoomNum() - 1).getPosX();
        int oldPlayerY = player.getPosY() + rooms.get(player.getRoomNum() - 1).getPosY() + topHeight;
        int newPlayerX = oldPlayerX + moveX;
        int newPlayerY = oldPlayerY + moveY;

        // System.out.println(oldPlayerX + ", " + oldPlayerY + ", " + newPlayerX + ", " + newPlayerY);

        Stack<Displayable>[][] objectGrid = displayGrid.getObjectGrid();
        Displayable newObject = objectGrid[newPlayerX][newPlayerY].peek();

        System.out.println("Running into " + newObject.getClass());
        
        /* If accumulated moves matches HPMoves, player gains 1 HP. */
        player.setAccumMoves(player.getAccumMoves() + 1);
        if (player.getAccumMoves() == player.getHpMoves()) {
            player.setHp(player.getHP() + 1);
            displayHP(displayGrid, player.getHP());
            player.setAccumMoves(0);
        }

        /* If new position is not creature, wall, or blank, then move player */
        if (newObject instanceof Creature == false && 
            newObject instanceof RoomWall == false && 
            newObject.getType() != ' ') {
            player.setPosX(player.getPosX() + moveX);
            player.setPosY(player.getPosY() + moveY);
            newObject = displayGrid.removeObjectFromDisplay(oldPlayerX, oldPlayerY);
            displayGrid.addObjectToDisplay(player, newPlayerX, newPlayerY);
        }
        /* If running into Monster, exchange attacks */
        else if (newObject instanceof Monster) {
            Monster monster = (Monster) newObject;
            System.out.println("Monster Had : " + monster.getHP());
            System.out.println("Player  Had : " + player.getHP());

            /* Subtract monster and player hitpoints */
            int monsterLostHP = monster.receiveDamage(player);
            int playerLostHP = player.receiveDamage(monster);
            String infoString = "";

            displayHP(displayGrid, player.getHP());
            System.out.println("Monster Lost : " + monsterLostHP);
            System.out.println("Player  Lost : " + playerLostHP);

            /* If player dead, end game */ 
            if (!player.getHealthStatus()) {
                infoString = "You Lose! End Game";
                endGameDungeon(displayGrid, infoString);
            }
            /* If monster dead, remove monster from display */ 
            else if (!monster.getHealthStatus()) {
                infoString = monster.getName() + " killed!";
                displayGrid.removeObjectFromDisplay(newPlayerX, newPlayerY);
            }
            else {
                infoString = "Monster lost " + monsterLostHP + "HP, Player lost " + playerLostHP + "HP";
            }
            displayInfo(displayGrid, infoString);
        }
    }

    /* Pick up item from floor and add to player's pack */
    public void pick(ObjectDisplayGrid displayGrid) {
        int absPlayerX = player.getPosX() + rooms.get(player.getRoomNum() - 1).getPosX();
        int absPlayerY = player.getPosY() + rooms.get(player.getRoomNum() - 1).getPosY() + topHeight;

        Stack<Displayable>[][] objectGrid = displayGrid.getObjectGrid();
        Stack<Displayable> location = objectGrid[absPlayerX][absPlayerY];
        Displayable objectUnderPlayer = location.get(location.size() - 2);

        /* If item under player, pick it up */ 
        if (objectUnderPlayer instanceof Item) {
            // Displayable pickedObj = displayGrid.removeObjectFromDisplay(absPlayerX, absPlayerY);
            // System.out.println(pickedObj);
            Item pickedItem = (Item) displayGrid.removeObjectFromDisplay(absPlayerX, absPlayerY, location.size() - 2);
            if (pickedItem != null) {
                player.addItem(pickedItem);
                displayInfo(displayGrid, "Picked up " + pickedItem.getName());
            }
            else {
                displayInfo(displayGrid, "ERROR: PICKED ITEM IS NULL");
            }
        }
        else {
            displayInfo(displayGrid, "No item to pick up!");
        }
    }

    /* Remove item from player's pack and place under player */ 
    public void drop(ObjectDisplayGrid displayGrid, int dropNum) {
        int absPlayerX = player.getPosX() + rooms.get(player.getRoomNum() - 1).getPosX();
        int absPlayerY = player.getPosY() + rooms.get(player.getRoomNum() - 1).getPosY() + topHeight;
        
        Stack<Displayable>[][] objectGrid = displayGrid.getObjectGrid();
        Stack<Displayable> location = objectGrid[absPlayerX][absPlayerY];
        ArrayList<Item> pack = player.getItems();

        /* If selected item can be dropped, drop it */
        if (pack.size() > 0) {
            if (0 <= dropNum - 1 && dropNum - 1 < pack.size()) {
                Item droppedItem = pack.remove(dropNum - 1);
                displayGrid.addObjectToDisplay(droppedItem, absPlayerX, absPlayerY, location.size() - 1);
                displayInfo(displayGrid, "Dropped " + droppedItem.getName());
            }
            else {
                displayInfo(displayGrid, "Selected item number " + dropNum + " doesn't exist!");
            }
        }
        else {
            displayInfo(displayGrid, "No item to drop!");
        }
    }

    /* Display HP text */
    public void displayHP(ObjectDisplayGrid displayGrid, int HP) {
        String HPString = "" + HP;
        int HPStartX = 4;
        int HPStartY = 0;

        eraseDisplay(displayGrid, HPStartX, HPStartX + 3, HPStartY, 1);
        
        if (HPStartY + HPString.length() > width) {
            System.out.println("ERROR: DISPLAY INFO STRING TOO LONG");
        }
        displayGrid.displayString(HPString, HPStartX, HPStartY);
    }

    /* Display pack text */
    public void displayPack(ObjectDisplayGrid displayGrid) {
        ArrayList<Item> pack = player.getItems();
        int stringStartX = 6;
        int stringStartY = topHeight + gameHeight + bottomHeight / 2 - 1;
        String itemName = "";
        int itemNum = 1;

        eraseDisplay(displayGrid, stringStartX, width, stringStartY, stringStartY + bottomHeight / 2);

        /* Display pack info */
        for (Item item : pack) {
            itemName = "[" + itemNum++ + "] " + item.getName();
            if (stringStartX > width) {
                stringStartX = 6;
                stringStartY++;
            }
            // System.out.println(stringStartX + ", " + stringStartY + ", " + itemName);
            displayGrid.displayString(itemName, stringStartX, stringStartY);
            stringStartX += itemName.length() + 1;
        }
    }

    /* Display info text */
    public void displayInfo(ObjectDisplayGrid displayGrid, String infoString) {
        int infoStartX = 6;
        int infoStartY = topHeight + gameHeight + bottomHeight - 1;

        eraseDisplay(displayGrid, infoStartX, width, infoStartY, infoStartY + 1);

        if (infoStartY + infoString.length() > width) {
            System.out.println("ERROR: DISPLAY INFO STRING TOO LONG");
        }
        displayGrid.displayString(infoString, infoStartX, infoStartY);
    }

    /* Erase display to overwrite previous text */
    private void eraseDisplay(ObjectDisplayGrid displayGrid, int eraseStartX, int eraseEndX, int eraseStartY, int eraseEndY) {
        Stack<Displayable>[][] objectGrid = displayGrid.getObjectGrid();
        Stack<Displayable> location;
        Displayable newObject;

        for (int j = eraseStartY; j < eraseEndY; j++) {
            for (int i = eraseStartX; i < eraseEndX; i++) {
                location = objectGrid[i][j];
                newObject = location.peek();
                // System.out.println("i: " + i + "; j: " + j);
                if (newObject.getType() != ' ') {
                    displayGrid.removeObjectFromDisplay(i, j);
                }
            }
        }
    }

    /* End game */
    public void endGameDungeon(ObjectDisplayGrid displayGrid, String infoStr) {
        displayInfo(displayGrid, infoStr);
        displayGrid.endGameGrid();
    }

    // /* Prints stack at player location for debugging */
    // public void print(ObjectDisplayGrid displayGrid) {
    //     int absPlayerX = player.getPosX() + rooms.get(player.getRoomNum() - 1).getPosX();
    //     int absPlayerY = player.getPosY() + rooms.get(player.getRoomNum() - 1).getPosY() + topHeight;

    //     Stack<Displayable>[][] objectGrid = displayGrid.getObjectGrid();
    //     Stack<Displayable> location = objectGrid[absPlayerX][absPlayerY];
    //     System.out.println(location);
    // }
}
