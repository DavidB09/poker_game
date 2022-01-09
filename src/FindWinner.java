/**
 * Creator: David Brunner
 * Date: 4/15/2021
 * Description: Creates a FindWinner class that receives two hands of cards and evaluates them to see which contains the better value
 */


//Imports ArrayList from java.util
import java.util.ArrayList;
import java.util.Collections; //Imports the Collections class from java.util

/** The public class FindWinner contains several methods and data fields for examining the cpu and user hand of cards to see which one contains the higher hand value */
public class FindWinner {
	
	private ArrayList<Integer> userHand = new ArrayList<Integer>(); //The userHand ArrayList is created to represent the user hand
	private ArrayList<Integer> cpuHand = new ArrayList<Integer>(); //The cpuHand ArrayList is created to represent the cpu hand
	
	double userScore = 0; //The userScore double is created to represent the value of the user hand 
	double cpuScore = 0; //The cpuScore double is created to represent the value of the cpu hand 
	
	/** The constructor for the FindWinner class and takes two PokerCards parameters for the user hand first and the cpu hand second */
	FindWinner(PokerCards userDeck, PokerCards cpuDeck) {
		userHand.add(0, userDeck.getCardIndex(0)); //Retrieves the first images index from the deck of cards and adds it to the userHand ArrayList
		userHand.add(1, userDeck.getCardIndex(1)); //Retrieves the second images index from the deck of cards and adds it to the userHand ArrayList
		userHand.add(2, userDeck.getCardIndex(2)); //Retrieves the third images index from the deck of cards and adds it to the userHand ArrayList
		userHand.add(3, userDeck.getCardIndex(3)); //Retrieves the fourth images index from the deck of cards and adds it to the userHand ArrayList
		userHand.add(4, userDeck.getCardIndex(4)); //Retrieves the fifth images index from the deck of cards and adds it to the userHand ArrayList
		
		cpuHand.add(0, cpuDeck.getCardIndex(0)); //Retrieves the first images index from the deck of cards and adds it to the cpuHand ArrayList
		cpuHand.add(1, cpuDeck.getCardIndex(1)); //Retrieves the second images index from the deck of cards and adds it to the cpuHand ArrayList
		cpuHand.add(2, cpuDeck.getCardIndex(2)); //Retrieves the third images index from the deck of cards and adds it to the cpuHand ArrayList
		cpuHand.add(3, cpuDeck.getCardIndex(3)); //Retrieves the fourth image's index from the deck of cards and adds it to the cpuHand ArrayList
		cpuHand.add(4, cpuDeck.getCardIndex(4)); //Retrieves the fifth image's index from the deck of cards and adds it to the cpuHand ArrayList
	}
	
	/** The public method findWinner calls several methods to evaluate the hands of cards and find the winner */ 
	public String findWinner() {
		sortBySuit(); //Calls the sortBySuit method to sort the hands by their cards value
		
		boolean royalFlush = checkRoyalFlush(); //Creates a boolean that checks whether a hand contains a Royal Flush
		
		//Checks whether a Royal Flush occurred 
		if (royalFlush) {
			return userScore == cpuScore ? "DRAW" : userScore > cpuScore ? "USER" : "CPU"; //If it has, then a string is returned with the same score resulting in a "DRAW", a higher userScore resulting in a "USER", and a higher cpuScore resulting in a "CPU"
		} else {
			boolean wildRoyalFlush = checkWildRoyalFlush(); //If a Royal Flush hasn't occurred, then a boolean is created to check whether a deck contains a Wild Royal Flush
			
			//Checks whether a Wild Royal Flush occurred 
			if (wildRoyalFlush) {
				return userScore == cpuScore ? "DRAW" : userScore > cpuScore ? "USER" : "CPU"; //If it has, then a string is returned with the same score resulting in a "DRAW", a higher userScore resulting in a "USER", and a higher cpuScore resulting in a "CPU"
			} else {
				boolean fiveOfAKind = checkFiveOfAKind(); //If a Wild Royal Flush hasn't occurred, then a boolean is created to check whether a deck contains a Five Of A Kind  
				
				//Checks whether a Five Of A Kind occurred 
				if (fiveOfAKind) {
					return userScore == cpuScore ? "DRAW" : userScore > cpuScore ? "USER" : "CPU"; //If it has, then a string is returned with the same score resulting in a "DRAW", a higher userScore resulting in a "USER", and a higher cpuScore resulting in a "CPU"
				} else {
					boolean straightFlush = checkStraightFlush(); //If a Five Of A Kind hasn't occurred, then a boolean is created to check whether a deck contains a Straight Flush
					
					//Checks whether a Straight Flush occurred 
					if (straightFlush) {
						return userScore == cpuScore ? "DRAW" : userScore > cpuScore ? "USER" : "CPU"; //If it has, then a string is returned with the same score resulting in a "DRAW", a higher userScore resulting in a "USER", and a higher cpuScore resulting in a "CPU"
					} else {
						boolean fourOfAKind = checkFourOfAKind(); //If a Straight Flush hasn't occurred, then a boolean is created to check whether a deck contains a Four Of A Kind 
						
						//Checks whether a Four Of A Kind occurred 
						if (fourOfAKind) {
							return userScore == cpuScore ? "DRAW" : userScore > cpuScore ? "USER" : "CPU"; //If it has, then a string is returned with the same score resulting in a "DRAW", a higher userScore resulting in a "USER", and a higher cpuScore resulting in a "CPU"
						} else {
							boolean fullHouse = checkFullHouse(); //If a Four Of A Kind hasn't occurred, then a boolean is created to check whether a deck contains a Full House 
							
							//Checks whether a Full House occurred 
							if (fullHouse) {
								return userScore == cpuScore ? "DRAW" : userScore > cpuScore ? "USER" : "CPU"; //If it has, then a string is returned with the same score resulting in a "DRAW", a higher userScore resulting in a "USER", and a higher cpuScore resulting in a "CPU"
							} else {
								boolean flush = checkFlush(); //If a Full House hasn't occurred, then a boolean is created to check whether a deck contains a Flush
								
								//Checks whether a Flush occurred 
								if (flush) {
									return userScore == cpuScore ? "DRAW" : userScore > cpuScore ? "USER" : "CPU"; //If it has, then a string is returned with the same score resulting in a "DRAW", a higher userScore resulting in a "USER", and a higher cpuScore resulting in a "CPU"
								} else {
									boolean straight = checkStraight(); //If a Flush hasn't occurred, then a boolean is created to check whether a deck contains a Straight
									
									//Checks whether a Straight occurred 
									if (straight) {
										return userScore == cpuScore ? "DRAW" : userScore > cpuScore ? "USER" : "CPU"; //If it has, then a string is returned with the same score resulting in a "DRAW", a higher userScore resulting in a "USER", and a higher cpuScore resulting in a "CPU"
									} else {
										boolean threeOfAKind = checkThreeOfAKind(); //If a Straight hasn't occurred, then a boolean is created to check whether a deck contains a Three Of A Kind
										
										//Checks whether a Three Of A Kind occurred 
										if (threeOfAKind) {
											return userScore == cpuScore ? "DRAW" : userScore > cpuScore ? "USER" : "CPU"; //If it has, then a string is returned with the same score resulting in a "DRAW", a higher userScore resulting in a "USER", and a higher cpuScore resulting in a "CPU"
										} else {
											checkTwoPair(); //If a Three Of A Kind hasn't occurred, then the only remaining possible hand is a Two Pair, thus the function is called 
											
											return userScore == cpuScore ? "DRAW" : userScore > cpuScore ? "USER" : "CPU"; //A string is returned with the same score resulting in a "DRAW", a higher userScore resulting in a "USER", and a higher cpuScore resulting in a "CPU"
										}
									}
								}
				
							}
						}
					}
				}
			}
		}
	}
	
