package application;

import java.util.NoSuchElementException;
/* Toby Anthony-Egorp
 * ICS 4U1
 * Mr.Bulhao
 * 
 */
import java.util.Optional;
//Importing the needed packages
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;


public class Main extends Application {

	//Declaring all the things needed. Labels, textfields, buttons, imageviews, stackpane, strings, arrays of differents types and booleans.
	private Label lblPlayerHand, lblDealerHand, lblPTotal, lblBTotal, lblResult, lblPlayer, lblDealer, lblPlayerScore, lblDealerScore;
	private TextField txtBet;
	private Button btnDeal, btnBet, btnStand;
	private ImageView iviewBack;
	private StackPane root;
	private String name;
	private int score1, score2, bet, winner;
	private ImageView [] ivLeftSide;
	private ImageView [] ivRightSide;
	private Label [] lblSide;
	private Label [] lbrSide;
	private int []vLeftSide;
	private int []vRightSide;
	private Card[] lSide;
	private Card[] rSide;
	private boolean stand = false;
	private boolean getWinner = false;
	private DeckOfCards dCards;
	private int counter;
	public void start(Stage primaryStage) {
		try {

			final int SCENE_WIDTH = 1000;

			//boolean valid false for the label at the start of the program
			boolean valid = false;
			
			//setting the background of the scene
			Image imgTitle = new Image("file:images/logo_green.png");
			ImageView iviewTitle = new ImageView(imgTitle);
			
			//arrays for the imageviews of the left side and right side for the cards
			ivLeftSide = new ImageView[6];
			ivRightSide = new ImageView[6];
			vLeftSide = new int[6];
			vRightSide = new int [6];

			/*declaring array for cards on the left side and right side, as well as arrays of labels for both sides,
			and the creating a deckofcards object and shuffling it*/
			lSide = new Card[6];
			rSide = new Card[6];
			lblSide = new Label[6];
			lbrSide = new Label[6];
			dCards = new DeckOfCards(true);

			//filling each index of the left and right side with cards
			for (int i = 0; i < lSide.length; i++)
			{
				lSide[i] = new Card();
			}

			for (int j = 0; j < rSide.length; j++)
			{
				rSide[j] = new Card();
			}

			//filling each index of the left and right side with labels and setting their images to the default back of card
			for (int i = 0; i < lblSide.length; i++)
			{
				lblSide[i]= new Label();
				lblSide[i].setAlignment(Pos.TOP_CENTER);
				lblSide[i].setTextAlignment(TextAlignment.CENTER);
				lblSide[i].setContentDisplay(ContentDisplay.TOP);
				lblSide[i].setWrapText(true);
				lblSide[i].setGraphic(lSide[i].getImageView());

			}

			for (int i = 0; i < lbrSide.length; i++)
			{
				lbrSide[i]= new Label();
				lbrSide[i].setAlignment(Pos.TOP_CENTER);
				lbrSide[i].setTextAlignment(TextAlignment.CENTER);
				lbrSide[i].setContentDisplay(ContentDisplay.TOP);
				lbrSide[i].setWrapText(true);
				lbrSide[i].setGraphic(rSide[i].getImageView());

			}

			//dealing cards for each array and setting the imageviews with the labels on both the left and right side
			for (int i = 0; i < lSide.length; i++)
			{
				lSide[i] = dCards.dealCard();

				rSide[i] = dCards.dealCard();
				ivLeftSide[i] = lSide[i].getImageView();
				ivRightSide[i] = rSide[i].getImageView();
				vLeftSide[i] = lSide[i].getValue();
				vRightSide[i] = rSide[i].getValue();
			}
			
			//part of background
			iviewBack = new ImageView(new Image("file:images/card_table.jpg"));

			//creating label for player's hand total
			lblPlayerHand = new Label("Player's hand:");
			lblPlayerHand.setFont(Font.font("System", FontWeight.BOLD, 18));
			lblPlayerHand.setAlignment(Pos.CENTER);
			lblPlayerHand.setPrefSize(150, 40);
			lblPlayerHand.setTextFill(Color.WHITE);

			//creating label to say "Player's score"
			lblPlayer = new Label("Player's Score:");
			lblPlayer.setFont(Font.font("System", FontWeight.BOLD, 18));
			lblPlayer.setPrefSize(150, 40);
			lblPlayer.setTextFill(Color.WHITE);

			//creating label to display player's actual score
			lblPlayerScore = new Label(Integer.toString(0));
			lblPlayerScore.setFont(Font.font("System", FontWeight.BOLD, 18));
			lblPlayerScore.setAlignment(Pos.CENTER);
			lblPlayerScore.setPrefSize(50, 40);
			lblPlayerScore.setTextFill(Color.WHITE);

			//label to say "Dealer's hand"
			lblDealerHand = new Label("Dealer's hand:");
			lblDealerHand.setFont(Font.font("System", FontWeight.BOLD, 18));
			lblDealerHand.setAlignment(Pos.CENTER);
			lblDealerHand.setPrefSize(150, 40);
			lblDealerHand.setTextFill(Color.WHITE);

			//label to say "dealer's score"
			lblDealer = new Label("Dealer's score:");
			lblDealer.setFont(Font.font("System", FontWeight.BOLD, 18));
			lblDealer.setPrefSize(150, 40);
			lblDealer.setTextFill(Color.WHITE);

			//creating label to display player's actual score
			lblDealerScore = new Label(Integer.toString(0));
			lblDealerScore.setFont(Font.font("System", FontWeight.BOLD, 18));
			lblDealerScore.setPrefSize(50, 40);
			lblDealerScore.setTextFill(Color.WHITE);
			lblDealerScore.setAlignment(Pos.CENTER);

			//Total score for player
			lblPTotal = new Label();
			lblPTotal.setFont(Font.font("System", FontWeight.BOLD, 28));
			lblPTotal.setAlignment(Pos.CENTER);
			lblPTotal.setTextFill(Color.WHITE);
			lblPTotal.setPrefSize(150, 40);
			lblPTotal.setText(Integer.toString(0));
			lblPTotal.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, 
					BorderWidths.DEFAULT, null)));

			//Total score for dealer
			lblBTotal = new Label();
			lblBTotal.setFont(Font.font("System", FontWeight.BOLD, 28));
			lblBTotal.setAlignment(Pos.CENTER);
			lblBTotal.setTextFill(Color.WHITE);
			lblBTotal.setPrefSize(150, 40);
			lblBTotal.setText(Integer.toString(0));
			lblBTotal.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, null)));

			//Label at the bottom to show what happens
			lblResult = new Label();
			lblResult.setPrefSize(400, 40);
			lblResult.setFont(Font.font("System", FontWeight.BOLD, 18));
			lblResult.setTextFill(Color.WHITE);
			lblResult.setText("Click DEAL to begin!");
			lblResult.setAlignment(Pos.CENTER);

			//Textfield that allows player to make bet
			txtBet = new TextField();
			txtBet.setEditable(false);
			txtBet.setText(Integer.toString(0));
			txtBet.setAlignment(Pos.CENTER);
			txtBet.setPrefSize(120, 40);
			txtBet.setMaxSize(120, 40);

			try {
			//TextInputDialog at the beginning of game that allows user to enter name. Checks if it's empty in a do-while loop
			TextInputDialog input = new TextInputDialog();
			input.setTitle("Best-Pair-31");
			input.setHeaderText(null);
			input.setContentText("Welcome to Best-Pair-31!\nPlease enter your name:");
			input.setGraphic(new ImageView(new Image("file:images/cards_icon.png")));

			
			do
			{
				Optional<String> result = input.showAndWait();

				if (!result.get().isEmpty())
				{
					valid = true;
					name = result.get();
					lblPlayerHand.setText(name + "'s hand:");
					lblPlayer.setText(name + "'s score:");
				}

				else//Tells user to enter name if the field is empty
				{
					Alert error = new Alert(AlertType.ERROR);
					error.setHeaderText(null);
					error.setTitle("Error");
					error.setContentText("Please enter a name!");
					error.showAndWait();
				}
			} 
			while (!valid);
			//Catches the error when user clicks the cancel button and just normally exits the program
			}catch (NoSuchElementException e)
			{
				System.exit(0);
			}
			//counter that determines what happens whenever value is increased
			counter = 0;

			//Deal Button created when user wants to flip cards and check to see who wins the round 
			btnDeal = new Button("DEAL");
			btnDeal.setPrefSize(120, 40);
			btnDeal.setFocusTraversable(false);
			btnDeal.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {



					//Flips the first two dealt cards on the left and right side
					lblSide[0].setGraphic(ivLeftSide[0]);
					lbrSide[0].setGraphic(ivRightSide[0]);


					//Adds the total of the value of each side based on the card 
					//Score variables are created to keep track of player and dealer's score
					lblPTotal.setText(Integer.toString(vLeftSide[0]));
					lblBTotal.setText(Integer.toString(vRightSide[0]));
					score1 = 0;
					score2 = 0;
					
					//If the left side is greater than right side, left side wins. If the right side is greater than left, right side wins. If it's a tie on the face value, it checks the suit rank to determine a winner.
					if (vLeftSide[0]  > vRightSide[0])
					{

						score1++;
						lblPlayerScore.setText(Integer.toString(score1));
						lblResult.setText(name + " wins(" + score1 + ") point!");
					}

					if (vRightSide[0] > vLeftSide[0])
					{
						score2++;
						lblDealerScore.setText(Integer.toString(score2));
						lblResult.setText("Dealer wins(" + score2 + ") point!");
					}

					else if (vLeftSide[0] == vRightSide[0])
					{
						if (lSide[0].getSuit() == "DIAMONDS")
						{
							score1++;
							lblPlayerScore.setText(Integer.toString(score1));
							lblResult.setText(name + " wins(" + score1 + ") point!");
						}

						else if (rSide[0].getSuit() == "DIAMONDS")
						{
							score2++;
							lblDealerScore.setText(Integer.toString(score2));
							lblResult.setText("Dealer wins(" + score2 + ") point!");
						}

						else if (lSide[0].getSuit() == "HEARTS" && rSide[0].getSuit() != "DIAMONDS")
						{
							score1++;
							lblPlayerScore.setText(Integer.toString(score1));
							lblResult.setText(name + " wins(" + score1 + ") point!");

						}

						else if (rSide[0].getSuit() == "HEARTS" && lSide[0].getSuit() != "DIAMONDS")
						{
							score2++;
							lblDealerScore.setText(Integer.toString(score2));
							lblResult.setText("Dealer wins(" + score2 + ") point!");
						}
						else if (lSide[0].getSuit() == "SPADES" && rSide[0].getSuit() != "CLUBS")
						{
							score2++;
							lblDealerScore.setText(Integer.toString(score2));
							lblResult.setText("Dealer wins(" + score2 + ") point!");
						}

						else if (rSide[0].getSuit() == "SPADES" && lSide[0].getSuit() != "CLUBS")
						{
							score1++;
							lblPlayerScore.setText(Integer.toString(score1));
							lblResult.setText(name + " wins(" + score1 + ") point!");
						}
						else if (lSide[0].getSuit() == "CLUBS")
						{
							score2++;
							lblDealerScore.setText(Integer.toString(score2));
							lblResult.setText("Dealer wins(" + score2 + ") point(s)!");

						}

						else if (rSide[0].getSuit() == "CLUBS")
						{
							score1++;
							lblPlayerScore.setText(Integer.toString(score1));
							lblResult.setText(name + "wins(" + score1 + ") point!");

						}



					}
					//After the first round, the bet textfield is editable, the deal and stand buttons are disabled and the bet button is enabled
					txtBet.setEditable(true);
					btnDeal.setDisable(true);
					btnBet.setDisable(false);
					btnStand.setDisable(true);

					if (counter == 0) {
						//counter increases to 1
						counter++;
					}

					if(counter == 1)
					{
						//Goes to the bet method which checks to see if the provided bet is valid. After this, stand button is disable and the deal button in enabled
						bet();
						btnStand.setDisable(true);
						btnDeal.setDisable(true);
						if (score1 == 11)
						{
							winner = 1;
							endGame();
						}
						
						if (score2 >= 11)
						{
							endGame();
						}

						
						//counter increases to 2
						counter++;


					}
					//						txtBet.setEditable(false);
					//						btnDeal.setDisable(false);
					//						btnBet.setDisable(true);
					//						btnStand.setDisable(true);
					
					//when counter is 2, make the label at the bottom say "click deal" and move to the next round which is pair
					else if(counter == 2)

					{
						//						btnDeal.setOnAction(new EventHandler<ActionEvent> () {
						//							public void handle (ActionEvent f) {
						lblResult.setText("Click DEAL!");
						pair();
						if (score1 == 11)
						{
							winner = 1;
							endGame();
						}
						
						if (score2 >= 11)
						{
							endGame();
						}
//						checkGameEnd();
//						btnBet.setDisable(false);
//						btnDeal.setDisable(true);
//						txtBet.setEditable(true);
						//							}
						//						});
						//counter increases again to 3
						counter++;
					}

					else if(counter == 3)//When counter is 3, go to the bet method again but this time for round 3 which is 31
					{
						txtBet.setEditable(true);


						bet();
						if (score1 == 11)
						{
							winner = 1;
							endGame();
						}
						
						if (score2 >= 11)
						{
							endGame();
						}
						//counter goes to 4
						counter++;
					}
					else if(counter == 4)//When counter is 4, make the stand button enabled and the deal button disabled
						//If the stand button is pressed, it goes to the first stand method then keeps increasing the counter to go to the second and third stand method if a winner has not yet been decided. If not, it goes to the normal round 31 stage then increases the counter to the second and third normal round 31 stages if a winner has not been decided.
					{
						
						btnStand.setDisable(false);
						btnDeal.setDisable(false);
						if(btnStand.isPressed())
						{
							btnStand.setOnAction(new EventHandler<ActionEvent> () {
								public void handle(ActionEvent e) {
									
									stand = true;
									round31stand();
									if (score1 == 11)
									{
										winner = 1;
										endGame();
									}
									
									else if (score2 >= 11)
									{
										winner = 0;
										endGame();
									}
									counter++;
									
									if (counter ==5)
									{
										round31standtwo();
										if (score1 == 11)
										{
											winner = 1;
											endGame();
										}
										
										else if (score2 >= 11)
										{
											winner = 0;
											endGame();
										}
										counter++;
										if (counter == 6)
											
										{
											round31standthree();
											if (score1 == 11)
											{
												winner = 1;
												endGame();
											}
											
											else if (score2 >= 11)
											{
												winner = 0;
												endGame();
											}
											counter++;
										}
									}
								}
							});
					
						}
							
							else {
								round31();
								if (score1 == 11)
								{
									winner = 1;
									endGame();
								}
								
								else if (score2 >= 11)
								{
									winner = 0;
									endGame();
								}
								counter++;
								
								if(counter == 5)
								{
									round31two();
									if (score1 == 11)
									{
										winner = 1;
										endGame();
									}
									
									else if (score2 >= 11)
									{
										winner = 0;
										endGame();
									}
									counter++;
									
									if(counter == 6)
									{
										round31three();
										if (score1 == 11)
										{
											winner = 1;
											endGame();
										}
										
										else if (score2 >= 11)
										{
											winner = 0;
											endGame();
										}
									}
									
								}
							}
						}
						
//						else
//						{
//							round31();
//							if (score1 == 11)
//							{
//								winner = 1;
//								endGame();
//							}
//							
//							if (score2 >= 11)
//							{
//								endGame();
//							}
//						}
					}

