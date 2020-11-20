package client.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import server.Questions;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

public class GameViewController implements Initializable{
    ScreenNavigator s = new ScreenNavigator();
    ObjectInputStream in;
    ObjectOutputStream out;
    Socket connectToServer;
    ObservableList<Button> buttonList = FXCollections.observableArrayList();
    int pointCounter = 0;
    int roundsCounter = 0;
    int questionsCounter = 0;

    @FXML
    private AnchorPane screen4;

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
    void rButtonClicked(ActionEvent event) throws IOException, ClassNotFoundException {

        if (((Control) event.getSource()) == buttonList.get(0)) {
            pointCounter++;
            System.out.println("win");
            String points = pointCounter + "";
            System.out.println(points);
            out.writeObject("START"+points);

        } else {
            System.out.println("looser");
            String points = pointCounter + "";
            out.writeObject("START"+points);
        }

        updateGameWindow();
        //s.loadNewScreen(ScreenNavigator.GAME_OVERVIEW, rButton1);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pointCounter = 0;
        buttonList.addAll(rButton1, rButton2, rButton3, rButton4);

        try {
            in = ScreenNavigator.inputStreamer;
            out = ScreenNavigator.outputStreamer;

            out.writeObject("START");
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
            System.out.println("Fråga : " + ((Questions) temp).getQuestion());
            counterText.setText("Poäng: " + Integer.toString(pointCounter));
            questionText.setText(((Questions) temp).getQuestion());
            buttonList.get(0).setText(((Questions) temp).getCorrectAnswer());
            buttonList.get(1).setText(((Questions) temp).getWrongAnswer1());
            buttonList.get(2).setText(((Questions) temp).getWrongAnswer2());
            buttonList.get(3).setText(((Questions) temp).getWrongAnswer3());
        }else
            s.loadNewScreen(ScreenNavigator.GAME_OVERVIEW, rButton1);
    }
}

