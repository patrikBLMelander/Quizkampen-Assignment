package server;

public class Protocol {

    MultiWriter m = new MultiWriter();
    Database database = new Database();
    int p1counter = 0;
    int p2counter = 0;
    int roundCounter = 0;
    int waitingCounter = 0;

    static int userRoundCounter = 2;
    static int userQuestionCounter = 4;
    String category;


    public Object processInput(String s , Object object) {
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
    public Object playerQuestionCounter (String s, int counter){
        Object o;
        System.out.println(s + "Är på fråga " + p2counter+1);
        o = database.test.get(p2counter);
        if (counter < userQuestionCounter) {
        } else if (roundCounter < userRoundCounter) {
            o = "Final";
            roundCounter++;
        }
        return o;
    }
}
