package game;

import java.io.File;
import java.io.IOException;
import java.time.YearMonth;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import java.util.ArrayList;

public class Rogue implements Runnable {

    private static final int DEBUG = 0;
    private boolean isRunning;
    public static final int FRAMESPERSECOND = 60;
    public static final int TIMEPERLOOP = 1000000000 / FRAMESPERSECOND;
    private static ObjectDisplayGrid displayGrid = null;
    private Thread keyStrokePrinter;
    private Dungeon dungeon;
    private int width;
    private int topHeight;
    private int gameHeight;
    private int bottomHeight;
    private int totalHeight;
    private ArrayList<Room> rooms;
    private int room_x1;
    private int room_x2;
    private int room_y1;
    private int room_y2;
    private ArrayList<Creature> creatures;
    private ArrayList<Passage> passages;
    private ArrayList<Integer> passage_posX;
    private ArrayList<Integer> passage_posY;
    private ArrayList<Item> items;

    public Rogue(Dungeon _dungeon) {

        /* Get dungeon and extract info */
        dungeon = _dungeon;
        rooms = dungeon.getRooms();
        width = dungeon.getWidth();
        topHeight = dungeon.getTopHeight();
        gameHeight = dungeon.getGameHeight();
        bottomHeight = dungeon.getBottomHeight();
        totalHeight = topHeight + gameHeight + bottomHeight;
        displayGrid = new ObjectDisplayGrid(width, totalHeight);
        System.out.println("Construct Rogue Game");

    }

    @Override
    public void run() {

        /* Begin display */ 
        displayGrid.fireUp();
        displayGrid.initializeDisplay();
        System.out.println("Run Game");
        
        /* Display text */
        displayGrid.displayString("HP: " + dungeon.getPlayer().getHP(), 0, 0);
        displayGrid.displayString("Score: 0", 8, 0);
        displayGrid.displayString("Pack: ", 0, totalHeight - 3);
        displayGrid.displayString("Info: ", 0, totalHeight - 1);

        /* Get necessary ArrayLists to be displayed */
        rooms = dungeon.getRooms();
        creatures = dungeon.getCreatures();
        passages = dungeon.getPassages();
        items = dungeon.getItems();

        /* Draw rooms in dungeon */
        for (Room room : rooms) {
            // System.out.println(room);
            room_x1 = room.getPosX();
            room_x2 = room_x1 + room.getWidth();
            room_y1 = room.getPosY() + topHeight;
            room_y2 = room_y1 + room.getHeight();
            // System.out.println(room_x1 + ", " + room_x2 + ", " + room_y1 + ", " + room_y2);

            /* Draw bare room */
            RoomWall wall = new RoomWall();
            RoomFloor floor = new RoomFloor();
            for (int x = room_x1; x < room_x2; x++) {
                displayGrid.addObjectToDisplay(wall, x, room_y1);
            }
            for (int y = room_y1 + 1; y < room_y2 - 1; y++) {
                displayGrid.addObjectToDisplay(wall, room_x1, y);
                for (int x = room_x1 + 1; x < room_x2 - 1; x++) {
                    displayGrid.addObjectToDisplay(floor, x, y);
                }
                displayGrid.addObjectToDisplay(wall, room_x2 - 1, y);
            }
            for (int x = room_x1; x < room_x2; x++) {
                displayGrid.addObjectToDisplay(wall, x, room_y2 - 1);
            }
        }

        /* Draw passages */
        PassageJunction passageJunction = new PassageJunction();
        PassageFloor passageFloor = new PassageFloor();
        int passagePosLen;
        int x1;
        int x2;
        int y1;
        int y2;
        for (Passage passage : passages) {
            // System.out.println(passage);
            passage_posX = passage.getPosXs();
            passage_posY = passage.getPosYs();
            passagePosLen = passage_posX.size();
            
            if (passagePosLen > 0) {
                /* Compare consecutive posX, posY of passage */
                for (int posNum = 0; posNum < passagePosLen - 1; posNum++) {
                    x1 = passage_posX.get(posNum);
                    x2 = passage_posX.get(posNum + 1);
                    y1 = passage_posY.get(posNum) + topHeight;
                    y2 = passage_posY.get(posNum + 1) + topHeight;
                    // System.out.println(x1 + ", " + x2 + ", " + y1 + ", " + y2);
                    /* If x is same, draw between the 2 y values */
                    if (x1 == x2) {
                        if (y1 < y2) {
                            for (int y = y1; y < y2; y++) {
                                displayGrid.addObjectToDisplay(passageFloor, x1, y);
                            }
                        }
                        else {
                            for (int y = y1; y > y2; y--) {
                                displayGrid.addObjectToDisplay(passageFloor, x1, y);
                            }
                        }
                    }
                    /* If y is same, draw between the 2 x values */
                    else if (y1 == y2) {
                        if (x1 < x2) {
                            for (int x = x1; x < x2; x++) {
                                displayGrid.addObjectToDisplay(passageFloor, x, y1);
                            }
                        }
                        else {
                            for (int x = x1; x > x2; x--) {
                                displayGrid.addObjectToDisplay(passageFloor, x, y1);
                            }
                        }
                    }
                    else {
                        System.out.println("ERROR: CANNOT DRAW PASSAGE");
                    }
                }
                /* Draw passage junctions at start and end of passage */
                displayGrid.addObjectToDisplay(passageJunction, passage_posX.get(0), passage_posY.get(0) + topHeight);
                displayGrid.addObjectToDisplay(passageJunction, passage_posX.get(passagePosLen - 1), passage_posY.get(passagePosLen - 1) + topHeight);
            }
        }

        /* Draw items */
        for (Item item : items) {
            Room curRoom = rooms.get(item.getRoomNum() - 1);
            // System.out.println(item);
            displayGrid.addObjectToDisplay(item, curRoom.getPosX() + item.getPosX(), curRoom.getPosY() + item.getPosY() + topHeight);
        }

        /* Draw creatures */
        for (Creature creature : creatures) {
            Room curRoom = rooms.get(creature.getRoomNum() - 1);
            // System.out.println("Cr: " + creature);
            // System.out.println("DA:\n" + creature.getDeathActions());
            // System.out.println("HA:\n" + creature.getHitActions());
            displayGrid.addObjectToDisplay(creature, curRoom.getPosX() + creature.getPosX(), curRoom.getPosY() + creature.getPosY() + topHeight);
        }
    }

