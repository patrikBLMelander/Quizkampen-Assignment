package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Sara Carlsson
 * Date: 13/11/2020
 * Time:10:19
 * Project: Quizkampen1
 * Copywright: MIT
 */
public class User extends Thread implements Serializable {
    private String userName;
    private int player;
    private InetAddress iadr;
    private int points;
    private User opponent;
    private Socket connectionToClient;
    ObjectOutputStream out;
    ObjectInputStream in;
    Protocol p;

    public User(String userName) {
        this.userName = userName;
    }

    public User(String userName, Socket socket, int player, Protocol p) {
        connectionToClient = socket;
        this.userName = userName;
        this.iadr = InetAddress.getLoopbackAddress();
        this.player = player;
        this.p = p;

        try {
            out = new ObjectOutputStream(connectionToClient.getOutputStream());
            in = new ObjectInputStream(connectionToClient.getInputStream());

            System.out.println("WELCOME " + getUserName());
            if(getPlayer()==1){
                p.processInput(2);
                out.writeObject("1");}
            else {
                p.processInput(4);
                out.writeObject(" ");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getPlayer() {
        return player;
    }

    public String getUserName() {
        return userName;
    }

    public InetAddress getIadr() {
        return iadr;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public User getOpponent() {
        return opponent;
    }

    public void setOpponent(User opponent) {
        this.opponent = opponent;
    }

    public void run() {
        System.out.println("MESSAGE All players connected");
        Protocol p = new Protocol();

        Object temp;

        try {
            while ((temp = in.readObject()) != null) {
                System.out.println("tagit emot " + temp.toString());
                out.writeObject(p.processInput(temp));
                out.flush();
            }
        }
        catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
