/**
 * Creator: David Brunner
 * Date: 4/15/2021
 * Description: Creates a PokerCards class that creates a deck of cards and contains several helpful methods for evaluating and exchanging a hand of 5
 */

import java.util.ArrayList; //Imports the ArrayList functionality from java.util

import javafx.scene.image.Image; //Imports the Image class from JavaFX
import javafx.scene.image.ImageView; //Imports the ImageView class from JavaFX

/** The public class PokerCards contains several methods and data fields for the displaying the information of a hand of 5 and the overall deck */
public class PokerCards {
	static private ArrayList<Image> cardArray = new ArrayList<Image>(); //Creates the imageArray ArrayList to hold each of the card images and represent the deck
	static private int deckSize = 54; //Sets the size of the deck to 54 cards 
	static private ArrayList<Image> usedCards = new ArrayList<Image>(); //Creates the usedCards ArrayList to hold each of the discarded card images
	static private ArrayList<Integer> usedCardsIndex = new ArrayList<Integer>(); //Creates the usedCardsIndex ArrayList to hold each of the discarded card indexes
	
	private boolean isHold0; //Creates a boolean value representing whether the user would like to keep the first card in the current hand 
	private int index0; //Creates a integer value representing the current index, position, within the cardArray of the first card in the current hand 
	private boolean isHold1; //Creates a boolean value representing whether the user would like to keep the second card in the current hand 
	private int index1; //Creates a integer value representing the current index, position, within the cardArray of the second card in the current hand 
	private boolean isHold2; //Creates a boolean value representing whether the user would like to keep the third card in the current hand 
	private int index2; //Creates a integer value representing the current index, position, within the cardArray of the third card in the current hand 
	private boolean isHold3; //Creates a boolean value representing whether the user would like to keep the fourth card in the current hand 
	private int index3; //Creates a integer value representing the current index, position, within the cardArray of the fourth card in the current hand 
	private boolean isHold4; //Creates a boolean value representing whether the user would like to keep the fifth card in the current hand 
	private int index4; //Creates a integer value representing the current index, position, within the cardArray of the fifth card in the current hand 
	
	private boolean isCpu; //Creates a boolean value that determines whether the current deck represents a Cpu deck
	
	private int oppIndex0; //Creates a integer value representing the index of the first card of the opponent's hand 
	private int oppIndex1; //Creates a integer value representing the index of the first card of the opponent's hand 
	private int oppIndex2; //Creates a integer value representing the index of the first card of the opponent's hand 
	private int oppIndex3; //Creates a integer value representing the index of the first card of the opponent's hand 
	private int oppIndex4; //Creates a integer value representing the index of the first card of the opponent's hand 
	
	static {
		
		//Loops 54 times to add the images of each card within the deck 
		for (int i = 0; i < deckSize; i++) {
			cardArray.add(new Image("card_image/" + i + ".png")); //Adds a new image from within the cardimage folder and with a uniquenumber.png as its name
		}; 
	}
	
	/** The basic constructor for PokerCards, mainly used to represent the user */
	PokerCards() {
		this.isHold0 = true; //Initializes the isHold0 to true to represent the player wanting to keep the card in the new hand
		this.index0 = getNewIndex(); //Retrieves a new, unique, and random index for the first card from the deck 
		this.isHold1 = true; //Initializes the isHold1 to true to represent the player wanting to keep the card in the new hand
		this.index1 = getNewIndex(); //Retrieves a new, unique, and random index for the second card from the deck 
		this.isHold2 = true; //Initializes the isHold2 to true to represent the player wanting to keep the card in the new hand
		this.index2 = getNewIndex(); //Retrieves a new, unique, and random index for the third card from the deck
		this.isHold3 = true; //Initializes the isHold3 to true to represent the player wanting to keep the card in the new hand
		this.index3 = getNewIndex(); //Retrieves a new, unique, and random index for the fourth card from the deck 
		this.isHold4 = true; //Initializes the isHold4 to true to represent the player wanting to keep the card in the new hand
		this.index4 = getNewIndex();//Retrieves a new, unique, and random index for the fifth card from the deck 
		
		this.isCpu = false; //Sets the isCpu boolean value to false 
	}
	
