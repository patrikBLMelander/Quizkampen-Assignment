package server.Categories;

import javafx.application.Application;
import javafx.fxml.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;


public class GameScreen extends Application implements Serializable
{
	
	@Override
	public void start(Stage stage) throws IOException
	{
		ArrayList <String>
			questonElements;
		
		String
			categoryS ,
			QuestionS ,
			correctAnswerS ,
			wrongAnswer0S ,
			wrongAnswer1S ,
			wrongAnswer2S ,
			font;
		
		double[]
			buttonDimensions;
		
		GridPane
			gridPaneMomma ,
			gridPaneBabyText ,
			gridPaneBabyButtons;
		
		Text
			text0 ,
			text1;
		
		Button
			button0 ,
			button1 ,
			button2 ,
			button3;
		
		Scene
			scene0;
		
		
		questonElements = new ArrayList <String>(6);
		
		
		/*questonElements = importElementsFromQuestion("HÄR MÅSTE INSTANSEN AV EN FRÅGA LÄGGAS IN!");
		
		categoryS = questonElements.get(0);
		QuestionS = questonElements.get(1);
		correctAnswerS = questonElements.get(2);
		wrongAnswer0S = questonElements.get(3);
		wrongAnswer1S = questonElements.get(4);
		wrongAnswer2S = questonElements.get(5);*/
		
		
		
		categoryS = "Kategori";
		QuestionS = "Fråga";
		correctAnswerS = "Rätt svar";
		wrongAnswer0S = "Fel svar 0";
		wrongAnswer1S = "Fel svar 1";
		wrongAnswer2S = "Fel svar 2";
		
		
		
		font = "-fx-font: normal bold 24px 'serif' ";
		
		
		text0 = new Text(categoryS);
		text1 = new Text(QuestionS);
		
		
		text0.setStyle(font);
		
		
		button0 = new Button(correctAnswerS);
		button1 = new Button(wrongAnswer0S);
		button2 = new Button(wrongAnswer1S);
		button3 = new Button(wrongAnswer2S);
		
		
		gridPaneBabyButtons = new GridPane();
		gridPaneBabyButtons.setMinSize(400, 200);
		gridPaneBabyButtons.setPadding(new Insets(10, 10, 10, 10));
		gridPaneBabyButtons.setVgap(5);
		gridPaneBabyButtons.setHgap(5);
		gridPaneBabyButtons.setAlignment(Pos.CENTER);
	
		gridPaneBabyButtons.add(button0, 0, 1);
		gridPaneBabyButtons.add(button1, 1, 1);
		gridPaneBabyButtons.add(button2, 0, 2);
		gridPaneBabyButtons.add(button3, 1, 2);
		
		
		buttonDimensions = new double[2];
		buttonDimensions = setButtonSizes(250);
		
		
		button0.setPrefSize(buttonDimensions[0], buttonDimensions[1]);
		button1.setPrefSize(buttonDimensions[0], buttonDimensions[1]);
		button2.setPrefSize(buttonDimensions[0], buttonDimensions[1]);
		button3.setPrefSize(buttonDimensions[0], buttonDimensions[1]);
		
		
		gridPaneBabyText = new GridPane();
		gridPaneBabyText.setPadding(new Insets(10, 10, 10, 10));
		gridPaneBabyText.setVgap(5);
		gridPaneBabyText.setHgap(5);
		gridPaneBabyText.setAlignment(Pos.CENTER);
		gridPaneBabyText.add(text0 , 1, 0);
		gridPaneBabyText.add(text1 , 1, 1);
		
		
		gridPaneMomma = new GridPane();
		gridPaneMomma.setPadding(new Insets(0, 0, 0, 0));
		gridPaneMomma.setVgap(5);
		gridPaneMomma.setHgap(5);
		gridPaneMomma.setAlignment(Pos.CENTER);
		gridPaneMomma.add(gridPaneBabyText, 0 , 0);
		gridPaneMomma.add(gridPaneBabyButtons, 0 , 1);
		
		
		scene0 = new Scene(gridPaneMomma);
		
		
		stage.setTitle(categoryS);
		stage.setScene(scene0);
		stage.show();
		
	}
	
	
	
	public static void main(String args[])
	{
		launch(args);
		
	}
	
	
	
	private ArrayList<String> importElementsFromQuestion(Questions questionImport)
	{
		ArrayList <String> questonElements = new ArrayList <String>(6);
		
	
		questonElements.add(0, questionImport.getCategory());
		
		if(questonElements.get(0).equals(""))
		{
			questonElements.remove(0);
			questonElements.add(0, "INGEN KATEGORI VALD");
			
		}
		
		questonElements.add(1, questionImport.getQuestion());
		questonElements.add(2, questionImport.getCorrectAnswer());
		questonElements.add(3, questionImport.getWrongAnswer0());
		questonElements.add(4, questionImport.getWrongAnswer1());
		questonElements.add(5, questionImport.getWrongAnswer2());
		
		return questonElements;
	
	}
	
	
	
	private double[] setButtonSizes(double horizontalAlignment)
	{
		double[] dimensions = new double[2];
		
		dimensions[0] = horizontalAlignment;
		dimensions[1] = horizontalAlignment * 0.8;
		
		return dimensions;
		
	}
	
}