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
    CountDownLatch startGameCountDownLatch = new CountDownLatch(2);
    CountDownLatch endGameCountDownLatch = new CountDownLatch(2);

    static int userRoundCounter = 2;
    static int userQuestionCounter = 4;
    String category;
    List<Questions> listToSend = new ArrayList<>();

    public  Object processInput(String playerName , Object object){
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
            Collections.shuffle(listToSend);
            System.out.println(category);
            objectToSend = category;
        }
        else if(input.startsWith("WAITING")) {

            System.out.println(playerName + "Är i waiting for opponent");

            startGameCountDownLatch.countDown();
            try {
                startGameCountDownLatch.await();
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("Countdownlatch: " + startGameCountDownLatch.getCount());
            System.out.println(playerName + " är ur waitingloopen");

            objectToSend = "GO_TO_SEND_QUESTION";
            reset(startGameCountDownLatch);//sätter tillbaka countDownLatch till 2

        }
        else if(input.startsWith("NEW_QUESTION")){

            String answer = input.substring(12);
            for(User u : database.userList) {
                if (playerName.equals(u.getUserName())) {
                    if (answer.equals("true")) {
                        u.addPoints();
                        u.setResultArray(roundCounter, u.getCounter()-1, true);
                    }
                    else if (answer.equals("false"))
                        u.setResultArray(roundCounter, u.getCounter()-1, false);
                    else
                        System.out.println("Ingen fråga skickad än.");
                    objectToSend = playerQuestionCounter(u);
                    u.addCounter();
                }
            }
        }
        else if(input.startsWith("END_GAME_WAIT")) {

            System.out.println(playerName + "Är i end game waiting");

            endGameCountDownLatch.countDown();
            try {
                endGameCountDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Countdownlatch: " + endGameCountDownLatch.getCount());
            System.out.println(playerName + " är ur waitingloopen");

            objectToSend = ""; //TODO: här ska vi skicka något
            reset(endGameCountDownLatch);//sätter tillbaka countDownLatch till 2
        }
        else if(input.startsWith("RESULT")){
            for(User u : database.userList) {
                if (playerName.equals(u.getUserName()))
                    objectToSend = u;


            }
        }


        // TODO : Skapa en ny controller till waiting
        //TODO : Ta emot user i GameOverViewController

        else if(input.startsWith("START_NEXT_ROUND")) {
            System.out.println(playerName + " Är i ny runda");
            for(User u : database.userList) {

                u.resetCounter();
            }

            if (roundCounter<userRoundCounter){

                System.out.println(playerName + " Kommit förbi roundCounter");
                if (roundCounter% 2 == 0){
                    if(playerName.equals("Player 1")) {
                        objectToSend = "WAITING";

                    }
                    else if(playerName.equals("Player 2")){
                        System.out.println(playerName + " inne i if satsen som ska skicka CATEGORY");
                        objectToSend = "GO_TO_CHOOSE_CATEGORY";
                    }
                }
                else{
                    if(playerName.equals("Player 1")) {
                        objectToSend = "GO_TO_CHOOSE_CATEGORY";

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

    //public synchronized Object playerQuestionCounter (String s, int counter){
    public synchronized Object playerQuestionCounter (User u){
        Object o = null;

        if (u.getCounter() < userQuestionCounter) {
            o = listToSend.get(u.getCounter());
            System.out.println(u.getUserName() + " Är på fråga " + (u.getCounter()+1));
        } else if (roundCounter < userRoundCounter) {
            System.out.println("Poäng " + u.getUserName() + ": " + u.getPoints() +
                    "Poäng " + u.getOpponent().getUserName() + ": " + u.getOpponent().getPoints());
            for (int i = 0; i <=roundCounter; i++) {
                for (int j = 0; j <userQuestionCounter; j++) {
                    System.out.print(u.getResultArray()[i][j] + " ");
                }
                System.out.println();
            }

            o = "Final";
        }
        return o;
    }
    public void reset(CountDownLatch countDownLatch){
        countDownLatch = new CountDownLatch(2);
    }
}