	/** The constructor for PokerCards with the opponents information, mainly used to represent the cpu */
	PokerCards(PokerCards opponentDeck, boolean isCpu) {
		this.oppIndex0 = opponentDeck.getCardIndex(0); //Sets the oppIndex0 to the index of the first card in the opponents hand
		this.oppIndex1 = opponentDeck.getCardIndex(1); //Sets the oppIndex1 to the index of the second card in the opponents hand
		this.oppIndex2 = opponentDeck.getCardIndex(2); //Sets the oppIndex3 to the index of the third card in the opponents hand
		this.oppIndex3 = opponentDeck.getCardIndex(3); //Sets the oppIndex4 to the index of the fourth card in the opponents hand
		this.oppIndex4 = opponentDeck.getCardIndex(4); //Sets the oppIndex5 to the index of the fifth card in the opponents hand
		
		this.isHold0 = false; //Initializes isHold0 to false so represent the player wanting to exchange the card in the new hand
		this.index0 = getNewIndex(); //Retrieves a new, unique, and random index for the first card from the deck that also isn't found in the opponent hand
		this.isHold1 = false; //Initializes isHold1 to false so represent the player wanting to exchange the card in the new hand
		this.index1 = getNewIndex(); //Retrieves a new, unique, and random index for the second card from the deck that also isn't found in the opponent hand
		this.isHold2 = false; //Initializes isHold2 to false so represent the player wanting to exchange the card in the new hand
		this.index2 = getNewIndex(); //Retrieves a new, unique, and random index for the third card from the deck that also isn't found in the opponent hand
		this.isHold3 = false; //Initializes isHold3 to false so represent the player wanting to exchange the card in the new hand
		this.index3 = getNewIndex(); //Retrieves a new, unique, and random index for the fourth card from the deck that also isn't found in the opponent hand
		this.isHold4 = false; //Initializes isHold4 to false so represent the player wanting to exchange the card in the new hand
		this.index4 = getNewIndex(); //Retrieves a new, unique, and random index for the fifth card from the deck that also isn't found in the opponent hand
		
		this.isCpu = isCpu; //Sets the isCpu value to true 
	}
	
	/** The public method deal exchanges all of the cards within the current hand, thus turning their isHold value to false */
	public void deal() {
		usedCardsIndex.add(index0); //Adds the index of the first card within the current hand to usedCardsIndex
		usedCards.add(cardArray.get(index0)); //Adds the Image of the first card within the current hand to usedCards
		cardArray.set(index0, null); //Removes the card Image from the deck 
		isHold0 = false; //Sets the first cards isHold value to false 
		index0 = getNewIndex(); //Retrieves a new, unique, and random index for the first card from the deck that also isn't found in the opponent hand
		
		usedCardsIndex.add(index1); //Adds the index of the second card within the current hand to usedCardsIndex
		usedCards.add(cardArray.get(index1)); //Adds the Image of the second card within the current hand to usedCards
		cardArray.set(index1, null); //Removes the card Image from the deck
		isHold1 = false; //Sets the second cards isHold value to false 
		index1 = getNewIndex(); //Retrieves a new, unique, and random index for the second card from the deck that also isn't found in the opponent hand
		
		usedCardsIndex.add(index2); //Adds the index of the third card within the current hand to usedCardsIndex
		usedCards.add(cardArray.get(index2)); //Adds the Image of the third card within the current hand to usedCards
		cardArray.set(index2, null); //Removes the card Image from the deck
		isHold2 = false; //Sets the third cards isHold value to false  
		index2 = getNewIndex(); //Retrieves a new, unique, and random index for the third card from the deck that also isn't found in the opponent hand
		
		usedCardsIndex.add(index3); //Adds the index of the fourth card within the current hand to usedCardsIndex
		usedCards.add(cardArray.get(index3)); //Adds the Image of the fourth card within the current hand to usedCards
		cardArray.set(index3, null); //Removes the card Image from the deck
		isHold3 = false; //Sets the fourth cards isHold value to false  
		index3 = getNewIndex(); //Retrieves a new, unique, and random index for the fourth card from the deck that also isn't found in the opponent hand
		
		usedCardsIndex.add(index4); //Adds the index of the fifth card within the current hand to usedCardsIndex
		usedCards.add(cardArray.get(index4)); //Adds the Image of the fifth card within the current hand to usedCards
		cardArray.set(index4, null); //Removes the card Image from the deck
		isHold4 = false; //Sets the fifth cards isHold value to false   
		index4 = getNewIndex(); //Retrieves a new, unique, and random index for the fifth card from the deck that also isn't found in the opponent hand
	}
	
