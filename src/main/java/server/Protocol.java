package server;

public class Protocol {
    private static final int LOGIN = 0;
    private static final int MAIN_MENU = 1;
    private static final int WAITING_FOR_OPPONENT = 2;
    private static final int SEND_QUESTION = 3;
    private static final int CHECK_ANSWER = 4;
    private static final int FINAL_SCORE = 5;


    private int state = SEND_QUESTION;

    Database database = new Database();
    Response response = new Response(false);
    String databaseResponse;
    String fromClient;
    Object output;
    int counter = 0;


    public Object processInput(Object object) {
        Object objectToSend = null;


        if (state == LOGIN) {
            System.out.println(object.toString());
            state = SEND_QUESTION;
        } else if (state == MAIN_MENU) {

        } else if (state == WAITING_FOR_OPPONENT) {

        } else if (state == SEND_QUESTION) {
            System.out.println("Är i Send_Question");
            objectToSend = database.test.get(counter);

            state = CHECK_ANSWER;
        } else if (state == CHECK_ANSWER) {
            boolean temp = database.findCorrectAnswer(object.toString());
            response.setSuccess(temp);

            objectToSend = response;
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
