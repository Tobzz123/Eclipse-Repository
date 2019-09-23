package application;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
* DeckOfCards class which contains two constructors and 5 methods
*
* @param DeckOfCards-card deck
* @return default variables
* @throws IndexOutOfBoundsException – if the index argument is
negative or not less than the length of the array in it
*/

public class DeckOfCards {

	private Card[] cards;
	private Card dealtCard, backOfCard;
	private int numCardsDealt, cardsRemaining;
	private Random rnd;
	public static int TOTAL_CARDS = 52;

	
	/**
	* Deck of cards which is a no arg constructor
	*
	* @param DeckOfCards = no arguments
	* @return The method in it which is generateDeck();
	* @throws Method doesn't exist
	*/

	public DeckOfCards() {

		generateDeck();
		
	}
	
	/**
	* DeckOfCards constructor that takes a boolean argument
	* @param DeckOfCards = takes a boolean argument which specifies if the deck should be shuffled or not
	* @return Method generate deck and shuffleDeck is boolean shuffle is equal to true
	* @throws Undefined constructor with undefined arguments
	*/

	public DeckOfCards(boolean shuffle) {
		
		generateDeck();
		
		if (shuffle == true)
		{
			shuffleDeck();
		}
		
	}
	
	/**
	* Changes default values, has two for loops that step through the array of Cards and shuffles them and gives them their suit values in ascending order
	*
	* @param generateDeck = no parameter
	* @return returns the altered values
	* @throws IndexOutOfBoundsException – if the index argument is
	negative or not less than the length of the array or for loop
	*/

	private void generateDeck() {
		
		numCardsDealt = -1;
		rnd = new Random();
		cards = new Card[52];
		int index =  0;
		String[] suits = new String[] {"DIAMONDS", "HEARTS", 
				"CLUBS", "SPADES"};

		for (int i = 2; i <= 14; i++)
		{
			for (int j = 0; j < suits.length; j++)
			{
				cards[index] = new Card();
				cards[index].setValue(i);
				cards[index].setRank(i);
				cards[index].setSuit(suits[j]);
				cards[index].setImage(new Image("file:images/" + i + 
						suits[j].substring(0, 1) + ".png"));
				if (index >= 36 && index <= 40)
				{
					cards[index].setValue(10);
				}
				
				if (index >= 41 && index <= 44)
				{
					cards[index].setValue(10);
				}
				
				if (index >= 45 && index <= 48)
				{
					cards[index].setValue(10);
				}

				if (index >= 48 && index <= 52)
				{
					cards[index].setValue(11);
				}
				if (i < 11)
				{
					cards[index].setName(cards[index].getValue() + " of " + 
							cards[index].getSuit());
				}
				else if (i == 11)
				{
					cards[index].setName("JACK of " + 
							cards[index].getSuit());
					
				}
				else if (i == 12)
				{
					cards[index].setName("QUEEN of " + 
							cards[index].getSuit());
					
				}
				else if (i == 13)
				{
					cards[index].setName("KING of " + 
							cards[index].getSuit());
					
				}
				else
				{
					cards[index].setName("ACE of " + 
							cards[index].getSuit());
					
				}

				cards[index].setImageView(new ImageView(cards[index].getImage()));
				cards[index].setWidth(cards[index].getImage().getWidth());
				cards[index].setHeight(cards[index].getImage().getHeight());
				index++;
			}
		}

		backOfCard = new Card();
	}

	/**
	* Shuffles the deck
	*
	* @param shuffleDeck =  shuffles the cards 1000 times.
	* @return The shuffled deck
	* @throws IndexOutOfBoundsException – if the index argument is
	negative or not less than the length of the array cards
	*/

	public void shuffleDeck() {

		for (int counter = 1; counter <= 1000; counter++)
		{
			for (int i = 0; i < cards.length; i++)
			{
				Card currentCard = cards[i];
				int randomIndex = rnd.nextInt(52);
				cards[i] = cards[randomIndex];
				cards[randomIndex] = currentCard;
			}
		}
	}

	/**
	* get the back of card
	*
	* @param getBackOfCard = no parameters
	* @return the back of card
	* @throws wrong method reference(different name, case sensitivity, etc)
	*/

	public Card getBackOfCard() {

		return backOfCard;
	}

	/**
	* deals the card
	*
	* @param dealCard = no parameters
	* @return number of cards dealt in cards array
	* @throws wrong method reference(different name, case sensitivity, etc) or index out of bound error
	*/
	public Card dealCard() {

		numCardsDealt++;
		cardsRemaining--;
		return cards[numCardsDealt];

	}
	
	/**
	* get remaining cards
	*
	* @param getRemainingCards = no parameters
	* @return the remaining cards as an integer
	* @throws wrong method reference(different name, case sensitivity, etc)
	*/
	public int getCardsRemaining() {
		return cardsRemaining;
	}
	
	/**
	* sets the cards remaining
	*
	* @param setCardsRemaining = takes int paramenter cardsRemaining
	* @return cardsRemaining
	* @throws wrong method reference(different name, case sensitivity, etc)
	*/
	public void setCardsRemaining(int cardsRemaining) {
		this.cardsRemaining = cardsRemaining;
	}
	
}