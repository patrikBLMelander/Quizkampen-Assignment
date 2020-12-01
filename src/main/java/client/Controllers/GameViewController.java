package client.Controllers;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import server.Questions;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

public class GameViewController implements Initializable{
    ScreenNavigator s = new ScreenNavigator();
    ObjectInputStream in;
    ObjectOutputStream out;
    ObservableList<Button> buttonList = FXCollections.observableArrayList();
    Circle [] circleArray = new Circle[5];
    int pointCounter = 0;
    int counter = 0;

    @FXML
    private Text counterText;

    @FXML
    private Text questionText;

    @FXML
    private Button rButton1;

    @FXML
    private Button rButton2;

    @FXML
    private Button rButton3;

    @FXML
    private Button rButton4;

    @FXML
    private Circle circle1;

    @FXML
    private Circle circle2;

    @FXML
    private Circle circle3;

    @FXML
    private Circle circle4;

    @FXML
    private Circle circle5;


    @FXML
    void rButtonClicked(ActionEvent event){
        Button correctButton = null;
        try {

            if (((Control) event.getSource()) == buttonList.get(0)) {
                pointCounter++;
                ((Button) event.getSource()).setStyle("-fx-background-color: greenyellow");
                circleArray[counter].setFill(Color.YELLOWGREEN);
                out.writeObject("NEW_QUESTION"+"true");
                out.flush();
            } else {
                correctButton = correctButton();
                ((Button) event.getSource()).setStyle("-fx-background-color: red");
                correctButton.setStyle("-fx-background-color: greenyellow");
                circleArray[counter].setFill(Color.RED);
                out.writeObject("NEW_QUESTION"+"false");
                out.flush();
            }
            circleArray[counter].setVisible(true);
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            Button finalCorrectButton = correctButton;
            pause.setOnFinished(e -> {
                ((Button) event.getSource()).setStyle("-fx-background-color: green");
                if(finalCorrectButton!=null)
                    finalCorrectButton.setStyle("-fx-background-color: green");
                counter++;
                try {
                    updateGameWindow();
                } catch (IOException | ClassNotFoundException ioException) {
                    ioException.printStackTrace();
                }
            });
            pause.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pointCounter = 0;
        circleArray = new Circle[]{circle1, circle2, circle3, circle4, circle5};
        buttonList.addAll(rButton1, rButton2, rButton3, rButton4);

        try {
            in = ScreenNavigator.inputStreamer;
            out = ScreenNavigator.outputStreamer;

            out.writeObject("NEW_QUESTION" + "0");
            out.flush();
            updateGameWindow();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateGameWindow() throws IOException, ClassNotFoundException {
        Object temp;
        temp = in.readObject();
        Collections.shuffle(buttonList);
        if (temp instanceof Questions) {
            counterText.setText("Poäng: " + Integer.toString(pointCounter));
            questionText.setText(((Questions) temp).getQuestion());
            buttonList.get(0).setText(((Questions) temp).getCorrectAnswer());
            buttonList.get(1).setText(((Questions) temp).getWrongAnswer1());
            buttonList.get(2).setText(((Questions) temp).getWrongAnswer2());
            buttonList.get(3).setText(((Questions) temp).getWrongAnswer3());
        }else
            s.loadNewScreen(ScreenNavigator.POST_WAITING, rButton1);
    }
    
    
    public void rButtonGiveUp(ActionEvent event) {
        pointCounter = 0;
        int laps = 25;
        String giveUpColor = "-fx-background-color: #656565";
        
        for(int i = 0; i < laps; i++)
        {
            ((Button) event.getSource()).setStyle(giveUpColor);
            circleArray[counter].setFill(Color.GRAY);
        }
    }

    public Button correctButton(){
        Button temp = null;
        for (Button b: buttonList){
            if(b==buttonList.get(0)) {
                temp = b;
                break;
            }
        }
        return temp;
    }
}

