package game;

public class Action {
    
    //list of variables for Action class:
    private String msg;
    private int intValue;
    private char charValue;


    //variable setters:
    public void setMessage(String _msg) {
        System.out.println("Message set: " + _msg);
        msg = _msg;
    }

    public void setIntValue(int _value) {
        System.out.println("Action intValue set: " + _v);
        intValue = _value;
    }
    
    public void setCharValue(char _char) {
        System.out.println("Action charValue set: " + _c);
        charValue = _char;
    }


    //variable getters:
    public sTRING getMessage() {
        return msg;
    }

    public int getIntValue() {
        return intValue;
    }
    
    public char getCharValue() {
        return charValue;
    }
}
