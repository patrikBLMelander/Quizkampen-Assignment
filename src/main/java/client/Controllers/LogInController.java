package client.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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
        String name = logInName.getText();
        s.setName(name);
        s.loadNewScreen(ScreenNavigator.MAIN_MENU, logInButton);
    }
}
