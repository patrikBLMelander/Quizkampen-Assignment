package server;

import java.io.Serializable;
import java.net.InetAddress;

/**
 * Created by Sara Carlsson
 * Date: 13/11/2020
 * Time:10:19
 * Project: Quizkampen1
 * Copywright: MIT
 */
public class User implements Serializable {
    private String userName;
    private InetAddress iadr;
    private int points;
    private User opponent;

    public User(String userName) {
        this.userName = userName;
        this.iadr = InetAddress.getLoopbackAddress();
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
}
