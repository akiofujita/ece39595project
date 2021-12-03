package game;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Random;

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
    private static boolean gameIsOver = false;
    private static boolean isHallucinate = false;
    private static String displayChars = ".X#+|]?SHT";

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

    public static Dungeon getDungeon() {
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

    public void setPlayer(Player _player) {
        player = _player;
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

    public boolean getIsHallucinate() {
        return isHallucinate;
    }

    public void getIsHallucinate(boolean _isHallucinate) {
        isHallucinate = _isHallucinate;
    }

    public String getDisplayChars() {
        return displayChars;
    }

    public void setDisplayChars(String _displayChars) {
        displayChars = _displayChars;
    }

    /* Move player around dungeon */
    public void move(ObjectDisplayGrid displayGrid, int moveX, int moveY) {
        int oldPlayerX = player.getPosX() + rooms.get(player.getRoomNum() - 1).getPosX();
        int oldPlayerY = player.getPosY() + rooms.get(player.getRoomNum() - 1).getPosY() + topHeight;
        int newPlayerX = oldPlayerX + moveX;
        int newPlayerY = oldPlayerY + moveY;
        boolean monsterIsAlive = false;

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

        /* Decrement hallucinate moves until it reaches 0 */
        if (isHallucinate) {
            player.setHalMoves(player.getHalMoves() - 1);
            if (player.getHalMoves() == -1) {
                endHallucinate(displayGrid);
            }
            else {
                refreshGame(displayGrid);
                printHallucinate(displayGrid);
            }
        }
        
        /* Check if creature is alive or not */
        if (newObject instanceof Monster) {
            Monster monsterCheck = (Monster) newObject;
            monsterIsAlive = monsterCheck.getHealthStatus();
        }

        /* If new position is not live creature, wall, or blank, then move player */
        if (newObject instanceof RoomWall == false && 
            newObject.getType() != ' ' &&
            !monsterIsAlive) {
            player.setPosX(player.getPosX() + moveX);
            player.setPosY(player.getPosY() + moveY);
            newObject = displayGrid.removeObjectFromDisplay(oldPlayerX, oldPlayerY);
            displayGrid.addObjectToDisplay(player, newPlayerX, newPlayerY);
        }
        /* If running into Monster, exchange attacks */
        else if (newObject instanceof Monster) {
            Monster monster = (Monster) newObject;
            String infoString = "";
            System.out.println("Monster Had : " + monster.getHP());
            System.out.println("Player  Had : " + player.getHP());

            /* Subtract monster and player hitpoints and perform hit actions */
            Stack<Displayable> location = objectGrid[oldPlayerX][oldPlayerY];
            int monsterLostHP = monster.receiveDamage(player);
            int playerLostHP = player.receiveDamage(monster);       

            /* Update HP Value */
            displayHP(displayGrid, player.getHP());
            System.out.println("Monster Lost : " + monsterLostHP);
            System.out.println("Player  Lost : " + playerLostHP);

            /* If player dead, perform death actions and end game */ 
            if (!player.getHealthStatus()) {
                infoString = performDeathActions(displayGrid, player, oldPlayerX, oldPlayerY);
                endGameDungeon(displayGrid, infoString);
                return;
            }
            else {
                infoString = performPlayerHitActions(displayGrid, player, oldPlayerX, oldPlayerY, location.size() - 1);
                displayInfo(displayGrid, infoString);
            }

            /* If monster dead, perform its death actions */ 
            if (!monster.getHealthStatus()) {
                infoString = performDeathActions(displayGrid, monster, newPlayerX, newPlayerY);
            }
            else {
                infoString = performMonsterHitActions(displayGrid, monster, newPlayerX, newPlayerY);
                displayInfo(displayGrid, infoString);

                infoString = "Monster lost " + monsterLostHP + " HP, Player lost " + playerLostHP + " HP";
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
            Item droppedItem = player.dropItem(dropNum);
            if (droppedItem != null) {
                if (droppedItem == player.getArmor()) {
                    player.setArmor(null);
                }
                else if (droppedItem == player.getSword()) {
                    player.setSword(null);
                }
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

    public void read(ObjectDisplayGrid displayGrid, int readNum) {
        ArrayList<Item> pack = player.getItems();

        /* Read scroll */
        if (pack.size() > 0) {
            if (readNum - 1 >= 0 && readNum - 1 < pack.size()) {
                Item readItem = pack.get(readNum - 1);
                if (readItem instanceof Scroll) {
                    player.dropItem(readNum);
                    Scroll scroll = (Scroll) readItem;
                    String infoString = performItemAction(displayGrid, scroll);
                    if (isHallucinate) {
                        displayInfo(displayGrid, infoString);
                    }
                }
                else {
                    displayInfo(displayGrid, "Selected number " + readNum + " doesn't match scroll number!");
                }
            }
            else {
                displayInfo(displayGrid, "Selected item number " + readNum + " doesn't exist!");
            }
        }
        else {
            displayInfo(displayGrid, "No scroll to read!");
        }
    }

    /* Display HP text */
    public void displayHP(ObjectDisplayGrid displayGrid, int HP) {
        String HPString = "" + HP;
        int HPStartX = 4;
        int HPStartY = 0;

        eraseDisplay(displayGrid, HPStartX, HPStartX + 3, HPStartY, HPStartY);
        
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
        String infoString = "";
        
        /* Display pack info */
        for (Item item : pack) {
            infoString += "[" + itemNum++ + "] " + item.getName();
            if (item == player.getSword()) {
                infoString += " (w)";
            }
            else if (item == player.getArmor()) {
                infoString += " (a)";
            }
            if (stringStartX > width) {
                stringStartX = 6;
                stringStartY++;
            }
            // System.out.println(stringStartX + ", " + stringStartY + ", " + itemName);
            stringStartX += itemName.length() + 1;
            infoString += " ";
        }
        int strLen = infoString.length();
        for (int i = 0; i < width - strLen - 6; i++) {
            infoString += " ";
        }
        displayGrid.displayString(infoString, 6, stringStartY);

        // System.out.println("|"+infoString+"|");
        if (pack.size() == 0) {
            displayInfo(displayGrid, "No items in pack");
        }
    }

    /* Display info text */
    public void displayInfo(ObjectDisplayGrid displayGrid, String infoString) {
        int infoStartX = 6;
        int infoStartY = topHeight + gameHeight + bottomHeight - 1;

        if (infoStartX + infoString.length() > width) {
            System.out.println("ERROR: DISPLAY INFO STRING TOO LONG");
        }
        else {
            int strLen = infoString.length();
            for (int i = 0; i < width - strLen - 6; i++) {
                infoString += " ";
            }
            // System.out.println("|"+infoString+"|");
        }
        displayGrid.displayString(infoString, infoStartX, infoStartY);
    }

    /* Erase display to overwrite previous text */
    private void eraseDisplay(ObjectDisplayGrid displayGrid, int eraseStartX, int eraseEndX, int eraseStartY, int eraseEndY) {
        String eraseString;
        for (int j = eraseStartY; j < eraseEndY; j++) {
            eraseString = "";
            for (int i = eraseStartX; i < eraseEndX; i++) {
                eraseString += " ";
            }
            displayGrid.displayString(eraseString, eraseStartX, j);
        }
    }

    public void help(ObjectDisplayGrid displayGrid) {
        displayInfo(displayGrid, "h,l,k,j,i,?,H,c,d,p,r,T,w,E,0-9. H <cmd> for more info");
    }

    public void helpInfo(ObjectDisplayGrid displayGrid, char cmd) {
        String infoString = "";
        if (Character.isDigit(cmd)) {
            infoString = "0-9 select index of item";
        }
        else {
            switch(cmd) {
                case 'h':
                    infoString = "h - Move left";
                    break;
                
                case 'l':
                    infoString = "l - Move right";
                    break;
                    
                case 'k':
                    infoString = "k - Move up";
                    break;

                case 'j':
                    infoString = "j - Move down";
                    break;

                case 'i':
                    infoString = "i - Show inventory";
                    break;

                case '?':
                    infoString = "? - Help: show different commands";
                    break;

                case 'H':
                    infoString = "H - Help: more specific command";
                    break;

                case 'c':
                    infoString = "c - Change armor";
                    break;

                case 'd':
                    infoString = "d - Drop item";
                    break;

                case 'p':
                    infoString = "p - Pick up item from flooor";
                    break;
                
                case 'r':
                    infoString = "r - Read an item";
                    break;
                    
                case 'T':
                    infoString = "T - Take out weapon";
                    break;

                case 'w':
                    infoString = "w - Wear item";
                    break;

                case 'E':
                    infoString = "E - End game";
                    break;
                
                default:
                    break;
            }
        }
        displayInfo(displayGrid, infoString);
    }

    /* End game */
    public void endGameDungeon(ObjectDisplayGrid displayGrid, String infoStr) {
        gameIsOver = true;
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

    private String performDeathActions(ObjectDisplayGrid displayGrid, Creature creature, int removeX, int removeY) {
        String infoString = "";
        for(CreatureAction deathAction : creature.getDeathActions()) {
            switch(deathAction.getName()) {
                case "Remove":
                    displayGrid.removeObjectFromDisplay(removeX, removeY);
                    break;
                
                case "YouWin":
                    infoString = deathAction.getMessage();
                    break;

                case "ChangeDisplayedType":
                    creature.setType(deathAction.getCharValue());
                    break;

                case "UpdateDisplay":
                    displayGrid.updateDisplay(removeX, removeY);
                    break;

                case "EndGame":
                    infoString = deathAction.getMessage();
                    break;
                
                default:
                    System.out.println("ERROR: UNKNOWN DEATH ACTION");
            }
        }
        return infoString;
    }

    private String performPlayerHitActions(ObjectDisplayGrid displayGrid, Creature creature, int dropX, int dropY, int stackIndex) {
        String infoString = "";
        for (CreatureAction hitAction : creature.getHitActions()) {
            switch(hitAction.getName()) {
                case "DropPack":
                    if (creature instanceof Player) {
                        Player player = (Player) creature;
                        Item droppedItem = player.dropItem(1);
                        if (droppedItem != null) {
                            displayGrid.addObjectToDisplay(droppedItem, dropX, dropY, stackIndex);
                            displayInfo(displayGrid, "Dropped " + droppedItem.getName());
                        }
                    }
                    else {
                        System.out.println("ERROR: Non-Player has DropPack ability");
                    }
                    break;
                
                default:
                    System.out.println("ERROR: UNKNOWN HIT ACTION");
            }
            infoString = hitAction.getMessage();
        }
        return infoString;
    }

    private String performMonsterHitActions(ObjectDisplayGrid displayGrid, Creature creature, int oldX, int oldY) {
        String infoString = "";
        for (CreatureAction hitAction : creature.getHitActions()) {
            switch(hitAction.getName()) {
                case "Teleport":
                    teleport(displayGrid, creature, oldX, oldY);
                    break;
                
                default:
                    System.out.println("ERROR: UNKNOWN DEATH ACTION");
            }
            infoString = hitAction.getMessage();
        }
        return infoString;
    }

    private String performItemAction(ObjectDisplayGrid displayGrid, Scroll scroll) {
        String infoString = "";
        ItemAction itemAction = scroll.getItemAction();
        switch(itemAction.getName()) {
            case "BlessArmor":
                bless(displayGrid, itemAction);
                break;

            case "Hallucinate":
                beginHallucinate(displayGrid, itemAction);
                infoString = itemAction.getMessage();
                break;
            
            default:
                System.out.println("ERROR: UNKNOWN ITEM ACTION");
        }

        return infoString;
    }

    private void teleport(ObjectDisplayGrid displayGrid, Creature creature, int oldX, int oldY) {
        Random rand = new Random();
        Stack<Displayable>[][] objectGrid = displayGrid.getObjectGrid();
        Displayable newObject = null;
        int newX = 0;
        int newY = 0;
        while (newObject instanceof RoomFloor == false &&
               newObject instanceof PassageFloor == false ) {
                
            newX = rand.nextInt(width);
            newY = rand.nextInt(gameHeight) + topHeight;
            newObject = objectGrid[newX][newY].peek();
        }
        
        // System.out.println("Teleport: " + oldX + ", " + oldY + ", " + newX + ", " + newY);
        Displayable monster = displayGrid.removeObjectFromDisplay(oldX, oldY);
        displayGrid.addObjectToDisplay(monster, newX, newY);
    }

    private void refreshGame(ObjectDisplayGrid displayGrid) {
        displayGrid.refreshDisplay(0, topHeight, width, gameHeight);
    }

    private void beginHallucinate(ObjectDisplayGrid displayGrid, ItemAction itemAction) {
        isHallucinate = true;
        player.setHalMoves(itemAction.getIntValue());
        refreshGame(displayGrid);
        printHallucinate(displayGrid);
    }

    private void endHallucinate(ObjectDisplayGrid displayGrid) {
        isHallucinate = false;
        player.setHalMoves(0);
        refreshGame(displayGrid);
        displayInfo(displayGrid, "Hallucinations ended");
    }

    private void printHallucinate(ObjectDisplayGrid displayGrid) {
        String moveString = " moves";
        if (player.getHalMoves() == 1) {
            moveString = " move";
        }
        displayInfo(displayGrid, "Hallucinations will continue for " + player.getHalMoves() + moveString);
    }

    public void changeArmor(ObjectDisplayGrid displayGrid) {
        if (player.getArmor() != null) {
            displayInfo(displayGrid, player.getArmor().getName() + " taken off!");
            player.setArmor(null);
        }
        else {
            displayInfo(displayGrid, "No armor is being worn!");
        }
    }

    public void wearArmor(ObjectDisplayGrid displayGrid, int indexArmorWorn) {
        ArrayList<Item> pack = player.getItems();

        /* If selected item can be changed (take off worn), take off and place in pack */
        if (pack.size() > 0) {
            if (indexArmorWorn - 1 >= 0 && indexArmorWorn - 1 < pack.size()) {
                Item selectedItem = pack.get(indexArmorWorn - 1);
                if (selectedItem instanceof Armor) {
                    player.setArmor((Armor)selectedItem);
                    displayInfo(displayGrid, "Wearing " + selectedItem.getName());
                }
                else {
                    displayInfo(displayGrid, "Selected item is not an Armor!");
                }
            }
            else {
                displayInfo(displayGrid, "Selected item number " + indexArmorWorn + " doesn't exist!");
            }
        }
        else {
            displayInfo(displayGrid, "No Armor in Pack!");
        }
    }

	public void takeOutWeapon(ObjectDisplayGrid displayGrid, int indexSwordHeld) {
        ArrayList<Item> pack = player.getItems();

        /* If selected item can be changed (take off worn), take off and place in pack */
        if (pack.size() > 0) {
            if (indexSwordHeld - 1 >= 0 && indexSwordHeld - 1 < pack.size()) {
                Item selectedItem = pack.get(indexSwordHeld - 1);
                if (selectedItem instanceof Sword) {
                    player.setSword((Sword)selectedItem);
                    displayInfo(displayGrid, "Wielding " + selectedItem.getName());
                }
                else {
                    displayInfo(displayGrid, "Selected item is not a Sword!");
                }
            }
            else {
                displayInfo(displayGrid, "Selected item number " + indexSwordHeld + " doesn't exist!");
            }
        }
        else {
            displayInfo(displayGrid, "No Sword in Pack!");
        }
    }
    
    private void bless(ObjectDisplayGrid displayGrid, ItemAction itemAction) {
        int intVal = itemAction.getIntValue();
        switch (itemAction.getCharValue()) {
            case 'a':
                Armor armor = player.alterArmor(intVal);
                if (armor != null) {
                    displayInfo(displayGrid, armor.getName() + " cursed! " + intVal + " taken from its effectiveness");
                }
                else {
                    displayInfo(displayGrid, "Scroll of cursing does nothing because armor is not worn");
                }
                break;

            case 'w':
                Sword sword = player.alterSword(intVal);
                if (sword != null) {
                    displayInfo(displayGrid, sword.getName() + " cursed! " + intVal + " taken from its effectiveness");
                }
                else {
                    displayInfo(displayGrid, "Scroll of cursing does nothing because sword is not wielded");
                }
                break;
            
            default:
                break;
        }
    }

}