//				{ if((counter == 6) && (score1 < 31) && (score1 != 31))
//					{
//						round31();
//						counter++;
//					}
//					else if((counter == 7) && (score1 < 31) && (score1 != 31))
//					{
//						round31two();
//						counter++;
//					}
//					else if((counter == 7) && (score1 < 31) && (score1 != 31))
//					{
//						round31three();
//						counter++;
//					}




					//counter++;

					//						if(counter == 4)
					//						{
					//							if(getWinner == false)
					//							{
					//							round31two();
					//							}
					//							if(getWinner == false)
					//							{
					//								round31three();
					//							}
					//						}
					//					
					//						if(stand == true)
					//						{
					//							if(getWinner == false)
					//							{
					//							round31stand();
					//							}
					//							if(getWinner == false)
					//							{
					//								round31standtwo();
					//							}
					//							
					//							if(getWinner == false)
					//							{
					//							round31standthree();
					//							}
					//							
					//							
				}

			); 

			//Creating the button bet and making it disabled at first
			btnBet = new Button("PLACE BET");
			btnBet.setPrefSize(120, 40);
			btnBet.setFocusTraversable(false);
			btnBet.setDisable(true);


			//Creating the button stand and making it disabled at first
			btnStand = new Button("STAND");
			btnStand.setPrefSize(120, 40);
			btnStand.setFocusTraversable(false);
			btnStand.setDisable(true);

			
			
			//Setting the Card's locations on the stackpane
			
			for(int i = 0; i < lSide.length; i++)
			{

				if (i == 0)
				{
					StackPane.setMargin(lblSide[0], new Insets(245, 0, 0, 
							SCENE_WIDTH / 2 - lblPTotal.getPrefWidth() - 100));
				}

				if (i == 1)
				{
					StackPane.setMargin(lblSide[1], new Insets(325, 0, 0, SCENE_WIDTH / 2 - lblPTotal.getPrefWidth() - 125));
				}

				if (i == 2)
				{
					StackPane.setMargin(lblSide[2], new Insets(325, 0, 0, SCENE_WIDTH / 2 - lblPTotal.getPrefWidth() - 70));

				}

				if (i == 3)
				{
					StackPane.setMargin(lblSide[3], new Insets(405, 0, 0, SCENE_WIDTH / 2 - lblPTotal.getPrefWidth() - 160));
				}

				if (i == 4)
				{
					StackPane.setMargin(lblSide[4], new Insets(405, 0, 0, SCENE_WIDTH / 2 - lblPTotal.getPrefWidth() - 100));
				}

				if (i == 5)
				{
					StackPane.setMargin(lblSide[5], new Insets(405, 0, 0, SCENE_WIDTH / 2 - lblPTotal.getPrefWidth() - 40));
				}
			}



			//declaring the stackpane, adding all the components to it, labels, textfields and buttons
			root = new StackPane();
			root.setPadding(new Insets(0, 0, 0, 0));
			root.setAlignment(Pos.TOP_LEFT);
			StackPane.setMargin(iviewTitle, new Insets(10, 0, 0, SCENE_WIDTH / 2 - imgTitle.getWidth() / 2));
			StackPane.setMargin(lblPlayer, new Insets(175, 0, 0, 780));
			StackPane.setMargin(lblPlayerScore, new Insets(175, 0, 0, 935));
			StackPane.setMargin(lblDealer, new Insets(225, 0, 0, 780));
			StackPane.setMargin(lblDealerScore, new Insets(225, 0, 0, 935));
			StackPane.setMargin(lblPlayerHand, new Insets(130, 0, 0, 250));
			StackPane.setMargin(lblDealerHand, new Insets(130, 0, 0, 600));
			StackPane.setMargin(lblPTotal, new Insets(175, 0, 0, 250));
			StackPane.setMargin(lblBTotal, new Insets(175, 0, 0, 600));
			StackPane.setMargin(lblResult, new Insets(615, 0, 0, SCENE_WIDTH / 2 - lblResult.getPrefWidth() / 2));
			StackPane.setMargin(txtBet, new Insets(175, 0, 0, SCENE_WIDTH / 2 - txtBet.getPrefWidth() / 2));
			StackPane.setMargin(btnBet, new Insets(225, 0, 0, SCENE_WIDTH / 2 - btnBet.getPrefWidth() / 2));
			StackPane.setMargin(btnDeal, new Insets(275, 0, 0, SCENE_WIDTH / 2 - btnDeal.getPrefWidth() / 2));
			StackPane.setMargin(btnStand, new Insets(325, 0, 0, SCENE_WIDTH / 2 - btnDeal.getPrefWidth() / 2));

			//Placing all the cards on the right side accordingly on the stackpane
			for(int j = 0; j < rSide.length; j++)
			{
				

				if (j == 0)
				{
					StackPane.setMargin(lbrSide[0], new Insets(245, 0, 0, SCENE_WIDTH / 2 + lblBTotal.getPrefWidth() - 45));
				}

				if (j == 1)
				{
					StackPane.setMargin(lbrSide[1], new Insets(325, 0, 0, SCENE_WIDTH / 2 + lblBTotal.getPrefWidth() - 70));
				}

				if (j == 2)
				{
					StackPane.setMargin(lbrSide[2], new Insets(325, 0, 0, SCENE_WIDTH / 2 + lblBTotal.getPrefWidth() - 20));					
				}

				if (j == 3)
				{
					StackPane.setMargin(lbrSide[3], new Insets(405, 0, 0, SCENE_WIDTH / 2 + lblBTotal.getPrefWidth() - 105));
				}

				if (j == 4)
				{
					StackPane.setMargin(lbrSide[4], new Insets(405, 0, 0, SCENE_WIDTH / 2 + lblBTotal.getPrefWidth() - 40));
				}

				if (j == 5)
				{
					StackPane.setMargin(lbrSide[5], new Insets(405, 0, 0, SCENE_WIDTH / 2 + lblBTotal.getPrefWidth() + 15));
				}
			}

			//Adding all components to stack pane root and creating a scene
			root.getChildren().addAll(iviewBack, iviewTitle, lblPlayer, lblPlayerScore, lblDealer, lblDealerScore, lblPlayerHand, lblDealerHand, lblPTotal, lblBTotal, lblResult, txtBet,
					btnBet, btnDeal, btnStand, lblSide[0],lblSide[1],lblSide[2],lblSide[3],lblSide[4],lblSide[5],lbrSide[0],lbrSide[1],lbrSide[2],lbrSide[3],lbrSide[4],lbrSide[5]);

			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			primaryStage.setTitle("Best-Pair-31");
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch(Exception e) {
			e.printStackTrace();
		}}

