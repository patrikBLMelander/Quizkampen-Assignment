package server;

import java.util.*;
import java.util.concurrent.CountDownLatch;

import java.util.ArrayList;
import java.util.List;

public class Protocol {
    Database database = new Database();
    int roundCounter = 0;
    Boolean lastRound = false;
    CountDownLatch countDownLatch = new CountDownLatch(2);


    static int userRoundCounter = 2;
    static int userQuestionCounter = 4;
    String category;
    List<Questions> listToSend = new ArrayList<>();

    public  Object processInput(String playerName , Object object){
        String input = " ";

        if (object instanceof String){
            input = (String) object;
        } else if (object instanceof User) {
            User u = (User) object;
            database.addUser(u);
        } else System.out.println("Felaktigt objekt skickat");

        Object objectToSend = null;

        if (input.startsWith("ROUNDS")) {
            userRoundCounter = Integer.parseInt(input.substring(6, 7));
            userQuestionCounter = Integer.parseInt(input.substring(8));
            objectToSend = "GO_TO_CHOOSE_CATEGORY";
        }

        else if(input.startsWith("GET_3")){
            String category1 = Database.randomCategorys();
            String category2 = Database.randomCategorys();
            String category3 = Database.randomCategorys();

            while (true) {
                if (category1.equals(category2) || category3.equals(category2)){
                    category2 = Database.randomCategorys();
                }
                else if (category1.equals(category3)){
                    category3 = Database.randomCategorys();
                }
                else
                    break;
            }
            List<String> categorys = new ArrayList<>();
            categorys.add(category1);
            categorys.add(category2);
            categorys.add(category3);
            objectToSend = categorys;
        }

        else if (input.startsWith("CATEGORY")){
            category = input.substring(8);
            listToSend = database.chooseCategory(category);
            Collections.shuffle(listToSend);
            objectToSend = category;
        }

        else if(input.startsWith("WAITING")) {

            countDownLatch.countDown();
            try {
                countDownLatch.await();
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
            objectToSend = "GO_TO_SEND_QUESTION";
            reset();//sätter tillbaka countDownLatch till 2
        }

        else if(input.startsWith("NEW_QUESTION")){
            String answer = input.substring(12);
            User u = findCorrectUser(playerName);
            if (answer.equals("true")) {
                u.addPoints();
                u.setResultArray(roundCounter, u.getCounter()-1, 1);
            } else if (answer.equals("false"))
                u.setResultArray(roundCounter, u.getCounter()-1, 2);

            objectToSend = playerQuestionCounter(u);
            u.addCounter();
        }

        else if(input.startsWith("END_GAME_WAIT")) {
            countDownLatch.countDown();
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            objectToSend = "SHOW_OVERVIEW";
            reset();
            if(playerName.equals("Player 1"))
                roundCounter++;
            if(roundCounter==userRoundCounter)
                lastRound = true;
        }

        else if(input.startsWith("IS_GAME_OVER")){
            Boolean temp = lastRound; // Denna behövs för att man inte ska skicka "samma objekt" varje gång
            objectToSend = temp;
        }

        else if(input.startsWith("RESULT")){
            User u = findCorrectUser(playerName);
            objectToSend = "POINTS" + u.getPoints() + u.getOpponent().getPoints() + roundCounter;
        }

        else if (input.startsWith("PLAYER1")){
            User u = findCorrectUser(playerName);
            int [][] temp = u.getResultArray();// Denna behövs för att man inte ska skicka "samma objekt" varje gång
            objectToSend = temp;
        }

        else if (input.startsWith("PLAYER2")) {
            User u = findCorrectUser(playerName);
            int[][] temp = u.getOpponent().getResultArray();// Denna behövs för att man inte ska skicka "samma objekt" varje gång
            objectToSend = temp;
        }

        else if(input.startsWith("START_NEXT_ROUND")) {
            for(User u : database.userList) {
                u.resetCounter();
            }
            if (roundCounter<userRoundCounter){

                if (roundCounter% 2 != 0){
                    if(playerName.equals("Player 1")) {
                        objectToSend = "WAITING";
                    }
                    else if(playerName.equals("Player 2")){
                        objectToSend = "START_NEW_ROUND";
                    }
                }
                else{
                    if(playerName.equals("Player 1")) {

                        objectToSend = "START_NEW_ROUND";
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
    public synchronized Object playerQuestionCounter (User u){
        Object o = null;

        if (u.getCounter() < userQuestionCounter)
            o = listToSend.get(u.getCounter());
        else if (roundCounter < userRoundCounter)
            o = "FINAL";
        return o;
    }

    public void reset(){
        countDownLatch = new CountDownLatch(2);
    }

    public User findCorrectUser(String name) {
        User user = null;
        for (User u : database.userList) {
            if (name.equals(u.getUserName())){
                user = u;
                break;
            }
        }
        return user;
    }
}