	/** The public getFinalHand method returns a String with the type of hand that won */
	public String getFinalHand(String winner) {
		
		//Checks whether the winner was the user 
		if (winner == "USER") {
			if (userScore >= 10) { //Checks whether the score was above or equal to ten
				return winner + ": Royal Flush"; //If true, then the score represents a Royal Flush hand 
			} else if (userScore >= 9) { //Checks whether the score was above or equal to nine
				return winner + ": Wild Royal Flush"; //If true, then the score represents a Wild Royal Flush hand 
			} else if (userScore >= 8) { //Checks whether the score was above or equal to eight
				return winner + ": Five of a Kind"; //If true, then the score represents a Five Of A Kind hand 
			} else if (userScore >= 7) { //Checks whether the score was above or equal to seven
				return winner + ": Straight Flush"; //If true, then the score represents a Straight Flush hand 
			} else if (userScore >= 6) { //Checks whether the score was above or equal to six
				return winner + ": Four Of A Kind"; //If true, then the score represents a Four Of A Kind hand 
			} else if (userScore >= 5) { //Checks whether the score was above or equal to five
				return winner + ": Full House"; //If true, then the score represents a Full House hand 
			} else if (userScore >= 4) { //Checks whether the score was above or equal to four
				return winner + ": Flush"; //If true, then the score represents a Flush hand 
			} else if (userScore >= 3) { //Checks whether the score was above or equal to three
				return winner + ": Straight"; //If true, then the score represents a Straight hand 
			} else if (userScore >= 2) { //Checks whether the score was above or equal to two
				return winner + ": Three Of A Kind"; //If true, then the score represents a Three Of A Kind hand 
			} else if (userScore >= 1) { //Checks whether the score was above or equal to one
				return winner + ": Two Pair"; //If true, then the score represents a Two Pair hand 
			} else {
				return winner + ": Nothing"; //The score represents nothing as no hand occurred
			}
		} else if (winner == "CPU") { //Else, checks whether the winner was the cpu 
			if (cpuScore >= 10) { //Checks whether the score was above or equal to ten
				return winner + ": Royal Flush"; //If true, then the score represents a Royal Flush hand 
			} else if (cpuScore >= 9) { //Checks whether the score was above or equal to nine
				return winner + ": Wild Royal Flush"; //If true, then the score represents a Wild Royal Flush hand 
			} else if (cpuScore >= 8) { //Checks whether the score was above or equal to eight
				return winner + ": Five of a Kind"; //If true, then the score represents a Five Of A Kind hand 
			} else if (cpuScore >= 7) { //Checks whether the score was above or equal to seven
				return winner + ": Straight Flush"; //If true, then the score represents a Straight Flush hand 
			} else if (cpuScore >= 6) { //Checks whether the score was above or equal to six
				return winner + ": Four Of A Kind"; //If true, then the score represents a Four Of A Kind hand 
			} else if (cpuScore >= 5) { //Checks whether the score was above or equal to five
				return winner + ": Full House"; //If true, then the score represents a Full House hand 
			} else if (cpuScore >= 4) { //Checks whether the score was above or equal to four
				return winner + ": Flush"; //If true, then the score represents a Flush hand 
			} else if (cpuScore >= 3) { //Checks whether the score was above or equal to three
				return winner + ": Straight"; //If true, then the score represents a Straight hand 
			} else if (cpuScore >= 2) { //Checks whether the score was above or equal to two
				return winner + ": Three Of A Kind"; //If true, then the score represents a Three Of A Kind hand 
			} else if (cpuScore >= 1) { //Checks whether the score was above or equal to one
				return winner + ": Two Pair"; //If true, then the score represents a Two Pair hand 
			} else {
				return winner + ": Nothing"; //The score represents nothing as no hand occurred
			}
		} else { //Else the result was a draw between the user and the cpu
			return getFinalHand("USER") + "\n" + getFinalHand("CPU"); //A string containing both the user and cpu hand is returned
		}
	}
	
	/** The public getWinAmount method returns a double value with the amount of winnings based on the type of hand */
	public double getWinAmount(double betAmount) {
		if (userScore >= 10) { //Checks whether the score was above or equal to ten
			return betAmount * 100; //If true, then the bet amount is multiplied by 100
		} else if (userScore >= 9) { //Checks whether the score was above or equal to nine
			return betAmount * 75; //If true, then the bet amount is multiplied by 75
		} else if (userScore >= 8) { //Checks whether the score was above or equal to eight
			return betAmount * 50; //If true, then the bet amount is multiplied by 50 
		} else if (userScore >= 7) { //Checks whether the score was above or equal to seven
			return betAmount * 25; //If true, then the bet amount is multiplied by 25 
		} else if (userScore >= 6) { //Checks whether the score was above or equal to six
			return betAmount * 15; //If true, then the bet amount is multiplied by 15
		} else if (userScore >= 5) { //Checks whether the score was above or equal to five
			return betAmount * 8; //If true, then the bet amount is multiplied by 8
		} else if (userScore >= 4) { //Checks whether the score was above or equal to four
			return betAmount * 6; //If true, then the bet amount is multiplied by 6
		} else if (userScore >= 3) { //Checks whether the score was above or equal to three
			return betAmount * 4; //If true, then the bet amount is multiplied by 4
		} else if (userScore >= 2) { //Checks whether the score was above or equal to two
			return betAmount * 3; //If true, then the bet amount is multiplied by 3
		} else if (userScore >= 1) { //Checks whether the score was above or equal to one
			return betAmount * 2; //If true, then the bet amount is multiplied by 2
		} else {
			return betAmount; //The score represents nothing as no particular hand occurred
		}
	}
	
	/** The private method getRank receives a card from a deck of 54 and return its rank */
	private int getRank(int card) {
		int rank = card; //Retrieves the card in a deck of 54 from the parameter
		
		//Checks whether the card a Wild Card, or Joker
		if (rank == 52 || rank == 53) {
			return 100; //If true, then the rank of 100 is returned
		}
		
		//Loops while the card value is above -1 when subtracted by 13, resulting in the rank for the card 
		while (rank - 13 > -1) {
			rank -= 13; //Subtracts 13 from the card value, bringing it down through a "lower" suit  
		}; 
		
		return rank;  //Returns the rank of the card 
	}
	
	/** The private method sortBySuit sorts the cards in the user and cpu hand by the lowest to highest rank of the cards, an Ace card is determined to be the lowest possible card */
	private void sortBySuit() {
		ArrayList<Integer> sortedUserCards = new ArrayList<Integer>(); //The sortedUserCards ArrayList is created to represent the sorted userHand
		ArrayList<Integer> sortedCpuCards = new ArrayList<Integer>(); //The sortedCpuCards ArrayList is created to represent the sorted cpuHand
		
		//Creates a for loop to iterate through each possible type of card in a deck of 52
		for (int i = 0; i < 13; i++) {
			
			//The for loop iterates 5 times to check each card within a hand of 5
			for (int a = 0; a < 5; a++) {
				
				//Checks whether the rank of the current card in userHand is equal to the current card value in the for loop
				if (getRank(userHand.get(a)) == i) {
					sortedUserCards.add(userHand.get(a)); //Adds the current card in userHand to the sortedUserCards 
				}; 
				
				//Checks whether the rank of the current hand in cpuHand is equal to the current card value in the for loop
				if (getRank(cpuHand.get(a)) == i) {
					sortedCpuCards.add(cpuHand.get(a)); //Adds the current card in cpuHand to the sortedCpuCards 
				}; 
			}; 
		}; 
		
		//Since a deck of 54 with two wildCards is used, the user hand must be checked if it contains a Joker
		if (userHand.contains(52)) {
			sortedUserCards.add(52); //If true, the Joker card is added last to the sorted deck
		}
		
		//Since a deck of 54 with two wildCards is used, the user hand must be checked if it contains a Joker
		if (userHand.contains(53)) {
			sortedUserCards.add(53); //If true, the Joker card is added last to the sorted deck
		}
		
		//Since a deck of 54 with two wildCards is used, the cpu hand must be checked if it contains a Joker
		if (cpuHand.contains(52)) {
			sortedCpuCards.add(52); //If true, the Joker card is added last to the sorted deck
		}
		
		//Since a deck of 54 with two wildCards is used, the cpu hand must be checked if it contains a Joker
		if (cpuHand.contains(53)) {
			sortedCpuCards.add(53); //If true, the Joker card is added last to the sorted deck
		}
		
		userHand = sortedUserCards; //Sets the userHand ArrayList to its newly sorted ArrayList
		cpuHand = sortedCpuCards; //Sets the cpuHand ArrayList to its newly sorted ArrayList
	}
	
