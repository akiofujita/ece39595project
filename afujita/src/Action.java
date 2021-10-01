package afujita.src;

public class Action extends Displayable {
    
    public void setMessage(String _msg) {
        System.out.println("Message set");
    }

    public void setIntValue(int _v) {
        System.out.println("Int Value set");
    }

    public void setCharValue(char _c) {
        System.out.println("Char Value set");
    }
}
