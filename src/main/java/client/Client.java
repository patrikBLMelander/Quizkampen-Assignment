package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    public Client() {
        try (Socket connectToServer = new Socket("127.0.0.1", 55555);
             ObjectOutputStream out = new ObjectOutputStream(connectToServer.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(connectToServer.getInputStream())) {

            Object temp;
            String answer = "";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Client c = new Client();

    }
}