	/** The private method checkRoyalFlush checks whether the user or cpu hand contains a Royal Flush */
	private boolean checkRoyalFlush() {
		
		//The for loop iterates four times to check each suit in a deck of 52
		for (int i = 0; i < 52; i += 13) {
			
			//Checks whether userHand contains the Ace, 10, Jack, Queen, and King in the current suit
			if (userHand.contains(i) && userHand.contains(i + 9) && userHand.contains(i + 10) && userHand.contains(i + 11) && userHand.contains(i + 12)) {
				userScore = 10; //If true, then userScore is set to 10
			}; 
			
			//Checks whether cpuHand contains the Ace, 10, Jack, Queen, and King in the current suit
			if (cpuHand.contains(i) && cpuHand.contains(i + 9) && cpuHand.contains(i + 10) && cpuHand.contains(i + 11) && cpuHand.contains(i + 12)) {
				cpuScore = 10; //If true, then cpuScore is set to 10
			}; 
		}; 
		
		return (userScore == 10 || cpuScore == 10); //Returns whether the user or cpu hand contains a Royal Flush
	}
	
	/** The private method checkWildRoyalFlush checks whether the user or cpu hand contains a Wild Royal Flush */
	private boolean checkWildRoyalFlush() {
		
		//The for loop iterates four times to check each suit in a deck of 52
		for (int i = 0; i < 52; i += 13) {
			int numOfUserOccurrances = 0; //An Integer value is initialized to represent the number of occurrences of a higher ranked card within userHand
			int numOfCpuOccurrances = 0; //An Integer value is initialized to represent the number of occurrences of a higher ranked card within cpuHand
			
			//The for loop iterates 5 times to check each card within a hand of 5
			for (int a = 0; a < 5; a++) {
				
				//Checks whether the userHand contains an Ace, 10, Jack, Queen, or King in the current suit
				if (userHand.get(a) == i || userHand.get(a) == i + 9 || userHand.get(a) == i + 10 || userHand.get(a) == i + 11 || userHand.get(a) == i + 12) {
					numOfUserOccurrances++; //If true, then the number of user occurrences is increased by one
				}; 
				
				//Checks whether the cpuHand contains an Ace, 10, Jack, Queen, or King in the current suit
				if (cpuHand.get(a) == i || cpuHand.get(a) == i + 9 || cpuHand.get(a) == i + 10 || cpuHand.get(a) == i + 11 || cpuHand.get(a) == i + 12) {
					numOfCpuOccurrances++; //If true, then the number of cpu occurrences is increased by one
				}; 
			}; 
			
			//Checks whether the number of occurrences of a higher ranked card within userHand is equal to 3
			if (numOfUserOccurrances == 3) {
				
				//Checks whether the userHand contains two Jokers 
				if (userHand.contains(52) && userHand.contains(53)) {
					userScore = 9; //If true, then the userScore is set to 9
				}; 
			} else if (numOfUserOccurrances == 4) { //Else, checks whether the number of occurrences of a higher ranked card within userHand is equal to 4
				
				//Checks whether the userHand contains one Joker
				if (userHand.contains(52) || userHand.contains(53)) {
					userScore = 9.5; //If true, then the userScore is set to 9
				}; 
			}; 
			
			//Checks whether the number of occurrences of a higher ranked card within cpuHand is equal to 3
			if (numOfCpuOccurrances == 3) {
				
				//Checks whether the cpuHand contains two Jokers 
				if (cpuHand.contains(52) && cpuHand.contains(53)) {
					cpuScore = 9; //If true, then the cpuScore is set to 9
				};
			} else if (numOfCpuOccurrances == 4) { //Else, checks whether the number of occurrences of a higher ranked card within cpuHand is equal to 4
				
				//Checks whether the cpuHand contains one Jokers 
				if (cpuHand.contains(52) || cpuHand.contains(53)) { 
					cpuScore = 9.5; //If true, then the cpuScore is set to 9.5
				}; 
			}; 
		}; 
		
		return (userScore >= 9 || cpuScore >= 9); //Returns whether the user or cpu hand contains a Wild Royal Flush
	}
	
	/** The private method checkFiveOfAKind checks whether the user or cpu hand contains a Five Of A Kind */
	private boolean checkFiveOfAKind() {
		int userValue = 0; //Initializes a value for the rank of a possible Five Of A Kind within userHand
		int cpuValue = 0;  //Initializes a value for the rank of a possible Five Of A Kind within cpuHand
		
		//The for loop iterates 13 times to check each card rank in a deck of 52
		for (int i = 0; i < 13; i++) {
			int numOfUserOccurrances = 0; //An Integer value is initialized to represent the number of occurrences of a particular card within userHand
			int numOfCpuOccurrances = 0; //An Integer value is initialized to represent the number of occurrences of a particular card within cpuHand
			
			//The for loop iterates 5 times to check each card within a hand of 5
			for (int a = 0; a < 5; a++) {
				
				//Checks whether the current card within userHand is equal to the current card rank
				if (getRank(userHand.get(a)) == i) {
					numOfUserOccurrances++; //If true, then the number of user occurrences is increased by one
				}; 
				
				//Checks whether the current card within cpuHand is equal to the current card rank
				if (getRank(cpuHand.get(a)) == i) {
					numOfCpuOccurrances++; //If true, then the number of cpu occurrences is increased by one
				}; 
			}; 
			
			//Checks whether the number of occurrences of a particular card within userHand is equal to 3 and the userHand contains two Jokers
			if (numOfUserOccurrances == 3 && (userHand.contains(52) && userHand.contains(53))) {
				userScore = 8; //If true, then the userScore is set to 8
				userValue = i; //The userValue is set to the current rank of the card
			} else if (numOfUserOccurrances == 4 && (userHand.contains(52) || userHand.contains(53))) { //Else, checks whether the number of occurrences of a particular card within userHand is equal to 4 and the userHand contains one Jokers
				userScore = 8; //If true, then the userScore is set to 8
				userValue = i; //The userValue is set to the current rank of the card
			} else if (numOfUserOccurrances == 5) { //Checks whether the number of occurrences of a particular card within userHand is equal to 5
				userScore = 8; //If true, then the userScore is set to 8
				userValue = i; //The userValue is set to the current rank of the card
			}; 
			
			//Checks whether the number of occurrences of a particular card within cpuHand is equal to 3 and the cpuHand contains two Jokers
			if (numOfCpuOccurrances == 3 && (cpuHand.contains(52) && cpuHand.contains(53))) {
				cpuScore = 8; //If true, then the cpuScore is set to 8
				cpuValue = i; //The cpuValue is set to the current rank of the card
			} else if (numOfCpuOccurrances == 4 && (cpuHand.contains(52) || cpuHand.contains(53))) { //Else, checks whether the number of occurrences of a particular card within cpuHand is equal to 4 and the cpuHand contains one Jokers
				cpuScore = 8; //If true, then the cpuScore is set to 8
				cpuValue = i; //The cpuValue is set to the current rank of the card
			} else if (numOfCpuOccurrances == 5) { //Else, checks whether the number of occurrences of a particular card within cpuHand is equal to 5
				cpuScore = 8; //If true, then the cpuScore is set to 8
				cpuValue = i; //The cpuValue is set to the current rank of the card
			}; 
		}; 
		
		//Checks whether both the user and the cpu got a Five Of A Kind
		if (userScore == 8 && cpuScore == 8) {
			
			//Checks whether the rank that appeared five times in the userHand was an Ace
			if (userValue == 0) {  
				userScore = 8.5; //If true, then the userScore is set to 8.5
			} else if (cpuValue == 0) { //Else, checks whether the rank that appeared five times in cpuHand was an Ace 
				cpuScore = 8.5; //If true, then the cpuScore is set to 8.5
			} else if (userValue > cpuValue) { //Else, checks whether the rank that appeared five times in userHand was bigger than the one in cpuHand
				userScore = 8.5; //If true, then the userScore is set to 8.5
			} else { //Else the rank that appeared five times in cpuHand was bigger than the one in userHand
				cpuScore = 8.5; //The cpuScore is set to 8.5
			}; 
			
			return true; //True is returned since the user and the cpu hand have a Five Of A Kind
		} else {
			return (userScore == 8 || cpuScore == 8); //Returns whether the user or cpu hand contains a Five Of A Kind
		}
	}
	
