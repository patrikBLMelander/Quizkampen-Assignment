package client.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class GameOverViewController implements Initializable {

    ScreenNavigator s = new ScreenNavigator();
    ObjectInputStream in;
    ObjectOutputStream out;

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
        resultText.setText("Information about scores of both players to be fetched and displayed here");

        Object temp;
        try {
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


    public void nextRoundBtnClicked(ActionEvent actionEvent) {
        try {

            out.writeObject("START_NEXT_ROUND");



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}