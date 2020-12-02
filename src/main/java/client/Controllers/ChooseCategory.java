package client.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;

import java.util.ArrayList;
import java.util.List;
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
                List<String> categories = new ArrayList<>();

                int counter = 0;
                try {
                        out.writeObject("GET_3_CATEGORIES");
                        Object object = in.readObject();
                        System.out.println(object);
                        if (object.equals("GO_TO_CHOOSE_CATEGORY")) {
                                out.writeObject("GET_3_CATEGORIES");
                                object = in.readObject();
                        }
                        System.out.println(object);
                        categories = (ArrayList<String>)object;

                } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                }
                for (var category : categorys) {
                        buttonList.get(counter).setText(category);

                        counter++;
                }
        }
}