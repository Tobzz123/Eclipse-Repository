package application;

import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

/**
* Stopwatch class which times the sprinters for each race <p>
* Contains a double variable for the time and a Timeline for each race </p>
*
* @param No parameters
* @return the double value for the time, and the time line object
* @throws NullPointerException – if the argument is not a double
*/

public class Stopwatch {
	
	private double time;
	private Timeline run;
	
	
	/**
	* No arg Stopwatch object that initializes variables with default values and time line object
	* </p>
	*
	* @param No parameters
	* @return The time for stopwatch and it's time line object
	* @throws NullPointerException – if there is no value assigned to the time variable and there is no keyframe attached 
	* to the timeline
	*/

	public Stopwatch() {
		time = 0.00;
		run = new Timeline(new KeyFrame(Duration.millis(1), new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				time += 0.001;
			}
		}));
		run.setCycleCount(Timeline.INDEFINITE);
	}
	
	
	/**
	* Starts the time line object </p>
	*
	* @param No parameters
	* @return The command for the time line object to begin
	* @throws NullPointerException – if the object being started is not a time line object
	*/

	public void start() {
		run.play();
	}
	
	/**
	* Stops the time line object </p>
	*
	* @param No parameters
	* @return The command for the time line object to stop
	* @throws NullPointerException – if the object being started is not a time line object
	*/
	public void stop() {
		run.stop();
	}
	
	/**
	* Resets the time line object and sets time to 0 </p>
	*
	* @param No parameters
	* @return The command for the time line object to stop and set the time to 0
	* @throws NullPointerException – if there is object being started is not a time line object
	*/
	public void reset() {
		stop();
		time = 0.00;
	}
	
	/**
	* Gets the time of the time line object </p>
	*
	* @param No parameters
	* @return The time at that moment in the time line object
	* @throws NullPointerException – if the value being retrieved is not a double
	*/
	public double getTime() {
		return time;
	}
	
	/**
	* Gets the status of the time line object </p>
	*
	* @param No parameters
	* @return The status of the time line object
	* @throws NullPointerException – if the value which status is being retrieved is not a timeline object
	*/
	public Status getStatus() {
		return run.getStatus();
	}
	
	/**
	*Changes and formats the time to String </p>
	*
	* @param No parameters
	* @return The double value of the time being formatted as a string
	* @throws NullPointerException – if the value being retrieved is not a double
	*/
	public String toString() {
		return String.format("%05.2f", time);
	}

}
