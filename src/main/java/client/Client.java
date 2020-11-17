package client;

import server.Questions;
import server.Response;
import server.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class Client {
    Socket connectToServer;
    ObjectOutputStream out;
    ObjectInputStream in;

    public ObjectOutputStream getOut() {
        return out;
    }

    public ObjectInputStream getIn() {
        return in;
    }

    public Client(){ }

    public Client(User user) {
        Scanner scan = new Scanner(System.in);
        try {
            connectToServer = new Socket("127.0.0.1", 55510);
            out = new ObjectOutputStream(connectToServer.getOutputStream());
            in = new ObjectInputStream(connectToServer.getInputStream());


            String name = user.getUserName();

            out.writeObject(name);
            receiveFromServer();



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object receiveFromServer(){
        //int pointCounter = 0;
        Object temp = null;

        try{
            if((temp = in.readObject())!=null);
            /*
            temp=in.readObject();
            if (temp instanceof Questions){
                System.out.println("Fråga : " + ((Questions) temp).getQuestion());
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
            */
        }
        catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return temp;
    }
}