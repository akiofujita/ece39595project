package game;

public class Action {
    
    //list of variables for Action class:
    protected String name;
    protected String type;
    private String msg;
    private int intValue;
    private char charValue;

    public Action(String _name, String _type) {
        name = _name;
        type = _type;
        msg = "";
        intValue = 0;
        // charValue holds default char value '\u0000'
    }

    @Override
    public String toString() {
        String outputStr = "";
        
        if( this instanceof CreatureAction ) {
            outputStr += "CA INFO:";
        }
        else if( this instanceof ItemAction ) {
            outputStr += "IA INFO:";
        }
        
        outputStr += "\nmsg: " + msg;
        outputStr += "\n";
        return outputStr;
    }

    public void setName(String _name) {
        name = _name;
    }

    public void setType(String _type) {
        type = _type;
    }

    //variable setters:
    public void setMessage(String _msg) {
        msg = _msg;
    }

    public void setIntValue(int _value) {
        intValue = _value;
    }
    
    public void setCharValue(char _char) {
        charValue = _char;
    }

    //variable getters:
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return msg;
    }

    public int getIntValue() {
        return intValue;
    }
    
    public char getCharValue() {
        return charValue;
    }
}
