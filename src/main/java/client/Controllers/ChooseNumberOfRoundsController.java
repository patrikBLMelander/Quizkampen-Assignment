package client.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Sara Carlsson
 * Date: 15/11/2020
 * Time:14:20
 * Project: Quizkampen1
 * Copywright: MIT
 */
public class ChooseNumberOfRoundsController implements Initializable {
    static int round;
    static int questions;

    public static void setRound(int round) {
        ChooseNumberOfRoundsController.round = round;
    }

    public static void setQuestions(int questions) {
        ChooseNumberOfRoundsController.questions = questions;
    }

    public int getRound() {
        return round;
    }

    public int getQuestions() {
        return questions;
    }

    ScreenNavigator s = new ScreenNavigator();
    ObjectOutputStream out;

    @FXML
    private AnchorPane screen2;

    @FXML
    private ChoiceBox<Integer> scrollRounds;

    @FXML
    private ChoiceBox<Integer> scrollQuestions;

    @FXML
    private Button startGameBtn;

    @FXML
    void startNewGameAction(ActionEvent event) throws IOException {
        out.writeObject("5");
        setRound(round = scrollRounds.getValue());;
        setQuestions(questions = scrollQuestions.getValue());
        s.loadNewScreen(ScreenNavigator.GAME_VIEW, startGameBtn);
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
