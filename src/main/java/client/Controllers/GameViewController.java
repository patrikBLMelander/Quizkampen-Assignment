package client.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

public class GameViewController implements Initializable {

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

    ObservableList<RadioButton> buttonList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttonList.addAll(rButton1, rButton2, rButton3, rButton4);
        Collections.shuffle(buttonList);
        for(RadioButton r : buttonList){
            System.out.println(r.getText());
        }
    }
}

