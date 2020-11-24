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
                for(Button b : buttonList){
                        if(event.getSource().equals(b))
                                System.out.println(b.getText());
                                out.writeObject("CATEGORY" + b.getText());
                                break;
                }

                s.loadNewScreen(ScreenNavigator.WAITING, Cat1Btn);
        }



        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                int counter = 0;
                buttonList.addAll(Cat1Btn, Cat2Btn, Cat3Btn);
                out = ScreenNavigator.outputStreamer;
                String [] cat = {Database.randomCategorys(),Database.randomCategorys(),Database.randomCategorys()};

                while (true) {
                        if (cat[0].equals(cat[1]) || cat[2].equals(cat[1])){
                                cat[1] = Database.randomCategorys();
                        }
                        else if (cat[0].equals(cat[2])){
                                cat[2] = Database.randomCategorys();
                        }
                        else
                                break;
                }
                for(Button b : buttonList){
                        b.setText(cat[counter]);
                        counter++;
                }

        }
}