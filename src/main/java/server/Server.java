package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public Server(){

        try (ServerSocket server = new ServerSocket(55555)){
            while (true) {
                Socket connectionToClient = server.accept();
                MultiServer m = new MultiServer(connectionToClient);
                m.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        Server s = new Server();
    }

}