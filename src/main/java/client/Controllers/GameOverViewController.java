package client.Controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.io.Serializable;
import java.util.ResourceBundle;

public class GameOverViewController implements Initializable, Runnable, Serializable{
    Thread thread = new Thread(this);
    ScreenNavigator s = new ScreenNavigator();
    ObjectInputStream in;
    ObjectOutputStream out;
    ObservableList <Text> textList = FXCollections.observableArrayList();

    @FXML
    public Button nextRoundBtn1;

    @FXML
    private Text resultText;

    @FXML
    private Button playAgainBtn;

    @FXML
    private Button endGameBtn;

    @FXML
    private FlowPane hBoxPl1;

    @FXML
    private FlowPane hBoxpl2;

    @FXML
    private FlowPane roundsTextBox;



    @FXML
    void playAgainBtnClicked(ActionEvent event) throws IOException, ClassNotFoundException {
        s.loadNewScreen(ScreenNavigator.MAIN_MENU, playAgainBtn);
        
    }

    @FXML
    void endGameBtnClicked(ActionEvent event) throws IOException, ClassNotFoundException {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        out = ScreenNavigator.outputStreamer;
        in = ScreenNavigator.inputStreamer;

        Circle circle = new Circle();

        playAgainBtn.managedProperty().bind(playAgainBtn.visibleProperty());
        nextRoundBtn1.managedProperty().bind(nextRoundBtn1.visibleProperty());

        thread.start();

    }



    public void nextRoundBtnClicked(ActionEvent actionEvent) {
        try {
            out.writeObject("START_NEXT_ROUND");
            out.flush();

            updateGameWindow();
          
            } catch(IOException  | ClassNotFoundException e){
                e.printStackTrace();
            }
    }

    @Override
    public void run() {
        try {
            int counter = 0;
            int rounds = 0;
            out.writeObject("IS_GAME_OVER");
            Object inputObject;
            while((inputObject = in.readObject())!=null) {

                if(inputObject instanceof Boolean) {
                    if ((Boolean)inputObject) nextRoundBtn1.setVisible(false);
                    else playAgainBtn.setVisible(false);
                    out.writeObject("RESULT");
                }

                if(inputObject instanceof String){
                    if(inputObject.toString().startsWith("POINTS")){
                        int pointPlayer1 = Integer.parseInt(inputObject.toString().substring(6,7));
                        int pointPlayer2 = Integer.parseInt(inputObject.toString().substring(7,8));
                        rounds = Integer.parseInt(inputObject.toString().substring(8));
                        resultText.setText(pointPlayer1+ " - " + pointPlayer2);
                        System.out.println(pointPlayer1+ " - " + pointPlayer2);
                        out.writeObject("PLAYER1");
                    }
                }

                else if (inputObject instanceof int [][]){
                    if (counter==0){
                        int [][] arrayPl1 = (int[][]) inputObject;
                        for (int i = 0; i < arrayPl1.length; i++) {
                            for (int j = 0; j < arrayPl1.length; j++) {
                                System.out.print(arrayPl1[i][j] + " ");
                            }
                            System.out.println();
                        }
                        for (int i = 0; i < arrayPl1.length; i++) {
                            for (int j = 0; j < arrayPl1.length; j++) {
                                Circle c = new Circle();
                                if (arrayPl1[i][j]==1) c.setFill(Color.GREENYELLOW);
                                else if (arrayPl1[i][j]==2) c.setFill(Color.RED);
                                else if(arrayPl1[i][j]==0) c.setVisible(false);
                                c.setRadius(12);
                                Platform.runLater(() -> {
                                    hBoxPl1.getChildren().add(c);
                                    //hBoxPl1.setHgap(5);
                                    //hBoxPl1.setVgap(10);
                                });
                            }
                            //if(i<=rounds)
                                //textList.get(rounds-1).setVisible(true);
                        }
                        out.writeObject("PLAYER2");
                        counter++;
                    }
                    else if(counter==1){
                        int [][] arrayPl2 = (int[][]) inputObject;
                        for (int i = 0; i < arrayPl2.length; i++) {
                            for (int j = 0; j < arrayPl2.length; j++) {
                                Circle c = new Circle();
                                if (arrayPl2[i][j]==1) c.setFill(Color.GREENYELLOW);
                                else if (arrayPl2[i][j]==2) c.setFill(Color.RED);
                                else if (arrayPl2[i][j]==0) c.setVisible(false);
                                c.setRadius(12);
                                Platform.runLater(() -> {
                                    hBoxpl2.getChildren().add(c);
                                    //hBoxpl2.setHgap(5);
                                    //hBoxpl2.setVgap(10);
                                });
                            }
                        }
                        //for (int i = 0; i <= rounds; i++) {
                          //  Text temp = new Text("Omgång " + (i+1));
                          //  Platform.runLater(() -> roundsTextBox.getChildren().add(temp));
                       // }
                        break;
                    }

                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateGameWindow() throws IOException, ClassNotFoundException {
        String temp;
        temp = in.readObject().toString();
        if (temp.equals("WAITING"))
            s.loadNewScreen(ScreenNavigator.WAITING, nextRoundBtn1);

        else if (temp.equals("GO_TO_CHOOSE_CATEGORY"))
            s.loadNewScreen(ScreenNavigator.SELECT_CATEGORY, nextRoundBtn1);

    }
}