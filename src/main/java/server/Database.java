package server;


import server.Categories.Patrik;
import server.Categories.Simon;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Database implements Serializable {

    List<User> userList = new ArrayList<>();

    Questions sara = new Questions("Hur gammal är Sara", "37", "25", "43", "32");
    Questions simon = new Questions("Hur gammal är Simon", "21", "18", "30", "25");
    Questions axel = new Questions("Hur gammal är Axel", "27", "25", "30", "20");
    Questions patrik = new Questions("Hur gammal är Patrik", "35", "40", "30", "25");
    ArrayList<Questions> test = new ArrayList<>();

    Simon s1 = new Simon("Hur gammal är Sara", "37", "25", "43", "32");
    Simon s2 = new Simon("Hur gammal är Simon", "21", "18", "30", "25");
    Simon s3 = new Simon("Hur gammal är Axel", "27", "25", "30", "20");
    Simon s4 = new Simon("Hur gammal är Patrik", "35", "40", "30", "25");

    Patrik p1 = new Patrik("Hur gammal är Sara", "37", "25", "43", "32");
    Patrik p2 = new Patrik("Hur gammal är Simon", "21", "18", "30", "25");
    Patrik p3 = new Patrik("Hur gammal är Axel", "27", "25", "30", "20");
    Patrik p4 = new Patrik("Hur gammal är Patrik", "35", "40", "30", "25");

    ArrayList<Simon> simonList = new ArrayList<>();
    ArrayList<Patrik> patrikList = new ArrayList<>();

    public Database() {
        test.add(sara);
        test.add(simon);
        test.add(axel);
        test.add(patrik);

        simonList.add(s1);
        simonList.add(s2);
        simonList.add(s3);
        simonList.add(s4);

        patrikList.add(p1);
        patrikList.add(p2);
        patrikList.add(p3);
        patrikList.add(p4);

    }

    public boolean findCorrectAnswer(String s) {
        for (Questions q : test) {
            if (q.getCorrectAnswer().equalsIgnoreCase(s))
                return true;
        }
        return false;
    }


    public ArrayList chooseCategory(String buttonID) {

        switch (buttonID) {
            case "btnID1":
                return test;
            case "btnID2":
                return simonList;
            case "btnID3":
                return patrikList;
            default:
                System.out.println("error");
        }
        return null;
    }

    public void addUser(User u) {
        userList.add(u);
    }

    public List<User> getUsers() {
        return userList;
    }

    public void removeUser(User u) {
        userList.remove(u);
    }
}
