package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    int counter = 0;

    public Server(){

        try (ServerSocket server = new ServerSocket(55000)){
            while (true) {

                Socket connectionToClient = server.accept();
                MultiServer m = new MultiServer(connectionToClient);
                m.start();
                System.out.println("Spelare: " + (counter+1));
                if(counter==1){
                    counter=0;
                }
                else
                    counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        Server s = new Server();
    }

}