package src;

public class Action extends Displayable {
    
    public void setMessage(String _msg) {
        System.out.println("Message set");
    }

    public void setActionMessage(String _msg) {
        System.out.println("Action message created");
    }
    
    public void setActionIntValue(int _actionIntValue) {
        System.out.println("Action int value created");
    }
}
