package server;


import javafx.scene.control.Button;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Database implements Serializable {

    List<User> userList = new ArrayList<>();
    List<ArrayList<Questions>> categoryList = new ArrayList<>();

    Questions sara = new Questions("test JAVA1", "37", "25", "43", "32", "JAVA");
    Questions simon = new Questions("test JAVA2", "21", "18", "30", "25", "JAVA");
    Questions axel = new Questions("test JAVA3", "27", "25", "30", "20", "JAVA");
    Questions patrik = new Questions("test JAVA4", "35", "40", "30", "25", "JAVA");
    ArrayList<Questions> test = new ArrayList<>();

    Questions s1 = new Questions("test SPORT1", "37", "25", "43", "32", "SPORT");
    Questions s2 = new Questions("test SPORT2", "21", "18", "30", "25", "SPORT");
    Questions s3 = new Questions("test SPORT3", "27", "25", "30", "20", "SPORT");
    Questions s4 = new Questions("test SPORT4", "35", "40", "30", "25", "SPORT");
    ArrayList<Questions> simonList = new ArrayList<>();

    Questions p1 = new Questions("test GAMING1", "37", "25", "43", "32", "GAMING");
    Questions p2 = new Questions("test GAMING2", "21", "18", "30", "25", "GAMING");
    Questions p3 = new Questions("test GAMING3", "27", "25", "30", "20", "GAMING");
    Questions p4 = new Questions("test GAMING4", "35", "40", "30", "25", "GAMING");
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

        categoryList.add(test);
        categoryList.add(simonList);
        categoryList.add(patrikList);

    }

    public boolean findCorrectAnswer(String s) {
        boolean temp = false;
        for (Questions q : test) {
            if (q.getCorrectAnswer().equalsIgnoreCase(s))
                temp=  true;
        }
        return temp;
    }


    public List chooseCategory(String catRecieved) {
        List<Questions> tempList = new ArrayList<>();
        int counter = 0;
        for (var v: categoryList) {
            if (v.get(counter).getCategoryName().equalsIgnoreCase(catRecieved)){
                tempList = v;
            }
        }
        return tempList;
    }

    public List<ArrayList<Questions>> getCategoryList() {
        return categoryList;
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

    public static String randomCategorys(){
        Random r = new Random();
        int temp = r.nextInt(6);
        if (temp == 1)
            return "JAVA";
        else if (temp==2)
            return "SPORT";
        else if (temp==3)
            return "GAMING";
        else if (temp==4)
            return "HUVUDSTÄDER";
        else if (temp==5)
            return "HISTORIA";
        else
        return"GEOGRAFI";
    }
}
