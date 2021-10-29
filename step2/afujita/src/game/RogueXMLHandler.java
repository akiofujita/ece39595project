package game;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RogueXMLHandler extends DefaultHandler {

    // the two lines that follow declare a DEBUG flag to control
    // debug print statements and to allow the class to be easily
    // printed out.  These are not necessary for the parser.
    private static final int DEBUG = 1;
    private static final String CLASSID = "RogueXMLHandler";

    // data can be called anything, but it is the variables that
    // contains information found while parsing the xml file
    private StringBuilder data = null;

    // When the parser parses the file it will add references to
    // Student objects to this array so that it has a list of 
    // all specified students.  Had we covered containers at the
    // time I put this file on the web page I would have made this
    // an ArrayList of Students (ArrayList<Student>) and not needed
    // to keep tract of the length and maxStudents.  You should use
    // an ArrayList in your project.


    // The XML file contains a list of Students, and within each 
    // Student a list of activities (clubs and classes) that the
    // student participates in.  When the XML file initially
    // defines a student, many of the fields of the object have
    // not been filled in.  Additional lines in the XML file 
    // give the values of the fields.  Having access to the 
    // current Student and Activity allows setters on those 
    // objects to be called to initialize those fields.
    private Structure structureBeingParsed = null;
    private Creature creatureBeingParsed = null;
    private Action actionBeingParsed = null;
    private Item itemBeingParsed = null;

    // Variables
    private Dungeon dungeon;

    // Used by code outside the class to get the Dungeon object
    // that has been constructed.
    public Dungeon getDungeon() {
        return dungeon;
    }

    // A constructor for this class.  It makes an implicit call to the
    // DefaultHandler zero arg constructor, which does the real work
    // DefaultHandler is defined in org.xml.sax.helpers.DefaultHandler;
    // imported above, and we don't need to write it.  We get its 
    // functionality by deriving from it!
    public RogueXMLHandler() {
    }

    // startElement is called when a <some element> is called as part of 
    // <some element> ... </some element> start and end tags.
    // Rather than explain everything, look at the xml file in one screen
    // and the code below in another, and see how the different xml elements
    // are handled.
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (DEBUG > 1) {
            System.out.println(CLASSID + ".startElement qName: " + qName);
        }

        if (qName.equalsIgnoreCase("Dungeon")) {
            String name = attributes.getValue("name");
            int width = Integer.parseInt(attributes.getValue("width"));
            int topHeight = Integer.parseInt(attributes.getValue("topHeight"));
            int gameHeight = Integer.parseInt(attributes.getValue("gameHeight"));
            int bottomHeight = Integer.parseInt(attributes.getValue("bottomHeight"));
            dungeon = Dungeon.buildDungeon(name, width, topHeight, gameHeight, bottomHeight);
        }
        else if (qName.equalsIgnoreCase("Room")) {
            int roomID = Integer.parseInt(attributes.getValue("room"));
            Room room = new Room(roomID);
            dungeon.addRoom(room);
            structureBeingParsed = room;
            creatureBeingParsed = null;
            itemBeingParsed = null;
        }
        else if (qName.equalsIgnoreCase("Passage")) {
            int room1 = Integer.parseInt(attributes.getValue("room1"));
            int room2 = Integer.parseInt(attributes.getValue("room2"));
            Passage passage = new Passage(room1, room2);
            dungeon.addPassage(passage);
            structureBeingParsed = passage;
            creatureBeingParsed = null;
            itemBeingParsed = null;
        }
        else if (qName.equalsIgnoreCase("Monster")) {
            String name = attributes.getValue("name");
            int room = Integer.parseInt(attributes.getValue("room"));
            int serial = Integer.parseInt(attributes.getValue("serial"));
            Monster creature = new Monster(name);
            creature.setRoomNum(room);
            creature.setSerialNum(serial);
            dungeon.addCreature(creature);
            creatureBeingParsed = creature;
            itemBeingParsed = null;
        }
        else if (qName.equalsIgnoreCase("Player")) {
            String name = attributes.getValue("name");
            int room = Integer.parseInt(attributes.getValue("room"));
            int serial = Integer.parseInt(attributes.getValue("serial"));
            Player creature = new Player(name);
            creature.setRoomNum(room);
            creature.setSerialNum(serial);
            dungeon.addCreature(creature);
            creatureBeingParsed = creature;
            itemBeingParsed = null;
        }
        else if (qName.equalsIgnoreCase("CreatureAction")) {
            String name = attributes.getValue("name");
            String type = attributes.getValue("type");
            CreatureAction action = new CreatureAction(name, type);
            switch (type) {
                case "death":
                    creatureBeingParsed.setDeathAction(action);
                    break;
                case "hit":
                    creatureBeingParsed.setHitAction(action);
                    break;
                default:
                    System.out.println("Unknown creature action: " + type);
                    break;
            }
            actionBeingParsed = action;
        } 
        else if (qName.equalsIgnoreCase("Armor")) {
            String name = attributes.getValue("name");
            int roomNum = Integer.parseInt(attributes.getValue("room"));
            int serialNum = Integer.parseInt(attributes.getValue("serial"));
            Item armor = new Armor(name, roomNum, serialNum);
            dungeon.addItem(armor);
            itemBeingParsed = armor;
            if (creatureBeingParsed instanceof Monster) {
                creatureBeingParsed = null;
            }
        }
        else if (qName.equalsIgnoreCase("Scroll")) {
            String name = attributes.getValue("name");
            int roomNum = Integer.parseInt(attributes.getValue("room"));
            int serialNum = Integer.parseInt(attributes.getValue("serial"));
            Item scroll = new Scroll(name, roomNum, serialNum);
            dungeon.addItem(scroll);
            itemBeingParsed = scroll;
            if (creatureBeingParsed instanceof Monster) {
                creatureBeingParsed = null;
            }
        } 
        else if (qName.equalsIgnoreCase("Sword")) {
            String name = attributes.getValue("name");
            int roomNum = Integer.parseInt(attributes.getValue("room"));
            int serialNum = Integer.parseInt(attributes.getValue("serial"));
            Item sword = new Sword(name, roomNum, serialNum);
            dungeon.addItem(sword);
            itemBeingParsed = sword;
            if (creatureBeingParsed instanceof Monster) {
                creatureBeingParsed = null;
            }
        } 
        /***************************************************************
         * instructor, credit, name, meetingTIme, meetingDay, number 
         * and location are handled in endElement.
         **************************************************************/
        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("visible")) {
            if( creatureBeingParsed != null ) {
                creatureBeingParsed.setVisible();
            }
            else if( itemBeingParsed != null ) {
                itemBeingParsed.setVisible();
            }
            else if( structureBeingParsed != null ) {
                structureBeingParsed.setVisible();
            }
            else {
                System.out.println("ERROR: Visible");
            }
        }
        else if (qName.equalsIgnoreCase("posX")) {
            int PosX = Integer.parseInt(data.toString());
            if( creatureBeingParsed != null ) {
                System.out.println("CREATURE: Set PosX");
                creatureBeingParsed.setPosX(PosX);
            }
            else if( itemBeingParsed != null ) {
                System.out.println("ITEM: Set PosX");
                itemBeingParsed.setPosX(PosX);
            }
            else if( structureBeingParsed != null ) {
                System.out.println("STRUCTURE: Set PosX");
                structureBeingParsed.setPosX(PosX);
            }
            else {
                System.out.println("ERROR: PosX");
            }
        }
        else if (qName.equalsIgnoreCase("PosY")) {
            int PosY = Integer.parseInt(data.toString());
            if( creatureBeingParsed != null ) {
                creatureBeingParsed.setPosY(PosY);
            }
            else if( itemBeingParsed != null ) {
                itemBeingParsed.setPosY(PosY);
            }
            else if( structureBeingParsed != null ) {
                structureBeingParsed.setPosY(PosY);
            }       
            else {
                System.out.println("ERROR: PosY");
            }
        }
        else if (qName.equalsIgnoreCase("width")) {
            Room room = (Room) structureBeingParsed;
            room.setWidth(Integer.parseInt(data.toString()));
        }
        else if (qName.equalsIgnoreCase("height")) {
            Room room = (Room) structureBeingParsed;
            room.setHeight(Integer.parseInt(data.toString()));
        }
        else if (qName.equalsIgnoreCase("type")) {
            Monster monster = (Monster) creatureBeingParsed;
            monster.setType(data.toString().charAt(0));
        }
        else if (qName.equalsIgnoreCase("hp")) {
            creatureBeingParsed.setHp(Integer.parseInt(data.toString()));
        }
        else if (qName.equalsIgnoreCase("maxhit")) {
            creatureBeingParsed.setMaxHit(Integer.parseInt(data.toString()));
        }
        else if (qName.equalsIgnoreCase("actionMessage")) {
            actionBeingParsed.setMessage(data.toString());
        }
        else if (qName.equalsIgnoreCase("actionIntValue")) {
            actionBeingParsed.setIntValue(Integer.parseInt(data.toString()));
        }
        else if (qName.equalsIgnoreCase("hpMoves")) {
            Player player = (Player) creatureBeingParsed;
            player.setHpMoves(Integer.parseInt(data.toString()));
        }
        else if (qName.equalsIgnoreCase("ItemIntValue")) {
            itemBeingParsed.setIntValue(Integer.parseInt(data.toString()));
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
        if (DEBUG > 1) {
            System.out.println(CLASSID + ".characters: " + new String(ch, start, length));
            System.out.flush();
        }
    }

    @Override
    public String toString() {
        String str = "RogueXMLHandler\n";

        for (Room room : dungeon.getRooms()) {
            str += room.toString() + "\n";
        }
        str += "   structureBeingParsed: " + structureBeingParsed.toString() + "\n";
        str += "   creatureBeingParsed: " + creatureBeingParsed.toString() + "\n";
        str += "   actionBeingParsed: " + actionBeingParsed.toString() + "\n";
        return str;
    }
}
