package server;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import java.util.ArrayList;
import java.util.List;

public class Protocol {
    Database database = new Database();
    int p1counter = 0;
    int p2counter = 0;
    int roundCounter = 0;
    CountDownLatch countDownLatch = new CountDownLatch(2);

    static int userRoundCounter = 2;
    static int userQuestionCounter = 4;
    String category;
    List<Questions> listToSend = new ArrayList<>();

    public  Object processInput(String s , Object object){
        String input = " ";

        if (object instanceof String){
            input = (String) object;
        }
        else if (object instanceof User) {
            User u = (User) object;
            System.out.println(u.getUserName());
            database.userList.add(u);
        }
        else
            System.out.println("error");

        Object objectToSend = null;

        if (input.startsWith("ROUNDS")) {
            userRoundCounter = Integer.parseInt(input.substring(6, 7));
            System.out.println("Antalet rundor: " + userRoundCounter);

            userQuestionCounter = Integer.parseInt(input.substring(8));
            System.out.println("Antalet frågor: " + userQuestionCounter);
            objectToSend = "GO_TO_CHOOSE_CATEGORY";
        }
        else if (input.startsWith("CATEGORY")){
            category = input.substring(8);
            listToSend = database.chooseCategory(category);
            System.out.println(category);
            objectToSend = category;
        }
        else if(input.startsWith("WAITING")) {
            System.out.println(s + " Är i waiting for opponent");
            countDownLatch.countDown();
            try {
                countDownLatch.await();
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("Countdownlatch: " + countDownLatch.getCount());
            System.out.println(s + " är ur waitingloopen");
            objectToSend = "GO_TO_SEND_QUESTION";

        }
        else if(input.startsWith("NEW_QUESTION")){
            int points = Integer.parseInt(input.substring(12));
            for(User u : database.userList) {
                if (s.equals(u.getUserName()))
                    u.setPoints(points);
            }

            if(s.equals("Player 1")) {
                objectToSend = playerQuestionCounter(s, p1counter);
                p1counter++;
            }
            if(s.equals("Player 2")){
                objectToSend = playerQuestionCounter(s, p2counter);
                p2counter++;
            }
        }

        else if(input.startsWith("START_NEXT_ROUND")) {
            System.out.println(s + " Är i ny runda");

            if (roundCounter<userRoundCounter){

                System.out.println(s + " Kommit förbi roundCounter");
                if (roundCounter% 2 == 0){
                    if(s.equals("Player 1")) {
                        objectToSend = "WAITING";
                        roundCounter++;
                    }
                    else if(s.equals("Player 2")){
                        objectToSend = "CATEGORY";
                    }
                }
                else{
                    if(s.equals("Player 1")) {
                        objectToSend = "CATEGORY";
                        roundCounter++;
                    }
                    else if(s.equals("Player 2")){
                        objectToSend = "WAITING";
                    }
                }
            }
            else
                objectToSend = "END_OF_GAME";
        }
        return objectToSend;
    }

    public synchronized Object playerQuestionCounter (String s, int counter){
        Object o = null;

        if (counter < userQuestionCounter) {
            o = listToSend.get(counter);
            System.out.println(s + "Är på fråga " + (counter+1));
        } else if (roundCounter < userRoundCounter) {
            o = "Final";
        }
        return o;
    }
}