//The method that occurs if the dealer has to draw a sixth card when his hand is less than 31. This occurs when the player clicks stand.
	//It checks to see if the dealer's hand is greater than the players hand but less than 31, it checks to see if the hand is 31, and if it is greater than 31
	//In the first two situations, the dealer wins. In the third condition, the player wins.
	private void round31standthree() {
		lbrSide[5].setGraphic(ivRightSide[5]);

		if(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] + vLeftSide[3] < vRightSide[0] + vRightSide[1] + vRightSide[2] + vRightSide[3] + vRightSide[4] + vRightSide[5]&& vRightSide[0] + vRightSide[1] + vRightSide[2] + vRightSide[3] + vRightSide[4] + vRightSide[5]<  31)
		{
			Alert dealerW = new Alert(AlertType.CONFIRMATION);
			dealerW.setHeaderText(null);
			dealerW.setTitle("Best-Pair-31");
			dealerW.setContentText("Dealer wins the hand!");
			dealerW.showAndWait();
			score2+= bet;
			lblDealerScore.setText(Integer.toString(score2));
			lblResult.setText("Dealer wins " + bet + " point(s)");
			lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2] + vRightSide[3] + vRightSide[4] + vRightSide[5]));
			
			if (score2 >= 11)
			{
				winner = 0;
				endGame();
			}
			else
			{
				reshuffleDeck();
			}
		}

		else if (vRightSide[0] + vRightSide[1] + vRightSide[2] + vRightSide[3] + vRightSide[4] + vRightSide[5] == 31)
		{
			Alert dealerW = new Alert(AlertType.CONFIRMATION);
			dealerW.setHeaderText(null);
			dealerW.setTitle("Best-Pair-31");
			dealerW.setContentText("Dealer wins the hand!");
			dealerW.showAndWait();
			score2+= bet;
			lblDealerScore.setText(Integer.toString(score2));
			lblResult.setText("Dealer wins " + bet + " point(s)");
			lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2] + vRightSide[3] + vRightSide[4] + vRightSide[5]));
			if (score2 >= 11)
			{
				winner = 0;
				endGame();
			}
			else
			{
				reshuffleDeck();
			}
		}

		else if(vRightSide[0] + vRightSide[1] + vRightSide[2] + vRightSide[3] + vRightSide[4] + vRightSide[5]> 31)
		{
			Alert playerW = new Alert(AlertType.CONFIRMATION);
			playerW.setHeaderText(null);
			playerW.setTitle("Best-Pair-31");
			playerW.setContentText("Player wins the hand!");
			playerW.showAndWait();
			score1+= bet;
			lblPlayerScore.setText(Integer.toString(score1));
			lblResult.setText(name + " wins " + bet + " point(s)!");
			lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2] + vRightSide[3] + vRightSide[4] + vRightSide[5]));
			if (score1 == 11)
			{
				winner = 1;
				endGame();
			}
			else
			{
				reshuffleDeck();
			}
		}
	}


	//The method that occurs if the dealer has to draw a fifth card when his hand is less than 31. This occurs when the player clicks stand.
		//It checks to see if the dealer's hand is greater than the players hand but less than 31, it checks to see if the hand is 31, and if it is greater than 31
		//In the first two situations, the dealer wins. In the third condition, the player wins.
	private void round31standtwo() {
		lbrSide[4].setGraphic(ivRightSide[4]);

		if(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] + vLeftSide[3] < vRightSide[0] + vRightSide[1] + vRightSide[2] + vRightSide[3] + vRightSide[4] && vRightSide[0] + vRightSide[1] + vRightSide[2] + vRightSide[3] + vRightSide[4] <  31)
		{
			Alert dealerW = new Alert(AlertType.CONFIRMATION);
			dealerW.setHeaderText(null);
			dealerW.setTitle("Best-Pair-31");
			dealerW.setContentText("Dealer wins the hand!");
			dealerW.showAndWait();
			score2+= bet;
			lblDealerScore.setText(Integer.toString(score2));
			lblResult.setText("Dealer wins " + bet + " point(s)");
			lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2] + vRightSide[3] + vRightSide[4]));
			if (score2 >= 11)
			{
				winner = 0;
				endGame();
			}
			else
			{
				reshuffleDeck();
			}
		}

		else if (vRightSide[0] + vRightSide[1] + vRightSide[2] + vRightSide[3] + vRightSide[4]  == 31)
		{
			Alert dealerW = new Alert(AlertType.CONFIRMATION);
			dealerW.setHeaderText(null);
			dealerW.setTitle("Best-Pair-31");
			dealerW.setContentText("Dealer wins the hand!");
			dealerW.showAndWait();
			score2+= bet;
			lblDealerScore.setText(Integer.toString(score2));
			lblResult.setText("Dealer wins " + bet + " point(s)");
			lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2] + vRightSide[3] + vRightSide[4]));
			if (score2 >= 11)
			{
				winner = 0;
				endGame();
			}
			else
			{
				reshuffleDeck();
			}
		}

		else if(vRightSide[0] + vRightSide[1] + vRightSide[2] + vRightSide[3] + vRightSide[4] > 31)
		{
			Alert playerW = new Alert(AlertType.CONFIRMATION);
			playerW.setHeaderText(null);
			playerW.setTitle("Best-Pair-31");
			playerW.setContentText("Player wins the hand!");
			playerW.showAndWait();
			score1+= bet;
			lblPlayerScore.setText(Integer.toString(score1));
			lblResult.setText(name + " wins " + bet + " point(s)!");
			lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2] + vRightSide[3] + vRightSide[4]));
			if (score1 == 11)
			{
				winner = 1;
				endGame();
			}
			else
			{
				reshuffleDeck();
			}}
	}


	//The method that occurs if the dealer has to draw a fourth card when his hand is less than 31. This occurs when the player clicks stand.
		//It checks to see if the dealer's hand is greater than the players hand but less than 31, it checks to see if the hand is 31, and if it is greater than 31
		//In the first two situations, the dealer wins. In the third condition, the player wins.
	private void round31stand() {

		btnDeal.setDisable(true);
		btnBet.setDisable(true);
		txtBet.setEditable(false);
		lbrSide[3].setGraphic(ivRightSide[3]);

		if(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] + vLeftSide[3] < vRightSide[0] + vRightSide[1] + vRightSide[2] + vRightSide[3] && vRightSide[0] + vRightSide[1] + vRightSide[2] + vRightSide[3] <  31)
		{
			Alert dealerW = new Alert(AlertType.CONFIRMATION);
			dealerW.setHeaderText(null);
			dealerW.setTitle("Best-Pair-31");
			dealerW.setContentText("Dealer wins the hand!");
			dealerW.showAndWait();
			score2+= bet;
			lblDealerScore.setText(Integer.toString(score2));
			lblResult.setText("Dealer wins " + bet + " point(s)");
			lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2] + vRightSide[3]));
			if (score2 >= 11)
			{
				winner = 0;
				endGame();
			}
			else
			{
				reshuffleDeck();
			}
		}

		else if (vRightSide[0] + vRightSide[1] + vRightSide[2] + vRightSide[3]  == 31)
		{
			Alert dealerW = new Alert(AlertType.CONFIRMATION);
			dealerW.setHeaderText(null);
			dealerW.setTitle("Best-Pair-31");
			dealerW.setContentText("Dealer wins the hand!");
			dealerW.showAndWait();
			score2+= bet;
			lblDealerScore.setText(Integer.toString(score2));
			lblResult.setText("Dealer wins " + bet + " point(s)");
			lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2] + vRightSide[3]));
			if (score2 >= 11)
			{
				winner = 0;
				endGame();
			}
			else
			{
				reshuffleDeck();
			}
		}

		else if(vRightSide[0] + vRightSide[1] + vRightSide[2] + vRightSide[3] > 31)
		{
			Alert playerW = new Alert(AlertType.CONFIRMATION);
			playerW.setHeaderText(null);
			playerW.setTitle("Best-Pair-31");
			playerW.setContentText("Player wins the hand!");
			playerW.showAndWait();
			score1+= bet;
			lblPlayerScore.setText(Integer.toString(score1));
			lblResult.setText(name + " wins " + bet + " point(s)!");
			lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2] + vRightSide[3]));
			if (score1 == 11)
			{
				winner = 1;
				endGame();
			}
			else
			{
				reshuffleDeck();
			}
		}
	}

	//This method occurs in the 31 stage. It deals the last card for the player , until they reach 31 and wins. If the player's hand is 31, they win. If it's less than 31 and less than the dealer's hand, or over 31 they lose
	//If the dealer's hand is 31, they win. If the hands are equal to 31 but the score is not 11, then the decks are reshuffled and everything is reset except for the player's scores.
	private void round31three() {
		lblSide[5].setGraphic(ivLeftSide[5]);
		

		lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] + vLeftSide[3] + vLeftSide[4] + vLeftSide[5]));
		

		if(Integer.parseInt(lblPTotal.getText()) == 31)
		{

			lblPlayerScore.setText(Integer.toString(score1 + bet));
			lblResult.setText(name + " wins(" + bet + ") point(s)!");
			Alert dealerW0 = new Alert(AlertType.CONFIRMATION);
			dealerW0.setHeaderText(null);
			dealerW0.setTitle("Best-Pair-31");
			dealerW0.setContentText(name + " wins the hand!");
			dealerW0.showAndWait();
			if (score1 == 11)
			{
				winner = 1;
				endGame();
			}
			else
			{
				reshuffleDeck();
			}
		}

		else if(Integer.parseInt(lblBTotal.getText()) == 31)
		{

			lblDealerScore.setText(Integer.toString(score2 + bet));
			lblResult.setText("Dealer wins(" + bet + ") point(s)!");
			Alert dealerW1 = new Alert(AlertType.CONFIRMATION);
			dealerW1.setHeaderText(null);
			dealerW1.setTitle("Best-Pair-31");
			dealerW1.setContentText("Dealer wins the hand!");
			dealerW1.showAndWait();

			if (score2 >= 11)
			{
				winner = 0;
				endGame();
			}
			else
			{
				reshuffleDeck();
			}
		}

		else if((31 - Integer.parseInt(lblPTotal.getText())) > (31 - Integer.parseInt(lblBTotal.getText())))
		{

			lblDealerScore.setText(Integer.toString(score2 + bet));
			lblResult.setText("Dealer wins(" + bet + ") point(s)!");
			//			lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] + vLeftSide[3]));
			//			lblBTotal.setText(Integer.toString(vRightSide[0] + vLeftSide[2] + vLeftSide[2] + vLeftSide[3]));
			if (score2 >= 11)
			{
				winner = 0;
				endGame();
			}
			else
			{
				reshuffleDeck();
			}
		}

		else if(31 - Integer.parseInt(lblPTotal.getText()) > 31 - Integer.parseInt(lblBTotal.getText()))
		{

			lblPlayerScore.setText(Integer.toString(score1 + bet));
			lblResult.setText(name + " wins(" + bet + ") point(s)!");
			//			lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] + vLeftSide[3]));
			//			lblBTotal.setText(Integer.toString(vRightSide[0] + vLeftSide[2] + vLeftSide[2] + vLeftSide[3]));
			if (score1 == 11)
			{
				winner = 1;
				endGame();
			}
			else
			{
				reshuffleDeck();
			}
		}

		else if(Integer.parseInt(lblPTotal.getText()) > 31)
		{
			lblDealerScore.setText(Integer.toString(score2 + bet));
			lblResult.setText("Dealer wins(" + bet + ") point(s)!");
			//			lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] + vLeftSide[3]));
			//			lblBTotal.setText(Integer.toString(vRightSide[0] + vLeftSide[2] + vLeftSide[2] + vLeftSide[3]));
			Alert dealerW = new Alert(AlertType.CONFIRMATION);
			dealerW.setHeaderText(null);
			dealerW.setTitle("Best-Pair-31");
			dealerW.setContentText("Dealer wins the hand!");
			dealerW.showAndWait();
			if (score2 >= 11)
			{
				winner = 0;
				endGame();
			}
			else
			{
				reshuffleDeck();
			}
		}

	}


	//This method occurs in the 31 stage. It deals the fifth card for the player , until they reach 31 and wins. If the player's hand is 31, they win. If it's less than 31 and less than the dealer's hand, or over 31 they lose
		//If the dealer's hand is 31, they win. If the hands are equal to 31 but the score is not 11, then the decks are reshuffled and everything is reset except for the player's scores.
	private void round31two() {
		lblSide[4].setGraphic(ivLeftSide[4]);
		
		lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] + vLeftSide[3] + vLeftSide[4]));
		
		if(Integer.parseInt(lblPTotal.getText()) == 31)
		{
			winner = 1;
			lblPlayerScore.setText(Integer.toString(score1 + bet));
			lblResult.setText(name + " wins(" + bet + ") point(s)!");
			Alert dealerW0 = new Alert(AlertType.CONFIRMATION);
			dealerW0.setHeaderText(null);
			dealerW0.setTitle("Best-Pair-31");
			dealerW0.setContentText(name + " wins the hand!");
			dealerW0.showAndWait();
			if (score1 == 11)
			{
				winner = 1;
				endGame();
			}
			else
			{
				reshuffleDeck();
			}
		}

		else if(Integer.parseInt(lblBTotal.getText()) == 31)
		{
			winner = 0;
			lblDealerScore.setText(Integer.toString(score2 + bet));
			lblResult.setText("Dealer wins(" + bet + ") point(s)!");
			Alert dealerW1 = new Alert(AlertType.CONFIRMATION);
			dealerW1.setHeaderText(null);
			dealerW1.setTitle("Best-Pair-31");
			dealerW1.setContentText("Dealer wins the hand!");
			dealerW1.showAndWait();

			if (score2 >= 11)
			{
				winner = 0;
				endGame();
			}

			else
			{
				reshuffleDeck();
			}
		}

		else if((31 - Integer.parseInt(lblPTotal.getText())) > (31 - Integer.parseInt(lblBTotal.getText())))
		{

			lblDealerScore.setText(Integer.toString(score2 + bet));
			lblResult.setText("Dealer wins(" + bet + ") point(s)!");
			//			lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] + vLeftSide[3]));
			//			lblBTotal.setText(Integer.toString(vRightSide[0] + vLeftSide[2] + vLeftSide[2] + vLeftSide[3]));
			if (score2 >= 11)
			{
				winner = 0;
				endGame();
			}
			else
			{
				reshuffleDeck();
			}
		}

		else if(31 - Integer.parseInt(lblPTotal.getText()) > 31 - Integer.parseInt(lblBTotal.getText()))
		{

			lblPlayerScore.setText(Integer.toString(score1 + bet));
			lblResult.setText(name + " wins(" + bet + ") point(s)!");
			//			lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] + vLeftSide[3]));
			//			lblBTotal.setText(Integer.toString(vRightSide[0] + vLeftSide[2] + vLeftSide[2] + vLeftSide[3]));
			if (score1 == 11)
			{
				winner = 1;
				endGame();
			}
			else
			{
				reshuffleDeck();
			}
		}

		else if(Integer.parseInt(lblPTotal.getText()) > 31)
		{
			lblDealerScore.setText(Integer.toString(score2 + bet));
			lblResult.setText("Dealer wins(" + bet + ") point(s)!");
			//			lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] + vLeftSide[3]));
			//			lblBTotal.setText(Integer.toString(vRightSide[0] + vLeftSide[2] + vLeftSide[2] + vLeftSide[3]));
			Alert dealerW = new Alert(AlertType.CONFIRMATION);
			dealerW.setHeaderText(null);
			dealerW.setTitle("Best-Pair-31");
			dealerW.setContentText("Dealer wins the hand!");
			dealerW.showAndWait();
			if (score2 >= 11)
			{
				winner = 0;
				endGame();
			}
			else
			{
				reshuffleDeck();
			}
		}


	}
	
	
	//Resets the entire game after a winner has been determined. Resets counter, deckofcards, scores and hand scores, and makes the cards face down.
	private void resetGame() {
		counter = 0;
		dCards = new DeckOfCards(true);
		lblPlayerScore.setText(Integer.toString(0));
		lblDealerScore.setText(Integer.toString(0));
		lblPTotal.setText(Integer.toString(0));
		lblBTotal.setText(Integer.toString(0));
		lblResult.setText("Click DEAL to begin!");
		txtBet.setEditable(false);
		txtBet.setText(Integer.toString(0));
		btnBet.setDisable(true);
		btnStand.setDisable(true);
		btnDeal.setDisable(false);
		for(int i = 0; i < lSide.length; i++)
		{
			lSide[i] = new Card();
			rSide[i] = new Card();
			lblSide[i].setGraphic(lSide[i].getImageView());
			lbrSide[i].setGraphic(rSide[i].getImageView());
		}
	}
	