	/** The  public method drawNewCard retrieves a specific card within the hand and exchanges it for a new one */
	public void drawNewCard(int card) {
		
		//Checks whether the number represents the first card 
		if (card == 0) {
			usedCardsIndex.add(index0); //If true, then the index of the first card within the current hand is added to usedCardsIndex
			usedCards.add(cardArray.get(index0)); //Adds the Image of the first card within the current hand to usedCards
			cardArray.set(index0, null); //Removes the card Image from the deck
			index0 = getNewIndex(); //Retrieves a new, unique, and random index for the first card from the deck that also isn't found in the opponent hand
		}; 
			
		//Checks whether the number represents the second card 
		if (card == 1) {
			usedCardsIndex.add(index1); //If true, then the index of the second card within the current hand is added to usedCardsIndex
			usedCards.add(cardArray.get(index1)); //Adds the Image of the second card within the current hand to usedCards
			cardArray.set(index1, null); //Removes the card Image from the deck
			index1 = getNewIndex(); //Retrieves a new, unique, and random index for the second card from the deck that also isn't found in the opponent hand
		}; 
		
		//Checks whether the number represents the third card 
		if (card == 2) {
			usedCardsIndex.add(index2); //If true, then the index of the third card within the current hand is added to usedCardsIndex
			usedCards.add(cardArray.get(index2)); //Adds the Image of the third card within the current hand to usedCards
			cardArray.set(index2, null); //Removes the card Image from the deck
			index2 = getNewIndex(); //Retrieves a new, unique, and random index for the third card from the deck that also isn't found in the opponent hand
		}; 
		
		//Checks whether the number represents the fourth card 
		if (card == 3) {
			usedCardsIndex.add(index3); //If true, then the index of the fourth card within the current hand is added to usedCardsIndex
			usedCards.add(cardArray.get(index3)); //Adds the Image of the fourth card within the current hand to usedCards
			cardArray.set(index3, null); //Removes the card Image from the deck
			index3 = getNewIndex(); //Retrieves a new, unique, and random index for the fourth card from the deck that also isn't found in the opponent hand
		}; 
		
		//Checks whether the number represents the fifth card 
		if (card == 4) {
			usedCardsIndex.add(index4); //If true, then the index of the fifth card within the current hand is added to usedCardsIndex
			usedCards.add(cardArray.get(index4)); //Adds the Image of the fifth card within the current hand to usedCards
			cardArray.set(index4, null); //Removes the card Image from the deck
			index4 = getNewIndex(); //Retrieves a new, unique, and random index for the fifth card from the deck that also isn't found in the opponent hand
		}; 
	}
	
	/** The public method setHold sets the isHold value for a specific card to determine whether it gets exchanged in a draw */
	public void setHold(int card, boolean value) {
		
		//Checks whether the number represents the first card 
		if (card == 0) 
			isHold0 = value; //If true, changes the isHold value to the value parameter
		else if (card == 1) //Else, checks whether the number represents the second card 
			isHold1 = value; //If true, changes the isHold value to the value parameter
		else if (card == 2) //Else, checks whether the number represents the third card 
			isHold2 = value; //If true, changes the isHold value to the value parameter
		else if (card == 3) //Else, checks whether the number represents the fourth card 
			isHold3 = value; //If true, changes the isHold value to the value parameter
		else if (card == 4) //Else, checks whether the number represents the fifth card 
			isHold4 = value; //If true, changes the isHold value to the value parameter
		else 
			throw new Error("No card found!"); //Throws a new error just in case a false integer is provided 
	}
	
	/** The public method getCard retrieves and returns a specific card within the hand */
	public ImageView getCard(int card) {
		
		//Checks whether the number represents the first card 
		if (card == 0) 
			return new ImageView(cardArray.get(index0)); //If true, returns the Image that the first card holds within the deck as an ImageView 
		else if (card == 1) //Else, checks whether the number represents the second card 
			return new ImageView(cardArray.get(index1)); //If true, returns the Image that the second card holds within the deck as an ImageView 
		else if (card == 2) //Else, checks whether the number represents the third card 
			return new ImageView(cardArray.get(index2)); //If true, returns the Image that the third card holds within the deck as an ImageView 
		else if (card == 3) //Else, checks whether the number represents the fourth card 
			return new ImageView(cardArray.get(index3)); //If true, returns the Image that the fourth card holds within the deck as an ImageView 
		else if (card == 4) //Else, checks whether the number represents the fifth card 
			return new ImageView(cardArray.get(index4)); //If true, returns the Image that the fifth card holds within the deck as an ImageView 
		else 
			throw new Error("No card found!"); //Throws a new error just in case a false integer is provided 
	}
	