	/** The private method checkStraightFlush checks whether the user or cpu hand contains a Straight Flush */
	private boolean checkStraightFlush() {
		checkStraight(); //Calls the checkStraight method to see whether the user or cpu hand contains a Straight
		double straightUserScore = userScore; //A double value is created to remember whether the user hand contains a Straight
		double straightCpuScore = cpuScore; //A double value is created to remember whether the cpu hand contains a Straight
		
		checkFlush(); //Calls the checkStraight method to see whether the user or cpu hand contains a Flush
		
		//Checks whether the user hand contains a Straight and a Flush
		if (straightUserScore >= 3 && userScore >= 4) {
			
			//Checks whether the userScore is equal to 4.5 
			if (userScore == 4.5) {
				userScore = 7.5; //If true, the userScore is set to 7.5
				return true; //True is returned since the user hand contains a Straight Flush with the highest value
			} else {
				userScore = 7; //Else, the userScore is set to 7
			}; 
		}; 
		
		//Checks whether the cpu hand contains a Straight and a Flush
		if (straightCpuScore >= 3 && cpuScore >= 4) { 
			
			//Checks whether the cpuScore is equal to 4.5 
			if (cpuScore == 4.5) {
				cpuScore = 7.5; //If true, the cpuScore is set to 7.5
				return true; //True is returned since the cpu hand contains a Straight Flush with the highest value
			} else {
				cpuScore = 7; //Else, the cpuScore is set to 7
			}; 
		}; 
		
		return (userScore == 7 || cpuScore == 7); //Returns whether the user or cpu hand contain a Straight Flush
	}
	
	/** The private method checkFourOfAKind checks whether the user or cpu hand contains a Four Of A Kind */ 
	private boolean checkFourOfAKind() {
		int userValue = 0; //Initializes a value for the rank of a possible Four Of A Kind in userHand
		int cpuValue = 0;  //Initializes a value for the rank of a possible Four Of A Kind in cpuHand
		
		//The for loop iterates 13 times to check each card rank in a deck of 52
		for (int i = 0; i < 13; i++) {
			int numOfUserOccurrances = 0; //An Integer value is initialized to represent the number of occurrences of a particular card within userHand
			int numOfCpuOccurrances = 0; //An Integer value is initialized to represent the number of occurrences of a particular card within cpuHand
			
			//The for loop iterates 5 times to check each card in a hand of 5
			for (int a = 0; a < 5; a++) {
				
				//Checks whether the current card within userHand is equal to the current card rank
				if (getRank(userHand.get(a)) == i) {
					numOfUserOccurrances++; //If true, then the number of user occurrences is increased by one
				}; 
				
				//Checks whether the current card within cpuHand is equal to the current card rank
				if (getRank(cpuHand.get(a)) == i) {
					numOfCpuOccurrances++; //If true, then the number of cpu occurrences is increased by one
				}; 
			}; 
			
			//Checks whether the number of occurrences of a particular card within userHand is equal to 4
			if (numOfUserOccurrances == 4) {
				userScore = 6; //If true, then userScore is set to 6
				userValue = i; //The userValue is set to the current rank of the card
			} else if (numOfUserOccurrances == 3 && (userHand.contains(52) || userHand.contains(53))) { //Else, checks whether the number of occurrences of a particular card within userHand is equal to 3 and the userHand contains one Joker
				userScore = 6; //If true, then userScore is set to 6 
				userValue = i; //The userValue is set to the current rank of the card
			} else if (numOfUserOccurrances == 2 && (userHand.contains(52) && userHand.contains(53))) { //Else, checks whether the number of occurrences of a particular card within userHand is equal to 2 and the userHand contains two Jokers
				userScore = 6; //If true, then userScore is set to 6 
				userValue = i; //The userValue is set to the current rank of the card
			}
			
			//Checks whether the number of occurrences of a particular card within cpuHand is equal to 4
			if (numOfCpuOccurrances == 4) {
				cpuScore = 6; //If true, then cpuScore is set to 6
				cpuValue = i; //The cpuValue is set to the current rank of the card
			} else if (numOfCpuOccurrances == 3 && (cpuHand.contains(52) || cpuHand.contains(53))) { //Else, checks whether the number of occurrences of a particular card within cpuHand is equal to 3 and the cpuHand contains one Joker
				cpuScore = 6; //If true, then cpuScore is set to 6
				cpuValue = i; //The cpuValue is set to the current rank of the card
			} else if (numOfCpuOccurrances == 2 && (cpuHand.contains(52) && cpuHand.contains(53))) { //Else, checks whether the number of occurrences of a particular card within cpuHand is equal to 2 and the cpuHand contains two Jokers
				cpuScore = 6; //If true, then cpuScore is set to 6
				cpuValue = i; //The cpuValue is set to the current rank of the card
			}; 
		}; 
		
		//Checks whether both the user and the cpu got a Four Of A Kind
		if (userScore == 6 && cpuScore == 6) {
			
			//Checks whether the rank that appeared four times in userHand was an Ace 
			if (userValue == 0) { 
				userScore = 6.5; //If true, then the userScore is set to 6.5
			} else if (cpuValue == 0) { //Checks whether the rank that appeared four times in cpuHand was an Ace 
				cpuScore = 6.5; //If true, then the cpuScore is set to 6.5
			} else if (userValue > cpuValue) { //Checks whether the rank that appeared four times in userHand was bigger than the one in cpuHand
				userScore = 6.5; //If true, then the userScore is set to 6.5
			} else { //Else the rank that appeared four times in cpuHand was bigger than the one in the userHand
				cpuScore = 6.5; //The cpuScore is set to 6.5
			}; 
			
			return true; //True is returned since the user and the cpu hand have a Four Of A Kind
		} else {
			return (userScore == 6 || cpuScore == 6); //Returns whether the user or cpu hand contains a Four Of A Kind
		}
	}
	
