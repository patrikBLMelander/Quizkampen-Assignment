package server;

public class Protocol {
    private static final int LOGIN = 0;
    private static final int MAIN_MENU = 1;
    private static final int WAITING_FOR_OPPONENT = 2;
    private static final int SEND_QUESTION = 3;
    private static final int ANSWER = 4;
    private static final int FINAL_SCORE = 5;


    private int state = SEND_QUESTION;

    Database database = new Database();
    String databaseResponse;
    String fromClient;
    Object output;
    int counter = 0;


    public Object processInput(Object object) {
        Object objectToSend = null;


        if (state == LOGIN) {
            //Hemskärm?
        } else if (state == MAIN_MENU) {

        } else if (state == WAITING_FOR_OPPONENT) {

        } else if (state == SEND_QUESTION) {

            objectToSend = database.test.indexOf(counter);

            state = ANSWER;
        } else if (state == ANSWER) {

            if (counter < 4) {
                state = SEND_QUESTION;
                counter++;
            } else
                state = FINAL_SCORE;

        } else if (state == FINAL_SCORE) {

        }






        return objectToSend;
    }
}
