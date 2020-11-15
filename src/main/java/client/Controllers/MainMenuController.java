package client.Controllers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import server.User;

/**
 * Created by Sara Carlsson
 * Date: 13/11/2020
 * Time:10:24
 * Project: Quizkampen1
 * Copywright: MIT
 */
public class MainMenuController{

    private User user;

    @FXML
    private Button newGameBtn;

    public void setUser(User user){
        this.user = user;
    }

    public User getUser(){
        return user;
    }

    @FXML
    void startNewGameAction(ActionEvent event) throws Exception {
        if (event.getSource() == newGameBtn) {
            Stage stage = (Stage) newGameBtn.getScene().getWindow();
            stage.close();

            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("HowManyView.fxml"));
            Parent root = (Parent) loader.load();
            //MainMenuController controller = loader.getController();
            //controller.setUser(user);

            primaryStage.setTitle(user.getUserName());
            primaryStage.setScene(new Scene(root));
            primaryStage.show();

        }
    }
}
