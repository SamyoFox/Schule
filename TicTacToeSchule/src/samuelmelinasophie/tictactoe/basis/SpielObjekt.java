package samuelmelinasophie.tictactoe.basis;

public class SpielObjekt {

    public String name;

    public SpielObjekt(String name){
        this.name = name;
    }

    public String toString(){
        return name == null ? "-" : name;
    }

}
