package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class User extends Thread implements Serializable {
    private final String userName;
    private int points = 0;
    private int counter = 0;
    private final int [][] resultArray= new int[5][5];
    private User opponent;
    ObjectOutputStream out;
    ObjectInputStream in;
    Protocol p;

    public User(String userName, Socket socket, Protocol p) {
        this.userName = userName;
        this.p = p;

        setArray();

        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            System.out.println("WELCOME " + getUserName());
            if(getUserName().equals("Player 1")){
                out.writeObject("1");
            }
            else {
                out.writeObject(" ");
            }
            p.processInput(getUserName(), User.this);
        } catch (IOException  e) {
            e.printStackTrace();
        }
    }

    public void setResultArray(int row, int column, int x){
        this.resultArray[row][column] = x;
    }

    public int [][] getResultArray(){return resultArray;}

    public int getCounter(){return counter; }

    public void addCounter(){ counter++;}

    public void resetCounter(){ this.counter = 0; }

    public String getUserName() {
        return userName;
    }

    public void addPoints()
    {
        points++;
    }
    
    public void removePoints()
    {
        points--;
        
    }
    
    public void deletePoints()
    {
        points = 0;
        
    }
    
    public void invalidatePoints()
    {
        points = -1;
        
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
                Object obj = p.processInput(getUserName(), temp);
                out.reset();
                out.writeObject(obj);
                out.flush();
            }
        }
        catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    public void setArray(){
        for (int i = 0; i < this.resultArray.length; i++) {
            for (int j = 0; j < this.resultArray.length; j++) {
                this.resultArray[i][j]=0;
            }
        }
    }
}
