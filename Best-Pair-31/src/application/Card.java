package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
* Creates a Card class that holds default values
*
* @param Card - It has private variables. Double width and height, int value and cardrank, string name suit and suitvalue, and Image imgCard and Imageview ivCards
* @return returns the default values when a card object is created
* @throws Undefined constructor when an argument is typed in the no-arg constructor.
*/
public class Card {

	private double width, height;
	private int value, cardRank;
	private String name, suit, suitValue;
	private Image imgCard;
	private ImageView ivCard;
	
	public Card() {
		
		imgCard = new Image("file:images/back_blue.png");
		value = 0;
		suit = "";
		name = "";
		ivCard = new ImageView(imgCard);
		width = imgCard.getWidth();
		height = imgCard.getHeight();
		
	}
	
	/**
	* Returns the imageView of the Card
	*
	* @param getImageView = It doesn't take any parameters
	* @return the imageview of the card
	* @throws Undefined constructor when an argument is passed in the parameters
*/
	public ImageView getImageView() {
		
		return ivCard;
	}
	
	/**
	* Returns the Value of the Card
	*
	* @param getValue = It doesn't take any parameters
	* @return the value of the card
	* @throws Undefined constructor when an argument is passed in the parameters
*/
	public int getValue() {
	    
	    return value;
	}
	
	/**
	* Returns the image of the Card
	*
	* @param getValue = It doesn't take any parameters
	* @return the image of the card
	* @throws Undefined constructor when an argument is passed in the parameters
*/
	public Image getImage() {
	    
	    return imgCard;
	}
	
	/**
	* Returns the suit of the Card as a String
	*
	* @param getImage = It doesn't take any parameters
	* @return the suit of the card
	* @throws Undefined constructor when an argument is passed in the parameters
*/
	public String getSuit() {
	    
	    return suit;
	}
	
	/**
	* Returns the name of the Card as a String
	*
	* @param getSuit =It doesn't take any parameters
	* @return the name of the card
	* @throws Undefined constructor when an argument is passed in the parameters
*/
	public String getName() {
	    
	    return name;
	}
	
	/**
	* Returns the width of the Card as a double
	*
	* @param getName = It doesn't take any parameters
	* @return the width of the card
	* @throws Undefined constructor when an argument is passed in the parameters
*/
	public double getWidth() {
	    
	    return width;
	}
	
	/**
	* Returns the height of the Card as a double
	*
	* @param getHeight= It doesn't take any parameters
	* @return the height of the card
	* @throws Undefined constructor when an argument is passed in the parameters
*/
	public double getHeight() {
	    
	    return height;
	}
	
	/**
	*Sets the width of the Card as a double
	*
	* @param getWidth = It doesn't take any parameters
	* @return the width of the card
	* @throws Undefined constructor when an argument is passed in the parameters
*/
	public void setWidth(double width) {
		
		this.width = width;
	}
	
	/**
	* Sets the height of the Card
	*
	* @param setHeight = Passes a double called width
	* @return the height of the card
	* @throws Undefined constructor when an argument that is not double is passed in the parameters
*/
	public void setHeight(double h) {
		
		height = h;
	}
	
	/**
	* Sets the name of the Card as a double
	*
	* @param setName = passes a double; h
	* @return the name  of the card
	* @throws Undefined constructor when an argument is passed in the parameters
*/
	public void setName(String n) {
		
		name = n;
	}
	
	/**
	* Sets the value of the Card as a String
	*
	* @param setValue = passes a String n to the method
	* @return the value of the card
	* @throws Undefined constructor when an argument is passed in the parameters
*/
	public void setValue(int val) {
		
		value = val;
	}
	
	/**
	* Sets the rank of the Card
	*
	* @param setRank = Passes an int val to the method
	* @return the rank of the card
	* @throws Undefined constructor when an argument is passed in the parameters
*/

	public void setRank(int rank) {
		cardRank = rank;
	}
	
	/**
	* gets the rank of the Card
	*
	* @param getRank = return an int rank 
	* @return the rank value of the card
	* @throws Undefined constructor when an argument is passed in the parameters
*/
	public int getRank() {
		return cardRank;
	}
	
	/**
	* sets the image of the Card
	*
	* @param setImage = It doesn't take any parameters
	* @return the imageview of the card
	* @throws Undefined constructor when an argument is passed in the parameters
*/
	public void setImage(Image img) {
	    
	    imgCard = img;
	}
	
	/**
	* Returns the suit of the Card as a String
	*
	* @param setSuit = Returns an image img
	* @return the suit of the card
	* @throws Undefined constructor when an argument is passed in the parameters
*/
	public void setSuit(String s) {
	    
	    suit = s;
	}
	
	/**
	*Sets the imageView of the Card
	*
	* @param setSuit = Returns an ImageView iv
	* @return the imageview of the card
	* @throws Undefined constructor when an argument is passed in the parameters
*/
	public void setImageView(ImageView iv) {
	    
	    ivCard = iv;
	}
	
}
