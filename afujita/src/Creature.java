package afujita.src;

public class Creature extends Displayable {

    public Creature() {
        System.out.println("Creature Created");
    }

    public void setHp(int h) {
        System.out.println("Hp set");
    }

    public void setHpMoves(int hpm) {
        System.out.println("Hp Moves set");
    }

    public void setDeathAction(CreatureAction da) {
        System.out.println("Death action set");
    }

    public void setHitAction(ha) {
        System.out.println("Hit Action set");
    }
}