//	private void checkGameEnd() {
//		if(score1 == 11)
//		{
//			lblResult.setText(name + " wins the game!");
//			Alert gameDone = new Alert(AlertType.ERROR);
//			gameDone.setHeaderText(null);
//			gameDone.setTitle("Game Over");
//			gameDone.setContentText("GAME OVER!\n" + name + " wins " + score1 + "-" + score2 + ".\n Would you like to play again?");
//			gameDone.showAndWait();
//			gameDone.getButtonTypes().clear();
//			gameDone.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
//			Optional<ButtonType> input = gameDone.showAndWait();
//			if (input.get() == ButtonType.YES)
//			{
//				
//				resetGame();
//			}
//			else
//			{
//				
//				System.exit(0);
//			}
//			getWinner = true;
//		}
//
//		else if(score2 >= 11)
//		{
//			lblResult.setText("Dealer wins the game!");
//			Alert gameDone2 = new Alert(AlertType.ERROR);
//			gameDone2.setHeaderText(null);
//			gameDone2.setTitle("Game Over");
//			gameDone2.setContentText("GAME OVER!\nDealer wins " + score2 + "-" + score1 + ".\n Would you like to play again?");
//			gameDone2.showAndWait();
//			gameDone2.getButtonTypes().clear();
//			gameDone2.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
//			Optional<ButtonType> input2 = gameDone2.showAndWait();
//			if (input2.get() == ButtonType.YES)
//			{
//				
//				resetGame();
//			}
//			else
//			{
//				
//				System.exit(0);
//			}
//			
//			
//			getWinner = true;
//		}
//
//	}

	//Reshuffles the deck after either the player or the dealer wins the hand. Resets everything but keeps scores for both player and dealer
	private void reshuffleDeck()
	{
		counter = 0;
		dCards = new DeckOfCards(true);
		lblPTotal.setText(Integer.toString(0));
		lblBTotal.setText(Integer.toString(0));
		lblResult.setText("Click DEAL to begin!");
		txtBet.setEditable(false);
		txtBet.setText(Integer.toString(0));
		btnBet.setDisable(true);
		btnStand.setDisable(true);
		btnDeal.setDisable(false);
		
		for(int i = 0; i < lSide.length; i++)
		{
			lSide[i] = new Card();
			rSide[i] = new Card();
			lblSide[i].setGraphic(lSide[i].getImageView());
			lbrSide[i].setGraphic(rSide[i].getImageView());
			ivLeftSide[i] = lSide[i].getImageView();
			ivRightSide[i] = rSide[i].getImageView();
			vLeftSide[i] = lSide[i].getValue();
			vRightSide[i] = rSide[i].getValue();
		}
		
	}
	
	//This method occurs in the 31 stage. It deals the fourth for the player , until they reach 31 and wins. If the player's hand is 31, they win. If it's less than 31 and less than the dealer's hand, or over 31 they lose
		//If the dealer's hand is 31, they win. If the hands are equal to 31 but the score is not 11, then the decks are reshuffled and everything is reset except for the player's scores.
	private void round31() {

		lblSide[3].setGraphic(ivLeftSide[3]);
		
		lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] + vLeftSide[3]));
		

		if(Integer.parseInt(lblPTotal.getText()) == 31)
		{

			lblPlayerScore.setText(Integer.toString(score1 + bet));
			lblResult.setText(name + " wins(" + bet + ") point(s)!");
			Alert dealerW0 = new Alert(AlertType.CONFIRMATION);
			dealerW0.setHeaderText(null);
			dealerW0.setTitle("Best-Pair-31");
			dealerW0.setContentText(name + " wins the hand!");
			dealerW0.showAndWait();
			if (score1 == 11)
			{
				winner = 1;
				endGame();
			}
			else
			{
				
				reshuffleDeck();
			}
		}

		else if(Integer.parseInt(lblBTotal.getText()) == 31)
		{

			lblDealerScore.setText(Integer.toString(score2 + bet));
			lblResult.setText("Dealer wins(" + bet + ") point(s)!");
			Alert dealerW1 = new Alert(AlertType.CONFIRMATION);
			dealerW1.setHeaderText(null);
			dealerW1.setTitle("Best-Pair-31");
			dealerW1.setContentText("Dealer wins the hand!");
			dealerW1.showAndWait();
			if (score2 == 11)
			{
				winner = 0;
				endGame();
			}
			else
			{
				
				reshuffleDeck();
			}
		}

		else if((31 - Integer.parseInt(lblPTotal.getText())) > (31 - Integer.parseInt(lblBTotal.getText())))
		{

			lblDealerScore.setText(Integer.toString(score2 + bet));
			lblResult.setText("Dealer wins(" + bet + ") point(s)!");
			//			lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] + vLeftSide[3]));
			//			lblBTotal.setText(Integer.toString(vRightSide[0] + vLeftSide[2] + vLeftSide[2] + vLeftSide[3]));
			if (score2 == 11)
			{
				
				winner = 0;
				endGame();
			}
			else
			{
				reshuffleDeck();
			}
		}

		else if(31 - Integer.parseInt(lblPTotal.getText()) > 31 - Integer.parseInt(lblBTotal.getText()))
		{

			lblPlayerScore.setText(Integer.toString(score1 + bet));
			lblResult.setText(name + " wins(" + bet + ") point(s)!");
			//			lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] + vLeftSide[3]));
			//			lblBTotal.setText(Integer.toString(vRightSide[0] + vLeftSide[2] + vLeftSide[2] + vLeftSide[3]));
			if (score1 == 11)
			{
				winner = 1;
				endGame();
			}
			else
			{
				reshuffleDeck();
			}
		}

		else if(Integer.parseInt(lblPTotal.getText()) > 31)
		{
			lblDealerScore.setText(Integer.toString(score2 + bet));
			lblResult.setText("Dealer wins(" + bet + ") point(s)!");
			Alert dealerW = new Alert(AlertType.CONFIRMATION);
			dealerW.setHeaderText(null);
			dealerW.setTitle("Best-Pair-31");
			dealerW.setContentText("Dealer wins the hand!");
			dealerW.showAndWait();
			//			lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] + vLeftSide[3]));
			//			lblBTotal.setText(Integer.toString(vRightSide[0] + vLeftSide[2] + vLeftSide[2] + vLeftSide[3]));
			if (score2 == 11)
			{
				winner = 0;
				endGame();
			}
			else
			{
				reshuffleDeck();
			}
		}
	}


	//A method that determines if the player or dealer won the game. If the player won the game then winner is 1 and it outputs a message for the player. If the dealer won the game then winner is equal to 0 and outputs a win message for the dealer.
	private void endGame() {

		if (winner == 1)
		{
			lblResult.setText(name + " wins the game!");
			Alert gameDone = new Alert(AlertType.ERROR);
			gameDone.setHeaderText(null);
			gameDone.setTitle("Game Over");
			gameDone.setContentText("GAME OVER!\n" + name + " wins " + score1 + "-" + score2 + ".\n Would you like to play again?");
			gameDone.showAndWait();
			gameDone.getButtonTypes().clear();
			gameDone.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
			Optional<ButtonType> input = gameDone.showAndWait();
			if (input.get() == ButtonType.YES)
			{
				
				resetGame();
			}
			else
			{
				
				System.exit(0);
			}
		}

		else if (winner == 0)
		{
			Alert gameOver1 = new Alert(AlertType.ERROR);
			gameOver1.setHeaderText(null);
			gameOver1.setTitle("Game Over");
			gameOver1.setContentText("GAME OVER!\n Dealer wins " + score1 + "-" + score2 + ".\n Would you like to play again?");
			gameOver1.showAndWait();
			gameOver1.getButtonTypes().clear();
			gameOver1.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
			Optional<ButtonType> input = gameOver1.showAndWait();
			if (input.get() == ButtonType.YES)
			{
				
				resetGame();
			}
			else
			{
				
				System.exit(0);
			}
		}
	}


