package server;


import server.Categories.Patrik;
import server.Categories.Simon;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Database implements Serializable {

    List<User> userList = new ArrayList<>();

    Questions sara = new Questions("Hur gammal är Sara", "37", "25", "43", "32");
    Questions simon = new Questions("Hur gammal är Simon", "21", "18", "30", "25");
    Questions axel = new Questions("Hur gammal är Axel", "27", "25", "30", "20");
    Questions patrik = new Questions("Hur gammal är Patrik", "35", "40", "30", "25");
    ArrayList<Questions> test = new ArrayList<>();

    Questions s1 = new Questions("Hur gammal är Sara", "37", "25", "43", "32");
    Questions s2 = new Questions("Hur gammal är Simon", "21", "18", "30", "25");
    Questions s3 = new Questions("Hur gammal är Axel", "27", "25", "30", "20");
    Questions s4 = new Questions("Hur gammal är Patrik", "35", "40", "30", "25");
    ArrayList<Questions> simonList = new ArrayList<>();

    Questions p1 = new Questions("Hur gammal är Sara", "37", "25", "43", "32");
    Questions p2 = new Questions("Hur gammal är Simon", "21", "18", "30", "25");
    Questions p3 = new Questions("Hur gammal är Axel", "27", "25", "30", "20");
    Questions p4 = new Questions("Hur gammal är Patrik", "35", "40", "30", "25");
    ArrayList<Questions> patrikList = new ArrayList<>();

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
        boolean temp = false;
        for (Questions q : test) {
            if (q.getCorrectAnswer().equalsIgnoreCase(s))
                temp=  true;
        }
        return temp;
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

    static String randomCategory (){
        Random r = new Random();
        int temp = r.nextInt(1,);

        return
    }
}
