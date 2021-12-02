package game;

public class Monster extends Creature {

    //list of variables for Monster class:

    public Monster(String _name) {
        super(_name);
        if (_name == "Troll") {
            type = 'T';
        }
        if (_name == "Snake") {
            type = 'S';
        }
        if (_name == "Hobgoblin") {
            type = 'H';
        }
    }

}
