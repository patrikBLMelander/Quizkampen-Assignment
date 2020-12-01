package client.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    ScreenNavigator s = new ScreenNavigator();
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Socket connectToServer;

    @FXML
    private Text helloText;

    @FXML
    private Button newGameBtn;

    @FXML
    void newGameViewAction(ActionEvent event) throws IOException, ClassNotFoundException {
        connectToServer = new Socket("127.0.0.1", 55000);
        out = new ObjectOutputStream(connectToServer.getOutputStream());
        in = new ObjectInputStream(connectToServer.getInputStream());
        s.setInputStreamer(in);
        s.setOutputStreamer(out);

        if (in.readObject().toString().equals("1")) {
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
