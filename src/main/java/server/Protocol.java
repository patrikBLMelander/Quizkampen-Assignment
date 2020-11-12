package server;

public class Protocol {
    private static final int INTRO = 0;
    private static final int WAITING_FOR_GAME = 1;

    private int state = INTRO;

    Database database = new Database();
    String databaseResponse;
    String fromClient;
    Object output;


    public Object processInput (Object object){
        Object objectToSend = null;
        if (state == INTRO){
            //Hemskärm?
        }
        else if (state==WAITING_FOR_GAME){

        }




        return objectToSend;
    }
}
