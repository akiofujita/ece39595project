
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
    private Room[] rooms;
    private int maxRooms = 0;
    private int roomCount = 0;

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

    // Used by code outside the class to get the list of Student objects
    // that have been constructed.
    // public Student[] getStudents() {
    //     return students;
    // }

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
            Dungeon dungeon = Dungeon.buildDungeon(name, width, topHeight, gameHeight, bottomHeight);
        }
        else if (qName.equalsIgnoreCase("Room")) {
            String roomID = attributes.getValue("room");
            Room room = new Room(roomID);
            Dungeon.addRoom(room);
            structureBeingParsed = room;
        }
        else if (qName.equalsIgnoreCase("Passage")) {
            int room1 = Integer.parseInt(attributes.getValue("room1"));
            int room2 = Integer.parseInt(attributes.getValue("room2"));
            Passage passage = new Passage(room1, room2);
            Dungeon.addPassage(passage);
            structureBeingParsed = passage;
        }
        else if (qName.equalsIgnoreCase("Monster")) {
            String name = attributes.getValue("name");
            int room = Integer.parseInt(attributes.getValue("room"));
            int serial = Integer.parseInt(attributes.getValue("serial"));
            Creature creature = new Monster(name, room, serial);
            addCreature(creature);
            creatureBeingParsed = creature;
        }
        else if (qName.equalsIgnoreCase("Player")) {
            String name = attributes.getValue("name");
            int room = Integer.parseInt(attributes.getValue("room"));
            int serial = Integer.parseInt(attributes.getValue("serial"));
            Creature creature = new Player(name, room, serial);
            addCreature(creature);
            creatureBeingParsed = creature;
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
        } 
        /***************************************************************
         * instructor, credit, name, meetingTIme, meetingDay, number 
         * and location are handled in endElement.
         **************************************************************/
        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        Course course;
        if (qName.equalsIgnoreCase("instructor")) {
            course = (Course) activityBeingParsed;
            course.setInstructor(data.toString());
        } else if (qName.equalsIgnoreCase("credit")) {
            course = (Course) activityBeingParsed;
            course.setCredit(Integer.parseInt(data.toString()));
        } else if (qName.equalsIgnoreCase("name")) {
            activityBeingParsed.setName(data.toString());
        } else if (qName.equalsIgnoreCase("number")) {
            course = (Course) activityBeingParsed;
            course.setNumber(data.toString());
        } else if (qName.equalsIgnoreCase("location")) {
            activityBeingParsed.setLocation(data.toString());
        } else if (qName.equalsIgnoreCase("meetingtime")) {
            activityBeingParsed.setMeetingTime(data.toString());
        } else if (qName.equalsIgnoreCase("meetingday")) {
            activityBeingParsed.setMeetingDay(data.toString());
        } else if (qName.equalsIgnoreCase("Students")) {
            if (studentCount != maxStudents) {
                System.out.println("wrong number of students parsed, should be " + maxStudents + ", is " + studentCount);
            }
        } else if (qName.equalsIgnoreCase("Student")) {
            studentBeingParsed = null;
        } else if (qName.equalsIgnoreCase("Activity")) {
            activityBeingParsed = null;
        }
    }

    private void addRoom(Room room) {
        rooms[roomCount++] = room;
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
        String str = "StudentsXMLHandler\n";
        str += "   maxStudents: " + maxStudents + "\n";
        str += "   studentCount: " + studentCount + "\n";
        for (Student student : students) {
            str += student.toString() + "\n";
        }
        str += "   studentBeingParsed: " + studentBeingParsed.toString() + "\n";
        str += "   activityBeingParsed: " + activityBeingParsed.toString() + "\n";
        return str;
    }
}
