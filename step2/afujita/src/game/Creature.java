package game;

public class Creature extends Displayable {

    //list of private variables for Creature class
    private int hitPoint;
    private int hitPointMoves;
    private CreatureAction deathAction;
    private CreatureAction hitAction;


    public Creature() {
        System.out.println("Creature created");
    }


    //variable setters
    public void setHp(int _hitPoint) {
        System.out.println("Hp set");
        hitPoint = _hitPoint;
    }

    public void setHpMoves(int _hitPointMoves) {
        System.out.println("Hp moves set");
        hitPointMoves = _hitPointMoves;
    }

    public void setDeathAction(CreatureAction _deathAction) {
        System.out.println("Death action set");
        deathAction = _deathAction;
    }

    public void setHitAction(CreatureAction _hitAction) {
        System.out.println("Hit action set");
        hitAction = _hitAction;
    }


    //variable getters
    public int getHp() {
        return hitPoint;
    }

    public int getHpMoves() {
        return hitPointMoves;
    }

    public CreatureAction getDeathAction() {
        return deathAction;
    }

    public CreatureAction getHitAction(CreatureAction _ha) {
        return hitAction;
    }


}
