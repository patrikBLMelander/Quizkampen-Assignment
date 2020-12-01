package client.Controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class Waiting implements Initializable, Runnable{
    Thread thread = new Thread(this);
    ScreenNavigator s = new ScreenNavigator();
    ObjectInputStream input;
    ObjectOutputStream output;

    @FXML
    private ImageView image;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        input = ScreenNavigator.inputStreamer;
        output = ScreenNavigator.outputStreamer;
        thread.start();
    }

    @Override
    public void run() {

        try {
            output.writeObject("WAITING");
            String s2;
            while((s2 = input.readObject().toString())!=null) {
                if (s2.endsWith("QUESTION")) {
                    Platform.runLater(() -> {
                        try {
                            s.loadNewScreen(ScreenNavigator.GAME_VIEW, image);
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
