package client.Controllers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import server.User;

import java.io.IOException;

public class LogInController{

    @FXML
    private TextField logInName;

    @FXML
    private TextField logInPassword;

    @FXML
    private Button logInButton;

    @FXML
    void logInAction(ActionEvent event) throws Exception {
        User user = new User(logInName.getText());

        Stage stage = (Stage) logInName.getScene().getWindow();
        stage.close();

        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainMenuView.fxml"));
        Parent root = (Parent) loader.load();
        MainMenuController controller = loader.getController();
        controller.setUser(user);

        primaryStage.setTitle(user.getUserName());
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
