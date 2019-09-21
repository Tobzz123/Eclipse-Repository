// Jeffery and Toby
// April 10th, 2018
//ICS 4U1
//Deal or no Deal program
package application;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Main extends Application {

	private ImageView ivPlayersCase; //Image of first case selected at bottom left corner
	private Label lblMessage;
	private ImageView[][] ivAmounts;
	private Button[][] btnCases;
	private String[] amounts;
	private int[] caseValues = new int[26];
	private int roundNum = 1, turnNum = 0, maxTurns = 6;

	public void start(Stage primaryStage) {
		try {

			ivPlayersCase = new ImageView();
			ivPlayersCase.setFitWidth(53);
			ivPlayersCase.setFitHeight(48);

			ivAmounts = new ImageView[13][2];
			amounts = new String[] {"0.01", "1", "5", "10", "25", "50", "75", "100", "200", "300", "400",
					"500", "750", "1000", "5000", "10000", "25000", "50000", "75000", "100000",
					"200000", "300000", "400000", "500000", "750000", "1000000"};

			ImageView ivTitle = new ImageView(new Image("file:images/dond_logo.png"));

			VBox westPanel = new VBox();
			westPanel.setAlignment(Pos.TOP_CENTER);
			westPanel.setSpacing(10);

			VBox eastPanel = new VBox();
			eastPanel.setAlignment(Pos.TOP_CENTER);
			eastPanel.setSpacing(10);

			int count = 0;
			

			for (int cols = 0; cols < ivAmounts[0].length; cols++)
			{
				for (int rows = 0; rows < ivAmounts.length; rows++)
				{
					ivAmounts[rows][cols] = new ImageView(new Image("file:images\\money\\" + 
							amounts[count] + ".png"));
					ivAmounts[rows][cols].setFitHeight(18);
					ivAmounts[rows][cols].setFitWidth(127);
					ivAmounts[rows][cols].setId(amounts[count]);

					if (cols == 0)
					{
						westPanel.getChildren().add(ivAmounts[rows][cols]);
					}
					else
					{
						eastPanel.getChildren().add(ivAmounts[rows][cols]);
					}
					count++;
				}
			}

			FlowPane centerPanel = new FlowPane();
			centerPanel.setPadding(new Insets(0, 10, 0, 10));
			centerPanel.setAlignment(Pos.CENTER);

			lblMessage = new Label();
			lblMessage.setPrefSize(692, 50);
			lblMessage.setAlignment(Pos.CENTER);
			lblMessage.setFont(Font.font("System", FontWeight.BOLD, 18));
			lblMessage.setTextFill(Color.rgb(252, 234, 151));
			lblMessage.setText("Choose one of the briefcases!");

			StackPane southPanel = new StackPane();
			southPanel.setAlignment(Pos.CENTER_LEFT);
			southPanel.setPadding(new Insets(10, 0, 0, 0));
			StackPane.setMargin(ivPlayersCase, new Insets(0, 0, 0, 40));
			southPanel.getChildren().addAll(lblMessage, ivPlayersCase);

			BorderPane root = new BorderPane();
			root.setPadding(new Insets(0, 0, 10, 0));
			root.setStyle("-fx-background-color: black");

			BorderPane.setAlignment(ivTitle, Pos.CENTER);
			BorderPane.setMargin(ivTitle, new Insets(10, 10, 10, 10));
			root.setTop(ivTitle);
			root.setLeft(westPanel);
			root.setRight(eastPanel);

			BorderPane.setAlignment(centerPanel, Pos.TOP_CENTER);
			root.setCenter(centerPanel);
			root.setBottom(southPanel);

			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			btnCases = new Button[5][];
			btnCases[0] = new Button[5];
			btnCases[1] = new Button[5];
			btnCases[2] = new Button[5];
			btnCases[3] = new Button[5];
			btnCases[4] = new Button[6];

			int caseInsertNum = 1;		//The current case number

			//Run a for-loop to populate btnCases
			for (int i = 0; i < btnCases.length; i++)
			{
				for (int j = 0; j < btnCases[i].length; j++)
				{
					//Declare & initialize a new button with a black background and the corresponding image defined by caseInsertNum
					btnCases[i][j] = new Button();
					btnCases[i][j].setStyle("-fx-background-color: black");
					btnCases[i][j].setGraphic(new ImageView(new Image("file:images\\suitcases\\case" + caseInsertNum + ".png")));
					centerPanel.getChildren().add(btnCases[i][j]);

					if (i == 4) {
						FlowPane.setMargin(btnCases[i][j], new Insets(5, 0, 5, 0));		//If we're on the last row, shrink the margin to fit six cases
					}
					else {
						FlowPane.setMargin(btnCases[i][j], new Insets(5, 5, 5, 5));		//Otherwise, set regular margins
					}

					btnCases[i][j].setId(Integer.toString(caseInsertNum));		//Set the case's ID to its number
					btnCases[i][j].setOnAction(e -> {
						caseClick((Button)(e.getSource()));		//When a case is clicked, run caseClicked with the clicked button as an argument
						if ((roundNum <= 5 && turnNum >= 7 - roundNum) || roundNum >= 5) {
							makeAnOffer();
							roundNum++;
							maxTurns = Math.max(maxTurns - 1, 1);
							turnNum = 1;
						}
						else {
							turnNum++;
						}
						
						lblMessage.setText("Open " + (maxTurns - turnNum + 1) + " briefcase(s)!");
					});

					caseInsertNum++;		//Add 1 to the current case number
				}
			}

			randomizeCaseValues();

			primaryStage.setTitle("Deal or No Deal");
			primaryStage.setScene(scene);
			primaryStage.show();

		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void randomizeCaseValues() {
		boolean[] selectedNums = new boolean[amounts.length];		//Array of booleans to identify if a number (index) has already been selected
		for (int i = 0; i < selectedNums.length; i++) {
			selectedNums[i] = false;																//Set each boolean in the array to false
		}

		for (int i = 0; i < amounts.length; i++) {
			int[] possibleNums = new int[amounts.length - i];					//Array of integers to hold all unselected numbers
			int addIndex = 0;																				//The current position in possibleNums
			for (int j = 0; j < selectedNums.length; j++) {
				if (!selectedNums[j]) {																//If the number hasn't been selected, add it into possibleNum. Increase addIndex by 1
					possibleNums[addIndex] = j;
					addIndex++;
				}
			}
			caseValues[i] = possibleNums[(int)(Math.random() * possibleNums.length)];	//Select a random number from the array of unselected numbers
			selectedNums[caseValues[i]] = true;																			//Set the index in selectedNums to true. "It has been selected"
		}
	}

	public void caseClick(Button selectedCase)
	{
		//If there isn't an ivPlayerCase, assign one
		selectedCase.setVisible(false);
		if (ivPlayersCase.getImage() == null) 
		{
			ivPlayersCase.setImage(new Image("file:images\\suitcases\\case" + selectedCase.getId() + ".png"));
			ivPlayersCase.setId(selectedCase.getId());
		}
		else {
			int caseNumber = Integer.parseInt(selectedCase.getId());
			String caseMoney = amounts[caseValues[caseNumber - 1]];
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Case #"  + caseNumber);
			alert.setGraphic(new ImageView(new Image("file:images\\suitcases\\case" + selectedCase.getId() + ".png")));
			alert.setContentText("Case #" + selectedCase.getId() + " contained $" + caseMoney);
			alert.showAndWait(); 
			
			int amountsIndex = caseValues[caseNumber - 1];
			
			if (amountsIndex >= 13)
			{
				ivAmounts[amountsIndex - 13][1].setVisible(false);
			}
			
			else if	(amountsIndex < 13)
			{
				ivAmounts[amountsIndex][0].setVisible(false);
			}
				
			
		}
			}
		
	

	public void makeAnOffer() {
		double offer = 0;
		double unopenedCases = 0;
		for (int i = 0; i < btnCases.length; i++)
		{
			for (int j = 0; j < btnCases[i].length; j++)
			{
				if (btnCases[i][j].isVisible()) {
					//For each button, check if its visible. If yes, add its case value to the offer number and add 1 to unopenedCases
					int caseNumber = Integer.parseInt(btnCases[i][j].getId());
					String caseMoney = amounts[caseValues[caseNumber - 1]];
					offer += Double.parseDouble(caseMoney);
					unopenedCases+= 1.0;
				}
			}
		}
		offer = offer / unopenedCases * roundNum * 0.1;		//Calculate the final offer
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText(null);
		alert.setTitle("Banker's Offer");
		alert.setContentText("The banker's offer is $" + String.format("%.2f", offer) + "\nDeal or No Deal?");
		alert.getButtonTypes().addAll(new ButtonType("DEAL"), new ButtonType("NO DEAL"));
		alert.showAndWait();
		
		Alert alertLast = new Alert(AlertType.CONFIRMATION, "There is only one case left.\nWould you like to keep your case?",

				ButtonType.YES, ButtonType.NO);

		alertLast.setHeaderText(null);

		alertLast.setTitle("Deal or No Deal");

		endGame(alertLast.showAndWait().get());

	}





	private void endGame(ButtonType input) {

		int lastCaseNumber = 0, playerCaseNumber = 0;

		ImageView icon;

		for (int i = 0; i < btnCases.length; i++)

		{

			for (int j = 0; j < btnCases[i].length; j++)

			{

				if (btnCases[i][j].isVisible()) {

					lastCaseNumber = Integer.parseInt(btnCases[i][j].getId());

				}

			}

		}

		playerCaseNumber = Integer.parseInt(ivPlayersCase.getId());

		String caseMoney = "";

		if (input == ButtonType.YES) {

			caseMoney = amounts[caseValues[playerCaseNumber - 1]];

			icon = new ImageView(new Image("file:images\\suitcases\\case" + playerCaseNumber + ".png"));

		}

		else {

			caseMoney = amounts[caseValues[lastCaseNumber - 1]];

			icon = new ImageView(new Image("file:images\\suitcases\\case" + lastCaseNumber + ".png"));

		}



		Alert alert = new Alert(AlertType.INFORMATION);

		alert.setHeaderText(null);

		alert.setTitle("It's a deal!");

		alert.setGraphic(icon);

		alert.setContentText("Congratulations, you're going home with $" + caseMoney);

		alert.showAndWait();
		
		//System.exit(0);

	}
	

	public static void main(String[] args) {
		launch(args);
	}
}
