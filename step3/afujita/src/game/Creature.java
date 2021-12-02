package game;

import java.util.Random;
import java.util.ArrayList;

public class Creature extends Displayable {

    //list of private variables for Creature class
    // private int hitPoint;
    // private int hitPointMoves;
    protected String name;
    protected int roomNum;
    protected int serialNum;
    protected boolean isAlive;
    protected int hitPoint;
    private ArrayList<CreatureAction> deathActions;
    private ArrayList<CreatureAction> hitActions;

    public Creature() {
        name = "";
        roomNum = 0;
        serialNum = 0;
        isAlive = true;
        hitPoint = 0;
        deathActions = new ArrayList<CreatureAction>();
        hitActions = new ArrayList<CreatureAction>();
    }

    public Creature(String _name) {
        name = _name;
        roomNum = 0;
        serialNum = 0;
        isAlive = true;
        hitPoint = 0;
        deathActions = new ArrayList<CreatureAction>();
        hitActions = new ArrayList<CreatureAction>();
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
        
        outputStr += "\nserialNum:      " + serialNum;
        // outputStr += "\nisVisible:      " + isVisible;
        // outputStr += "\nmaxHit:         " + maxHit;
        // outputStr += "\nhitPointMoves:  " + hitPointMoves;
        // outputStr += "\nhitPoint:       " + hitPoint;
        // outputStr += "\ntype:           " + type;
        // outputStr += "\nintValue:       " + intValue;
        outputStr += "\nposX:           " + posX;
        outputStr += "\nposY:           " + posY;
        // outputStr += "\nroomNum:        " + roomNum;

        outputStr += "\n";
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

    public void addDeathAction(CreatureAction _deathAction) {
        deathActions.add(_deathAction);
    }

    public void addHitAction(CreatureAction _hitAction) {
        hitActions.add(_hitAction);
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

    public ArrayList<CreatureAction> getDeathActions() {
        return deathActions;
    }

    public ArrayList<CreatureAction> getHitActions() {
        return hitActions;
    }

    /* Subtract hitpoint based on attacker's maxHit */
    public int receiveDamage(Creature attacker) {
        Random rand = new Random();
        int attackerMaxHit = attacker.getMaxHit();
        int damage = rand.nextInt(attackerMaxHit + 1);

        if (attacker instanceof Player) {
            Player player = (Player) attacker;
            Sword sword = player.getSword();
            if (sword != null) {
                damage += sword.getIntValue();
            }
        }
        else {
            Player player = (Player) this;
            Armor armor = player.getArmor();
            if (armor != null) {
                damage -= armor.getIntValue();
                if (damage < 0) {
                    damage = 0;
                }
            }
        }
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