    public static void main(String[] args) {

	// check if a filename is passed in.  If not, print a usage message.
	// If it is, open the file
        String fileName = null;
        switch (args.length) {
        case 1:
           // note that the relative file path may depend on what IDE you are
	   // using.  This worked for NetBeans.
           fileName = "xmlfiles/" + args[0];
           break;
        default:
           System.out.println("java src.game.Rogue <xmlfilename>");
	   return;
        }

	// Create a saxParserFactory, that will allow use to create a parser
	// Use this line unchanged
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

	// We haven't covered exceptions, so just copy the try { } catch {...}
	// exactly, // filling in what needs to be changed between the open and 
	// closed braces.
        try {
	    // just copy this
            SAXParser saxParser = saxParserFactory.newSAXParser();
	    // just copy this
            RogueXMLHandler handler = new RogueXMLHandler();
	    // just copy this.  This will parse the xml file given by fileName
            saxParser.parse(new File(fileName), handler);
	    // This will change depending on what kind of XML we are parsing
            Dungeon dungeon = handler.getDungeon();
	    
            Rogue game = new Rogue(dungeon);
            Thread gameThread = new Thread(game);
            gameThread.start();

            game.keyStrokePrinter = new Thread(new KeyStrokePrinter(displayGrid, dungeon));
            game.keyStrokePrinter.start();

            gameThread.join();
            game.keyStrokePrinter.join();

	// these lines should be copied exactly.
        } catch (ParserConfigurationException | SAXException | IOException | InterruptedException e) {
            e.printStackTrace(System.out);
        }
    }
}
