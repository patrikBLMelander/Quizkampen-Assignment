package client.Controllers;

import client.Client;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.User;

import java.io.IOException;

public class ScreenNavigator {

    public static final String LOGIN = "LogInView.fxml";
    public static final String MAIN_MENU = "MainMenuView.fxml";
    public static final String NUMBER_OF_ROUNDS = "ChooseNumersOfRoundsView.fxml";
    public static final String SELECT_CATEGORY = "CatSelectorView.fxml";
    public static final String GAME_VIEW = "GameView.fxml";
    public static final String GAME_OVERVIEW = "GameOverView.fxml";

    public static User user;

    public static Client client;

    public void setUser(User user){
        this.user = user;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void loadNewScreen(String fxml, Node node) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(fxml));
        Stage primaryStage = (Stage) node.getScene().getWindow();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