//Method that accounts for stage two, the pair stage. Compares the 2nd and 3rd cards on both sides to see if they are pairs then ranks by suit, then gets the highest individual cards from both sides and ranks them by suits. A winner is determined by the highest value card, or the highest suit, or highest value pair.
	private void pair() {

		txtBet.setEditable(true);
		btnDeal.setDisable(true);
		btnBet.setDisable(false);
		btnStand.setDisable(true);

		lblSide[1].setGraphic(ivLeftSide[1]);
		lbrSide[1].setGraphic(ivRightSide[1]);
		lblSide[2].setGraphic(ivLeftSide[2]);
		lbrSide[2].setGraphic(ivRightSide[2]);

		//PAIRS
		if(vLeftSide[1] == vLeftSide[2] && vRightSide[1] == vRightSide[2])
		{
			if(vLeftSide[1]  > vRightSide[1] )
			{
				score1+= bet;
				lblPlayerScore.setText(Integer.toString(score1));
				lblResult.setText(name + " wins " + bet + " point(s)!");
				lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
				lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
			}

			else if(vLeftSide[1]  < vRightSide[1] )
			{
				score2+= bet;
				lblDealerScore.setText(Integer.toString(score2));
				lblResult.setText("Dealer wins " + bet + " point(s)");
				lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
				lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
			}
			
			else if(vLeftSide[1] == vRightSide[1] )
			{
				if((lSide[1].getSuit() == "DIAMONDS") || (lSide[2].getSuit() ==  "DIAMONDS"))
				{
					score1+= bet;
					lblPlayerScore.setText(Integer.toString(score1));
					lblResult.setText(name + " wins " + bet + " point(s)!");
					lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
					lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
				}
				else if((rSide[1].getSuit() == "DIAMONDS") || (rSide[2].getSuit() ==  "DIAMONDS"))
				{
					score2+= bet;
					lblDealerScore.setText(Integer.toString(score2));
					lblResult.setText("Dealer wins " + bet + " point(s)!");
					lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
					lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
				}
			}
		}

		//HIGHEST CARD FOR THE LEFT SIDE
		else if((lSide[1].getRank() > rSide[2].getRank()) && (lSide[1].getRank() > rSide[1].getRank()))
		{
			score1+= bet;
			lblPlayerScore.setText(Integer.toString(score1));
			lblResult.setText(name + " wins " + bet + " point(s)!");
			lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
			lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
			
//			if(lSide[1].getSuit() == "DIAMONDS")
//			{
//				score1+= bet;
//				lblPlayerScore.setText(Integer.toString(score1));
//				lblResult.setText(name + " wins " + bet + " point(s)!");
//				lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
//				lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
//			}
//
//			else if(rSide[1].getSuit() == "DIAMONDS")
//			{
//				score2+= bet;
//				lblDealerScore.setText(Integer.toString(score2));
//				lblResult.setText("Dealer wins " + bet + " point(s)!");
//				lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
//				lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
//			}
//
//			else if(lSide[1].getSuit() == "CLUBS")
//			{
//				score2+= bet;
//				lblDealerScore.setText(Integer.toString(score2));
//				lblResult.setText("Dealer wins " + bet + " point(s)");
//				lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
//				lblBTotal.setText(Integer.toString(vRightSide[0] + vLeftSide[1] + vLeftSide[2]));
//			}
//
//			else if(rSide[1].getSuit() == "CLUBS")
//			{
//				score1+= bet;
//				lblPlayerScore.setText(Integer.toString(score1));
//				lblResult.setText(name + " wins " + bet + " point(s)!");
//				lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
//				lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
//			}
//
//			else if(lSide[1].getSuit() == "HEARTS" && rSide[1].getSuit() != "DIAMONDS")
//			{
//				score1+= bet;
//				lblPlayerScore.setText(Integer.toString(score1));
//				lblResult.setText(name + " wins " + bet + " point(s)!");
//				lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
//				lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
//			}
//
//			else if(rSide[1].getSuit() == "HEARTS" && lSide[1].getSuit() != "DIAMONDS")
//			{
//				score2+= bet;
//				lblDealerScore.setText(Integer.toString(score2));
//				lblResult.setText("Dealer wins " + bet + " point(s)");
//				lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
//				lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
//			}
//
//			else if(rSide[1].getSuit() == "SPADES" && lSide[1].getSuit() != "CLUBS")
//			{
//				score1+= bet;
//				lblPlayerScore.setText(Integer.toString(score1));
//				lblResult.setText(name + " wins " + bet + " point(s)!");
//				lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
//				lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
//			}
//
//			else if(lSide[1].getSuit() == "SPADES" && rSide[1].getSuit()!= "CLUBS" )
//			{
//				score2+= bet;
//				lblDealerScore.setText(Integer.toString(score2));
//				lblResult.setText("Dealer wins " + bet + " point(s)!");
//				lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
//				lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
//			}
		}
		
		//HIGHEST CARD ON THE LEFT SIDE
		else if(lSide[2].getRank() > rSide[2].getRank() && lSide[2].getRank() > rSide[1].getRank())
		{
			score1+= bet;
			lblPlayerScore.setText(Integer.toString(score1));
			lblResult.setText(name + " wins " + bet + " point(s)!");
			lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
			lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
			
//			if(lSide[1].getSuit() == "DIAMONDS")
//			{
//				score1+= bet;
//				lblPlayerScore.setText(Integer.toString(score1));
//				lblResult.setText(name + " wins " + bet + " point(s)!");
//				lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
//				lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
//			}
//
//			else if(rSide[2].getSuit() == "DIAMONDS")
//			{
//				score2+= bet;
//				lblDealerScore.setText(Integer.toString(score2));
//				lblResult.setText("Dealer wins " + bet + " point(s)");
//				lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
//				lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
//			}
//
//			else if(lSide[1].getSuit() == "CLUBS")
//			{
//				score2+= bet;
//				lblDealerScore.setText(Integer.toString(score2));
//				lblResult.setText("Dealer wins " + bet + " point(s)");
//				lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
//				lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
//			}
//
//			else if(rSide[2].getSuit() == "CLUBS")
//			{
//				score1+= bet;
//				lblPlayerScore.setText(Integer.toString(score1));
//				lblResult.setText(name + " wins " + bet + " point(s)!");
//				lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
//				lblBTotal.setText(Integer.toString(vRightSide[0] + vLeftSide[1] + vLeftSide[2]));
//			}
//
//			else if(lSide[1].getSuit() == "HEARTS" && rSide[2].getSuit() != "DIAMONDS")
//			{
//				score1+= bet;
//				lblPlayerScore.setText(Integer.toString(score1));
//				lblResult.setText(name + " wins " + bet + " point(s)!");
//				lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
//				lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
//			}
//
//			else if(rSide[2].getSuit() == "HEARTS" && lSide[1].getSuit() != "DIAMONDS")
//			{
//				score2+= bet;
//				lblDealerScore.setText(Integer.toString(score2));
//				lblResult.setText("Dealer wins " + bet + " point(s)");
//				lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
//				lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
//			}
//
//			else if(rSide[2].getSuit() == "SPADES" && lSide[1].getSuit() != "CLUBS")
//			{
//				score1+= bet;
//				lblPlayerScore.setText(Integer.toString(score1));
//				lblResult.setText(name + " wins " + bet + " point(s)!");
//				lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
//				lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
//			}
//
//			else if(lSide[1].getSuit() == "SPADES" && rSide[2].getSuit()!= "CLUBS" )
//			{
//				score2+= bet;
//				lblDealerScore.setText(Integer.toString(score2));
//				lblResult.setText("Dealer wins " + bet + " point(s)");
//				lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
//				lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
//			}
		}

		//HIGHEST CARD ON THE RIGHT SIDE
		else if(rSide[2].getRank() > lSide[2].getRank() && rSide[2].getRank() > lSide[1].getRank())
		{
			score2+= bet;
			lblDealerScore.setText(Integer.toString(score2));
			lblResult.setText("Dealer wins " + bet + " point(s)");
			lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
			lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
			
//			if(lSide[2].getSuit() == "DIAMONDS")
//			{
//				score1+= bet;
//				lblPlayerScore.setText(Integer.toString(score1));
//				lblResult.setText(name + " wins " + bet + " point(s)!");
//				lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
//				lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
//			}
//
//			else if(rSide[1].getSuit() == "DIAMONDS")
//			{
//				score2+= bet;
//				lblDealerScore.setText(Integer.toString(score2));
//				lblResult.setText("Dealer wins " + bet + " point(s)");
//				lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
//				lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
//			}
//
//			else if(lSide[2].getSuit() == "CLUBS")
//			{
//				score2+= bet;
//				lblDealerScore.setText(Integer.toString(score2));
//				lblResult.setText("Dealer wins " + bet + " point(s)");
//				lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
//				lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
//			}
//
//			else if(rSide[1].getSuit() == "CLUBS")
//			{
//				score1+= bet;
//				lblPlayerScore.setText(Integer.toString(score1));
//				lblResult.setText(name + " wins " + bet + " point(s)!");
//				lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
//				lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
//			}
//
//			else if(lSide[2].getSuit() == "HEARTS" && rSide[1].getSuit() != "DIAMONDS")
//			{
//				score1+= bet;
//				lblPlayerScore.setText(Integer.toString(score1));
//				lblResult.setText(name + " wins " + bet + " point(s)!");
//				lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
//				lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
//			}
//
//			else if(rSide[1].getSuit() == "HEARTS" && lSide[2].getSuit() != "DIAMONDS")
//			{
//				score2+= bet;
//				lblDealerScore.setText(Integer.toString(score2));
//				lblResult.setText("Dealer wins " + bet + " point(s)");
//				lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
//				lblBTotal.setText(Integer.toString(vRightSide[0] + vLeftSide[1] + vLeftSide[2]));
//			}
//
//			else if(rSide[1].getSuit() == "SPADES" && lSide[2].getSuit() != "CLUBS")
//			{
//				score1+= bet;
//				lblPlayerScore.setText(Integer.toString(score1));
//				lblResult.setText(name + " wins " + bet + " point(s)!");
//				lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
//				lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
//			}
//
//			else if(lSide[2].getSuit() == "SPADES" && rSide[1].getSuit()!= "CLUBS" )
//			{
//				score2+= bet;
//				lblDealerScore.setText(Integer.toString(score2));
//				lblResult.setText("Dealer wins " + bet + " point(s)");
//				lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
//				lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
//			}
		}

		//HIGHEST CARD ON THE RIGHT SIDE
		else if(rSide[1].getRank() > lSide[2].getRank() && rSide[1].getRank() > lSide[1].getRank())
		{
			score2+= bet;
			lblDealerScore.setText(Integer.toString(score2));
			lblResult.setText("Dealer wins " + bet + " point(s)");
			lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
			lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
			
//			if(lSide[2].getSuit() == "DIAMONDS")
//			{
//				score1+= bet;
//				lblPlayerScore.setText(Integer.toString(score1));
//				lblResult.setText(name + " wins " + bet + " point(s)!");
//				lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
//				lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
//			}
//
//			else if(rSide[2].getSuit() == "DIAMONDS")
//			{
//				score2+= bet;
//				lblDealerScore.setText(Integer.toString(score2));
//				lblResult.setText("Dealer wins " + bet + " point(s)");
//				lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
//				lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
//			}
//
//			else if(lSide[2].getSuit() == "CLUBS")
//			{
//				score2+= bet;
//				lblDealerScore.setText(Integer.toString(score2));
//				lblResult.setText("Dealer wins " + bet + " point(s)");
//				lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
//				lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
//			}
//
//			else if(rSide[2].getSuit() == "CLUBS")
//			{
//				score1+= bet;
//				lblPlayerScore.setText(Integer.toString(score1));
//				lblResult.setText(name + " wins " + bet + " point(s)!");
//				lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
//				lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
//			}
//
//			else if(lSide[2].getSuit() == "HEARTS" && rSide[2].getSuit() != "DIAMONDS")
//			{
//				score1+= bet;
//				lblPlayerScore.setText(Integer.toString(score1));
//				lblResult.setText(name + " wins " + bet + " point(s)!");
//				lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
//				lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
//			}
//
//			else if(rSide[2].getSuit() == "HEARTS" && lSide[2].getSuit() != "DIAMONDS")
//			{
//				score2+= bet;
//				lblDealerScore.setText(Integer.toString(score2));
//				lblResult.setText("Dealer wins " + bet + " point(s)");
//				lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
//				lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
//			}
//
//			else if(rSide[2].getSuit() == "SPADES" && lSide[2].getSuit() != "CLUBS")
//			{
//				score1+= bet;
//				lblPlayerScore.setText(Integer.toString(score1));
//				lblResult.setText(name + " wins " + bet + " point(s)!");
//				lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
//				lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
//			}
//
//			else if(lSide[2].getSuit() == "SPADES" && rSide[2].getSuit()!= "CLUBS" )
//			{
//				score2+= bet;
//				lblDealerScore.setText(Integer.toString(score2));
//				lblResult.setText("Dealer wins " + bet + " point(s)");
//				lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
//				lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
//			}
			
		}

		//CHECKS CARD VALUE THEN CHECKS SUIT
		else if((lSide[1].getRank() == rSide[2].getRank()) || (lSide[1].getRank() == rSide[1].getRank()))
		{
			if(lSide[1].getSuit() == "DIAMONDS")
				{
					score1+= bet;
					lblPlayerScore.setText(Integer.toString(score1));
					lblResult.setText(name + " wins " + bet + " point(s)!");
					lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
					lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
				}
	
				else if(rSide[1].getSuit() == "DIAMONDS" || rSide[2].getSuit() == "DIAMONDS")
				{
					score2+= bet;
					lblDealerScore.setText(Integer.toString(score2));
					lblResult.setText("Dealer wins " + bet + " point(s)!");
					lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
					lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
				}
	
			
				else if(lSide[1].getSuit() == "CLUBS")
				{
					score2+= bet;
					lblDealerScore.setText(Integer.toString(score2));
					lblResult.setText("Dealer wins " + bet + " point(s)");
					lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
					lblBTotal.setText(Integer.toString(vRightSide[0] + vLeftSide[1] + vLeftSide[2]));
				}
	
				else if(rSide[1].getSuit() == "CLUBS" || rSide[2].getSuit() == "CLUBS")
				{
					score1+= bet;
					lblPlayerScore.setText(Integer.toString(score1));
					lblResult.setText(name + " wins " + bet + " point(s)!");
					lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
					lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
				}
	
				else if(lSide[1].getSuit() == "HEARTS" && (rSide[1].getSuit() != "DIAMONDS" || rSide[2].getSuit() != "DIAMONDS"))
				{
					score1+= bet;
					lblPlayerScore.setText(Integer.toString(score1));
					lblResult.setText(name + " wins " + bet + " point(s)!");
					lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
					lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
				}
	
				else if((rSide[1].getSuit() == "HEARTS" || rSide[2].getSuit() == "HEARTS") && lSide[1].getSuit() != "DIAMONDS")
				{
					score2+= bet;
					lblDealerScore.setText(Integer.toString(score2));
					lblResult.setText("Dealer wins " + bet + " point(s)");
					lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
					lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
				}
	
				else if((rSide[1].getSuit() == "SPADES" || rSide[2].getSuit() == "SPADES") && lSide[1].getSuit() != "CLUBS")
				{
					score1+= bet;
					lblPlayerScore.setText(Integer.toString(score1));
					lblResult.setText(name + " wins " + bet + " point(s)!");
					lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
					lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
				}
	
				else if(lSide[1].getSuit() == "SPADES" && (rSide[1].getSuit() != "CLUBS" || rSide[2].getSuit() != "CLUBS" ))
				{
					score2+= bet;
					lblDealerScore.setText(Integer.toString(score2));
					lblResult.setText("Dealer wins " + bet + " point(s)!");
					lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
					lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
				}
		}
		
		//CHECKS CARD VALUE THEN CHECKS SUIT
				else if((lSide[2].getRank() == rSide[2].getRank()) || (lSide[2].getRank() == rSide[1].getRank()))
				{
					if(lSide[2].getSuit() == "DIAMONDS")
						{
							score1+= bet;
							lblPlayerScore.setText(Integer.toString(score1));
							lblResult.setText(name + " wins " + bet + " point(s)!");
							lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
							lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
						}
			
						else if(rSide[1].getSuit() == "DIAMONDS" || rSide[2].getSuit() == "DIAMONDS")
						{
							score2+= bet;
							lblDealerScore.setText(Integer.toString(score2));
							lblResult.setText("Dealer wins " + bet + " point(s)!");
							lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
							lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
						}
			
					
						else if(lSide[2].getSuit() == "CLUBS")
						{
							score2+= bet;
							lblDealerScore.setText(Integer.toString(score2));
							lblResult.setText("Dealer wins " + bet + " point(s)");
							lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
							lblBTotal.setText(Integer.toString(vRightSide[0] + vLeftSide[1] + vLeftSide[2]));
						}
			
						else if(rSide[1].getSuit() == "CLUBS" || rSide[2].getSuit() == "CLUBS")
						{
							score1+= bet;
							lblPlayerScore.setText(Integer.toString(score1));
							lblResult.setText(name + " wins " + bet + " point(s)!");
							lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
							lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
						}
			
						else if(lSide[2].getSuit() == "HEARTS" && (rSide[1].getSuit() != "DIAMONDS" || rSide[2].getSuit() != "DIAMONDS"))
						{
							score1+= bet;
							lblPlayerScore.setText(Integer.toString(score1));
							lblResult.setText(name + " wins " + bet + " point(s)!");
							lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
							lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
						}
			
						else if((rSide[1].getSuit() == "HEARTS" || rSide[2].getSuit() == "HEARTS") && lSide[2].getSuit() != "DIAMONDS")
						{
							score2+= bet;
							lblDealerScore.setText(Integer.toString(score2));
							lblResult.setText("Dealer wins " + bet + " point(s)");
							lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
							lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
						}
			
						else if((rSide[1].getSuit() == "SPADES" || rSide[2].getSuit() == "SPADES") && lSide[2].getSuit() != "CLUBS")
						{
							score1+= bet;
							lblPlayerScore.setText(Integer.toString(score1));
							lblResult.setText(name + " wins " + bet + " point(s)!");
							lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
							lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
						}
			
						else if(lSide[2].getSuit() == "SPADES" && (rSide[1].getSuit() != "CLUBS" || rSide[2].getSuit() != "CLUBS" ))
						{
							score2+= bet;
							lblDealerScore.setText(Integer.toString(score2));
							lblResult.setText("Dealer wins " + bet + " point(s)!");
							lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
							lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
						}
				}
		
		//CHECKS CARD VALUE THEN CHECKS SUIT
				else if((rSide[1].getRank() == lSide[2].getRank()) || (rSide[1].getRank() == lSide[1].getRank()))
				{
					if(rSide[1].getSuit() == "DIAMONDS")
						{
							score2+= bet;
							lblDealerScore.setText(Integer.toString(score2));
							lblResult.setText("Dealer wins " + bet + " point(s)!");
							lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
							lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
						}
			
						else if(lSide[1].getSuit() == "DIAMONDS" || lSide[2].getSuit() == "DIAMONDS")
						{
							score1+= bet;
							lblPlayerScore.setText(Integer.toString(score1));
							lblResult.setText(name + " wins " + bet + " point(s)!");
							lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
							lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
						}
			
					
						else if(rSide[1].getSuit() == "CLUBS")
						{
							score1+= bet;
							lblPlayerScore.setText(Integer.toString(score1));
							lblResult.setText(name + " wins " + bet + " point(s)!");
							lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
							lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
						}
			
						else if(lSide[1].getSuit() == "CLUBS" || lSide[2].getSuit() == "CLUBS")
						{
							score2+= bet;
							lblDealerScore.setText(Integer.toString(score2));
							lblResult.setText("Dealer wins " + bet + " point(s)!");
							lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
							lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
						}
			
						else if(rSide[1].getSuit() == "HEARTS" && (lSide[1].getSuit() != "DIAMONDS" || lSide[2].getSuit() != "DIAMONDS"))
						{
							score2+= bet;
							lblDealerScore.setText(Integer.toString(score2));
							lblResult.setText("Dealer wins " + bet + " point(s)!");
							lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
							lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
						}
			
						else if((lSide[1].getSuit() == "HEARTS" || lSide[2].getSuit() == "HEARTS") && rSide[1].getSuit() != "DIAMONDS")
						{
							score1+= bet;
							lblPlayerScore.setText(Integer.toString(score1));
							lblResult.setText(name + " wins " + bet + " point(s)!");
							lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
							lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
						}
			
						else if((lSide[1].getSuit() == "SPADES" || lSide[2].getSuit() == "SPADES") && rSide[1].getSuit() != "CLUBS")
						{
							score2+= bet;
							lblDealerScore.setText(Integer.toString(score2));
							lblResult.setText("Dealer wins " + bet + " point(s)!");
							lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
							lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
						}
			
						else if(rSide[1].getSuit() == "SPADES" && (lSide[1].getSuit() != "CLUBS" || lSide[2].getSuit() != "CLUBS" ))
						{
							score1+= bet;
							lblPlayerScore.setText(Integer.toString(score1));
							lblResult.setText(name + " wins " + bet + " point(s)!");
							lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
							lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
						}
				}
		
		//CHECKS CARD VALUE THEN CHECKS SUIT
				else if((rSide[2].getRank() == lSide[2].getRank()) || (rSide[2].getRank() == lSide[1].getRank()))
				{
					if(rSide[2].getSuit() == "DIAMONDS")
						{
							score2+= bet;
							lblDealerScore.setText(Integer.toString(score2));
							lblResult.setText("Dealer wins " + bet + " point(s)!");
							lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
							lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
						}
			
						else if(lSide[1].getSuit() == "DIAMONDS" || lSide[2].getSuit() == "DIAMONDS")
						{
							score1+= bet;
							lblPlayerScore.setText(Integer.toString(score1));
							lblResult.setText(name + " wins " + bet + " point(s)!");
							lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
							lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
						}
			
					
						else if(rSide[2].getSuit() == "CLUBS")
						{
							score1+= bet;
							lblPlayerScore.setText(Integer.toString(score1));
							lblResult.setText(name + " wins " + bet + " point(s)!");
							lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
							lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
						}
			
						else if(lSide[1].getSuit() == "CLUBS" || lSide[2].getSuit() == "CLUBS")
						{
							score2+= bet;
							lblDealerScore.setText(Integer.toString(score2));
							lblResult.setText("Dealer wins " + bet + " point(s)!");
							lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
							lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
						}
			
						else if(rSide[2].getSuit() == "HEARTS" && (lSide[1].getSuit() != "DIAMONDS" || lSide[2].getSuit() != "DIAMONDS"))
						{
							score2+= bet;
							lblDealerScore.setText(Integer.toString(score2));
							lblResult.setText("Dealer wins " + bet + " point(s)!");
							lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
							lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
						}
			
						else if((lSide[1].getSuit() == "HEARTS" || lSide[2].getSuit() == "HEARTS") && rSide[2].getSuit() != "DIAMONDS")
						{
							score1+= bet;
							lblPlayerScore.setText(Integer.toString(score1));
							lblResult.setText(name + " wins " + bet + " point(s)!");
							lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
							lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
						}
			
						else if((lSide[1].getSuit() == "SPADES" || lSide[2].getSuit() == "SPADES") && rSide[2].getSuit() != "CLUBS")
						{
							score2+= bet;
							lblDealerScore.setText(Integer.toString(score2));
							lblResult.setText("Dealer wins " + bet + " point(s)!");
							lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
							lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
						}
			
						else if(rSide[2].getSuit() == "SPADES" && (lSide[1].getSuit() != "CLUBS" || lSide[2].getSuit() != "CLUBS" ))
						{
							score1+= bet;
							lblPlayerScore.setText(Integer.toString(score1));
							lblResult.setText(name + " wins " + bet + " point(s)!");
							lblPTotal.setText(Integer.toString(vLeftSide[0] + vLeftSide[1] + vLeftSide[2] ));
							lblBTotal.setText(Integer.toString(vRightSide[0] + vRightSide[1] + vRightSide[2]));
						}
				}
		}
	