	/** The private method checkFullHouse checks whether the user or cpu hand contains a Full House */ 
	private boolean checkFullHouse() {
		int userValue = 0; //Initializes a value for the rank of a possible Full House in userHand
		int cpuValue = 0; //Initializes a value for the rank of a possible Full House in cpuHand
		
		//The for loop iterates 13 times to check each card rank in a deck of 52
		for (int i = 0; i < 13; i++) {
			int userOccurrance = 0; //Initializes a value for the number of occurrences of the current rank in userHand
			int cpuOccurrance = 0; //Initializes a value for the number of occurrences of the current rank in cpuHand
			
			//The for loop iterates four times to check each suit in a deck of 52
			for (int a = i; a < 52; a += 13) {
				
				//Checks whether userHand contains the current card rank in the current suit
				if (userHand.contains(a)) { 
					userOccurrance++; //If true, then userOccurrance is increased by one
				}; 
					
				//Checks whether the cpuHand contains the current card rank in the current suit
				if (cpuHand.contains(a)) { 
					userOccurrance++; //If true, then cpuOccurrance is increased by one
				}; 
			}; 
			
			//Checks whether the current rank appears three times in userHand or appears two times with one Joker present 
			if (userOccurrance == 3 || (userOccurrance == 2 && (userHand.contains(52) || userHand.contains(53)))) { 
				
				//The for loop iterates 13 times to check each card rank in a deck of 52
				for (int j = 0; j < 13; j++) {
					int userOccurrance2 = 0; //Initializes a value for the number of occurrences #2 of the current rank in userHand
					
					//Checks whether the current rank equals the rank that already appeared three times or two times with one Joker
					if (j == i) {
						continue; //If true, continue
					} else {
						
						//The for loop iterates 4 times to check each suit in a deck of 52
						for (int b = j; b < 52; b += 13) {
							
							//Checks whether userHand contains the current card rank #2 in the current suit
							if (userHand.contains(b)) {
								userOccurrance2++; //If true, then userOccurrance2 is increased by one
							}; 
						}; 
					}; 
					
					//Checks whether the current rank appears twice and the userHand contains two Jokers 
					if (userOccurrance == 2 && (userHand.contains(52) && userHand.contains(53))) {
						userScore = 5; //If true, then userScore is set to 5
						userValue = getRank(userHand.get(2)); //userValue is set to the highest ranked card in userHand that isn't a Joker
					} else if (userOccurrance2 == 2) { //Else, checks whether the current rank #2 appears twice in userHand
						
						//Checks whether the current rank appears three times in userHand
						if (userOccurrance == 3) {
							userScore = 5; //If true, the userScore is set to 5
							userValue = i; //The userValue is set to the current rank
						} else if (userOccurrance == 2) { //Else, checks whether the current rank appears twice in userHand
							userScore = 5; //If true, the userScore is set to 5
							userValue = j; //The userValue is set to the current rank #2
						}; 
					} else if (userOccurrance == 3 && userOccurrance2 == 1) { //Else, checks whether the current rank appears twice and the current rank #2 appears once in the userHand
						
						//Checks if userHand contains one Joker
						if (userHand.contains(52) || userHand.contains(53)) {
							userScore = 5; //If true, the userScore is set to 5
							userValue = i; //The userValue is set to the current rank
						}; 
					}; 
				}; 
			}; 
			
			//Checks whether the current rank appears three times in cpuHand or appears two times with one Joker present 
			if (cpuOccurrance == 3 || (cpuOccurrance == 2 && (cpuHand.contains(52) || cpuHand.contains(53)))) {
				
				//The for loop iterates 13 times to check each card rank in a deck of 52
				for (int j = 0; j < 13; j++) {
					int cpuOccurrance2 = 0; //Initializes a value for the number of occurrences #2 of the current rank in cpuHand
					
					//Checks whether the current rank equals the rank that already appeared three times or two times with one Joker
					if (j == i) {
						continue; //If true, continue
					} else {
						
						//The for loop iterates 4 times to check each suit in a deck of 52
						for (int b = j; b < 52; b += 13) {
							
							//Checks whether cpuHand contains the current card rank #2 in the current suit
							if (cpuHand.contains(b)) {
								cpuOccurrance2++; //If true, then userOccurrance2 is increased by one
							}; 
						}; 
					}; 
					
					//Checks whether the current rank appears twice and the cpuHand contains two Jokers 
					if (cpuOccurrance == 2 && (cpuHand.contains(52) && cpuHand.contains(53))) {
						cpuScore = 5; //If true, then cpuScore is set to 5
						cpuValue = getRank(cpuHand.get(2)); //The cpuValue is set to the highest ranked card in cpuHand that isn't a Joker
					} else if (cpuOccurrance2 == 2) { //Else, checks whether the current rank #2 appears twice in cpuHand
						
						//Checks whether the current rank appears three times in cpuHand
						if (cpuOccurrance == 3) {
							cpuScore = 5; //If true, the cpuScore is set to 5
							cpuValue = i; //The cpuValue is set to the current rank
						} else if (cpuOccurrance == 2) { //Else, checks whether the current rank appears twice in cpuHand
							cpuScore = 5; //If true, the cpuScore is set to 5
							cpuValue = j; //The cpuValue is set to the current rank #2
						}; 
					} else if (cpuOccurrance == 3 && cpuOccurrance2 == 1) { //Else, checks whether the current rank appears twice and the current rank #2 appears once in the cpuHand
						
						//Checks if cpuHand contains one Joker
						if (cpuHand.contains(52) || cpuHand.contains(53)) {
							cpuScore = 5; //If true, the cpuScore is set to 5
							cpuValue = i; //The cpuValue is set to the current rank
						}; 
					}; 
				}; 
			}; 
		}; 
		
		//Checks whether both the user and the cpu have a Full House
		if (userScore == 5 && cpuScore == 5) {
			
			//Checks whether the rank that appeared three times in userHand was an Ace
			if (userValue == 0) {  
				userScore = 5.5; //If true, the userScore is set to 5.5
			} else if (cpuValue == 0) { //Else, checks whether the rank that appeared three times in cpuHand was an Ace 
				cpuScore = 5.5; //If true, the cpuScore is set to 5.5
			} else if (userValue > cpuValue) { //Else, checks whether the rank that appeared three times in userHand was bigger than the one in cpuHand
				userScore = 5.5; //If true, the userScore is set to 5.5
			} else { //Else the rank that appeared three times in cpuHand was bigger than the one in userHand
				cpuScore = 5.5; //If true, the cpuScore is set to 5.5
			}; 
			
			return true; //True is returned since the user and the cpu hand have a Full House
		} else {
			return (userScore == 5 || cpuScore == 5); //Returns whether the user or cpu hand contains a Full House
		}
	}
	
	/** The private method checkFlush checks whether the user or cpu hand contains a Flush */ 
	private boolean checkFlush() {
		ArrayList<Integer> userValue = new ArrayList<>(); //Initializes an ArrayList for the rankings of the cards of a possible Flush in userHand
		ArrayList<Integer> cpuValue = new ArrayList<>(); //Initializes an ArrayList for the rankings of the cards of a possible Flush in cpuHand
		
		boolean userDone = false; //The userDone boolean is created for when the cards aren't within the same suit in userHand
		boolean cpuDone = false; //The cpuDone boolean is created for when the cards aren't within the same suit in cpuHand
			
		//The for loop iterates four times to check each suit in a deck of 52
		for (int i = 0; i < 52; i += 13) {
			
			//The for loop iterates 5 times to check each card within a hand of 5
			for(int a = 0; a < 5; a++) {
				
				//Checks whether the current card and all the other cards in userHand are within the current suit
				if (userHand.get(a) >= i && userHand.get(a) < i + 13 && !userDone) {
					userValue.add(getRank(userHand.get(a))); //If true, then the rank of the current card is added to userValue
				} else if ((userHand.get(a) == 52 || userHand.get(a) == 53) && !userDone) { //Else, checks whether the current card is a Joker and all the other cards in userHand are within the current suit
					userValue.add(getRank(userHand.get(a))); //If true, then the rank of the current card is added to userValue
				} else if (userValue.size() != 5) { //Else, the current card isn't within the current suit so the size of the userValue is checked to see whether a previous suit wasn't already checked or hadn't filled the ArrayList
					userValue.clear(); //If true, then userValue is cleared for the next suit 
				}; 
				
				//Checks whether the current card and all the other cards in cpuHand are still within the current suit
				if (cpuHand.get(a) >= i && cpuHand.get(a) < i + 13 && !cpuDone) {
					cpuValue.add(getRank(cpuHand.get(a))); //If true, then the rank of the current card is added to cpuValue
				} else if ((cpuHand.get(a) == 52 || cpuHand.get(a) == 53) && !cpuDone) { //Else, checks whether the current card is a Joker and all the other cards in cpuHand are within the current suit
					cpuValue.add(getRank(cpuHand.get(a))); 
				} else if (cpuValue.size() != 5) { //Else, the current card isn't within the current suit so the size of the cpuValue is checked to see whether a previous suit wasn't already checked or hadn't filled the ArrayList
					cpuValue.clear(); //If true, then cpuValue is cleared for the next suit 
				}; 
			}; 
			
			//Checks whether all of the cards in userHand are within the same suit since then the userValue ArrayList would be filled
			if (userValue.size() == 5) {
				userScore = 4; //If true, then userScore is set to 4
				Collections.sort(userValue); //The userValue ArrayList is sorted from lowest to highest ranking
				userDone = true; //The user is done checking since the suit has been found
			}; 
			
			//Checks whether all of the cards in cpuHand are within the same suit since then the cpuValue ArrayList would be filled
			if (cpuValue.size() == 5) {
				cpuScore = 4; //If true, then cpuScore is set to 4
				Collections.sort(cpuValue); //The cpuValue ArrayList is sorted from lowest to highest ranking
				cpuDone = true; //The cpu is done checking since the suit has been found
			}; 
		}; 
		
		
		//Checks whether both the user and the cpu hand have a Flush
		if (userScore == 4 && cpuScore == 4) {
			
			////Checks whether only the user has an Ace within their Flush as then it can function as the highest rank
			if (userValue.get(0) == 0 && cpuValue.get(0) != 0) {
				userScore = 4.5; //If true, then userScore is set to 4.5
			} else if (cpuValue.get(0) == 0 && userValue.get(0) != 0) { //Else, checks whether only the cpu has an Ace within their Flush as then it can function as the highest rank
				cpuScore = 4.5;  //If true, then cpuScore is set to 4.5
			} else { //Else either none or both of the Flush hands have an Ace so their card rankings must be compared
				
				//The for loop iterates backward 5 times through each card in a hand of 5
				for (int i = 4; i >= 0; i--) {
					
					//Checks whether the current card within userHand is a Joker and the current card within cpuHand isn't
					if (getRank(userHand.get(i)) == 100 && getRank(cpuHand.get(i)) != 100) {
						cpuScore = 4.5; //If true, then cpuScore is set to 4.5 since Jokers are evaluated as the lowest possible card ranking
						break; //The for loop is stopped since the winner has been found
					} else if (getRank(userHand.get(i)) != 100 && getRank(cpuHand.get(i)) == 100) { //Else, checks whether the current card within userHand isn't Joker and the current card within cpuHand is
						userScore = 4.5; //If true, then userScore is set to 4.5 since Jokers are evaluated as the lowest possible card ranking
						break; //The for loop is stopped since the winner has been found
					};
					
					//Checks whether the rank of the current card within the userHand is greater than the rank of the current card within cpuHand
					if (userValue.get(i) > cpuValue.get(i)) { //Checks whether the current rank of the the userValue is higher than the current rank of the cpuValue
						userScore = 4.5; //If true, then the userScore is set to 4.5
						break; //The for loop is quit since the highest rank has been found
					} else if (userValue.get(i) < cpuValue.get(i)) { //Else, checks whether the rank of the current card within the userHand is less than the rank of the current card within cpuHand
						cpuScore = 4.5; //If true, then the cpuScore is set to 4.5
						break; //The for loop is quit since the highest rank has been found
					}; 
				}; 
			}; 
			
			return true; //True is returned since the user and the cpu hand have a Flush
		} else {
			return (userScore == 4 || cpuScore == 4); //Returns whether the user or cpu hand contain a Flush
		}
	}
	
