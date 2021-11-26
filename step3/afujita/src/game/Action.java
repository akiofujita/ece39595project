package game;

public class Action {
    
    //list of variables for Action class:
    private String msg;
    private int intValue;
    private char charValue;


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
