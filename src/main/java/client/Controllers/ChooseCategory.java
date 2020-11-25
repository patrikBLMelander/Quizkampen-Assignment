package client.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import server.Database;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class ChooseCategory implements Initializable {


        ObjectOutputStream out;
        ScreenNavigator s = new ScreenNavigator();
        Database database = new Database();


        @FXML
        private Button Cat1Btn;

        @FXML
        private Button Cat2Btn;

        @FXML
        private Button Cat3Btn;

        @FXML
        void categoryChosen(ActionEvent event) throws IOException {

                if  ((event.getSource()).equals(Cat1Btn)) {
                        System.out.println(Cat1Btn.getText());
                        String send = Cat1Btn.getText();
                        out.writeObject("CATEGORY" + send);
                        //out.writeObject("CATEGORY" + database.chooseCategory(0));
                }
                else if  ((event.getSource()).equals(Cat2Btn)) {
                        //System.out.println(Cat2Btn.getText());
                        out.writeObject("CATEGORY" + Cat2Btn.getText());
                        //out.writeObject("CATEGORY" + database.chooseCategory(1));

                }
                else if  ((event.getSource()).equals(Cat3Btn)) {
                        //System.out.println(Cat3Btn.getText());
                        out.writeObject("CATEGORY" + Cat3Btn.getText());
                        //out.writeObject("CATEGORY" + database.chooseCategory(2));
                }

                s.loadNewScreen(ScreenNavigator.WAITING, Cat1Btn);
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