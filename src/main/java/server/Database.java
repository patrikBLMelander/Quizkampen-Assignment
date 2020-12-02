package server;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Database implements Serializable {

    List<User> userList = new ArrayList<>();
    List<ArrayList<Questions>> categoryList = new ArrayList<>();


    public Database() {

        deSerialize();

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
        int temp = r.nextInt(10);
        if (temp == 1)
            return "KROPP OCH KNOPP";
        else if (temp==2)
            return "MAT OCH DRYCK";
        else if (temp==3)
            return "KÄNDISAR";
        else if (temp==4)
            return "GEOGRAFI";
        else if (temp==5)
            return "HISTORIA";
        else if (temp==6)
            return "DJUR OCH NATUR";
        else if (temp==7)
            return "MUSIK";
        else if (temp==8)
            return "FILM OCH SERIER";
        else if (temp==9)
            return "KONST OCH KULTUR";
        else
        return "SPRÅK OCH TEXT";
    }
    public void serialize() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("category.ser"));
            out.writeObject(categoryList);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void deSerialize() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("category.ser"));
            categoryList = (List<ArrayList<Questions>>) in.readObject();
            in.close();
            for (var c:categoryList) {
                System.out.println(c.get(0).getQuestion());
            }
        } catch (Exception e) {
            System.out.println("category list not found");
        }
    }
}


