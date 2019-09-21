package application;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = new Pane();
			Scene scene = new Scene(root,450,320);

			//Title Label= Love Calculator initialization
			Label lblLove = new Label("LOVE CALCULATOR");
			lblLove.setPrefSize(290, 40);
			lblLove.setAlignment(Pos.CENTER);
			lblLove.setFont(Font.font("Cambria", FontWeight.BOLD, 30));
			lblLove.setTextFill(Color.rgb(255, 0, 0));
			lblLove.setLayoutX(80);
			lblLove.setLayoutY(25);

			//Declaring left cupid image into imageview as label and position
			Label lblLeft = new Label();
			lblLeft.setGraphic(new ImageView(new Image("file:cupidLeft.png")));
			lblLeft.setLayoutX(10);
			lblLeft.setLayoutY(85);

			//Declaring right cupid image into imageview as label and position
			Label lblRight = new Label();
			lblRight.setGraphic(new ImageView(new Image("file:cupidRight.png")));
			lblRight.setLayoutX(290);
			lblRight.setLayoutY(85);

			//Declaring the "enter your name" label as lblName
			Label lblName = new Label("Enter your name:");
			lblName.setPrefSize(160, 40);
			lblName.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
			lblName.setTextFill(Color.rgb(255, 0, 0));
			lblName.setLayoutX(40);
			lblName.setLayoutY(230);

			//Declaring the label for the love percentage and setting its position and color
			Label lblNum = new Label();
			lblNum.setPrefSize(100,50);
			lblNum.setFont(Font.font("Times New Roman", FontWeight.BOLD, 50));
			lblNum.setTextFill(Color.rgb(255,0,0));
			lblNum.setLayoutX(scene.getWidth() / 2 - lblNum.getPrefWidth() / 2 + 8);
			lblNum.setLayoutY(scene.getHeight() / 2 - lblLove.getPrefHeight() / 2 - 20);

			//Creating textfield for the first name and position
			TextField txtName = new TextField();
			txtName.setPrefSize(110, 20);
			txtName.setLayoutX(30);
			txtName.setLayoutY(270);

			//Creating textfield for the second name and position
			TextField txtOther = new TextField();
			txtOther.setPrefSize(120, 20);
			txtOther.setLayoutX(310);
			txtOther.setLayoutY(270);

			//Creating the submit button and initializing an action when it is clicked
			Button btnSubmit = new Button("SUBMIT");
			btnSubmit.setPrefSize(110, 30);
			btnSubmit.setLayoutX(scene.getWidth() / 2 - btnSubmit.getPrefWidth()/ 2);
			btnSubmit.setLayoutY(220);
			btnSubmit.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
			btnSubmit.setTextFill(Color.WHITE);
			btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
				public void handle (ActionEvent e) {

					//String variables that get the text in textfield
					String name = txtName.getText();
					String other = txtOther.getText();

					//Setting the text field to white
					txtName.setStyle("-fx-control-inner-background: white");
					txtOther.setStyle("-fx-control-inner-background: white");

					//If there isn't a name in both textfields, make a Joption pane that checks and tells the user to enter two names
					if (name.equals("") || other.equals("") )
					{
						JOptionPane.showMessageDialog(null,
								"Please enter two names!",
								"Error",
								JOptionPane.ERROR_MESSAGE);

						//If the first textfield has no name, make the inner background red
						if (name.equals(""))
						{
							txtName.setStyle("-fx-control-inner-background: red");
							txtName.requestFocus();
						}

						//If the second textfield has no name, make the inner background red
						if (other.equals(""))
						{
							txtOther.setStyle("-fx-control-inner-background: red");
							txtOther.requestFocus();
						}
					}
					
					//If none of these conditions are true(there are two names) proceed to the algorithm
					else
					{
						//turning both names to uppercase
						name = name.toUpperCase();
						other = other.toUpperCase();
						
						//new variables for empty strings that take away duplicate letters
						String newName = "";
						String newOther = "";

						//Creating int variable for final added ASCII values
						int addUp = 0;

						// for loop for removing duplicate letters
						for(int i = 0; i < name.length(); i++)
						{
							if(newName.indexOf(name.charAt(i)) < 0)
							{
								newName += name.charAt(i);
							}
						}

						//for loop for second name
						for(int i = 0; i < other.length(); i++)
						{
							if(newOther.indexOf(other.charAt(i)) < 0)
							{
								newOther += other.charAt(i);
							}
						}

						
						//Creating a string variable that combines both names after duplicate letters are removed
						String combo = newName.concat(newOther);
						

						//Casting the letter value at each index number to an integer and adding them all up
						for (int i = 0; i < combo.length(); i++)
						{

							addUp += (int)combo.charAt(i);

						}

						//Using another integer value for the final percentage and using modulo to get percentage(remainder)
						int love = addUp % 101;
						lblNum.setText(String.valueOf(love) + "%");

					}

				}});

			//Clear button and its side, position, color, and actions
			Button btnClear = new Button("CLEAR");
			btnClear.setPrefSize(110, 30);
			btnClear.setLayoutX(scene.getWidth() / 2 - btnClear.getPrefWidth()/ 2);
			btnClear.setLayoutY(260);
			btnClear.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
			btnClear.setTextFill(Color.WHITE);
			btnClear.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {

					txtName.clear();
					txtOther.clear();
				}
			});

			//Label for second name
			Label lblOther = new Label("Enter his/her name:");
			lblOther.setPrefSize(160, 40);
			lblOther.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
			lblOther.setTextFill(Color.rgb(255, 0, 0));
			lblOther.setLayoutX(320);
			lblOther.setLayoutY(230);



			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			root.getChildren().addAll(lblLove, lblLeft, lblRight, lblName, txtName, btnSubmit, btnClear, lblOther, txtOther, lblNum);

			primaryStage.setTitle("Love Calculator");
			primaryStage.setScene(scene);
			primaryStage.show();
			
			//Programming the close button and Joption panes if the user wants to exit or not
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent e) {

					int exit = JOptionPane.showConfirmDialog(null,
							"Are you sure you want to exit?",
							"Love Calculator",
							JOptionPane.YES_NO_OPTION);

					if (exit == JOptionPane.NO_OPTION || exit == JOptionPane.CLOSED_OPTION)
					{
						e.consume();
					}
					else if (exit == JOptionPane.YES_OPTION)
					{
						JOptionPane.showMessageDialog(null,
								"Thank you for using Love Calculator!",
								"Thanks",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
