package client.Controllers;

import javafx.fxml.Initializable;

import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Patrik Melander
 * Date: 2020-11-19
 * Time: 16:02
 * Project: Quiskampen
 * Copyright: MIT
 */
public class Waiting implements Initializable {
    ScreenNavigator s = new ScreenNavigator();
    ObjectInputStream input;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        input = ScreenNavigator.inputStreamer;
        if (inp)
    }
}
