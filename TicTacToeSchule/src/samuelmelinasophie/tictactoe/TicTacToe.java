package samuelmelinasophie.tictactoe;

import samuelmelinasophie.tictactoe.basis.SpielObjekt;
import samuelmelinasophie.tictactoe.basis.Spielfeld;
import samuelmelinasophie.tictactoe.objekte.O;
import samuelmelinasophie.tictactoe.objekte.X;

import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args){
        Spielfeld spielfeld = new Spielfeld();
        System.out.println("Willkommen bei Tic Tac Toe");
        System.out.println("von Melina, Sophie und Samuel");
        SpielObjekt dran = new X();
        Scanner s = new Scanner(System.in);
        while(!spielfeld.spielGewonnen){
            System.out.println(spielfeld.ausgeben());
            System.out.println(String.format("Jetzt ist Spieler %s dran", dran.name));
            boolean erfolgreichPlatziert = false;
            while(!erfolgreichPlatziert){
                System.out.println("X Y: ");
                String[] position = s.nextLine().split(" ");
                try{
                    erfolgreichPlatziert = spielfeld.spielobjektPlatzieren(Integer.parseInt(position[0]), Integer.parseInt(position[1]), dran);
                } catch (Exception e) {}
            }
            if (dran instanceof X) {
                dran = new O();
            } else {
                dran = new X();
            }
            SpielObjekt gewonnenObjekt = spielfeld.isSpielGewonnen();
            if (gewonnenObjekt instanceof X){
                System.out.println("X hat gewonnen!");
            } else if (gewonnenObjekt instanceof O) {
                System.out.println("O hat gewonnen!");
            }
        }
    }

}
