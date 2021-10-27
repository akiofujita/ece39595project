package src;

public class Creature extends Displayable {

    private int hitPoint;
    private int hitPointMoves;
    private CreatureAction deathAction;
    private CreatureAction hitAction;

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
