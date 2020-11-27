package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.CountDownLatch;

public class Server {

    public Server(){

        try (ServerSocket server = new ServerSocket(55100)){
            System.out.println("Server is Running");


            while (true) {
                Protocol p = new Protocol();
                User player1 = new User("Player 1", server.accept(), 1, p);
                System.out.println("Spelar 1 connected");
                User player2 = new User("Player 2", server.accept(), 2, p);
                System.out.println("Spelar 2 connected");

                player1.setOpponent(player2);
                player2.setOpponent(player1);

                player1.start();
                player2.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        Server s = new Server();
    }

}