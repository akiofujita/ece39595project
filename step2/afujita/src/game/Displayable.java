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
        // System.out.println("Displayable created");
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
        // System.out.println("Type set");
        type = _type;
    }

    public void setIntValue(int _intValue){
        System.out.println("IntValue set");
        intValue = _intValue;
    }

    public void setPosX(int _posX){
        System.out.println("PosX set");
        posX = _posX;
        System.out.println(this.posX);
    }

    public void setPosY(int _posY){
        System.out.println("PosY set");
        posY = _posY;
        System.out.println(this.posY);
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