	/** The private method checkStraight checks whether the user or cpu hand contains a Straight */
	private boolean checkStraight() {
		boolean isUserDuplicate = false; //The boolean isUserDuplicate is created to check whether userHand has cards with duplicate card rankings
		boolean isCpuDuplicate = false; //The boolean isCpuDuplicate is created to check whether cpuHand has cards with duplicate card rankings
		
		//The for loop iterates four times through the second until the last card in the userHand
		for (int i = 1; i < 5; i++) {
			
			//Checks whether the rank of the current card is equal to the rank of the previous card within the userHand and isn't a Joker
			if ((getRank(userHand.get(i)) == getRank(userHand.get(i - 1))) && getRank(userHand.get(i)) != 100) {
				isUserDuplicate = true; //If true, then isUserDuplicate is set to true
			}; 
			
			//Checks whether the rank of the current card is equal to the rank of the previous card within the cpuHand and isn't a Joker
			if ((getRank(cpuHand.get(i)) == getRank(cpuHand.get(i - 1))) && getRank(cpuHand.get(i)) != 100) {
				isCpuDuplicate = true; //If true, then isCpuDuplicate is set to true
			}; 
		}; 
		
		//Checks whether the first card in userHand is an Ace, as it can be used as the low or high card 
		if (getRank(userHand.get(0)) == 0) {
			int secondUserRank = getRank(userHand.get(1)); //Retrieves the rank of the lowest card in userHand (besides the Ace)
			
			//Checks whether userHand contains a Joker card
			if (userHand.contains(52) || userHand.contains(53)) {
				
				//Checks whether userHand contains two Joker cards
				if (userHand.contains(52) && userHand.contains(53)) {
					
					//Checks whether the rank of the third card within userHand is not higher than secondUserRank plus 3
					if (getRank(userHand.get(2)) <= secondUserRank + 3) {
						
						//Checks whether the userHand does not contain any cards with duplicate rankings
						if (!isUserDuplicate) {
							userScore = 3; //If true, then userScore is set to 3
						}; 
					}; 
				} else {
					
					//Checks whether the second and third cards within userHand are not higher than secondUserRank plus 3
					if (getRank(userHand.get(2)) <= secondUserRank + 3 && getRank(userHand.get(3)) <= secondUserRank + 3) {
						
						//Checks whether the userHand does not contain any cards with duplicate rankings
						if (!isUserDuplicate) {
							userScore = 3; //If true, then userScore is set to 3
						}; 
					}; 
				}; 
				
			} else if (getRank(userHand.get(2)) == secondUserRank + 1 && getRank(userHand.get(3)) == secondUserRank + 2 && getRank(userHand.get(4)) == secondUserRank + 3) { //Else, checks whether the rank of the rest of the cards is one higher than the previous, thus being four in a row
				userScore = 3; //If true, the userScore is set to 3
			}; 
		} else {
			
			//Checks whether userHand contains a Joker card
			if (userHand.contains(52) || userHand.contains(53)) {
				
				//Checks whether userHand contains two Joker cards
				if (userHand.contains(52) && userHand.contains(53)) {
					
					//Checks whether the rank of the second and third card within userHand is not higher than rank of the first card plus 4
					if (getRank(userHand.get(1)) <= getRank(userHand.get(0)) + 4 && getRank(userHand.get(2)) <= getRank(userHand.get(0)) + 4) {
						
						//Checks whether the userHand does not contain any cards with duplicate rankings
						if (!isUserDuplicate) {
							userScore = 3; //If true, then userScore is set to 3
						}; 
					}; 
				} else {
					
					//Checks whether the rank of the rest of the cards is one higher than the previous, thus being four in a row
					if (getRank(userHand.get(1)) <= getRank(userHand.get(0)) + 4 && getRank(userHand.get(2)) <= getRank(userHand.get(0)) + 4 && getRank(userHand.get(3)) <= getRank(userHand.get(0)) + 4) { 
						
						//Checks whether the userHand does not contain any cards with duplicate rankings
						if (!isUserDuplicate) {
							userScore = 3; //If true, then userScore is set to 3
						}; 
					}; 
				}; 
			} else {
				boolean isConsecutive = true; //Creates a boolean value for checking whether all of the card rankings in userHand are consecutive 
				
				//The for loop iterates four times through the second until the last card in the userHand
				for (int i = 1; i < 5; i++) {
					
					//Checks whether the current card is not equal to the ranking of the first card plus the index of the current card
					if (getRank(userHand.get(i)) != getRank(userHand.get(0)) + i) { 
						isConsecutive = false; //If the previous comparison is true, then isConsective is set to false 
					}; 
				}; 
				
				//Checks if the cards in userHand are consecutive
				if (isConsecutive) {
					userScore = 3; //If true, then the userScore is set to 3
				}; 
			}; 
		}; 
		
		//Checks whether the first card in the sorted cpu deck is an Ace, as it can be used as the low or high card (besides the Ace) 
		if (getRank(cpuHand.get(0)) == 0) {
			int secondCpuRank = getRank(cpuHand.get(1)); //Retrieves the rank of the lowest card in cpuHand (besides the Ace)
			
			//Checks whether cpuHand contains a Joker card
			if (cpuHand.contains(52) || cpuHand.contains(53)) {
				
				//Checks whether cpuHand contains two Joker cards
				if (cpuHand.contains(52) && cpuHand.contains(53)) {
					
					//Checks whether the rank of the third card within cpuHand is not higher than secondCpuRank plus 3
					if (getRank(cpuHand.get(2)) <= secondCpuRank + 3) {
						
						//Checks whether the cpuHand does not contain any cards with duplicate rankings
						if (!isCpuDuplicate) {
							cpuScore = 3; //If true, then cpuScore is set to 3
						}; 
					}; 
				} else {
					
					//Checks whether the second and third cards within cpuHand are not higher than secondCpuRank plus 3
					if (getRank(cpuHand.get(2)) <= secondCpuRank + 3 && getRank(cpuHand.get(3)) <= secondCpuRank + 3) {
						
						//Checks whether the cpuHand does not contain any cards with duplicate rankings
						if (!isCpuDuplicate) {
							cpuScore = 3; //If true, then cpuScore is set to 3
						}; 
					}; 
				}; 
				
			} else if (getRank(cpuHand.get(2)) == secondCpuRank + 1 && getRank(cpuHand.get(3)) == secondCpuRank + 2 && getRank(cpuHand.get(4)) == secondCpuRank + 3) { //Else, checks whether the rank of the rest of the cards is one higher than the previous, thus being four in a row
				cpuScore = 3; //If true, the cpuScore is set to 5
			}; 
		} else {
			
			//Checks whether cpuHand contains a Joker card
			if (cpuHand.contains(52) || cpuHand.contains(53)) {
				
				//Checks whether cpuHand contains two Joker cards
				if (cpuHand.contains(52) && cpuHand.contains(53)) {
					
					//Checks whether the rank of the second and third card within cpuHand is not higher than rank of the first card plus 4
					if (getRank(cpuHand.get(1)) <= getRank(cpuHand.get(0)) + 4 && getRank(cpuHand.get(2)) <= getRank(cpuHand.get(0)) + 4) {
						
						//Checks whether the cpuHand does not contain any cards with duplicate rankings
						if (!isCpuDuplicate) {
							cpuScore = 3; //If true, then cpuScore is set to 3
						}; 
					}; 
				} else {
					
					//Checks whether the rank of the rest of the cards is one higher than the previous, thus being four in a row
					if (getRank(cpuHand.get(1)) <= getRank(cpuHand.get(0)) + 4 && getRank(cpuHand.get(2)) <= getRank(cpuHand.get(0)) + 4 && getRank(cpuHand.get(3)) <= getRank(cpuHand.get(0)) + 4) {
						
						//Checks whether the cpuHand does not contain any cards with duplicate rankings
						if (!isCpuDuplicate) {
							cpuScore = 3; //If true, then cpuScore is set to 3
						}; 
					}; 
				}; 
			} else {
				boolean isConsecutive = true; //Creates a boolean value for checking whether all of the card rankings in cpuHand are consecutive 
				
				//The for loop iterates four times through the second until the last card in the cpuHand
				for (int i = 1; i < 5; i++) {
					
					//Checks whether the current card is not equal to the ranking of the first card plus the index of the current card
					if (getRank(cpuHand.get(i)) != getRank(cpuHand.get(0)) + i) {
						isConsecutive = false; //If the previous comparison is true, then isConsective is set to false 
					}; 
				}; 
				
				//Checks if the cards in cpuHand are consecutive
				if (isConsecutive) {
					cpuScore = 3; //If true, then cpuScore is set to 5
				};
			}; 
		};
		
		//Checks whether both the user and the cpu hand have a Straight
		if (userScore == 3 && cpuScore == 3) {
			
			//Checks whether only the user has an Ace within their Straight as then it can function as the highest rank
			if (getRank(userHand.get(0)) == 0 && getRank(userHand.get(1)) != 1 && getRank(cpuHand.get(0)) != 0) { 
				userScore = 3.5; //If true, then the userScore is set to 3.5
			} else if (getRank(cpuHand.get(0)) == 0 && getRank(cpuHand.get(1)) != 1 && getRank(userHand.get(0)) != 0 ) { //Else, checks whether only the cpu has an Ace within their Straight as then it can function as the highest rank
				cpuScore = 3.5; //If true, then the cpuScore is set to 3.5
			} else { //Else none of the Straight hands have an Ace so their card rankings must be compared
				
				//The for loop iterates backward 5 times through each card in a hand of 5
				for (int i = 4; i >= 0; i--) {
					
					//Checks whether the current card within userHand is a Joker and the current card within cpuHand isn't
					if (getRank(userHand.get(i)) == 100 && getRank(cpuHand.get(i)) != 100) {
						cpuScore = 4.5; //If true, then cpuScore is set to 4.5 since Jokers are evaluated as the lowest possible card ranking
						break; //The for loop is stopped since the winner has been found
					} else if (getRank(userHand.get(i)) != 100 && getRank(cpuHand.get(i)) == 100) { //Else, checks whether the current card within userHand isn't Joker and the current card within cpuHand is
						userScore = 4.5; //If true, then userScore is set to 4.5 since Jokers are evaluated as the lowest possible card ranking
						break; //The for loop is stopped since the winner has been found
					};
					
					//Checks whether the rank of the current card within the userHand is greater than the rank of the current card within cpuHand
					if (userHand.get(i) > cpuHand.get(i)) { //Checks whether the current rank of the the userValue is higher than the current rank of the cpuValue
						userScore = 4.5; //If true, then the userScore is set to 4.5
						break; //The for loop is quit since the highest rank has been found
					} else if (userHand.get(i) < cpuHand.get(i)) { //Else, checks whether the rank of the current card within the userHand is less than the rank of the current card within cpuHand
						cpuScore = 4.5; //If true, then the cpuScore is set to 4.5
						break; //The for loop is quit since the highest rank has been found
					}; 
				}; 
			}; 
			
			return true; //True is returned since the user and the cpu hand have a Straight
		} else {
			return (userScore == 3 || cpuScore == 3); //Returns whether the user or cpu hand contain a Straight
		}
	}
	
