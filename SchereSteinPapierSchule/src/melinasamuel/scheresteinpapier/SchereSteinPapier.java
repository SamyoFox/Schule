package melinasamuel.scheresteinpapier;

import melinasamuel.scheresteinpapier.objekte.Papier;
import melinasamuel.scheresteinpapier.objekte.Schere;
import melinasamuel.scheresteinpapier.objekte.Spielobjekt;
import melinasamuel.scheresteinpapier.objekte.Stein;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class SchereSteinPapier {

    public static void main(String[] args) throws Exception {
        System.out.println("Schere Stein Papier");
        System.out.println("von Melina, Samuel");
        Scanner s = new Scanner(System.in);
        while(true){
            System.out.println("[L]okal oder [N]etzwerk?");
            String playType = s.nextLine().trim().toLowerCase();
            switch(playType){
                case "n":
                    System.out.println("[S]erver/[C]lient?");
                    String sc = s.nextLine().trim().toLowerCase();
                    if (sc.equals("s")){
                        Multiplayer.playMultiplayerServer();
                    } else if (sc.equals("c")){
                        System.out.println("Gebe die IP des Gegenüber ein:");
                        String ip = s.nextLine().trim();
                        Multiplayer.playMultiplayerClient(ip);
                    }
                    break;
                case "l":
                    System.out.println("Willst du [N]ormal spielen oder [U]nmöglich?");
                    String gamePlayMode = s.nextLine().trim().toLowerCase();
                    switch(gamePlayMode){
                        case "n":
                            playNormal();
                            break;
                        case "u":
                            playImpossible();
                            break;
                        default:
                            System.out.println("Das ist kein gültiger Spielmodus");
                    }
                    break;
                default:
                    break;
            }

        }
    }

    private static void playNormal(){
        int runden = 2; // Rundenanzahl - 1 weil bei 0 anfängt
        int gespielteRunden = 0;
        int gewonnenSpieler = 0;
        int gewonnenComputer = 0;
        System.out.println("Normaler Modus");
        while(gespielteRunden <= runden){
            System.out.println(String.format("Runde %s von %s, wähle ein Objekt aus", gespielteRunden+1, runden+1));
            System.out.println("Schere - 1");
            System.out.println("Stein - 2");
            System.out.println("Papier - 3");
            boolean richtigeAntwort = false;
            Spielobjekt spielerObjekt = new Spielobjekt("nichts");
            while(!richtigeAntwort){
                Scanner s = new Scanner(System.in);
                switch(s.nextLine().trim()){
                    case "1":
                        spielerObjekt = new Schere();
                        richtigeAntwort = true;
                        break;
                    case "2":
                        spielerObjekt = new Stein();
                        richtigeAntwort = true;
                        break;
                    case "3":
                        spielerObjekt = new Papier();
                        richtigeAntwort = true;
                        break;
                    default:
                        System.out.println("Das ist kein gültiges Objekt!");
                }
            }
            Spielobjekt gegnerObjekt = new Spielobjekt("nichts");
            int gegnerObjektNum = ThreadLocalRandom.current().nextInt(3);
            switch(gegnerObjektNum){
                case 0:
                    gegnerObjekt = new Schere();
                    break;
                case 1:
                    gegnerObjekt = new Stein();
                    break;
                case 2:
                    gegnerObjekt = new Papier();
                    break;
                default:
                    System.out.println("Das sollte nicht passieren x3");
                    gegnerObjekt = new Papier();
                    break;
            }
            System.out.println(String.format("Spielerobjekt: %s | Gegnerobjekt: %s", spielerObjekt.toString(), gegnerObjekt.toString()));
            if ((spielerObjekt instanceof Schere && gegnerObjekt instanceof Stein) || (spielerObjekt instanceof Stein && gegnerObjekt instanceof Papier) || (spielerObjekt instanceof Papier && gegnerObjekt instanceof Schere)){
                System.out.println("Der Computer hat die Runde gewonnen!");
                gewonnenComputer++;
                gespielteRunden++;
            } else if ((spielerObjekt instanceof Schere && gegnerObjekt instanceof Papier) || (spielerObjekt instanceof Stein && gegnerObjekt instanceof Schere) || (spielerObjekt instanceof Papier && gegnerObjekt instanceof Stein)){
                System.out.println("Du hast die Runde gewonnen!");
                gewonnenSpieler++;
                gespielteRunden++;
            } else {
                System.out.println("Die Runde ist unentschieden!");
                gespielteRunden++;
            }
        }
        if (gewonnenComputer > gewonnenSpieler){
            System.out.println("Der Computer hat das Spiel gewonnen!");
        } else if (gewonnenSpieler > gewonnenComputer){
            System.out.println("Du hast das Spiel gewonnen!");
        } else {
            System.out.println("Das Spiel ist unentschieden ausgegangen!");
        }
    }

    private static void playImpossible(){
        int runden = 2; // Rundenanzahl - 1 weil bei 0 anfängt
        int gespielteRunden = 0;
        int gewonnenSpieler = 0;
        int gewonnenComputer = 0;
        System.out.println("Unmöglicher Modus");
        while(gespielteRunden <= runden){
            System.out.println(String.format("Runde %s von %s, wähle ein Objekt aus", gespielteRunden+1, runden+1));
            System.out.println("Schere - 1");
            System.out.println("Stein - 2");
            System.out.println("Papier - 3");
            boolean richtigeAntwort = false;
            Spielobjekt spielerObjekt = new Spielobjekt("nichts");
            while(!richtigeAntwort){
                Scanner s = new Scanner(System.in);
                switch(s.nextLine().trim()){
                    case "1":
                        spielerObjekt = new Schere();
                        richtigeAntwort = true;
                        break;
                    case "2":
                        spielerObjekt = new Stein();
                        richtigeAntwort = true;
                        break;
                    case "3":
                        spielerObjekt = new Papier();
                        richtigeAntwort = true;
                        break;
                    default:
                        System.out.println("Das ist kein gültiges Objekt!");
                }
            }
            Spielobjekt gegnerObjekt = new Spielobjekt("nichts");
            if (spielerObjekt instanceof Schere){
                gegnerObjekt = new Stein();
            } else if (spielerObjekt instanceof Stein){
                gegnerObjekt = new Papier();
            } else if (spielerObjekt instanceof Papier){
                gegnerObjekt = new Schere();
            }
            System.out.println(String.format("Spielerobjekt: %s | Gegnerobjekt: %s", spielerObjekt.toString(), gegnerObjekt.toString()));
            if ((spielerObjekt instanceof Schere && gegnerObjekt instanceof Stein) || (spielerObjekt instanceof Stein && gegnerObjekt instanceof Papier) || (spielerObjekt instanceof Papier && gegnerObjekt instanceof Schere)){
                System.out.println("Der Computer hat die Runde gewonnen!");
                gewonnenComputer++;
                gespielteRunden++;
            } else if ((spielerObjekt instanceof Schere && gegnerObjekt instanceof Papier) || (spielerObjekt instanceof Stein && gegnerObjekt instanceof Schere) || (spielerObjekt instanceof Papier && gegnerObjekt instanceof Stein)){
                System.out.println("Du hast die Runde gewonnen!");
                gewonnenSpieler++;
                gespielteRunden++;
            } else {
                System.out.println("Die Runde ist unentschieden!");
            }
        }
        if (gewonnenComputer > gewonnenSpieler){
            System.out.println("Der Computer hat das Spiel gewonnen!");
        } else if (gewonnenSpieler > gewonnenComputer){
            System.out.println("Du hast das Spiel gewonnen!");
        } else {
            System.out.println("Das Spiel ist unentschieden ausgegangen!");
        }
    }

}
