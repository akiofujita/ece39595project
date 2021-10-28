package game;

public class CreatureAction extends Action {

    //list of variables for CreatureAction class:
    private Creature owner;
    private String name;
    private String type;

    public CreatureAction(Creature _owner) {
        owner = _owner;
        System.out.println("CreatureActoin set");
    }
    
    public CreatureAction(String name, String type) {
        System.out.println("CreatureAction created");
    }


    //variable setters:
    public void setName(String _name){
        name = _name;
        System.out.println("set name: " + name);
    }

    public void setType(String _type){
        type = _type;
        System.out.println("set type: " + type);
    }


    //variable getters:
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

}
