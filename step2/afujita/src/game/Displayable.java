package game;

public class Displayable {

    //list of private variables for Displayable class:
    private boolean isVisible;
    private int maxHit;
    private int hitPointMoves;
    private int hitPoint;
    private char type;
    private int intValue;
    private int posX;
    private int posY;
    private int width;
    private int height;

    public Displayable() {
        // System.out.println("Displayable created");
    }
    
    //variable setters
    public void setInvisible() {
        System.out.println("Invisible set");
        isVisible = false;
    }

    public void setVisible() {
        System.out.println("Visible set");
        isVisible = true;
    }

    public void setMaxHit(int _maxHit) {
        System.out.println("MaxHit set"); 
        maxHit = _maxHit;
    }

    public void setHpMove(int _hitPointMoves) {
        System.out.println("HpMove set");
        hitPointMoves = _hitPointMoves;
    }

    public void setHp(int _hitPoint) {
        System.out.println("Hp set");
        hitPoint = _hitPoint;
    }

    public void setType(char _type) {
        System.out.println("Type set");
        type = _type;
    }

    public void setIntValue(int _intValue){
        System.out.println("IntValue set");
        intValue = _intValue;
    }

    public void setPosX(int _posX){
        System.out.println("PosX set");
        posX = _posX;
    }

    public void setPosY(int _posY){
        System.out.println("PosY set");
        posY = _posY;
    }

    public void setWidth(int _width){
        System.out.println("Width set");
        width = _width;
    }

    public void setHeight(int _height){
        System.out.println("Height set");   
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