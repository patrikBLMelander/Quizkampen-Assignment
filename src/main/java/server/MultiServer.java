package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Patrik Melander
 * Date: 2020-11-10
 * Time: 10:10
 * Project: TCPAndClientServer
 * Copyright: MIT
 */
public class MultiServer extends Thread {
    private final Socket connectionToClient;

    public MultiServer(Socket clientSocket) {
        this.connectionToClient = clientSocket;
    }


    public void run(){


        try (ObjectOutputStream out = new ObjectOutputStream(connectionToClient.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(connectionToClient.getInputStream())){

            String inputObject;
            Object outputPerson;


            Protocol p = new Protocol();
            out.writeObject(p.processInput(null));

            while ((inputObject = (String)ois.readObject())!=null){

                out.writeObject(p.processInput(inputObject));
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

}