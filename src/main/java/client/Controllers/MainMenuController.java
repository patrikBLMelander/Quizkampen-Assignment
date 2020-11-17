package client.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Sara Carlsson
 * Date: 13/11/2020
 * Time:10:24
 * Project: Quizkampen1
 * Copywright: MIT
 */
public class MainMenuController implements Initializable {
    ScreenNavigator s = new ScreenNavigator();

    @FXML
    private Text helloText;

    @FXML
    private AnchorPane screen1;

    @FXML
    private Button newGameBtn;

    private String name;

    public void setName(String name){
        this.name = name;
    }

    @FXML
    void newGameViewAction(ActionEvent event) throws IOException {
        s.loadNewScreen(ScreenNavigator.NUMBER_OF_ROUNDS, newGameBtn);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        helloText.setText("Hej " + ScreenNavigator.user.getUserName());
    }
}
