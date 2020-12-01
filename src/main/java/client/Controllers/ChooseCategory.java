package client.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import server.Database;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;

import java.util.ResourceBundle;

public class ChooseCategory implements Initializable {


        ObjectOutputStream out;
        ScreenNavigator s = new ScreenNavigator();
        ObservableList<Button> buttonList = FXCollections.observableArrayList();


        @FXML
        private Button Cat1Btn;

        @FXML
        private Button Cat2Btn;

        @FXML
        private Button Cat3Btn;

        @FXML
        void categoryChosen(ActionEvent event) throws IOException {

                if  ((event.getSource()).equals(Cat1Btn)) {
                        String send = Cat1Btn.getText();
                        out.writeObject("CATEGORY" + send);
                }
                else if  ((event.getSource()).equals(Cat2Btn)) {
                        out.writeObject("CATEGORY" + Cat2Btn.getText());
                }
                else if  ((event.getSource()).equals(Cat3Btn)) {
                        out.writeObject("CATEGORY" + Cat3Btn.getText());
                }



                s.loadNewScreen(ScreenNavigator.WAITING, Cat1Btn);
        }



        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                buttonList.addAll(Cat1Btn, Cat2Btn, Cat3Btn);
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