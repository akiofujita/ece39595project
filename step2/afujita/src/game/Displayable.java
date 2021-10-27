package src.game;

public class Displayable {

    public Displayable() {
        // System.out.println("Displayable created");
    }

    public void setInvisible() {
        System.out.println("Invisible set");
    }

    public void setVisible() {
        System.out.println("Visible set");
    }

    public void setMaxHit(int _maxHit) {
        System.out.println("MaxHit set"); 
    }

    public void setHpMove(int _hpMoves) {
        System.out.println("HpMove set");
    }

    public void setHp(int _Hp) {
        System.out.println("Hp set");
    }

    public void setType(char _t) {
        System.out.println("Type set");
    }

    public void setIntValue(int _v){
        System.out.println("IntValue set");
    }

    public void setPosX(int _x){
        System.out.println("PosX set");
    }

    public void setPosY(int _y){
        System.out.println("PosY set");
    }

    public void setWidth(int _x){
        System.out.println("Width set");
    }

    public void setHeight(int _y){
        System.out.println("Height set");   
    }
}