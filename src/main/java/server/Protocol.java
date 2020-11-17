package server;

public class Protocol {
    private static final int LOGIN = 0;
    private static final int MAIN_MENU = 1;
    private static final int WAITING_FOR_OPPONENT = 2;
    private static final int OVERVIEW = 3;
    private static final int CHOOSE_CATEGORY = 4;
    private static final int SEND_QUESTION = 5;
    private static final int CHECK_ANSWER = 6;
    private static final int FINAL_SCORE = 7;


    private int state = LOGIN;

    Database database = new Database();
    Response response = new Response(false);
    String databaseResponse;
    String fromClient;
    Object output;
    int counter = 0;
    int roundCounter = 0;
    int userRoundCounter = 2;
    int userQuestionCounter = 4;


    public Object processInput(Object object) {
        Object objectToSend = null;


        if (state == LOGIN) {
            objectToSend = "hej";
            System.out.println(object.toString());
            state = SEND_QUESTION;
        } else if (state == MAIN_MENU) {
            state = WAITING_FOR_OPPONENT;
        } else if (state == WAITING_FOR_OPPONENT) {
            state = OVERVIEW;
        } else if (state == OVERVIEW) {
            state = CHOOSE_CATEGORY;
        } else if (state == CHOOSE_CATEGORY) {
            objectToSend = database.chooseCategory(fromClient);
            state = SEND_QUESTION;
        } else if (state == SEND_QUESTION) {
            System.out.println("Är i SendQuestion");
            objectToSend = database.test.get(counter);
            state = CHECK_ANSWER;
        } else if (state == CHECK_ANSWER) {
            response.setSuccess(database.findCorrectAnswer(object.toString()));
            objectToSend = response;
            if (counter < userQuestionCounter) {
                state = SEND_QUESTION;
                counter++;
            } else if (roundCounter < userRoundCounter){
                state = OVERVIEW;
                roundCounter++;
                counter = 0;
            }
            else {
                state = FINAL_SCORE;
            }

        } else if (state == FINAL_SCORE) {
            state = MAIN_MENU;
        }






        return objectToSend;
    }
}
