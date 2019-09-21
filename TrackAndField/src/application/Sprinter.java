package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


/**
* Sprinter class for the sprinters which extends the ImageView object
* <p>It requires a integer, array of Image objects, a timeline object,
* two doubles for the speed and max speed of the printer, and a boolean
* object that determines is the sprinter is a player or computer</p>
*
* @param No parameters
* @return The integer value for the frame, the array indexes for the images, the timeline for the animation, the double values for the 
* speed and max speed, the boolean condition if isPlayer is true of false
* @throws IndexOutOfBoundsException – if the index argument is
negative or not less than the length of the images array
	NullPointerException - if the arguments are not the required data types or object types
*/

public class Sprinter extends ImageView {

	private int frame;
	private Image[] imgs;
	private Timeline animation;
	private double speed, maxSpeed;
	private boolean isPlayer = false;
	
	
	/**
	* No arg sprinter object that initializes the default values of the maxSpeed, speed and frame. The style is
	* set and the images of the array are filled</p>
	*
	* @param Takes no parameters
	* @return the default values of the max speed, speed, and frame and the images of the array
	* @throws IndexOutOfBoundsException – if the index argument is
	negative or not less than the length of this string.
	NullPointerException - If the variables have no default values
	*/

	public Sprinter() {
		maxSpeed = Math.random() * 2 + 10; //Max level
		speed = 0;
		frame = 1;
		setStyle(0);
		setImage(imgs[frame]);
		initAnimation();
	}
	
	
	/**
	* Timeline object that also requires keyframe object for the sprinter's animation to be initialized
	* </p> 
	*
	* @param No parameters
	* @return Timeline object for the animation. Determines how the sprinter is animated if it is not being controlled by the user
	* @throws IndexOutOfBoundsException – if the index argument is
	negative or not less than the length image array
	NullPointerException - If there is no index specified for the image to be set
	*/

	private void initAnimation() {
		animation = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (!isPlayer) {
					setY(getY() - speed + Main.SCREEN_SPEED);
					speed = Math.min(speed + 0.075, maxSpeed);
					frame += speed * 1.5;
				}
				else {
					frame += Main.SCREEN_SPEED * 1.5;
				}
				if (frame > 399) {
					frame = 0;
				}
				setImage(imgs[frame/100]);
			}
		}));
		animation.setCycleCount(Timeline.INDEFINITE);
	}


	/**
	* Sets the sprinter as a player</p>
	*
	* @param No parameters
	* @return the boolean value for isPlayer. Sets the value of isPlayer to true
	* @throws NullPointerException - If the object being set as player is not a Sprinter object
	*/

	public void setAsPlayer() {
		isPlayer = true;
	}
	
	/**
	* Sets the sprinter as a computer </p>
	*
	* @param No parameters
	* @return the boolean value for isPlayer. Sets the value of isPlayer to false
	* @throws NullPointerException - If the object being set as a computer is false
	*/

	public void setAsComputer() {
		isPlayer = false;
	}
	
	/**
	* Plays the timeline object called animation for the sprinter object</p>
	*
	* @param No parameters
	* @return the timeline object being played
	* @throws Type Mismatch error - if the wrong object type is being played
	*/

	public void playAnimation() {
		animation.play();
	}

	/**
	* Stops the timeline object of the sprinter called animation and sets the image to the index of the Image array</p>
	* 
	* @param No parameters
	* @return The timeline object being stopped
	* @throws NullPointerException - If no timeline object is set to be stopped
	* Type Mismatch Error - if the wrong object type is being stopped
	*/

	public void stopAnimation() {
		animation.stop();
		setImage(imgs[1]);
	}
	
	/**
	* Gets the speed of the sprinter object </p>
	*
	* @param No parameters
	* @return the speed of the specified Sprinter object
	* @throws NullPointerException – if there is no speed that is assigned to the sprinter object
	*/

	public double getSpeed() {
		return speed;
	}
	
	/**
	* Gets the max speed of the sprinter object </p>
	*
	* @param No parameters
	* @return the max speed of the specified Sprinter object
	* @throws NullPointerException – if there is no max speed that is assigned to the sprinter object
	*/
	public double getMaxSpeed() {
		return maxSpeed;
	}

	/**
	* Sets the speed of the Sprinter</p>
	*
	* @param spd – the speed of the Sprinter.
	* @return The speed that is initialized for the Sprinter
	* @throws NullPointerException - No value entered in parameters
	* Type Mismatch error - Wrong data type entered in the parameters
	*/
	public void setSpeed(double spd) {
		speed = spd;
		maxSpeed = spd;
	}
	
	/**
	* Sets the max speed of the Sprinter</p>
	*
	* @param spd – the  max speed of the Sprinter.
	* @return The max speed that is initialized for the Sprinter
	* @throws NullPointerException - No value entered in parameters
	* Type Mismatch error - Wrong data type entered in the parameters
	*/
	public void setMaxSpeed(double spd) {
		maxSpeed = spd;
	}
	
	/**
	* Sets the max speed of the Sprinters that aren't controlled by the user</p>
	*
	* @param min, range – the minimum speed of the Sprinter and the range it should be randomized by.
	* @return The max speed that is randomized for the computer Sprinters
	* @throws NullPointerException - No value entered in parameters
	* Type Mismatch error - Wrong data type entered in the parameters
	*/
	public void setMaxSpeed(double min, double range) {
		maxSpeed = Math.random() * range + min;
	}

	/**
	* Sets the image of the Sprinter as well as the ID</p>
	*
	* @param style – the image of the Sprinter.
	* @return The different image that is initialized for the Sprinter
	* @throws NullPointerException - No value entered in parameters
	* ArrayIndexOutOfBoundsException - value entered is either negative or greater than the highest index of the array
	* Type Mismatch error - Wrong data type entered in the parameters
	*/
	public void setStyle(int style) {
		imgs = new Image[] {
				new Image("file:sprites/sprinter" + style + "_0.png"),
				new Image("file:sprites/sprinter" + style + "_1.png"),
				new Image("file:sprites/sprinter" + style + "_2.png"),
				new Image("file:sprites/sprinter" + style + "_3.png")
		};
		setImage(imgs[1]);
		setId(Integer.toString(style));
	}

}
