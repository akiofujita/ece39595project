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
        isAlive = true;
    }

    public Creature(String _name) {
        name = _name;
        isAlive = true;
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
        name = _name;
    }

    public void setRoomNum(int _roomNum) {
        roomNum = _roomNum;
    }
    
    public void setSerialNum(int _serialNum) {
        serialNum = _serialNum;
    }

    public void setAlive() {
        isAlive = true;
    }

    public void setDead() {
        isAlive = false;
    }

    public void setHp(int _hitPoint) {
        hitPoint = _hitPoint;
        checkHP();
    }

    public void setHpMoves(int _hitPointMoves) {
        hitPointMoves = _hitPointMoves;
    }

    public void setDeathAction(CreatureAction _deathAction) {
        deathAction = _deathAction;
    }

    public void setHitAction(CreatureAction _hitAction) {
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
