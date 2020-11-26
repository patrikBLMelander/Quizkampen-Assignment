package client.Controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class GameOverViewController implements Initializable, Runnable {
    Thread thread = new Thread(this);
    ScreenNavigator s = new ScreenNavigator();
    ObjectInputStream in;
    ObjectOutputStream out;

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
        thread.start();
        //resultText.setText("Information about scores of both players to be fetched and displayed here");

    }


    public void nextRoundBtnClicked(ActionEvent actionEvent) {
        try {
            out.writeObject("START_NEXT_ROUND");

            Object temp;

            while ((temp = in.readObject()) != null) {
                if (temp.equals("WAITING")) {
                    s.loadNewScreen(ScreenNavigator.WAITING, nextRoundBtn1);
                } else if (temp.equals("CATEGORY")) {
                    s.loadNewScreen(ScreenNavigator.SELECT_CATEGORY, nextRoundBtn1);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        try {
            out.writeObject("RESULT");
            String s2;
            while((s2 = in.readObject().toString())!=null) {
                if (s2.startsWith("POINTS")) {
                    int pointsPl1 = Integer.parseInt(s2.substring(6,7));
                    int pointsPl2 = Integer.parseInt(s2.substring(7));
                    resultText.setText(pointsPl1 + " - " + pointsPl2);
                }
                //if(s2 instanceof Boolean [][])

            }
            thread.interrupt();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}