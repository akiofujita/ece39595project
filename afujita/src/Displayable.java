package afujita.src;
public class Displayable {

    public Displayable() {
        System.out.println("Creating a Displayable object");
    }

    public void setInvisible() {
        System.out.println("Invisible set");
    }

    public void setVisible() {
        System.out.println("Visible set");
    }

    public void setMaxHit(int maxHit) {
        System.out.println("maxHit set"); 
    }

    public void setHpMove(int hpMoves) {
        System.out.println("HpMove set");
    }

    public void setHp(int Hp) {
        System.out.println("Hp set");
    }

    public void setType(char t) {
        System.out.println("Type set");
    }

    public void setIntValue(int v){
        System.out.println("IntValue set");
    }

    public void setPosX(int x){
        System.out.println("PosX set");
    }

    public void setPosY(int y){
        System.out.println("PosY set");
    }

    public void setWidth(int x){
        System.out.println("Width set");
    }

    public void setHeight(int y){
        System.out.println("Height set");   
    }
}