	/** The private method checkThreeOfAKind checks whether the user or cpu hand contains a Three Of A Kind */ 
	private boolean checkThreeOfAKind() {
		int userValue = 0; //Initialize the userValue Integer to represent the rank of the Three Of A Kind in userHand
		int cpuValue = 0; //Initialize the cpuValue Integer to represent the rank of the Three Of A Kind in cpuHand
		
		//The for loop iterates 13 times to check each card rank in a deck of 52
		for (int i = 0; i < 13; i++) {
			int numOfUserOccurrances = 0; //Initializes the numOfUserOccurrances Integer to count the number of occurrences of the current rank in userHand
			int numOfCpuOccurrances = 0; //Initializes the numOfCpuOccurrances Integer to count the number of occurrences of the current rank in cpuHand
			
			//The for loop iterates 5 times to check each card in a hand of 5
			for (int a = 0; a < 5; a++) {
				
				 //Checks whether the current card in userHand equals the current rank
				if (getRank(userHand.get(a)) == i) {
					numOfUserOccurrances++; //If true, then the numOfUserOccurrances is increased by one
				}; 
				
				//Checks whether the current card in cpuHand equals the current rank
				if (getRank(cpuHand.get(a)) == i) { 
					numOfCpuOccurrances++; //If true, then the numOfCpuOccurrances is increased by one
				}; 
			}; 
			
			//Checks whether userHand contains two Jokers 
			if (userHand.contains(52) && userHand.contains(53)) {
				
				//Checks whether the userHand contains an Ace
				if (getRank(userHand.get(0)) == 0) {
					userScore = 2.5; //If true, then userScore is set to 2.5
				} else {
					userScore = 2; //Else, userScore is set to 2
					userValue = getRank(userHand.get(2)); //userValue is set to the rank of the highest non-Joker card
				}; 
			} else if (userHand.contains(52) || userHand.contains(53)) { //Else, checks whether userHand contains one Joker
				
				//Checks whether the number of occurrences of a particular card within userHand is equal to 2
				if (numOfUserOccurrances == 2) {
					
					//Checks whether the current card that appears twice times in userHand is an Ace 
					if (i == 0) { 
						userScore = 2.5; //If true, then userScore is set to 2.5
					} else {
						userScore = 2; //Else, the userScore is set to 2
						userValue = i; //The userValue is set to the current rank
					};
				}; 
			} else if (numOfUserOccurrances == 3) { //Else, Checks whether the number of occurrences of a particular card within userHand is equal to 3
				
				//Checks whether the current card that appears three times in userHand is an Ace 
				if (i == 0) { 
					userScore = 2.5; //If true, then userScore is set to 2.5
				} else {
					userScore = 2; //Else, the userScore is set to 2
					userValue = i; //The userValue is set to the current rank
				}; 
			}; 
			
			//Checks whether cpuHand contains two Jokers 
			if (cpuHand.contains(52) && cpuHand.contains(53)) {
				
				//Checks whether the cpuHand contains an Ace
				if (getRank(cpuHand.get(0)) == 0) {
					cpuScore = 2.5; //If true, then cpuScore is set to 2.5
				} else {
					cpuScore = 2; //Else, cpuScore is set to 2
					cpuValue = getRank(cpuHand.get(2)); //cpuValue is set to the rank of the highest non-Joker card
				}; 
			} else if (cpuHand.contains(52) || cpuHand.contains(53)) { //Else, checks whether cpuHand contains one Joker
				
				//Checks whether the number of occurrences of a particular card within cpuHand is equal to 2
				if (numOfCpuOccurrances == 2) {
					
					//Checks whether the current card that appears three times in cpuHand is an Ace 
					if (i == 0) { 
						cpuScore = 2.5; //If true, then cpuScore is set to 2.5
					} else {
						cpuScore = 2; //Else, the cpuScore is set to 2
						cpuValue = i; //The cpuValue is set to the current rank
					};
				}; 
			} else if (numOfCpuOccurrances == 3) { //Else, Checks whether the number of occurrences of a particular card within cpuHand is equal to 3
				
				//Checks whether the current card that appears three times in cpuHand is an Ace 
				if (i == 0) { 
					cpuScore = 2.5; //If true, then cpuScore is set to 2.5
				} else {
					cpuScore = 2; //Else, the cpuScore is set to 2
					cpuValue = i; //The cpuValue is set to the current rank
				}; 
			}; 
		}; 
		
		//Checks whether both the user and the cpu hand got a Three Of A Kind 
		if (userScore == 2 && cpuScore == 2) {
			
			//Checks whether the rank of the user card is higher than the rank of the cpu card
			if (userValue > cpuValue) { 
				userScore = 2.5; //If true, the userScore is set to 2.5
			} else if (userValue < cpuValue) { //Else, checks whether the rank of the user card is lower than the rank of the cpu card
				cpuScore = 2.5; //If true, the cpuScore is set to 2.5
			}; 
			
			return true; //True is returned since the user and the cpu hand have a Three Of A Kind
		} else {
			return (userScore == 2) && (cpuScore == 2); //Returns whether the user or cpu hand contain a Three Of A Kind
		}
	}
	
