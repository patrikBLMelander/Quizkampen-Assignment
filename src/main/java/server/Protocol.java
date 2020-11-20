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
    MultiWriter m = new MultiWriter();
    Database database = new Database();
    int p1counter = 0;
    int p2counter = 0;
    int roundCounter = 0;
    int waitingCounter = 0;

    static int userRoundCounter = 2;
    static int userQuestionCounter = 4;
    String category;


    public Object processInput(String s , Object object) throws InterruptedException {
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
            System.out.println(category);
            objectToSend = category;
        }
        else if(input.equals("Waiting")) {
            System.out.println(s + "Är i waiting for opponent");
            waitingCounter++;
            while (true) {
                if (waitingCounter == 2) {
                    break;
                }
            }
            System.out.println(s + "är ur waitingloopen");
            objectToSend = "GO_TO_SEND_QUESTION";

        }
        else if(input.startsWith("START")){

            if(s.equals("Player 1")) {
                System.out.println(s + "Är på fråga " + p1counter);
                objectToSend = database.test.get(p1counter);
                if (p1counter < userQuestionCounter) {
                    p1counter++;
                } else if (roundCounter < userRoundCounter) {
                    objectToSend = "Final";
                    roundCounter++;
                    p1counter = 0;
                }
            }
            if(s.equals("Player 2")){
                System.out.println(s + "Är på fråga " + p2counter);
                objectToSend = database.test.get(p2counter);
                if (p2counter < userQuestionCounter) {
                    p2counter++;
                } else if (roundCounter < userRoundCounter) {
                    objectToSend = "Final";
                    roundCounter++;
                    p2counter = 0;
                }
            }
        }
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
