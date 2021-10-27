package game;

public class Creature extends Displayable {

    public Creature() {
        System.out.println("Creature created");
    }

    public void setHp(int _hp) {
        System.out.println("Hp set");
    }

    public void setHpMoves(int _hpm) {
        System.out.println("Hp moves set");
    }

    public void setDeathAction(CreatureAction _da) {
        System.out.println("Death action set");
    }

    public void setHitAction(CreatureAction _ha) {
        System.out.println("Hit action set");
    }
}
