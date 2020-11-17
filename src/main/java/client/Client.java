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
            int pointCounter = 0;



            out.writeObject(name);


            while((temp=in.readObject())!=null) {
                if (temp instanceof Questions){
                    System.out.println("Fråga : " + ((Questions) temp).getQuestion());
                    temp=scan.nextLine();
                    out.writeObject(temp);
                }
                else if(temp instanceof Response){
                    boolean answer = ((Response) temp).getSuccess();
                    System.out.println(answer);

                    if(((Response) temp).getSuccess()){
                        System.out.println("Rätt svar");
                        pointCounter++;
                        String points = pointCounter + "";
                        out.writeObject(points);
                    }

                    else {
                        System.out.println("Fel svar");
                        String points = pointCounter + "";
                        out.writeObject(points);
                    }
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