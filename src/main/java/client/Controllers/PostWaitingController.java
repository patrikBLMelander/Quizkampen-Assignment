package client.Controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class PostWaitingController implements Initializable, Runnable {
    Thread thread = new Thread(this);
    ScreenNavigator s = new ScreenNavigator();
    ObjectInputStream input;
    ObjectOutputStream output;

    @FXML
    private ImageView image;

    @FXML
    private Button giveUpBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        input = ScreenNavigator.inputStreamer;
        output = ScreenNavigator.outputStreamer;
        System.out.println("Waiting");
        thread.start();
    }

    @Override
    public void run() {

        try {
            output.writeObject("END_GAME_WAIT");
            String s2;
            while((s2 = input.readObject().toString())!=null) {
                System.out.println(s2);
                if (s2.equals("SHOW_OVERVIEW")) {
                    System.out.println("Båda i waiting");
                    Platform.runLater(() -> {
                        try {
                            s.loadNewScreen(ScreenNavigator.GAME_OVERVIEW, image);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                    break;
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}