package client.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
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
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Socket connectToServer;

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
    void newGameViewAction(ActionEvent event) throws IOException, ClassNotFoundException {
        connectToServer = new Socket("127.0.0.1", 55000);
        out = new ObjectOutputStream(connectToServer.getOutputStream());
        in = new ObjectInputStream(connectToServer.getInputStream());
        s.setInputStreamer(in);
        s.setOutputStreamer(out);

        if (in.readObject().toString().equals("1")) {
            System.out.println("Inne i 1st player");
            s.loadNewScreen(ScreenNavigator.NUMBER_OF_ROUNDS, newGameBtn);
        }
        else {
            s.loadNewScreen(ScreenNavigator.WAITING, newGameBtn);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        helloText.setText("Hej " + ScreenNavigator.name);
    }
}