	/** The private method checkTwoPair checks whether the user or cpu hand contains a Two Pair */ 
	private boolean checkTwoPair() {
		int userPair1 = 0; //Creates a userPair Integer for the rank of the first pair in userHand
		boolean userPair1Found = false; //Creates a userPair1Found Boolean for determining the existence of a first pair in userHand
		int userPair2 = 0; //Creates a userPair Integer for the rank of the second pair in userHand
		boolean userPair2Found = false; //Creates a userPair2Found Boolean for determining the existence of a second pair in userHand
		int userLoner = 0; //Creates a userLoner Integer for the rank of the loner card without a pair in userHand
		
		int cpuPair1 = 0; //Creates a userPair Integer for the rank of the first pair in cpuHand
		boolean cpuPair1Found = false; //Creates a cpuPair1Found Boolean for determining the existence of a first pair in cpuHand
		int cpuPair2 = 0; //Creates a userPair Integer for the rank of the second pair in cpuHand
		boolean cpuPair2Found = false; //Creates a cpuPair2Found Boolean for determining the existence of a second pair in cpuHand
		int cpuLoner = 0;  //Creates a cpuLoner Integer for the rank of the loner card without a pair in cpuHand
		
		//The for loop iterates 13 times to check each card rank in a deck of 52
		for (int i = 0; i < 13; i++) {
			int numOfUserOccurrances = 0; //Initializes the numOfUserOccurrances Integer to count the number of occurrences of the current rank in userHand
			int numOfCpuOccurrances = 0; //Initializes the numOfCpuOccurrances Integer to count the number of occurrences of the current rank in cpuHand
			
			//The for loop iterates 5 times to check each card in a hand of 5
			for (int a = 0; a < 5; a++) {
				
				//Checks whether the current card in userHand equals the current rank
				if (getRank(userHand.get(a)) == i) { 
					numOfUserOccurrances++; //If true, then numOfUserOccurrances is increased by one
				}; 
				
				//Checks whether the current card in cpuHand equals the current rank
				if (getRank(cpuHand.get(a)) == i) { 
					numOfCpuOccurrances++; //If true, then numOfCpuOccurrances is increased by one
				}; 
			}; 
			
			//Checks whether the number of user occurrences of the current rank is equal to 2
			if (numOfUserOccurrances == 2) {
				
				//Checks whether the first pair of rankings has already been found
				if (userPair1Found) { 
					userPair2 = i; //If true, then the userPair2 is set to the current rank
					userPair2Found = true; //userPair2Found is set to true
				} else { 
					userPair1 = i; //Else, the userPair1 is set to the current rank
					userPair1Found = true; //userPair1Found is set to true
				}; 
			}; 
			
			//Checks whether the number of cpu occurrences of the current rank is equal to 2
			if (numOfCpuOccurrances == 2) {
				
				//Checks whether the first pair of rankings has already been found
				if (cpuPair1Found) { 
					cpuPair2 = i; //If true, then the cpuPair2 is set to the current rank
					cpuPair2Found = true; //cpuPair2Found is set to true
				} else {
					cpuPair1 = i; //Else, the cpuPair1 is set to the current rank
					cpuPair1Found = true; //cpuPair1Found is set to true
				}; 
			}; 
		}; 
		
		//Checks whether two pairs of rankings were found in userHand
		if (userPair1Found && userPair2Found) {
			userScore = 1; //If true, then userScore is set to 1
			
			//The for loop iterates 5 times to check each card in a hand of 5
			for (int i = 0; i < 5; i++) {
				
				//Checks whether the rank of the current card in userHand is not part of either pair
				if (getRank(userHand.get(i)) != userPair1 && getRank(userHand.get(i)) != userPair2) { 
					userLoner = getRank(userHand.get(i)); //If true, then userLoner is set to the rank of the current card
				}; 
			}; 
		}; 
		
		//Checks whether two pairs of rankings were found in cpuHand
		if (cpuPair1Found == true && cpuPair2Found == true) {
			cpuScore = 1; //If true, then cpuScore is set to 1
			
			//The for loop iterates 5 times to check each card in a hand of 5
			for (int i = 0; i < 5; i++) {
				
				//Checks whether the rank of the current card in cpuHand is not part of either pair
				if (getRank(cpuHand.get(i)) != cpuPair1 && getRank(cpuHand.get(i)) != cpuPair2) { 
					cpuLoner = getRank(cpuHand.get(i)); //If true, then cpuLoner is set to the rank of the current card
				}; 
			}; 
		}; 
	
		//Checks whether both the user and the cpu hand got a Two Pair 
		if (userScore == 1 && cpuScore == 1) {
			
			//Checks whether only the userPair1 was an Ace 
			if (userPair1 == 0 && cpuPair1 != 0) {
				userScore = 1.5; //If true, userScore is set to 1.5
			} else if (cpuPair1 == 0 && userPair1 != 0) { //Else, checks whether only the cpuPair1 was an Ace 
				cpuScore = 1.5; //If true, then cpuScore is set to 1.5
			} else {
				
				//Checks whether the rank of the second user pair is higher than the rank of the second cpu pair
				if (userPair2 > cpuPair2) { 
					userScore = 1.5; //If true, then userScore is set to 1.5
				} else if (userPair2 < cpuPair2) { //Else, checks whether the rank of the second user pair is lower than the rank of the second cpu pair
					cpuScore = 1.5; //If true, then cpuScore is set to 1.5
				} else {
					
					//Checks whether the rank of the first user pair is higher than the rank of the first cpu pair
					if (userPair1 > cpuPair1) { 
						userScore = 1.5; //If true, then userScore is set to 1.5
					} else if (userPair1 < cpuPair1) { //Checks whether the rank of the first user pair is lower than the rank of the first cpu pair
						cpuScore = 1.5; //If true, then cpuScore is set to 1.5
					} else {
						
						//Checks whether the rank of the userLoner is higher than the rank of the cpuLoner
						if (userLoner > cpuLoner) { 
							userScore = 1.5; //If true, then userScore is set to 1.5
						} else if (userLoner < cpuLoner) { //Checks whether the rank of the userLoner is lower than the rank of the cpuLoner
							cpuScore = 1.5; //If true, then cpuScore is set to 1.5
						}; 
					}; 
				}; 
			}; 
			
			return true; //True is returned since the user and the cpu hand have a Two Pair
		} else {
			return (userScore == 1 || cpuScore == 1); //Returns whether the user or cpu hand contain a Two Pair
		}
	}
	
}
