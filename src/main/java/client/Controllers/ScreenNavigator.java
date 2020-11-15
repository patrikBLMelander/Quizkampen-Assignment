package client.Controllers;

import client.Controller;
import client.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Sara Carlsson
 * Date: 15/11/2020
 * Time:16:52
 * Project: Quizkampen1
 * Copywright: MIT
 */
public class ScreenNavigator {

    public void loadNewScreen(String fxml, Node node) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(fxml));
        Stage primaryStage = (Stage) node.getScene().getWindow();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
