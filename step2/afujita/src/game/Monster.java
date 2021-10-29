package game;

public class Monster extends Creature {

    //list of variables for Monster class:


    public Monster(String _name) {
        super(_name);
        if (_name == "Troll") {
            type = 'T';
            System.out.println("Troll spawned");
        }
        if (_name == "Snake") {
            type = 'S';
            System.out.println("Snake spawned");
        }
        if (_name == "Hobgoblin") {
            type = 'H';
            System.out.println("Hobgoblin spawned");
        }
    }

}
