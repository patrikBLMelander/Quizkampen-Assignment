package client;

import server.Questions;
import server.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class Client {
    public Client() {
        Scanner scan = new Scanner(System.in);
        try (Socket connectToServer = new Socket("127.0.0.1", 55510);
             ObjectOutputStream out = new ObjectOutputStream(connectToServer.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(connectToServer.getInputStream())) {

            Object temp;
            String name = "Sara";


            out.writeObject(name);


            while((temp=in.readObject())!=null) {
                if (temp instanceof List){
                    for(var v : (List)temp)
                    System.out.println(v);
                    String answer = scan.nextLine();
                    out.writeObject(answer);
                }
                else if(temp instanceof Response){
                    if(((Response) temp).isSuccess())
                        System.out.println("Rätt svar");
                    else
                        System.out.println("Fel svar");
                }
            }





        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Client c = new Client();

    }
}