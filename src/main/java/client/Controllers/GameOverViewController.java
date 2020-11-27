package client.Controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import server.Questions;
import server.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.io.Serializable;
import java.util.ResourceBundle;

public class GameOverViewController implements Initializable, Runnable, Serializable{
    Thread thread = new Thread(this);
    ScreenNavigator s = new ScreenNavigator();
    ObjectInputStream in;
    ObjectOutputStream out;
    boolean isGameOver = false;

    @FXML
    Circle [][] circlesPl1 = new Circle[5][5];

    @FXML
    Circle [][] circlesPl2 = new Circle[5][5];

    @FXML
    public Button nextRoundBtn1;

    @FXML
    private Text resultText;

    @FXML
    private Button playAgainBtn;

    @FXML
    private Button endGameBtn;

    @FXML
    private HBox hBoxPl1;

    @FXML
    private Circle circle1;

    @FXML
    private Circle circle11;

    @FXML
    private Circle circle111;

    @FXML
    private Circle circle112;

    @FXML
    private Circle circle1121;

    @FXML
    private HBox hBoxpl2;


    @FXML
    void playAgainBtnClicked(ActionEvent event) throws IOException, ClassNotFoundException {
        s.loadNewScreen(ScreenNavigator.MAIN_MENU, playAgainBtn);
        
    }

    @FXML
    void endGameBtnClicked(ActionEvent event) throws IOException, ClassNotFoundException {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        out = ScreenNavigator.outputStreamer;
        in = ScreenNavigator.inputStreamer;

        Circle circle = new Circle();

        thread.start();
        //resultText.setText("Information about scores of both players to be fetched and displayed here");

        //try {
            //out.writeObject("GET_SCORE_DATA");
            String receivedData = "";
            //while (in.readObject() != null) {
            //    receivedData += in.readObject().toString();
            //}
            resultText.setText(receivedData);
            if(isGameOver) nextRoundBtn1.setVisible(false);
            else playAgainBtn.setVisible(false);
        //} //catch (IOException | ClassNotFoundException e) {
            //e.printStackTrace();
        //}

    }



    public void nextRoundBtnClicked(ActionEvent actionEvent) {
        try {
            out.writeObject("START_NEXT_ROUND");
            out.flush();

            updateGameWindow();
          
            } catch(IOException  | ClassNotFoundException e){
                e.printStackTrace();
            }

    }

    @Override
    public void run() {
        try {
            int counter = 0;
            out.writeObject("RESULT");
            Object inputObject;
            while((inputObject = in.readObject())!=null) {

                if(inputObject instanceof String){
                    if(inputObject.toString().startsWith("POINTS")){
                        int pointPlayer1 = Integer.parseInt(inputObject.toString().substring(6,7));
                        int pointPlayer2 = Integer.parseInt(inputObject.toString().substring(7));
                        resultText.setText(pointPlayer1+ " - " + pointPlayer2);
                        System.out.println(pointPlayer1+ " - " + pointPlayer2);
                        out.writeObject("PLAYER1");
                    }
                }

                else if (inputObject instanceof int [][]){
                    if (counter==0){
                        int [][] arrayPl1 = (int[][]) inputObject;
                        for (int i = 0; i < arrayPl1.length; i++) {
                            for (int j = 0; j < arrayPl1.length; j++) {
                                Circle c = new Circle();
                                System.out.println(arrayPl1[i][j]);
                                if (arrayPl1[i][j]==1) c.setFill(Color.GREENYELLOW);
                                else if (arrayPl1[i][j]==2) c.setFill(Color.RED);
                                else if(arrayPl1[i][j]==0) c.setVisible(false);
                                c.setRadius(12);
                                System.out.println("Player1" + c);
                                Platform.runLater(() -> hBoxPl1.getChildren().add(c));
                            }
                        }
                        out.writeObject("PLAYER2");
                        counter++;
                    }
                    else if(counter==1){
                        int [][] arrayPl2 = (int[][]) inputObject;
                        for (int i = 0; i < arrayPl2.length; i++) {
                            for (int j = 0; j < arrayPl2.length; j++) {
                                Circle c = new Circle();
                                if (arrayPl2[i][j]==1) c.setFill(Color.GREENYELLOW);
                                else if (arrayPl2[i][j]==2) c.setFill(Color.RED);
                                else if (arrayPl2[i][j]==0) c.setVisible(false);
                                c.setRadius(12);
                                System.out.println("Player2" + c);
                                Platform.runLater(() -> hBoxpl2.getChildren().add(c));

                            }
                        }
                        break;
                    }

                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateGameWindow() throws IOException, ClassNotFoundException {
        String temp;
        temp = in.readObject().toString();
        if (temp.equals("WAITING"))
            s.loadNewScreen(ScreenNavigator.WAITING, nextRoundBtn1);

        else if (temp.equals("GO_TO_CHOOSE_CATEGORY"))
            s.loadNewScreen(ScreenNavigator.SELECT_CATEGORY, nextRoundBtn1);

    }
    
  /*  public static int getCircle1Length()
    {
        int length = circlesPl1.length;
        length *= length;
        
        return length;
        
    }*/
}