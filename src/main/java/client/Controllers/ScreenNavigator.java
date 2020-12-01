package client.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ScreenNavigator {
    public static ObjectInputStream inputStreamer;
    public static ObjectOutputStream outputStreamer;
    public static String name;

    public static final String LOGIN = "LogInView.fxml";
    public static final String MAIN_MENU = "MainMenuView.fxml";
    public static final String NUMBER_OF_ROUNDS = "ChooseNumersOfRoundsView.fxml";
    public static final String SELECT_CATEGORY = "CatSelectorView.fxml";
    public static final String GAME_VIEW = "GameView.fxml";
    public static final String GAME_OVERVIEW = "GameOverView.fxml";
    public static final String WAITING = "waiting.fxml";
    public static final String POST_WAITING = "PostWaitingView.fxml";


    public void setName(String name){
        this.name = name;
    }

    public void setInputStreamer(ObjectInputStream in) {
            inputStreamer = in;
    }

    public void setOutputStreamer(ObjectOutputStream out) {
        outputStreamer = out;
    }

    public void loadNewScreen(String fxml, Node node) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(fxml));
        Stage primaryStage = (Stage) node.getScene().getWindow();
        primaryStage.setTitle("Quizkampen");
        primaryStage.setScene(new Scene(root, 640, 480));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
