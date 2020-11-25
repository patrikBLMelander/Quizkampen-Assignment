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
                out.writeObject("1");
                p.processInput(getUserName(), User.this);
            }
            else {
                out.writeObject(" ");
                p.processInput(getUserName(), User.this);
            }
        } catch (IOException  e) {
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
        Object temp;

        try {
            while ((temp = in.readObject()) != null) {
                System.out.println(getUserName() + " tagit emot " + temp.toString());
                Object obj = p.processInput(getUserName(), temp);
                out.writeObject(obj);
                out.flush();
            }
        }
        catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
