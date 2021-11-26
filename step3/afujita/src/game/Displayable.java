package game;

public class Displayable {

    //list of private variables for Displayable class:
    protected boolean isVisible;
    protected int maxHit;
    protected int hitPointMoves;
    protected int hitPoint;
    protected char type;
    protected int intValue;
    protected int posX;
    protected int posY;
    protected int width;
    protected int height;

    public Displayable() {
        isVisible = true;
        maxHit = 0;
        hitPointMoves = 0;
        hitPoint = 0;
        type = ' ';
        intValue = 0;
        posX = 0;
        posY = 0;
        width = 0;
        height = 0;
    }
    
    //variable setters
    public void setInvisible() {
        isVisible = false;
    }

    public void setVisible() {
        isVisible = true;
    }

    public void setMaxHit(int _maxHit) {
        maxHit = _maxHit;
    }

    public void setHpMove(int _hitPointMoves) {
        hitPointMoves = _hitPointMoves;
    }

    public void setHp(int _hitPoint) {
        hitPoint = _hitPoint;
    }

    public void setType(char _type) {
        type = _type;
    }

    public void setIntValue(int _intValue){
        intValue = _intValue;
    }

    public void setPosX(int _posX){
        posX = _posX;
    }

    public void setPosY(int _posY){
        posY = _posY;
    }

    public void setWidth(int _width){
        width = _width;
    }

    public void setHeight(int _height){
        height = _height;
    }


    //variable getters
    public boolean getIsVisible() {
        return isVisible;
    }

    public int getMaxHit() {
        return maxHit;
    }

    public int getHpMoves() {
        return hitPointMoves;
    }

    public int getHp() {
        return hitPoint;
    }

    public char getType() {
        return type;
    }

    public int getIntValue() {
        return intValue;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}