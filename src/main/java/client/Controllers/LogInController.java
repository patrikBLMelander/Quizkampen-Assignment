package client.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import server.User;

import java.net.URL;
import java.util.ResourceBundle;

public class LogInController{
    ScreenNavigator s = new ScreenNavigator() ;

    @FXML
    private AnchorPane logInPane;

    @FXML
    private TextField logInName;

    @FXML
    private TextField logInPassword;

    @FXML
    private Button logInButton;

    @FXML
    void logInAction(ActionEvent event) throws Exception {
        User u = new User(logInName.getText());
        s.setUser(u);
        s.loadNewScreen(ScreenNavigator.MAIN_MENU, logInButton);
    }
}
