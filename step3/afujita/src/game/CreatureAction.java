package game;

public class CreatureAction extends Action {

    //list of variables for CreatureAction class:
    private Creature owner;
    private String name;
    private String type;

    public CreatureAction(Creature _owner) {
        owner = _owner;
    }
    
    public CreatureAction(String name, String type) {
    }


    //variable setters:
    public void setName(String _name){
        name = _name;
    }

    public void setType(String _type){
        type = _type;
    }


    //variable getters:
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

}
