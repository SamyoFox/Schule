package melinasamuel.scheresteinpapier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Server implements Runnable {
    @Override
    public void run() {
        try {
            String enemy = new BufferedReader(new InputStreamReader(Multiplayer.client.getInputStream())).readLine();
            Multiplayer.enemyAnswer = Integer.parseInt(enemy);
            Multiplayer.enemyAnswered = true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
