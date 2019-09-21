package application;
// Toby Anthony-Egorp
// Little Caesars Program
// March 16th, 2018
// ICS 4U1


// Importing all necessary javafx components

import javax.swing.*;

import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.text.*; 


public class Main extends Application {

	//Declaring all global variables
	private int numToppings, sprite, coke, root, orange;
	private double sizeCost, bevCost, topCost;
	
	// TextField for Toppings, drinks, and sizes
	private TextField txtTopping = new TextField();
	private TextField txtDrink = new TextField();
	private TextField txtSize = new TextField();
	
	//Global comboboxes for each of the drinks.
	private ComboBox<Integer> cboCoke = new ComboBox<Integer>();
	private ComboBox<Integer> cboSprite = new ComboBox<Integer>();
	private ComboBox<Integer> cboRoot = new ComboBox<Integer>();
	private ComboBox<Integer> cboOrange = new ComboBox<Integer>();
	public void start(Stage primaryStage) {
		try {

			// Declaring flowpane and Image Objects for the GridPane
			FlowPane flowLogo = new FlowPane();
			ImageView imgCaesar = new ImageView(new Image("file:littlecaesarslogo.png"));
			ImageView imgPayment = new ImageView(new Image("file:PaymentOptions.png"));
			ImageIcon icnCaesar = new ImageIcon("littlecaesarsicon.png");

			//Setting the text of first TextField for the pizza size, it's size, and font
			txtSize.setText("$0.00");
			txtSize.setEditable(false);
			txtSize.setAlignment(Pos.CENTER);
			txtSize.setMaxSize(80, 30);
			txtSize.setFont(Font.font("Default", FontWeight.BOLD, 12));

			//Setting the text of second TextField for the pizza toppings, it's size, and font
			txtTopping.setText("$0.00");
			txtTopping.setEditable(false);
			txtTopping.setAlignment(Pos.CENTER);
			txtTopping.setMaxSize(100, 30);
			txtTopping.setFont(Font.font("Default", FontWeight.BOLD, 12));

			////Setting the text of third TextField for the drinks, it's size, and font
			txtDrink.setText("$0.00");
			txtDrink.setEditable(false);
			txtDrink.setAlignment(Pos.CENTER);
			txtDrink.setMaxSize(100,30);
			txtDrink.setFont(Font.font("Default", FontWeight.BOLD, 12));


			//Adding the image to the flowpane
			flowLogo.getChildren().addAll(imgCaesar);

			//Vbox for the radiobuttons and setting padding and spacing
			VBox radio = new VBox();
			radio.setPadding(new Insets(10,10,10,10));
			radio.setSpacing(10);

			//Declaring radiobutton for the small size and setting ID and sending the ID to the size Method
			RadioButton radSmall = new RadioButton("Small");
			radSmall.setPrefSize(100, 30);
			radSmall.setId("7.99");
			radSmall.setOnAction(e -> radSize(radSmall.getId()));
				
			//Declaring radiobutton for the medium size and setting ID and sending the ID to the size method
			RadioButton radMed = new RadioButton("Medium");
			radMed.setPrefSize(100, 30);
			radMed.setId("8.99");
			radMed.setOnAction(e -> radSize(radMed.getId()));
			
			//Declaring radiobutton for the large size and setting ID and sending the ID to the size method
			RadioButton radLarge = new RadioButton("Large");
			radLarge.setPrefSize(100, 30);
			radLarge.setId("9.99");
			radLarge.setOnAction(e -> radSize(radLarge.getId()));
			
			//Declaring radiobutton for the party size and setting ID and sending it to the size Method
			RadioButton radParty = new RadioButton("Party");
			radParty.setPrefSize(100, 30);
			radParty.setId("10.99");
			radParty.setOnAction(e -> radSize(radParty.getId()));
			
			//Creating a toggle group for all the radio buttons
			ToggleGroup toggleGroup = new ToggleGroup();
			toggleGroup.getToggles().addAll(radSmall, radMed, radLarge, radParty);

			//Adding the radio buttons to the vbox
			radio.getChildren().addAll(radSmall, radMed, radLarge, radParty);

			//Creating a titled pane for the size
			TitledPane tlSize = new TitledPane();
			tlSize.setText("SIZE");
			tlSize.setCollapsible(false);
			tlSize.setContent(radio);

			// Creating a tile Pane for the checkboxes
			TilePane checkTop = new TilePane();
			checkTop.setPrefColumns(2);
			checkTop.setPrefRows(4);
			checkTop.setVgap(10);
			checkTop.setHgap(10);

			// Creating a checkbox for the mushrooms
			CheckBox chkMush = new CheckBox("Mushrooms");
			chkMush.setPrefSize(125, 30);
			chkMush.setMaxSize(125, 30);
			chkMush.setOnAction(e -> ChkTopClick(chkMush));

			// Creating a checkbox for the green peppers
			CheckBox chkGpep = new CheckBox("Green Peppers");
			chkGpep.setMaxSize(125,30);
			chkGpep.setOnAction(e -> ChkTopClick(chkGpep));

			// Creating a checkbox for the onions and passing it to the method
			CheckBox chkOnions = new CheckBox("Onions");
			chkOnions.setMaxSize(125, 30);
			chkOnions.setOnAction(e -> ChkTopClick(chkOnions));

			// Creating a checkbox for the hot Peppers and passing it to the method
			CheckBox chkHpep = new CheckBox("Hot Peppers");
			chkHpep.setMaxSize(125, 30);
			chkHpep.setOnAction(e -> ChkTopClick(chkHpep));

			// Creating a checkbox for the pepperoni and passing it to the method
			CheckBox chkPepperoni = new CheckBox("Pepperoni");
			chkPepperoni.setMaxSize(125, 30);
			chkPepperoni.setOnAction(e -> ChkTopClick(chkPepperoni));

			// Creating a checkbox for the bacon and passing it to the method
			CheckBox chkBacon = new CheckBox("Bacon");
			chkBacon.setMaxSize(125, 30);
			chkBacon.setOnAction(e -> ChkTopClick(chkBacon));

			// Creating a checkbox for the tomatoes and passing it to the method
			CheckBox chkTomatoes = new CheckBox("Tomatoes");
			chkTomatoes.setMaxSize(125, 30);
			chkTomatoes.setOnAction(e -> ChkTopClick(chkTomatoes));

			// Creating a checkbox for the extra cheese and passing it to the method
			CheckBox chkExCheese = new CheckBox("Extra Cheese");
			chkExCheese.setMaxSize(125, 30);
			chkExCheese.setOnAction(e -> ChkTopClick(chkExCheese));

			//Adding all of the toppings to the tile pane
			checkTop.getChildren().addAll(chkMush, chkPepperoni, chkGpep, chkBacon, chkOnions, chkTomatoes, chkHpep, chkExCheese);
			TitledPane tlTop = new TitledPane();
			tlTop.setText("TOPPINGS");
			tlTop.setCollapsible(false);
			tlTop.setContent(checkTop);


			// Creating a label for coke and a combobox for all the numbers and passing it to the method
			Label lblCoke = new Label("Coke");
			lblCoke.setPrefSize(60, 30);
			cboCoke.setValue(0);
			cboCoke.getItems().addAll(0, 1, 2, 3, 4, 5, 6);
			cboCoke.setPrefWidth(20);
			cboCoke.setVisibleRowCount(3);
			cboCoke.setOnAction(e -> cboBev(cboCoke.getValue()));

			// Creating a label for sprite and a combobox for all the numbers and passing it to the method
			Label lblSprite = new Label("Sprite");
			lblSprite.setPrefSize(60, 30);
			cboSprite.setValue(0);
			cboSprite.getItems().addAll(0, 1, 2, 3, 4, 5, 6);
			cboSprite.setPrefWidth(20);
			cboSprite.setVisibleRowCount(3);
			cboSprite.setOnAction(e -> cboBev(cboSprite.getValue()));

			// Creating a label for orange and a combobox for all the numbers and passing it to the method
			Label lblOrange = new Label("Orange");
			lblOrange.setPrefSize(60, 30);
			cboOrange.setValue(0);
			cboOrange.getItems().addAll(0, 1, 2, 3, 4, 5, 6);
			cboOrange.setPrefWidth(20);
			cboOrange.setVisibleRowCount(3);
			cboOrange.setOnAction(e -> cboBev(cboOrange.getValue()));

			// Creating a label for root beer and a combobox for all the numbers and passing it to the method
			Label lblRoot = new Label("Root Beer");
			lblRoot.setPrefSize(60, 30);
			cboRoot.setValue(0);
			cboRoot.getItems().addAll(0, 1, 2, 3, 4, 5, 6);
			cboRoot.setPrefWidth(20);
			cboRoot.setVisibleRowCount(3);
			cboRoot.setOnAction(e -> cboBev(cboRoot.getValue()));

			//Creating a label for the message which says that first 3 toppings are free
			Label lblTopping = new Label("First three (3) toppings are free!");
			lblTopping.setFont(Font.font("Default", FontWeight.BOLD, 12));
			lblTopping.setMaxSize(200, 30);

			//Creating a tile Pane for the drinks as well as a titled Pane
			TilePane flwDrinks = new TilePane();
			flwDrinks.setPrefColumns(2);
			flwDrinks.setPrefRows(2);
			flwDrinks.setVgap(10);
			flwDrinks.getChildren().addAll(lblCoke, cboCoke, lblSprite, cboSprite, lblOrange, cboOrange, lblRoot, cboRoot);
			TitledPane tlDrinks = new TitledPane();
			tlDrinks.setText("BEVERAGES");
			tlDrinks.setCollapsible(false);
			tlDrinks.setContent(flwDrinks);


			// Creating labels for the subtotal, delivery fee, hst, and grand total.
			Label lblSub = new Label("SUBTOTAL:");
			Label lblDFee = new Label("DELIVERY FEE:");
			Label lblHst = new Label("HST:");
			Label lblGTotal = new Label("GRAND TOTAL:");

			//Creating textfields for subtotal, delivery fee, hst, and grand total
			TextField txtSub = new TextField();
			txtSub.setMaxSize(100, 30);
			TextField txtDFee= new TextField();
			txtDFee.setMaxSize(100, 30);
			TextField txtHst = new TextField();
			txtHst.setMaxSize(100, 30);
			TextField txtGTotal = new TextField();
			txtGTotal.setMaxSize(100, 30);

			//Creating a tile pane for the label and texfields for the subtotal, delivery fee, hst, and grand total
			TilePane tlCalc = new TilePane();
			tlCalc.setPrefColumns(2);
			tlCalc.setPrefRows(4);
			tlCalc.setAlignment(Pos.CENTER);
			tlCalc.setVgap(10);
			tlCalc.getChildren().addAll(lblSub, txtSub, lblDFee, txtDFee, lblHst, txtHst, lblGTotal, txtGTotal);


			//Creating a button for the checkout, disabling it, and checking if the order is correct
			Button btnCheckout = new Button("CHECKOUT");
			btnCheckout.setPrefSize(100,30);
			btnCheckout.setDisable(true);
			btnCheckout.setOnAction(new EventHandler<ActionEvent> () {
				public void handle(ActionEvent e) {
					//Creating a string to check if the order is correct and display the toppings, drinks, and size the user has selected
					String total1 = "Is this order correct?\n\n";

					//Adding radiobuttons to order
					if (radSmall.isSelected())
					{
						total1 = total1 + "PIZZA: \n -Small";
					}
					else if (radMed.isSelected())
					{
						total1 = total1 + "PIZZA: \n -Medium";
					}
					else if (radLarge.isSelected())
					{
						total1 = total1 + "PIZZA: \n -Large";
					}
					else if (radParty.isSelected())
					{
						total1 = total1 + "PIZZA: \n -Party";
					}

					//Adding checkboxes to order
					total1 = total1 + "\n\nTOPPINGS:\n";
					if (chkMush.isSelected())
					{
						total1 = total1 + "-Mushrooms";
					}
					else if (chkGpep.isSelected())
					{
						total1 = total1 + "-Green Peppers";
					}
					else if (chkOnions.isSelected())
					{
						total1 = total1 + "-Onions";
					}
					else if (chkHpep.isSelected())
					{
						total1 = total1 + "-Hpep";
					}
					else if (chkPepperoni.isSelected())
					{
						total1 = total1 + "-Pepperoni";
					}
					else if (chkBacon.isSelected())
					{
						total1 = total1 + "-Bacon";
					}
					else if (chkTomatoes.isSelected())
					{
						total1 = total1 + "-Tomatoes";
					}
					else if (chkExCheese.isSelected())
					{
						total1 = total1 + "-Extra Cheese";
					}

					//Adding combobox beverages to the order
					total1 = total1 + "\n\nBEVERAGES:\n";
					if (cboCoke.getValue() > 0 )
					{
						total1 = total1 + cboCoke.getValue() + "x Coke";
					}
					else if (cboSprite.getValue() > 0)
					{
						total1 = total1 +   cboSprite.getValue() + "x Sprite";
					}
					else if (cboOrange.getValue() > 0)
					{
						total1 = total1 + cboOrange.getValue() + "x Orange";
					}
					else if (cboRoot.getValue() > 0)
					{
						total1 = total1 + cboRoot.getValue() + "x Root Beer";
					}

					// Checking if the user clicks yes or no if the order is correct, and displaying JOption Pane
					int choice = JOptionPane.showConfirmDialog(null, total1, "Order Summary", JOptionPane.YES_NO_OPTION);

					if(choice == 0)
					{
						JOptionPane.showMessageDialog(null, "Thank you for ordering from Little Caesars!\nYour pizza will be delivered in 30 minutes or it's free!", "Little Caesars", JOptionPane.OK_OPTION);
					}

					if (choice == 1)
					{
						e.consume();
					}
				}
			});
			
			Button btnCalc = new Button("CALCULATE");
			btnCalc.setPrefSize(100, 30);
			btnCalc.setOnAction(new EventHandler<ActionEvent> ()
			{
				public void handle(ActionEvent e)
				{
					//Checking if any of the sizes are selected
					if (radSmall.isSelected() || radMed.isSelected() || radLarge.isSelected() || radParty.isSelected())
					{
					
					
					
					//Storing the cost of all 3 items in a double variable
					double calculate = sizeCost+bevCost+topCost; 
					
					//Setting the Subtotal textfield to the calculate variable
					txtSub.setText(String.format("%.2f", calculate));

					//Storing the hst value in another variable
					double hst = calculate * 0.13;
					
					//Storing the hst textfield to the tax variable
					txtHst.setText("$" + String.format("%.2f", hst));
					
					//Adding the subtotal to the tax and storing it as the grandtotal
					
					double gTotal = calculate + hst;
					btnCheckout.setDisable(false);
					
					//Checking to see if the subtotal is greater than or less than $15, add $5 if it is less than $15 and make it free if the order is greater than $15
					//Setting the background to green if it's free
					if(calculate >= 15.00)
					{
						
						txtDFee.setText("$0.00");
						txtDFee.setStyle("-fx-control-inner-background:green");
						gTotal = hst + calculate;
						txtGTotal.setText("$" + String.format("%.2f", gTotal));
					}

					else if (calculate < 15.00 && calculate > 0)
					{
						
						txtDFee.setText("$5.00");
						txtDFee.setStyle("-fx-control-inner-background:white");
						gTotal = hst + calculate + 5;
						txtGTotal.setText("$" + String.format("%.2f", gTotal));
					}
					
					// If there is no pizza size selected, it gives a JOptionPane saying the order couldn't be completed.
					else 
					{
						JOptionPane.showMessageDialog(null, "Your order could not be completed!\nPlease select a pizza size.", "Incomplete order", JOptionPane.ERROR_MESSAGE);
					}
			
					}
				}
			});

			//Clear button that clears everything that's selected, changes the textfield from green back to white, and sets the textfields to 0 and the selection to unchecked
			Button btnClear = new Button("CLEAR");
			btnClear.setPrefSize(100,30);
			btnClear.setOnAction(new EventHandler<ActionEvent> () {
				public void handle(ActionEvent e)
				{
					toggleGroup.getSelectedToggle().setSelected(false);
					numToppings = 0;
					txtDFee.setStyle("-fx-control-inner-background:white");
					btnCheckout.setDisable(true);
					chkMush.setSelected(false);
					chkGpep.setSelected(false);
					chkOnions.setSelected(false);
					chkHpep.setSelected(false);
					chkPepperoni.setSelected(false);
					chkBacon.setSelected(false);
					chkTomatoes.setSelected(false);
					chkExCheese.setSelected(false);
					cboCoke.setValue(0);
					cboSprite.setValue(0);
					cboOrange.setValue(0);
					cboRoot.setValue(0);
					txtSize.setText("$0.00");
					txtSize.setEditable(false);
					txtTopping.setText("$0.00");
					txtTopping.setEditable(false);
					txtDrink.setText("$0.00");
					txtDrink.setEditable(false);
					txtSub.setText("$0.00");
					txtSub.setEditable(false);
					txtDFee.setText("$0.00");
					txtDFee.setEditable(false);
					txtHst.setText("$0.00");
					txtHst.setEditable(false);
					txtGTotal.setText("$0.00");
					txtGTotal.setEditable(false);
				}
			});
			
			//If the user clicks exit, it will ask them to confirm if they want to exit. If they say no, it doesn't exit, ifthey say yes, it exits
			Button btnExit = new Button("EXIT");
			btnExit.setPrefSize(100,30);
			btnExit.setOnAction(new EventHandler<ActionEvent> () {
				public void handle(ActionEvent e) {
					int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Little Caesars", JOptionPane.YES_NO_OPTION);

					if(option == 0)
					{
						JOptionPane.showMessageDialog(null, "Thank you for choosing Little Caesars!", "Little Caesars", JOptionPane.INFORMATION_MESSAGE, icnCaesar);
						System.exit(0);

					}

					if (option == 1)
					{
						e.consume();
					}
				}
			});

			//Creating a tilePane and adding all the buttons to it. For calculate, clear, checkout, and exit
			TilePane tlButtons = new TilePane();
			tlButtons.setPrefColumns(1);
			tlButtons.setPrefRows(4);
			tlButtons.setAlignment(Pos.CENTER);
			tlButtons.setVgap(10);
			tlButtons.getChildren().addAll(btnCalc, btnClear, btnCheckout, btnExit);



			//Creating a gridpane for the whole scene. Setting Hgap and vGap as well as padding.
			GridPane root = new GridPane();
			root.setVgap(15);
			root.setHgap(10);
			root.setPadding(new Insets(10,10,10,10));
			root.setGridLinesVisible(false);
			//Adding tilepanes, images, and labels to the gridpane and vertically aligning them
			GridPane.setValignment(tlSize, VPos.TOP);
			GridPane.setValignment(tlTop, VPos.TOP);
			GridPane.setValignment(tlDrinks, VPos.TOP);
			//Horizontally aligning components
			GridPane.setHalignment(imgCaesar, HPos.CENTER);
			GridPane.setHalignment(imgPayment, HPos.CENTER);
			GridPane.setHalignment(lblTopping, HPos.CENTER);
			GridPane.setHalignment(tlButtons, HPos.CENTER);
			GridPane.setHalignment(txtSize, HPos.CENTER);
			GridPane.setHalignment(txtTopping, HPos.CENTER);
			GridPane.setHalignment(txtDrink, HPos.CENTER);
			GridPane.setHalignment(tlCalc, HPos.CENTER);

			//Adding components to different rows and columns in the gridpane
			root.add(imgCaesar, 0, 0, 3, 1);
			root.add(tlSize, 0, 1);
			root.add(tlTop, 1, 1);
			root.add(tlDrinks, 2, 1);
			root.add(radio, 0, 1);
			root.add(flwDrinks, 2, 1);
			root.add(lblTopping, 1, 2);
			root.add(txtSize, 0, 3);
			root.add(txtTopping, 1, 3);
			root.add(txtDrink, 2, 3);
			root.add(imgPayment, 0, 4);
			root.add(tlCalc, 1, 4);
			root.add(tlButtons, 2, 4);
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			primaryStage.setTitle("Little Caesars");
			primaryStage.setScene(scene);
			primaryStage.show();
			//Set on close request for when the user clicks close
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent> () {
				public void handle(WindowEvent e) {
					int exit = JOptionPane.showConfirmDialog(null,
							"Are you sure you want to exit?",
							"Little Caesars",
							JOptionPane.YES_NO_OPTION);

					if (exit == JOptionPane.NO_OPTION || exit == JOptionPane.CLOSED_OPTION)
					{
						e.consume();
					}
					else if (exit == JOptionPane.YES_OPTION)
					{
						JOptionPane.showMessageDialog(null,
								"Thank you for using Little Caesars!",
								"Little Caesars",
								JOptionPane.INFORMATION_MESSAGE, icnCaesar);
					}
				}
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	//Method that changes the size of the pizza from a string to a double and displays it with decimal value
	private void radSize(String size) 
	{
		sizeCost = Double.parseDouble(size);
		txtSize.setText("$" + String.format("%.2f", sizeCost));
	}

	//Method that passes all comboboxes and their integer value in order to calculate the total cost for drinks
	private void cboBev(Integer value) {

		sprite = cboSprite.getValue();
		coke = cboCoke.getValue();
		root = cboRoot.getValue();
		orange = cboOrange.getValue();

		bevCost = sprite+coke+root+orange;
	    bevCost = bevCost * 0.99;
		String.format("%.2f", bevCost);
		txtDrink.setText("$" + (bevCost));
	}

	//Method that passes global toppings and increases by 1 whenever the user checks a checkbox and decreases by 1 whenever the user unselects a checkbox
	private void ChkTopClick(CheckBox chkAll) {

		if (chkAll.isSelected())
		{
			numToppings++;
		}
		else
		{
			numToppings--;
		}
		
		// Checks if the number is greater than the first 3 toppings and calculates the cost for number of toppings starting from 3
		if (numToppings >= 3)
		{
			topCost = numToppings * 1;		
			topCost = numToppings - 3;
			txtTopping.setText("$" + topCost + "0");
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
