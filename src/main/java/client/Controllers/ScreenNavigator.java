package client.Controllers;

import client.Client;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ScreenNavigator {
    public static ObjectInputStream inputStreamer;
    public static ObjectOutputStream outputStreamer;
    public static Socket socket;
    public static String name;

    public static final String LOGIN = "LogInView.fxml";
    public static final String MAIN_MENU = "MainMenuView.fxml";
    public static final String NUMBER_OF_ROUNDS = "ChooseNumersOfRoundsView.fxml";
    public static final String SELECT_CATEGORY = "CatSelectorView.fxml";
    public static final String GAME_VIEW = "GameView.fxml";
    public static final String GAME_OVERVIEW = "GameOverView.fxml";

    public void setName(String name){
        this.name = name;
    }

    public ObjectInputStream getInputStreamer() {
        return inputStreamer;
    }

    public void setInputStreamer(ObjectInputStream in) {
            inputStreamer = in;
    }

    public ObjectOutputStream getOutputStreamer() {
        return outputStreamer;
    }

    public void setOutputStreamer(ObjectOutputStream out) {
        outputStreamer = out;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket connectToServer) {
        socket = connectToServer;
    }


    public void loadNewScreen(String fxml, Node node) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(fxml));
        Stage primaryStage = (Stage) node.getScene().getWindow();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
