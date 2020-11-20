package server;

public class Protocol {
    private static final int STARTING_NEW_GAME = 1;
    private static final int CHOOSE_ROUNDS_AND_QUESTION_LIMIT = 2;
    private static final int CHOOSE_CATEGORY = 3;
    private static final int WAITING_FOR_OPPONENT = 4;
    private static final int SEND_QUESTION = 5;
    private static final int CHECK_ANSWER = 6;
    private static final int FINAL_SCORE = 7;


    //private int state = SEND_QUESTION;

    Database database = new Database();
    int counter = 0;
    int roundCounter = 0;
    int waitingCounter = 0;

    static int userRoundCounter = 2;
    static int userQuestionCounter = 4;


    public Object processInput(String s , Object object) throws InterruptedException {
        String input = " ";
        if (object instanceof Integer) {
            //state = (Integer) object;
        }
        else if (object instanceof String){
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

        else if(input.equals("Waiting")) {
            System.out.println(s + "Är i waiting for oponent");
            waitingCounter++;
            while (true) {
                if (waitingCounter == 2) {
                    break;
                }
            }
            System.out.println(s + "är ur waitingloopen");
            objectToSend = "GO_TO_SEND_QUESTION";

            //state = SEND_QUESTION;
            //waitingCounter = 0;
            //state = WAITING_FOR_OPPONENT;
        }

        else if(input.equals("HEJ"))
            objectToSend = "GO_TO_SEND_QUESTION";
        /*
        if (state == STARTING_NEW_GAME) {
            System.out.println(s + "Är i Starting new game");
            objectToSend = "GO_TO_WAITING";
            state = WAITING_FOR_OPPONENT;
        }

        else if (state == CHOOSE_ROUNDS_AND_QUESTION_LIMIT) {
            System.out.println(s + "Är i choose Rounds and question limit");
            /*
            userRoundCounter = Integer.parseInt(input.substring(7, 8));
            System.out.println("Antalet rundor: " + userRoundCounter);

            userQuestionCounter = Integer.parseInt(input.substring(9));
            System.out.println("Antalet frågor: " + userQuestionCounter);


            objectToSend = "GO_TO_CHOOSE_CATEGORY";
            state = CHOOSE_CATEGORY;


            //state=WAITING_FOR_OPPONENT;

        }
        else if (state == CHOOSE_CATEGORY) {
            System.out.println(s + "Är i choose Category");
            objectToSend = "GO_TO_WAITING_FOR_OPPONENT";

            state = WAITING_FOR_OPPONENT;
        }
       /* else if (state == WAITING_FOR_OPPONENT) {
            System.out.println(s + "Är i waiting for oponent");
            waitingCounter++;
            while(true) {
                if (waitingCounter == 2) {
                    break;
                }
            }
            objectToSend = "GO_TO_SEND_QUESTION";
            state = SEND_QUESTION;
            waitingCounter = 0;

        }

        */
       /* else if (state == SEND_QUESTION) {
            System.out.println(s + "Är i Send_Question");
            objectToSend = database.test.get(counter);

            if (counter < userQuestionCounter) {
                counter++;
            } else if (roundCounter < userRoundCounter){
                state = FINAL_SCORE;
                roundCounter++;
                counter = 0;
            }

        } else if (state == CHECK_ANSWER) {
            boolean temp = database.findCorrectAnswer(object.toString());
            System.out.println(temp);
            System.out.println(counter);

            objectToSend = new Response(temp);
            if (counter < userQuestionCounter) {
                state = SEND_QUESTION;
                counter++;
            } else if (roundCounter < userRoundCounter){
                state = WAITING_FOR_OPPONENT;
                roundCounter++;
                counter = 0;
            }
            else {
                state = FINAL_SCORE;
            }


        } else if (state == FINAL_SCORE) {

        }

        */

        return objectToSend;
    }

    private int getWaitingCounter() {
        return this.waitingCounter;
    }

    public static void setUserRoundCounter(int userRoundCounter) {
        Protocol.userRoundCounter = userRoundCounter;
    }

    public static void setUserQuestionCounter(int userQuestionCounter) {
        Protocol.userQuestionCounter = userQuestionCounter;
    }
}
