package client.Controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import server.Questions;
import server.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

public class GameOverViewController implements Initializable, Runnable {
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
            out.writeObject("RESULT");
            Object inputObject;
            while((inputObject = in.readObject())instanceof User) {
                User u = (User) inputObject;
                resultText.setText(u.getPoints()+ " - " + u.getOpponent().getPoints());
                Boolean [][] arrayPl1 = u.getResultArray();
                Boolean [][] arrayPl2 = u.getOpponent().getResultArray();

                for (int i = 0; i < arrayPl1.length; i++) {
                    for (int j = 0; j < arrayPl1.length; j++) {
                        Circle c = new Circle();
                        if (arrayPl1[i][j]) c.setStyle("-fx-background-color: greenyellow");
                        else if (!arrayPl1[i][j]) c.setStyle("-fx-background-color: red");
                        else c.setVisible(false);

                        hBoxPl1.getChildren().add(c);
                    }

                }
                for (int i = 0; i < arrayPl2.length; i++) {
                    for (int j = 0; j < arrayPl2.length; j++) {
                        Circle c = new Circle();
                        if (arrayPl2[i][j]) c.setStyle("-fx-background-color: greenyellow");
                        else if (!arrayPl2[i][j]) c.setStyle("-fx-background-color: red");
                        else c.setVisible(false);

                        hBoxpl2.getChildren().add(c);
                    }

                }
                    break;


                //if(s2 instanceof Boolean [][])

            }
            //thread.interrupt();

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
}