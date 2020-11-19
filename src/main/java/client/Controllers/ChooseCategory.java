package client.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import server.Database;
import server.Questions;

import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class ChooseCategory implements Initializable {


        ObjectOutputStream out;
        ScreenNavigator s = new ScreenNavigator();

        @FXML
        private AnchorPane screen1;

        @FXML
        private Button Cat1Btn;

        @FXML
        private Button Cat2Btn;

        @FXML
        private Button Cat3Btn;

        @FXML
        private Text ChooseCategoryBanner;

        @FXML
        void categoryChoosed(ActionEvent event) {

        }


        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                out = ScreenNavigator.outputStreamer;
                String cat1 = Database.randomCategorys();
                String cat2 = Database.randomCategorys();
                String cat3 = Database.randomCategorys();
                while (true) {
                        if (cat1.equals(cat2) || cat3.equals(cat2)){
                                cat2 = Database.randomCategorys();
                        }
                        else if (cat1.equals(cat3)){
                                cat3 = Database.randomCategorys();
                        }
                        else
                                break;
                }
                Cat1Btn.setText(cat1);
                Cat2Btn.setText(cat2);
                Cat3Btn.setText(cat3);
        }
}