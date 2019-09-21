package application;

//ICS 4U1
//Jeffery and Toby
//Track and Field- Sprinter
//June 16th, 2018

//Importing all necessary packages for our program
import java.util.Arrays;

import javafx.animation.Animation.Status;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;


public class Main extends Application {

	/*Declaring all required objects, components, and data types. VBox for rules, start screen, imageviews for background, 
	track start, end of track, caption, stopwatch, timeline for track and the countdown, sprinter objects for racers, levels,
	friction, scene height and screen speed.
	*/
	private VBox startScreen, controlScreen, resultScreen, blankScreen;
	private ImageView ivStart, ivEnd, ivBackground;
	private Label lblCaption;
	private Stopwatch stopwatch;
	private Timeline moveTrack, countdown;
	private Sprinter[] sprinters;
	private KeyCode lastPressed;
	private double[] lanes;
	private int playerLane = 3, level = 1, countdownTime = 5;
	private double seasonalBest = 99.99, personalBest = 99.99;
	public static double FRICTION = 0.05;
	public static final double SCENE_HEIGHT = 700;
	public static double SCREEN_SPEED = 0;

	public void start(Stage primaryStage) {
		try {
			
			//new stopwatch object that times the sprinters
			stopwatch = new Stopwatch();
			
			// X positions for the lanes
			lanes = new double[] {50, 150, 250, 350, 450, 550, 650, 750};
			
			//sprinters created for each lane
			sprinters = new Sprinter[lanes.length];

			//Commonly used fonts
			Font regularFont = Font.font("Verdana", FontWeight.BOLD, 15);
			Font titleFont = Font.font("Verdana", FontWeight.BOLD, 36);



			//Initializing the sprinters
			for (int i = 0; i < lanes.length; i++) {
				sprinters[i] = new Sprinter();
			}



			//Constructing the track
			ivBackground = new ImageView(new Image("file:images/TrackStraight.png"));
			ivStart = new ImageView(new Image("file:images/TrackStart.png"));
			ivEnd = new ImageView(new Image("file:images/TrackEnd.png"));



			//Elements of the race
			Label lblTime = new Label();
			lblTime.setPrefSize(100, 20);
			lblTime.setLayoutX(1);
			lblTime.setFont(regularFont);
			lblTime.setTextFill(Color.YELLOW);

			//Initializing the caption label for the title screen
			lblCaption = new Label();
			lblCaption.setPrefSize(900, 40);
			lblCaption.setLayoutY(100);
			lblCaption.setAlignment(Pos.CENTER);
			lblCaption.setFont(titleFont);
			lblCaption.setTextFill(Color.BLACK);

			//Timeline object with keyframe that moves the track according to y positions and the screen's speed
			// When the imageview of the end of the track's y position is greater than 450, method is called to finish the race
			moveTrack = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					ivStart.setY(ivStart.getY() + SCREEN_SPEED);
					ivEnd.setY(ivEnd.getY() + SCREEN_SPEED);
					lblTime.setText(stopwatch.toString());
					SCREEN_SPEED = Math.max(SCREEN_SPEED - FRICTION, 0);
					if (ivEnd.getY() > 450) {
						finishRace();
					}
				}
			}));
			moveTrack.setCycleCount(Timeline.INDEFINITE);

			//Countdown object for the beginning of the race. Shows the label 'caption' and says on your mark, set, bang every two seconds to start the race
			//When the race starts, the player who is the sprinter is given control to make the sprinter run
			//The stopwatch is started, and the track begins to move, the animation of all the other sprinters in played
			countdown = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					countdownTime--;
					if (countdownTime == 4) {
						lblCaption.setText("\"ON YOUR MARK\"");
						lblCaption.setTextFill(Color.BLACK);
					}
					else if (countdownTime == 2) {
						lblCaption.setText("\"SET\"");
						lblCaption.setTextFill(Color.BLACK);
					}
					else if (countdownTime == 0) {
						lblCaption.setText("*BANG*");
						lblCaption.setTextFill(Color.YELLOW);
						sprinters[playerLane].setAsPlayer();
						stopwatch.start();
						moveTrack.play();
						for (int i = 0; i < sprinters.length; i++) {
							sprinters[i].playAnimation();
						}
					}
					else {
						lblCaption.setText("");
					}
				}
			}));
			countdown.setCycleCount(countdownTime + 1);



			//Construction of startScreen and its elements
			Label lblWelcome = new Label("100m DASH!");
			lblWelcome.setFont(titleFont);
			lblWelcome.setAlignment(Pos.CENTER);
			lblWelcome.setPrefHeight(100);
			lblWelcome.setTextFill(Color.BLACK);

			//Start button initialized with all of its elements. When the start button is clicked, level is set to 1, and the seasonal best of the player is set to 99.99
			//The askPreferences and startRace methods are called
			Button btnStart = new Button("START SEASON");
			btnStart.setPrefSize(200, 50);
			btnStart.setFont(regularFont);
			btnStart.setOnAction(e -> {
				level = 1;
				seasonalBest = 99.99;
				askPreferences();
				startRace();
			});

			/*Button for controls. Tells user how to play when the button is clicked. playTransition method is called,
			start screen, control screen, and result screen are all visible
			*/
			Button btnControls = new Button("HOW TO PLAY");
			btnControls.setPrefSize(200, 50);
			btnControls.setFont(regularFont);
			btnControls.setOnAction(e -> {
				playTransition();
				startScreen.setVisible(false);
				controlScreen.setVisible(true);
				resultScreen.setVisible(false);
			});
			
			/*Label for seasonal and personal best is initialized with all of its components
			 
			 */
			Label lblBests = new Label(String.format("%-20s%5s\n%-20s%5s",
					"SEASONAL BEST: ", seasonalBest,
					"PERSONAL BEST: ", personalBest));
			lblBests.setFont(regularFont);
			lblBests.setAlignment(Pos.CENTER);
			lblBests.setPrefHeight(200);
			lblBests.setTextFill(Color.YELLOW);

			//Start screen is initialized as vBox with all of its components
			startScreen = new VBox();
			startScreen.setAlignment(Pos.CENTER);
			startScreen.setStyle("-fx-background-color: saddlebrown");
			startScreen.setPrefSize(900, SCENE_HEIGHT);
			startScreen.setPadding(new Insets(100,100,100,100));
			startScreen.setSpacing(20);
			startScreen.getChildren().addAll(lblWelcome, btnStart, btnControls, lblBests);



			//Construction of controlScreen and its elements
			Label lblControls = new Label("Repeatedly tap the left and right arrow keys (< and >) to accelerate."
					+ "\n\nContinue tapping the keys in sequence to keep running."
					+ "\n\nTapping out of order will cause you to slow down."
					+ "\n\nIf you begin tapping before the race starts, you will be charged with a False Start.");
			lblControls.setFont(regularFont);
			lblControls.setAlignment(Pos.CENTER);
			lblControls.setTextAlignment(TextAlignment.CENTER);
			lblControls.setTextFill(Color.BLACK);

			//Back button is initialized. Allows you to return back to control screen and result screen when clicked
			Button btnBack = new Button("BACK");
			btnBack.setPrefSize(200, 50);
			btnBack.setFont(regularFont);
			btnBack.setOnAction(e -> {
				playTransition();
				startScreen.setVisible(true);
				controlScreen.setVisible(false);
				resultScreen.setVisible(false);
				blankScreen.setVisible(true);
			});

			//Control screen is initialized and the label for control and the back button are added as components
			controlScreen = new VBox();
			controlScreen.setAlignment(Pos.CENTER);
			controlScreen.setStyle("-fx-background-color: saddlebrown");
			controlScreen.setPrefSize(900, SCENE_HEIGHT);
			controlScreen.setPadding(new Insets(100,100,100,100));
			controlScreen.setSpacing(20);
			controlScreen.getChildren().addAll(lblControls, btnBack);


			
			//Level increases when the button's text is changed to next race and the race is started
			//if that is not the case, then the transition method is called and the start screen is set to visible
			Button btnNext = new Button();
			btnNext.setPrefSize(150, 20);
			btnNext.setFont(regularFont);
			btnNext.setOnAction(e -> {
				if (btnNext.getText().equals("NEXT RACE")) {
					level++;
					startRace();
				}
				else {
					playTransition();
					startScreen.setVisible(true);
					resultScreen.setVisible(false);
					controlScreen.setVisible(false);	
					lblCaption.setText("");
				}
				lblTime.setText("");
				lblBests.setText(String.format("%-20s%5s\n%-20s%5s",
						"SEASONAL BEST: ", seasonalBest,
						"PERSONAL BEST: ", personalBest));
			});

			//Construction of resultScreen and its elements
			resultScreen = new VBox();
			resultScreen.setAlignment(Pos.CENTER);
			resultScreen.setStyle("-fx-background-color: black");
			resultScreen.setPadding(new Insets(10,10,10,10));
			resultScreen.setSpacing(5);
			resultScreen.setLayoutX(340);
			resultScreen.setLayoutY(150);
			for (int i = 0; i < lanes.length; i++) {
				Label lbl = new Label();
				lbl.setFont(regularFont);
				lbl.setPrefWidth(200);
				lbl.setAlignment(Pos.CENTER);
				lbl.setTextAlignment(TextAlignment.CENTER);
				resultScreen.getChildren().add(lbl);
			}
			resultScreen.getChildren().add(btnNext);



			//Construction of blankScreen
			blankScreen = new VBox();
			blankScreen.setPrefSize(900, SCENE_HEIGHT);
			blankScreen.setStyle("-fx-background-color: black");
			blankScreen.setVisible(false);


			//Initialize the visibilities of all the screens
			startScreen.setVisible(true);
			controlScreen.setVisible(false);
			resultScreen.setVisible(false);

			//Adding all UI into root Pane
			Pane root = new Pane();
			root.setStyle("-fx-background-color: dimgrey");
			root.getChildren().addAll(ivBackground, ivStart, ivEnd);
			root.getChildren().addAll(sprinters);
			root.getChildren().addAll(startScreen, controlScreen, resultScreen, blankScreen);
			root.getChildren().addAll(lblCaption, lblTime);

			//Scene object to display everything
			Scene scene = new Scene(root, 900, SCENE_HEIGHT);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			//KeyEvents when user is racing
			scene.setOnKeyPressed(e -> {
				if (e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.RIGHT) {
					if (stopwatch.getStatus() == Status.RUNNING) {
						if (e.getCode() != lastPressed) {
							SCREEN_SPEED = Math.min(SCREEN_SPEED + 1, 12);
							lastPressed = e.getCode();
						}
						else {
							SCREEN_SPEED = Math.max(SCREEN_SPEED - 2, 0);
						}
					}
					//False start condition
					else if (countdown.getStatus() == Status.RUNNING && stopwatch.getStatus() == Status.STOPPED) {
						countdown.stop();
						lblCaption.setText("FALSE START!");
						lblCaption.setTextFill(Color.RED);
						finishRace();
					}
				}
			});

			//primary stage for the title
			primaryStage.setTitle("100m Dash!");
			primaryStage.setScene(scene);
			primaryStage.setOnCloseRequest(e -> forceExit(e));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}



	//Fade-in transition
	private void playTransition() {
		FadeTransition fade = new FadeTransition(Duration.millis(500), blankScreen);
		fade.setFromValue(1);
		fade.setToValue(0);
		fade.setOnFinished(e -> {
			blankScreen.setVisible(false);
		});
		blankScreen.setVisible(true);
		fade.play();
	}



	//Linear search function for arrays
	private int linearSearch(double[] array, double val) {
		int found = -1;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == val) {
				found = i;
				break;
			}
		}
		return found;
	}



	//Method that shows the dialogs for getting the preferred lane and sprinter color
	private void askPreferences() {
		Alert dlgLane = new Alert(AlertType.NONE, "Choose your lane to run in:");
		dlgLane.setTitle("Lane Selection");
		for (int i = 0; i < lanes.length; i++) {
			dlgLane.getButtonTypes().add(new ButtonType("LANE " + (i + 1)));
		}
		Alert dlgStyle = new Alert(AlertType.NONE, "Choose your sprinter's colour:");
		dlgStyle.setTitle("Style Selection");
		dlgStyle.getButtonTypes().addAll(
				new ButtonType("1 (GREEN)"),
				new ButtonType("2 (RED)"),
				new ButtonType("3 (BLUE)"),
				new ButtonType("4 (YELLOW)"),
				new ButtonType("5 (PURPLE)"),
				new ButtonType("6 (ORANGE)"));
		String prefLane = dlgLane.showAndWait().get().getText(), prefStyle = dlgStyle.showAndWait().get().getText();
		playerLane = Integer.parseInt(prefLane.substring(prefLane.length() - 1, prefLane.length())) - 1;
		sprinters[playerLane].setStyle(Integer.parseInt(prefStyle.substring(0, 1)) - 1);
	}



	//Starts a new race
	private void startRace() {
		playTransition();
		stopwatch.reset();
		startScreen.setVisible(false);
		controlScreen.setVisible(false);
		resultScreen.setVisible(false);
		ivStart.setVisible(false);
		ivEnd.setVisible(false);
		ivBackground.setVisible(false);
		countdownTime = 5;
		
		String[] raceTitles = {"Pre-School", "Elementary School", "High School", "Provincials", "Nationals", "Olympics"};
		lblCaption.setText("RACE " + level + ": " + raceTitles[level - 1]);
		lblCaption.setTextFill(Color.WHITE);

		for (int i = 0; i < lanes.length; i++) {
			sprinters[i].setVisible(false);
			sprinters[i].stopAnimation();
			sprinters[i].setAsComputer();
			sprinters[i].setSpeed(0);
			sprinters[i].setMaxSpeed(5.0 + level, 2.0 - level / 5.0);
			if (i != playerLane) {
				int style;
				do {
					style = (int)(Math.random() * 6.0);
				} while (Integer.parseInt(sprinters[playerLane].getId()) == style);
				sprinters[i].setStyle(style);
			}
		}

		new Timeline(new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>() {
			public void handle (ActionEvent e) {
				playTransition();
				ivStart.setVisible(true);
				ivEnd.setVisible(true);
				ivBackground.setVisible(true);
				ivStart.setY(SCENE_HEIGHT - ivStart.getImage().getHeight());
				ivEnd.setY(-1e4 + SCENE_HEIGHT - ivEnd.getImage().getHeight());
				countdown.play();
				lblCaption.setText("");
				for (int i = 0; i < lanes.length; i++) {
					sprinters[i].setVisible(true);
					sprinters[i].setY(520);
					sprinters[i].setX(lanes[i]);
				}
			}
		})).play();
	}



	//Finish the race
	private void finishRace() {
		stopwatch.stop();
		moveTrack.stop();
		Button btnNext = (Button)resultScreen.getChildren().get(resultScreen.getChildren().size() - 1);

		if (lblCaption.getText().equals("FALSE START!")) {
			for (int i = 0; i < sprinters.length; i++) {
				Label lbl = (Label)resultScreen.getChildren().get(i);
				lbl.setText("- | --:-- | ---- -");
				lbl.setTextFill(Color.WHITE);
			}
			sprinters[playerLane].playAnimation();
			lblCaption.setTextFill(Color.RED);
			btnNext.setText("END SEASON");
		}
		else {
			double[] sortedTimes, unsortedTimes = new double[lanes.length];
			seasonalBest = Math.min(Double.parseDouble(stopwatch.toString()), seasonalBest);
			personalBest = Math.min(Double.parseDouble(stopwatch.toString()), personalBest);

			for (int i = 0; i < sprinters.length; i++) {
				double timeDifference = (sprinters[i].getY() - sprinters[playerLane].getY()) / sprinters[i].getMaxSpeed();
				timeDifference = timeDifference * Duration.millis(10).toSeconds();
				unsortedTimes[i] = stopwatch.getTime() + timeDifference;
			}

			sortedTimes = unsortedTimes.clone();
			Arrays.sort(sortedTimes);
			for (int i = 0; i < sprinters.length; i++) {
				Label lbl = (Label)resultScreen.getChildren().get(i);
				lbl.setText(Integer.toString(i + 1)
						+ " | " + String.format("%05.2f", sortedTimes[i])
						+ " | LANE " + (linearSearch(unsortedTimes, sortedTimes[i]) + 1));
				if (sortedTimes[i] == stopwatch.getTime()) {
					lbl.setTextFill(Color.YELLOW);
				}
				else {
					lbl.setTextFill(Color.WHITE);
				}
			}

			if (sortedTimes[0] == stopwatch.getTime()) {
				lblCaption.setTextFill(Color.YELLOW);
				if (level == 6) {
					lblCaption.setText("YOU'RE THE FASTEST ALIVE!");
					btnNext.setText("END SEASON");
				}
				else {
					lblCaption.setText("YOU WON!");
					btnNext.setText("NEXT RACE");
				}
			}
			else {
				lblCaption.setText("YOU LOST!");
				lblCaption.setTextFill(Color.RED);
				btnNext.setText("END SEASON");
			}
		}
		resultScreen.setVisible(true);
		sprinters[playerLane].setAsComputer();
		sprinters[playerLane].setSpeed(SCREEN_SPEED);
		SCREEN_SPEED = 0;
	}
	
	
	
	//Activated when the user quits the game
	private void forceExit(WindowEvent e) {
		Alert dlgAsk = new Alert(
				AlertType.CONFIRMATION,
				"Are you sure you want to exit 100m Dash?",
				ButtonType.YES, ButtonType.NO);
		dlgAsk.setTitle("100m Dash!");
		dlgAsk.setHeaderText(null);
		if (dlgAsk.showAndWait().get() == ButtonType.YES) {
			System.exit(0);
		}
		else {
			e.consume();
		}
	}



	public static void main(String[] args) {
		launch(args);
	}
}