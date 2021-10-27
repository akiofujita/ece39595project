package game;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

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

    public Rogue(Dungeon _dungeon) {

        /* Get dungeon and extract info */
        dungeon = _dungeon;
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
        
        displayGrid.addObjectToDisplay(new Char('P'), 0, gameHeight+ topHeight);
        displayGrid.addObjectToDisplay(new Char('a'), 1, gameHeight + topHeight);
        displayGrid.addObjectToDisplay(new Char('c'), 2, gameHeight + topHeight);
        displayGrid.addObjectToDisplay(new Char('k'), 3, gameHeight + topHeight);
        displayGrid.addObjectToDisplay(new Char(':'), 4, gameHeight + topHeight);
        displayGrid.addObjectToDisplay(new Char('I'), 0, gameHeight + topHeight + 2);
        displayGrid.addObjectToDisplay(new Char('n'), 1, gameHeight + topHeight + 2);
        displayGrid.addObjectToDisplay(new Char('f'), 2, gameHeight + topHeight + 2);
        displayGrid.addObjectToDisplay(new Char('o'), 3, gameHeight + topHeight + 2);
        displayGrid.addObjectToDisplay(new Char(':'), 4, gameHeight + topHeight + 2);

        displayGrid.addObjectToDisplay(new Char('H'), 0, 0);
        displayGrid.addObjectToDisplay(new Char('P'), 1, 0);
        displayGrid.addObjectToDisplay(new Char(':'), 2, 0);
        displayGrid.addObjectToDisplay(new Char(' '), 5, 0);
        displayGrid.addObjectToDisplay(new Char(' '), 6, 0);
        displayGrid.addObjectToDisplay(new Char('c'), 7, 0);
        displayGrid.addObjectToDisplay(new Char('o'), 8, 0);
        displayGrid.addObjectToDisplay(new Char('r'), 9, 0);
        displayGrid.addObjectToDisplay(new Char('e'), 10, 0);
        displayGrid.addObjectToDisplay(new Char(':'), 11, 0);
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