//Method for making player bets. It checks to see if the bet is valid, if there is even anything typed in the textfield, and also checks to make sure the bet is a number and not a character
	private void bet() {
		btnBet.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent e) {

//				if(counter == 3)
//				{
//					btnStand.setDisable(false);
//				}


				String textone = txtBet.getText();

				try {
					bet = Integer.parseInt(txtBet.getText());

				}catch (NumberFormatException f)
				{
					Alert text = new Alert(AlertType.ERROR);
					text.setTitle("Invalid Bet!");
					text.setHeaderText(null);
					text.setContentText("You must enter an integer!");
					text.showAndWait();
				}
				if((bet < 0) || (bet == 0))
				{
					Alert bettaz = new Alert(AlertType.ERROR);
					bettaz.setHeaderText(null);
					bettaz.setTitle("Invalid Bet!");
					bettaz.setContentText("You must bet at least (1) point!");
					bettaz.showAndWait();
				}
				else if (bet > 11 - score1)
				{
					int exceed = 11 - score1;
					Alert scoreaz = new Alert(AlertType.ERROR);
					scoreaz.setHeaderText(null);
					scoreaz.setTitle("Invalid Bet!");
					scoreaz.setContentText("Your bet cannot exceed " + exceed + " point(s)!");
					scoreaz.showAndWait();
				}
				else if ((bet <= (11 - score1)) && (bet > 0))
				{
					btnDeal.setDisable(false);
					btnBet.setDisable(true);
					txtBet.setEditable(false);

					//					 if((counter == 2)) {
					//							pair();
					//						}
				}
				else if (txtBet == null)
				{
					Alert empty = new Alert(AlertType.ERROR);
					empty.setHeaderText(null);
					empty.setTitle("Invalid Bet!");
					empty.setContentText("You must enter an integer!");
					empty.showAndWait();
				}



				lblResult.setText("Click DEAL!");
				




			}
		});

	}


	public static void main(String[] args) {
		launch(args);
	}
}
