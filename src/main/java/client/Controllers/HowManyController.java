package client.Controllers;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by Sara Carlsson
 * Date: 13/11/2020
 * Time:14:34
 * Project: Quizkampen1
 * Copywright: MIT
 */
public class HowManyController extends Application implements Initializable {

    Properties p = new Properties();
    int rounds;
    int questions;

    @FXML
    private Button newGameBtn;

    @FXML
    private ChoiceBox<Integer> scrollRounds;

    @FXML
    private ChoiceBox<Integer> scrollQuestions;

    @FXML
    void startNewGameAction(ActionEvent event) {
    }


    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        scrollRounds.getItems().addAll(2,3,4,5);
        scrollRounds.setValue(2);
        scrollQuestions.getItems().addAll(2,3,4,5);
        scrollQuestions.setValue(2);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("HowManyView.fxml"));
        primaryStage.setTitle("LOGIN");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
