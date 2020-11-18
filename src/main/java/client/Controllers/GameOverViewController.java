package client.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameOverViewController implements Initializable {
    ScreenNavigator s = new ScreenNavigator();

    @FXML
    private Text resultText;

    @FXML
    void playAgainBtnClicked(ActionEvent event) throws IOException, ClassNotFoundException {

    }

    @FXML
    void endGameBtnClicked(ActionEvent event) throws IOException, ClassNotFoundException {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resultText.setText("Information about scores of both players to be fetched and displayed here");
    }


}