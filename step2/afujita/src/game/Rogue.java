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
        // System.out.println(width +", " + totalHeight);
        displayGrid = new ObjectDisplayGrid(width, totalHeight);
        System.out.println("Construct Rogue Game");
    }

    @Override
    public void run() {

        displayGrid.fireUp();
        displayGrid.initializeDisplay();
        System.out.println("Run Game");
        
        displayObject("HP: ", 0, 0);
        displayObject("core: ", 8, 0);
        displayObject("Pack: ", 0, totalHeight - 3);
        displayObject("Info: ", 0, totalHeight - 1);

        rooms = dungeon.getRooms();
        creatures = dungeon.getCreatures();
        items = dungeon.getItems();

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
            wall.setType('X');
            floor.setType('.');
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

        /* Draw creatures on top of room floor */
        for (Creature creature : creatures) {
            if (creature instanceof Player) {
                creature.setType('@');
            }
            else if (creature instanceof Monster) {
                creature.setType('M');
            }
            Room curRoom = rooms.get(creature.getRoomNum() - 1);
            // System.out.println(creature);
            displayGrid.addObjectToDisplay(creature, curRoom.getPosX() + creature.getPosX(), curRoom.getPosY() + creature.getPosY() + topHeight);
        }

        /* Draw passages */
        for (Passage passage : passages) {
            // System.out.println(creature);
            passage_posX = passage.getPosXs();
            passage_posY = passage.getPosYs();
            
            for (int i = 0; i < passage_posX.size(); i++) {

            }
        }
        
    }

    private void displayObject(String str, int x_start, int y_start) {
        Displayable text = new Displayable();
        for (int i = 0; i < str.length(); i++) {
            text.setType(str.charAt(i));
            displayGrid.addObjectToDisplay(text, x_start + i, y_start);
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

            game.keyStrokePrinter = new Thread(new KeyStrokePrinter(displayGrid));
            game.keyStrokePrinter.start();

            gameThread.join();
            game.keyStrokePrinter.join();

	// these lines should be copied exactly.
        } catch (ParserConfigurationException | SAXException | IOException | InterruptedException e) {
            e.printStackTrace(System.out);
        }
    }
}
