package client.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class ChooseNumberOfRoundsController implements Initializable {

    ScreenNavigator s = new ScreenNavigator();
    ObjectOutputStream out;

    @FXML
    private ChoiceBox<Integer> scrollRounds;

    @FXML
    private ChoiceBox<Integer> scrollQuestions;

    @FXML
    private Button startGameBtn;

    @FXML
    void startNewGameAction(ActionEvent event) throws IOException {
        out.writeObject("ROUNDS"+ scrollRounds.getValue()+ "_"+scrollQuestions.getValue());
        s.loadNewScreen(ScreenNavigator.SELECT_CATEGORY, startGameBtn);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        out = ScreenNavigator.outputStreamer;
        scrollRounds.getItems().addAll(2,3,4,5);
        scrollRounds.setValue(2);
        scrollQuestions.getItems().addAll(2,3,4,5);
        scrollQuestions.setValue(2);
    }
}
