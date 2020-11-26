package server;

import java.util.*;
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

    public  Object processInput(String playerName , Object object){
        String input = " ";

        if (object instanceof String){
            input = (String) object;
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
            Collections.shuffle(listToSend);
            System.out.println(category);
            objectToSend = category;
        }
        else if(input.startsWith("WAITING")) {
            System.out.println(playerName + "Är i waiting for opponent");
            countDownLatch.countDown();
            try {
                countDownLatch.await();
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("Countdownlatch: " + countDownLatch.getCount());
            System.out.println(playerName + " är ur waitingloopen");
            objectToSend = "GO_TO_SEND_QUESTION";
            reset();

        }
        else if(input.startsWith("NEW_QUESTION")){

            if(playerName.equals("Player 1")) {
                objectToSend = playerQuestionCounter(playerName, p1counter);
                p1counter++;
            }
            if(playerName.equals("Player 2")){
                objectToSend = playerQuestionCounter(playerName, p2counter);
                p2counter++;
            }
        }

        else if(input.startsWith("START_NEXT_ROUND")) {
            System.out.println(playerName + " Är i ny runda");

            if (roundCounter<userRoundCounter){

                System.out.println(playerName + " Kommit förbi roundCounter");
                if (roundCounter% 2 == 0){
                    if(playerName.equals("Player 1")) {
                        objectToSend = "WAITING";

                    }
                    else if(playerName.equals("Player 2")){
                        System.out.println(playerName + "");
                        objectToSend = "CATEGORY";
                    }
                }
                else{
                    if(playerName.equals("Player 1")) {
                        objectToSend = "CATEGORY";

                    }
                    else if(playerName.equals("Player 2")){
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
    public void reset(){
        countDownLatch = new CountDownLatch(2);
    }
}
