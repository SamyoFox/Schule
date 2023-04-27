package samuelmelinasophie.tictactoe.basis;

import samuelmelinasophie.tictactoe.objekte.O;
import samuelmelinasophie.tictactoe.objekte.X;

public class Spielfeld {

    public SpielObjekt[] spielFeld;
    public boolean spielGewonnen;

    public Spielfeld(){
         spielFeld = new SpielObjekt[] {null, null, null,
                 null, null, null,
                 null, null, null};
         spielGewonnen = false;
    }

    public String ausgeben(){
        return String.format("%s | %s | %s\n%s | %s | %s\n%s | %s | %s", spielFeld).replace("null", "-");
    }

    public SpielObjekt isSpielGewonnen(){
        // Horizontal X
        if (spielFeld[0] instanceof X && spielFeld[1] instanceof X && spielFeld[2] instanceof X){
            spielGewonnen = true;
            return new X();
        }
        if (spielFeld[3] instanceof X && spielFeld[4] instanceof X && spielFeld[5] instanceof X){
            spielGewonnen = true;
            return new X();
        }
        if (spielFeld[6] instanceof X && spielFeld[7] instanceof X && spielFeld[8] instanceof X){
            spielGewonnen = true;
            return new X();
        }
        // Horizontal O
        if (spielFeld[0] instanceof O && spielFeld[1] instanceof O && spielFeld[2] instanceof O){
            spielGewonnen = true;
            return new O();
        }
        if (spielFeld[3] instanceof O && spielFeld[4] instanceof O && spielFeld[5] instanceof O){
            spielGewonnen = true;
            return new O();
        }
        if (spielFeld[6] instanceof O && spielFeld[7] instanceof O && spielFeld[8] instanceof O){
            spielGewonnen = true;
            return new O();
        }
        // Vertikal X
        if (spielFeld[0] instanceof X && spielFeld[3] instanceof X && spielFeld[6] instanceof X){
            spielGewonnen = true;
            return new X();
        }
        if (spielFeld[1] instanceof X && spielFeld[4] instanceof X && spielFeld[7] instanceof X){
            spielGewonnen = true;
            return new X();
        }
        if (spielFeld[2] instanceof X && spielFeld[5] instanceof X && spielFeld[8] instanceof X){
            spielGewonnen = true;
            return new X();
        }
        // Vertikal O
        if (spielFeld[0] instanceof O && spielFeld[3] instanceof O && spielFeld[6] instanceof O){
            spielGewonnen = true;
            return new O();
        }
        if (spielFeld[1] instanceof O && spielFeld[4] instanceof O && spielFeld[7] instanceof O){
            spielGewonnen = true;
            return new O();
        }
        if (spielFeld[2] instanceof O && spielFeld[5] instanceof O && spielFeld[8] instanceof O){
            spielGewonnen = true;
            return new O();
        }
        // Diagonal X
        if (spielFeld[0] instanceof X && spielFeld[4] instanceof X && spielFeld[8] instanceof X){
            spielGewonnen = true;
            return new X();
        }
        if (spielFeld[6] instanceof X && spielFeld[4] instanceof X && spielFeld[2] instanceof X){
            spielGewonnen = true;
            return new X();
        }
        // Diagonal O
        if (spielFeld[0] instanceof O && spielFeld[4] instanceof O && spielFeld[8] instanceof O){
            spielGewonnen = true;
            return new O();
        }
        if (spielFeld[6] instanceof O && spielFeld[4] instanceof O && spielFeld[2] instanceof O){
            spielGewonnen = true;
            return new O();
        }
        spielGewonnen = false;
        return null;
    }

    public boolean spielobjektPlatzieren(int X, int Y, SpielObjekt spielObjekt){
        if (Y > 3 || X > 3 || X < 1 || Y < 1){
            return false;
        }
        int startIndex = -1;
        switch(Y){
            case 1:
                startIndex = -1;
                break;
            case 2:
                startIndex = 2;
                break;
            case 3:
                startIndex = 5;
                break;
        }
        if (spielFeld[startIndex+X] == null){
            spielFeld[startIndex+X] = spielObjekt;
            return true;
        } else {
            return false;
        }
    }

}
