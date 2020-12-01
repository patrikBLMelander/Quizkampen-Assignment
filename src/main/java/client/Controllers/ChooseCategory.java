package client.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import server.Database;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChooseCategory implements Initializable {

        ObjectOutputStream out;
        ObjectInputStream in;
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

                for(Button b : buttonList) {
                        if ((event.getSource()).equals(b)) {
                                String send = b.getText();
                                out.writeObject("CATEGORY" + send);
                                break;
                        }
                }
                s.loadNewScreen(ScreenNavigator.WAITING, Cat1Btn);
        }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                buttonList.addAll(Cat1Btn, Cat2Btn, Cat3Btn);
                out = ScreenNavigator.outputStreamer;
                in = ScreenNavigator.inputStreamer;
                ArrayList<String> categorys = new ArrayList<>();
                int counter = 0;
                try {
                        out.writeObject("GET_3_CATEGORIES");
                        if(in.readObject()!=null) {
                                categorys = (ArrayList<String>) in.readObject();
                                for (var category : categorys) {
                                        buttonList.get(counter).setText(category);
                                        counter++;
                                }
                        }
                } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                }

        }
}