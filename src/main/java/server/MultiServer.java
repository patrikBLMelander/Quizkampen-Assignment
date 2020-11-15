package server.Categories;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Locale;

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
            Object outputList;
            Object temp;


            Protocol p = new Protocol();

            while ((temp = ois.readObject())!=null){

                    System.out.println("tagit emot " + temp.toString());
                    out.writeObject(p.processInput(temp));
                    out.flush();


            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

}