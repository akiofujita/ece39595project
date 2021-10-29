package game;

import java.util.Random;

public class Creature extends Displayable {

    //list of private variables for Creature class
    // private int hitPoint;
    // private int hitPointMoves;
    protected String name;
    protected int roomNum;
    protected int serialNum;
    protected boolean isAlive;
    private CreatureAction deathAction;
    private CreatureAction hitAction;

    public Creature() {
        System.out.println("Creature created");
        isAlive = true;
    }

    public Creature(String _name) {
        name = _name;
        isAlive = true;
        System.out.println("Creature created");
    }


    @Override
    public String toString() {
        String outputStr = "";
        
        if( this instanceof Player ) {
            outputStr += "PLAYER INFO:";
        }
        else if( this instanceof Monster ) {
            outputStr += "MONSTER INFO:";
        }
        
        // outputStr += "\nisVisible:      " + isVisible;
        // outputStr += "\nmaxHit:         " + maxHit;
        // outputStr += "\nhitPointMoves:  " + hitPointMoves;
        // outputStr += "\nhitPoint:       " + hitPoint;
        outputStr += "\ntype:           " + type;
        // outputStr += "\nintValue:       " + intValue;
        outputStr += "\nposX:           " + posX;
        outputStr += "\nposY:           " + posY;
        outputStr += "\nroomNum:        " + roomNum;
        return outputStr;
    }

    //variable setters
    public void setName(String _name) {
        System.out.println("Name set");
        name = _name;
    }

    public void setRoomNum(int _roomNum) {
        System.out.println("Room num set");
        roomNum = _roomNum;
    }
    
    public void setSerialNum(int _serialNum) {
        System.out.println("Serial num set");
        serialNum = _serialNum;
    }

    public void setAlive() {
        isAlive = true;
    }

    public void setDead() {
        isAlive = false;
    }

    public void setHp(int _hitPoint) {
        System.out.println("Hp set");
        hitPoint = _hitPoint;
        checkHP();
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
    public String getName() {
        return name;
    }

    public int getRoomNum() {
        return roomNum;
    }
    
    public int getSerialNum() {
        return serialNum;
    }

    public boolean getHealthStatus() {
        return isAlive;
    }

    public int getHP() {
        return hitPoint;
    }

    public int getHPMoves() {
        return hitPointMoves;
    }

    public CreatureAction getDeathAction() {
        return deathAction;
    }

    public CreatureAction getHitAction() {
        return hitAction;
    }

    public int receiveDamage(Creature attacker) {
        Random rand = new Random();
        int attackerMaxHit = attacker.getMaxHit();
        int damage = rand.nextInt(attackerMaxHit + 1);
        hitPoint -= damage;
        checkHP();
        return damage;
    }

    private void checkHP() {
        if (hitPoint <= 0) {
            hitPoint = 0;
            isAlive = false;
        }
    }
}
