package server;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

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
        else if(input.equals("Waiting")) {
            System.out.println(s + "Är i waiting for opponent");
            countDownLatch.countDown();
            try {
                countDownLatch.await();
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("Countdownlatch: " + countDownLatch.getCount());
            System.out.println(s + "är ur waitingloopen");
            objectToSend = "GO_TO_SEND_QUESTION";

        }
        else if(input.startsWith("START")){

            if(s.equals("Player 1")) {
                objectToSend = playerQuestionCounter(s, p1counter);
                p1counter++;
            }
            if(s.equals("Player 2")){
                objectToSend = playerQuestionCounter(s, p2counter);
                p2counter++;
            }
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
            roundCounter++;
        }
        return o;
    }
}
