package client.Controllers;


import client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import server.User;

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
    private User user;
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
        s.loadNewScreen("ChooseNumersOfRoundsView.fxml", newGameBtn);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        helloText.setText("Hej " + name);

    }
}
