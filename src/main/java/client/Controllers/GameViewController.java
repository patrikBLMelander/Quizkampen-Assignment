package client.Controllers;

import client.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import server.Questions;
import server.Response;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

public class GameViewController implements Initializable{
    ObjectInputStream in;
    ObjectOutputStream out;
    Socket connectToServer;
    ObservableList<RadioButton> buttonList = FXCollections.observableArrayList();

    @FXML
    private AnchorPane screen4;

    @FXML
    private Text questionText;

    @FXML
    private Button continueBtn;

    @FXML
    private RadioButton rButton1;

    @FXML
    private RadioButton rButton2;

    @FXML
    private RadioButton rButton3;

    @FXML
    private RadioButton rButton4;

    @FXML
    void rButtonClicked(ActionEvent event) {

        if (((Control)event.getSource()) == buttonList.get(0))
            System.out.println("win");
        else
            System.out.println("looser");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int pointCounter = 0;
        buttonList.addAll(rButton1, rButton2, rButton3, rButton4);
        Collections.shuffle(buttonList);

        try {
            connectToServer = new Socket("127.0.0.1", 55000);
            out = new ObjectOutputStream(connectToServer.getOutputStream());
            in = new ObjectInputStream(connectToServer.getInputStream());

            out.writeObject(ScreenNavigator.user.getUserName());
            Object temp;

            while ((temp = in.readObject()) != null) {
                if (temp instanceof Questions) {
                    System.out.println("Fråga : " + ((Questions) temp).getQuestion());
                    questionText.setText(((Questions) temp).getQuestion());
                    buttonList.get(0).setText(((Questions) temp).getCorrectAnswer());
                    buttonList.get(1).setText(((Questions) temp).getWrongAnswer1());
                    buttonList.get(2).setText(((Questions) temp).getWrongAnswer2());
                    buttonList.get(3).setText(((Questions) temp).getWrongAnswer3());
                    return;
                }
                else if (temp instanceof Response)
                    if(((Response) temp).getSuccess()){
                        System.out.println("Rätt svar");
                        pointCounter++;
                        String points = pointCounter + "";
                        out.writeObject(points);
                    }

                    else {
                        System.out.println("Fel svar");
                        String points = pointCounter + "";
                        out.writeObject(points);
                    }
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

