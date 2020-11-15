package client.Controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import server.User;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Sara Carlsson
 * Date: 13/11/2020
 * Time:10:24
 * Project: Quizkampen1
 * Copywright: MIT
 */
public class MainMenuController implements Initializable {
    private User user;

    //Screen 1 - Start-Meny
    @FXML
    private Text helloText;

    @FXML
    private AnchorPane screen1;

    @FXML
    private Button newGameBtn;

    @FXML
    void newGameViewAction(ActionEvent event) {
            screen1.setVisible(false);
            screen2.setVisible(true);
    }

    //Screen 2 "NYTT SPEL"

    @FXML
    private AnchorPane screen2;

    @FXML
    private ChoiceBox<Integer> scrollRounds;

    @FXML
    private ChoiceBox<Integer> scrollQuestions;

    @FXML
    private Button startGameBtn;

    @FXML
    void startNewGameAction(ActionEvent event) {
        screen2.setVisible(false);
        //screen3.setVisible(true);
        screen4.setVisible(true);

    }

    //Screen 3 -Välj kategori
    /*
    @FXML
    private AnchorPane screen3;

    @FXML
    private DropdownButton dropDownCat;

    @FXML
    private MenuItem cat1;

    @FXML
    private MenuItem cat2;

    @FXML
    private MenuItem cat3;

    @FXML
    private Button letsGoBtn;

    @FXML
    void letsGoAction(ActionEvent event) {

    }

     */

    //Screen 4  "SpelVy"

    @FXML
    private AnchorPane screen4;

    @FXML
    private Text questionText;

    @FXML
    private Button continueBtn;

    @FXML
    private RadioButton rButton1;

    @FXML
    private RadioButton rButton2;

    @FXML
    private RadioButton rButton3;

    @FXML
    private RadioButton rButton4;



    public void setUser(User user){
        this.user = user;
    }

    public User getUser(){
        return user;
    }

    /*@FXML
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

     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //helloText.setText("Hej " + user.getUserName());
        scrollRounds.getItems().addAll(2,3,4,5);
        scrollRounds.setValue(2);
        scrollQuestions.getItems().addAll(2,3,4,5);
        scrollQuestions.setValue(2);
    }
}
