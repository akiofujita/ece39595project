package game;

public class Monster extends Creature {

    public Monster(String _name) {
        if (_name == "Troll") {
            name = _name;
            type = 'T';
            System.out.println("Troll spawned");
        }
        if (_name == "Snake") {
            name = _name;
            type = 'S';
            System.out.println("Snake spawned");
        }
        if (_name == "Hobgoblin") {
            name = _name;
            type = 'H';
            System.out.println("Hobgoblin spawned");
        }
    }

}
