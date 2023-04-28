package melinasamuel.scheresteinpapier;

import melinasamuel.scheresteinpapier.objekte.Papier;
import melinasamuel.scheresteinpapier.objekte.Schere;
import melinasamuel.scheresteinpapier.objekte.Spielobjekt;
import melinasamuel.scheresteinpapier.objekte.Stein;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Multiplayer {

    public static volatile boolean enemyAnswered = false;
    public static volatile int enemyAnswer = 0;
    public static Socket client = null;

    public static void playMultiplayerServer() throws Exception {
        ServerSocket socket = new ServerSocket(6210);
        System.out.println(String.format("Server ist nun erreichbar unter der IP %s", Inet4Address.getLocalHost().getHostAddress()));
        System.out.println("Warte auf Verbindung des Gegenübers...");
        client = socket.accept();
        System.out.println("Gegenüber verbunden");
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintStream out = new PrintStream(client.getOutputStream());
        int runden = 2; // Rundenanzahl - 1 weil bei 0 anfängt
        int gespielteRunden = 0;
        int gewonnenSpieler = 0;
        int gewonnenComputer = 0;
        System.out.println("Normaler Modus");
        while(gespielteRunden <= runden){
            out.println(gespielteRunden + ";" + runden + ";" + gewonnenSpieler + ";" + gewonnenComputer);
            System.out.println(String.format("Runde %s von %s, wähle ein Objekt aus", gespielteRunden+1, runden+1));
            System.out.println("Warte auf Gegner...");
            Server server = new Server();
            new Thread(server).start();
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
            System.out.println("Warte auf Gegner...");
            while(!enemyAnswered){

            }
            out.println("go");
            out.println(spielerObjekt.toString());
            Spielobjekt gegnerObjekt = new Spielobjekt("nichts");
            int gegnerObjektNum = enemyAnswer;
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
                System.out.println("Der Gegner hat die Runde gewonnen!");
                out.println(1);
                gewonnenComputer++;
                gespielteRunden++;
            } else if ((spielerObjekt instanceof Schere && gegnerObjekt instanceof Papier) || (spielerObjekt instanceof Stein && gegnerObjekt instanceof Schere) || (spielerObjekt instanceof Papier && gegnerObjekt instanceof Stein)){
                System.out.println("Du hast die Runde gewonnen!");
                out.println(0);
                gewonnenSpieler++;
                gespielteRunden++;
            } else {
                System.out.println("Die Runde ist unentschieden!");
                out.println(2);
                gespielteRunden++;
            }
            enemyAnswered = false;
            enemyAnswer = 0;
        }
        if (gewonnenComputer > gewonnenSpieler){
            System.out.println("Der Gegner hat das Spiel gewonnen!");
        } else if (gewonnenSpieler > gewonnenComputer){
            System.out.println("Du hast das Spiel gewonnen!");
        } else {
            System.out.println("Das Spiel ist unentschieden ausgegangen!");
        }
    }

    public static void playMultiplayerClient(String ip) throws Exception {
        int runden = 2; // Rundenanzahl - 1 weil bei 0 anfängt
        int gespielteRunden = 0;
        int gewonnenSpieler = 0;
        int gewonnenComputer = 0;
        Socket server = new Socket(ip, 6210);
        BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
        PrintStream out = new PrintStream(server.getOutputStream());
        while(gespielteRunden <= runden) {
            String roundInfo = in.readLine();
            String[] roundInfoArray = roundInfo.split(";");
            gespielteRunden = Integer.parseInt(roundInfoArray[0]);
            runden = Integer.parseInt(roundInfoArray[1]);
            //gewonnenSpieler = Integer.parseInt(roundInfoArray[2]);
            //gewonnenComputer = Integer.parseInt(roundInfoArray[3]);
            System.out.println(String.format("Runde %s von %s, wähle ein Objekt aus", gespielteRunden + 1, runden + 1));
            System.out.println("Schere - 1");
            System.out.println("Stein - 2");
            System.out.println("Papier - 3");
            boolean richtigeAntwort = false;
            Spielobjekt spielerObjekt = new Spielobjekt("nichts");
            int obj = 0;
            while (!richtigeAntwort) {
                Scanner s = new Scanner(System.in);
                switch (s.nextLine().trim()) {
                    case "1":
                        spielerObjekt = new Schere();
                        richtigeAntwort = true;
                        obj = 0;
                        break;
                    case "2":
                        spielerObjekt = new Stein();
                        richtigeAntwort = true;
                        obj = 1;
                        break;
                    case "3":
                        spielerObjekt = new Papier();
                        richtigeAntwort = true;
                        obj = 2;
                        break;
                    default:
                        System.out.println("Das ist kein gültiges Objekt!");
                }
            }
            out.println(obj);
            System.out.println("Warte auf Gegner...");
            boolean waitOver = false;
            while (!waitOver) {
                String input = in.readLine();
                if (input.equals("go")) {
                    waitOver = true;
                }
            }
            Spielobjekt gegnerObjekt = new Spielobjekt("nichts");
            String gegnerObjektS = in.readLine();
            switch (gegnerObjektS) {
                case "Schere":
                    gegnerObjekt = new Schere();
                    break;
                case "Stein":
                    gegnerObjekt = new Stein();
                    break;
                case "Papier":
                    gegnerObjekt = new Papier();
                    break;
                default:
                    System.out.println("Das sollte nicht passieren x3");
                    gegnerObjekt = new Papier();
                    break;
            }
            System.out.println(String.format("Spielerobjekt: %s | Gegnerobjekt: %s", spielerObjekt.toString(), gegnerObjekt.toString()));
            int won = Integer.parseInt(in.readLine());
            switch (won) {
                case 1:
                    System.out.println("Du hast die Runde gewonnen!");
                    gewonnenComputer++;
                    gespielteRunden++;
                    break;
                case 0:
                    System.out.println("Der Gegner die Runde gewonnen!");
                    gewonnenSpieler++;
                    gespielteRunden++;
                    break;
                case 2:
                    System.out.println("Die Runde ist unentschieden");
                    gespielteRunden++;
                    break;
            }
        }
        if (gewonnenComputer > gewonnenSpieler){
            System.out.println("Du hast das Spiel gewonnen!");
        } else if (gewonnenSpieler > gewonnenComputer){
            System.out.println("Der Gegner das Spiel gewonnen!");
        } else {
            System.out.println("Das Spiel ist unentschieden ausgegangen!");
        }
    }

}
