package server;

public class Protocol {
    private static final int WAITING_FOR_OPPONENT = 2;
    private static final int CHOOSE_ROUNDS_AND_QUESTION_LIMIT = 3;
    private static final int CHOOSE_CATEGORY = 4;
    private static final int SEND_QUESTION = 5;
    private static final int CHECK_ANSWER = 6;
    private static final int FINAL_SCORE = 7;


    private int state = SEND_QUESTION;

    Database database = new Database();
    int counter = 0;
    int roundCounter = 0;

    static int userRoundCounter = 2;
    static int userQuestionCounter = 4;


    public Object processInput(Object object) {
        Object objectToSend = null;


        if (state == WAITING_FOR_OPPONENT) {
            System.out.println("Waiting for opponent");
            if (object.equals("Player 1")) {
                objectToSend = "CHOOSE_ROUNDS_AND_QUESTION_LIMIT";
                state = CHOOSE_ROUNDS_AND_QUESTION_LIMIT;
            }
            else{
                objectToSend = "GO_DIRECT_TO_SEND_QUESTIONS";
                state = SEND_QUESTION;
            }
        } else if (state == CHOOSE_ROUNDS_AND_QUESTION_LIMIT) {
            System.out.println("Är i choose Rounds and question limit");
            objectToSend = "CHOOSE_CATEGORY";
            state = CHOOSE_CATEGORY;

        } else if (state == CHOOSE_CATEGORY) {
            System.out.println("Är i choose Category");
            objectToSend = "GO_DIRECT_TO_SEND_QUESTIONS";

            state = SEND_QUESTION;
        } else if (state == SEND_QUESTION) {
            System.out.println("Är i Send_Question");
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

        return objectToSend;
    }
    public static void setUserRoundCounter(int userRoundCounter) {
        Protocol.userRoundCounter = userRoundCounter;
    }

    public static void setUserQuestionCounter(int userQuestionCounter) {
        Protocol.userQuestionCounter = userQuestionCounter;
    }
}