	/** The public method getCard retrieves and returns the index of specific card within the hand */
	public int getCardIndex(int card) {
		
		//Checks whether the number represents the first card 
		if (card == 0) 
			return index0; //If true, returns the index of the first card 
		else if (card == 1) //Else, checks whether the number represents the second card 
			return index1; //If true, returns the index of the second card 
		else if (card == 2) //Else, checks whether the number represents the third card 
			return index2; //If true, returns the index of the third card 
		else if (card == 3) //Else, checks whether the number represents the fourth card 
			return index3; //If true, returns the index of the fourth card 
		else if (card == 4) //Else, checks whether the number represents the fifth card 
			return index4; //If true, returns the index of the fifth card 
		else 
			throw new Error("No card found!"); //Throws a new error just in case a false integer is provided 
	}
	
	/** The public method getCardHold retrieves and returns the isHold boolean value of specific card within the hand */
	public boolean getCardHold(int card) {
		
		//Checks whether the number represents the first card 
		if (card == 0)
			return isHold0; //If true, returns the isHold value of the first card 
		else if (card == 1) //Else, checks whether the number represents the second card 
			return isHold1; //If true, returns the isHold value of the second card 
		else if (card == 2) //Else, checks whether the number represents the third card 
			return isHold2; //If true, returns the isHold value of the third card 
		else if (card == 3) //Else, checks whether the number represents the fourth card 
			return isHold3; //If true, returns the isHold value of the fourth card 
		else if (card == 4) //Else, checks whether the number represents the fifth card 
			return isHold4; //If true, returns the isHold value of the fifth card 
		else 
			throw new Error("No card found!"); //Throws a new error just in case a false integer is provided 
	}
	
	/** The private method getNewIndex finds and generates a random, unique number between 0 and 54, representing the index of a card within the array */
	private int getNewIndex() {
		int newIndex = (int)(Math.random() * deckSize); //Generates a random integer between 0 and 54; 
		
		//Checks whether the current deck represents the user deck 
		if (!this.isCpu) {
			
			//The while loop continues until the newIndex is unique and equal to the indexes of the other cards 
			while (newIndex == index0 || newIndex == index1 || newIndex == index2 || newIndex == index3 || newIndex == index4 || cardArray.get(newIndex) == null) {
				newIndex = (int)(Math.random() * deckSize); //Sets the random integer between and 0 and 54 if one of the previous comparisons were true 
			}; 
		} else {
			
			//The while loop continues until the newIndex does not equal any index of the opponent, or users, hand
			while (newIndex == index0 || newIndex == index1 || newIndex == index2 || newIndex == index3 || newIndex == index4 || 
					newIndex == oppIndex0 || newIndex == oppIndex1 || newIndex == oppIndex2 || newIndex == oppIndex3 || newIndex == oppIndex4 || 
					cardArray.get(newIndex) == null) {
				newIndex = (int)(Math.random() * deckSize); //Sets the random integer between and 0 and 54 if one of the previous comparisons were true 
			};
		}; 
		
		return newIndex; //Returns the new number 
	}
	
	/** The public method getRemainingSize returns the size of the deck of used cards */
	public int getRemainingSize() {
		return usedCards.size(); //Returns the size of the deck of used cards 
	}
	
	/** The public method shuffleDeck refills the deck of unused cards */
	public void shuffleDeck() {
		
		//The for loop iterates through each card within the ArrayList of used cards
		for (int i = 0; i < usedCards.size(); i++) {
			cardArray.set(usedCardsIndex.get(i), usedCards.get(i)); //Resets the current card within the unused deck with its original value 
		}; 
		
		usedCards.clear(); //Clears the ArrayList of Images of usedCards
		usedCardsIndex.clear(); //Clears the ArrayList of indexes of usedCards
	}
	
}
