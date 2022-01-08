/**
 * Class: COSC 1174 - Class Project: Jokers Wild!
 * Instructor: Professor Greg Yera
 * Group Members: David Brunner, Samson Oyewale 
 * Date: 4/15/2021
 * Description: Creates a Main class that extends Application and implements JavaFX features to create a representation of a Poker Game 
 */

//Designates the specific package of application

//Imports the overall JavaFX Application class
import javafx.application.Application;
import javafx.geometry.Pos; //Imports the Pos class from JavaFX
import javafx.stage.Stage; //Imports the Stage class from JavaFX
import javafx.util.Duration; //Imports the Duration class from JavaFX

import javafx.scene.Scene; //Imports the Scene class from JavaFX
import javafx.scene.control.Button; //Imports the Button class from JavaFX
import javafx.scene.control.ContentDisplay; //Imports the ContentDisplay class from JavaFX
import javafx.scene.control.Label; //Imports the Label class from JavaFX
import javafx.scene.control.RadioButton; //Imports the Radio Button class from JavaFX
import javafx.scene.control.TextField; //Imports the TextField class from JavaFX
import javafx.scene.layout.VBox; //Imports the VBox class from JavaFX
import javafx.scene.paint.Color; //Imports the Color class from JavaFX
import javafx.scene.text.Font; //Imports the Font class from JavaFX
import javafx.scene.text.Text; //Imports the Text class from JavaFX
import javafx.scene.text.TextAlignment; //Imports the Text Alignment class from JavaFX
import javafx.scene.layout.HBox; //Imports the HBox class from JavaFX
import javafx.scene.image.Image; //Imports the Image class from JavaFX
import javafx.scene.image.ImageView; //Imports the ImageView class from JavaFX to display the images
import javafx.scene.Camera; //Imports the Camera class from JavaFX
import javafx.scene.PerspectiveCamera; //Imports the PerspectiveCamera class from JavaFX
import javafx.scene.transform.Rotate; //Imports the Rotate class from JavaFX

import java.util.ArrayList; //Imports the ArrayList class from java.util
import java.util.regex.Matcher; //Imports the Matcher class from java.util
import java.util.regex.Pattern; //Imports the Pattern class from java.util

import javafx.animation.FadeTransition; //Imports the FadeTransition class from JavaFX
import javafx.animation.Interpolator; //Imports the Interpolator class from JavaFX
import javafx.animation.RotateTransition; //Imports the RotateTransition class from JavaFX
import javafx.animation.TranslateTransition; //Imports the TranslateTransition class from JavaFX

/** The public class Main contains several methods and data fields for designing and creating the JavaFX application by extending the Application class and overriding its start method */
public class Main extends Application {
	private PokerCards userDeck = new PokerCards(); //Creates the PokerCards class representing the deck of cards for the user
	private PokerCards cpuDeck = new PokerCards(userDeck, true); //Creates the PokerCards class representing the deck of cards for the cpu
	
	private ImageView card0 = userDeck.getCard(0); //Retrieves the first image in a hand of 5 from the deck of cards 
	private int card0Index = userDeck.getCardIndex(0); //Retrieves the first image's index from the deck of cards 
	private ImageView card1 = userDeck.getCard(1); //Retrieves the second image in a hand of 5 from the deck of cards 
	private int card1Index = userDeck.getCardIndex(1); //Retrieves the second image's index from the deck of cards 
	private ImageView card2 = userDeck.getCard(2); //Retrieves the third image in a hand of 5 from the deck of cards 
	private int card2Index = userDeck.getCardIndex(2); //Retrieves the third image's index from the deck of cards 
	private ImageView card3 = userDeck.getCard(3); //Retrieves the fourth image in a hand of 5 from the deck of cards 
	private int card3Index = userDeck.getCardIndex(3); //Retrieves the fourth image's index from the deck of cards 
	private ImageView card4 = userDeck.getCard(4); //Retrieves the fifth image in a hand of 5 from the deck of cards 
	private int card4Index = userDeck.getCardIndex(4); //Retrieves the fifth image's index from the deck of cards 
	
	private ImageView backRed0 = new ImageView(new Image("card_image/br2.png")); //Creates the red background image option for the first card 
	private ImageView backBlue0 = new ImageView(new Image("card_image/bb2.png")); //Creates the blue background image option for the first card 
	private ImageView backRed1 = new ImageView(new Image("card_image/br2.png")); //Creates the red background image option for the second card 
	private ImageView backBlue1 = new ImageView(new Image("card_image/bb2.png")); //Creates the blue background image option for the second card 
	private ImageView backRed2 = new ImageView(new Image("card_image/br2.png")); //Creates the red background image option for the third card 
	private ImageView backBlue2 = new ImageView(new Image("card_image/bb2.png")); //Creates the blue background image option for the third card 
	private ImageView backRed3 = new ImageView(new Image("card_image/br2.png")); //Creates the red background image option for the fourth card 
	private ImageView backBlue3 = new ImageView(new Image("card_image/bb2.png")); //Creates the blue background image option for the fourth card 
	private ImageView backRed4 = new ImageView(new Image("card_image/br2.png")); //Creates the red background image option for the fifth card 
	private ImageView backBlue4 = new ImageView(new Image("card_image/bb2.png")); //Creates the blue background image option for the fifth card 

	private HBox cardPane = new HBox(5); //Creates the overall HBox cardPane for the hand of 5, it is declared outside of the start function because it will be frequently altered
	
	private boolean newCpuDeck = false; //Creates a boolean that checks for when the Cpu deck must be changed
	
	private double pocketAmount = 200; //Creates an initial pocket amount of $200 for the user 
	private double betAmount = 0; //Initializes the bet amount to 0
	private boolean transactionOccurred = false; //Creates a boolean that checks whether the transaction for the bet occurred
	
	private boolean isFirst = true; //Checks if the game just started
	private int count = 0; //Initializes the number of different screens that had been showed to the user
	
	private int currentAvatar = 0; //Initializes the current Avatar image for the user
	private ArrayList<Image> avatarImages = new ArrayList<Image>(6); //Initializes the ArrayList for all of the Avatar Images
	
	HBox animationHBox = new HBox(0); //Creates the animationHBox for the Big Winnings animation
		
	/** The start method is overridden to create a stage containing a Scene and FlowPane node that displays 5 unique images, buttons to exchange the cards within the hand of 5, the beginning of the Poker game, and the results of the Poker game */
	@Override
	public void start(Stage primaryStage) {
		
		update(); //Calls the update function to reset the card Images, card indexes, the Avatars, and the cardPane
		
	    //Checks whether the user hasn't played two turns and isn't the first scene shown 
		if (count < 2 && !isFirst) {
			
			TranslateTransition translateStart0 = new TranslateTransition(Duration.millis(5), card0); //Creates a new TranslateTransition for the specified card with a duration of 5 milliseconds
			translateStart0.setFromY(0); //Sets the starting point of the card to (0, 0) within the coordinate graph 
			translateStart0.setToY(0); //Moves the specified card to the (0, 0) point within the coordinate graph 
			translateStart0.play(); //Plays the TranslateTransition animation 
			
			TranslateTransition translateStart1 = new TranslateTransition(Duration.millis(5), card1); //Creates a new TranslateTransition for the specified card with a duration of 5 milliseconds
			translateStart1.setFromY(0); //Sets the starting point of the card to (0, 0) within the coordinate graph 
			translateStart1.setToY(0); //Moves the specified card to the (0, 0) point within the coordinate graph 
			translateStart1.play(); //Plays the TranslateTransition animation 
			
			TranslateTransition translateStart2 = new TranslateTransition(Duration.millis(5), card2); //Creates a new TranslateTransition for the specified card with a duration of 5 milliseconds
			translateStart2.setFromY(0); //Sets the starting point of the card to (0, 0) within the coordinate graph 
			translateStart2.setToY(0); //Moves the specified card to the (0, 0) point within the coordinate graph 
			translateStart2.play(); //Plays the TranslateTransition animation 
			
			TranslateTransition translateStart3 = new TranslateTransition(Duration.millis(5), card3); //Creates a new TranslateTransition for the specified card with a duration of 5 milliseconds
			translateStart3.setFromY(0); //Sets the starting point of the card to (0, 0) within the coordinate graph 
			translateStart3.setToY(0); //Moves the specified card to the (0, 0) point within the coordinate graph 
			translateStart3.play(); //Plays the TranslateTransition animation 
			
			TranslateTransition translateStart4 = new TranslateTransition(Duration.millis(5), card4); //Creates a new TranslateTransition for the specified card with a duration of 5 milliseconds
			translateStart4.setFromY(0); //Sets the starting point of the card to (0, 0) within the coordinate graph 
			translateStart4.setToY(0); //Moves the specified card to the (0, 0) point within the coordinate graph 
			translateStart4.play(); //Plays the TranslateTransition animation 
			
			userDeck.setHold(0, true); //Calls the setHold function for the first card of the user hand to keep it from being exchanged without permission
		    userDeck.setHold(1, true); //Calls the setHold function for the second card of the user hand to keep it from being exchanged without permission
		    userDeck.setHold(2, true); //Calls the setHold function for the third card of the user hand to keep it from being exchanged without permission
		    userDeck.setHold(3, true); //Calls the setHold function for the fourth card of the user hand to keep it from being exchanged without permission
		    userDeck.setHold(4, true); //Calls the setHold function for the fifth card of the user hand to keep it from being exchanged without permission
			
			HBox avatarHBox = new HBox(15); //Creates a new avatarHBox to display the users Avatar Image and their money amounts
			avatarHBox.setAlignment(Pos.TOP_LEFT); //Sets the alignment to top_left
			avatarHBox.setStyle("-fx-padding: 0 0 0 25;"); //Sets the padding of the HBox to 25px on the left side
			avatarHBox.getChildren().add(new ImageView(avatarImages.get(currentAvatar))); //Adds the current Avatar Image of the user to the HBox
			
			Text currentUserText = new Text("  Current Pocket: $" + pocketAmount + "\n" + "  Bet Amount: $" + betAmount); //Creates a Text node to display the total amount of pocket available for the user and their bet 
			avatarHBox.getChildren().add(currentUserText); //Adds the currentUserText to avatarHBox to display the money information
			
			ImageView shuffleRed = backRed0; //Creates a new ImageView for the shuffling animation using backRed0
			ImageView shuffleBlue = backBlue0; //Creates a new ImageView for the shuffling animation using backBlue0
			
			//Checks whether the remaining size of the userDeck is less than or equal to 13
			if (52 - userDeck.getRemainingSize() <= 13) {
				avatarHBox.getChildren().clear(); //If true, then the avatarHBox is cleared of its contents
				avatarHBox.setAlignment(Pos.CENTER); //The alignment of avatarHBox is set to center
				avatarHBox.getChildren().add(shuffleRed); //The shuffleRed ImageView is added to avatarHBox
				avatarHBox.getChildren().add(shuffleBlue); //The shuffleBlue ImageView is added to avatarHBox
				transitionDeckShuffle(shuffleRed, shuffleBlue, avatarHBox); //The transitionDeckShuffle function is called to show the shuffling animation
				userDeck.shuffleDeck(); //The shuffleDeck function is called for the userDeck to shuffle the decks cards
			}; 
					
		    HBox buttonPane = new HBox(25); //Creates the HBox buttonPane for containing the buttons that exchange the cards 
		    buttonPane.setAlignment(Pos.CENTER); //Sets center alignment for buttonPane
		    	
			Button discard0 = new Button("Discard 0"); //Creates the discard button for the first card 
			buttonPane.getChildren().add(discard0); //Adds the discard0 button to the buttonPane
			
			//Creates a event object for when discard0 is clicked 
			discard0.setOnMouseClicked(e -> {
				
				//Checks if the current hold value for the first card is true
				if (userDeck.getCardHold(0)) {
			        transitionCardDiscard1(0); //If true, then the transitionCardDiscard function is called to show the discarding animation for the first card        
			        userDeck.setHold(0, false); //Sets the hold value of the first card to false
				}; 
			});
			
			//Creates a event object for when the blue background image for the first card is clicked
			backBlue0.setOnMouseClicked(e -> {
				
				//Checks if the current hold value for the first card is false
				if (!userDeck.getCardHold(0)) {
					transitionCardHold1(0); //If true, then the transitionCardHold1 function is called to show the flip animation for the first card
					userDeck.setHold(0, true); //Sets the hold value of the first card to true
				}; 
			});
			
			//Creates a event object for when the red background image for the first card is clicked
			backRed0.setOnMouseClicked(e -> {
				
				//Checks if the current hold value for the first card is false
				if (!userDeck.getCardHold(0)) {
					transitionCardHold1(0); //If true, then the transitionCardHold1 function is called to show the flip animation for the first card
					userDeck.setHold(0, true); //Sets the hold value of the first card to true
				}; 
			});
			
			Button discard1 = new Button("Discard 1");  //Creates the discard button for the second card 
			buttonPane.getChildren().add(discard1); //Adds the discard1 button to the buttonPane
			
			//Creates a event object for when discard1 is clicked 
			discard1.setOnAction(e -> {
				
				//Checks if the current hold value for the second card is true
				if (userDeck.getCardHold(1)) {
			        transitionCardDiscard1(1); //If true, then the transitionCardDiscard function is called to show the discarding animation for the second card  	        
			        userDeck.setHold(1, false); //Sets the hold value of the second card to false
				};  
			});
			
			//Creates a event object for when the blue background image for the second card is clicked
			backBlue1.setOnMouseClicked(e -> {
				
				//Checks if the current hold value for the second card is false
				if (!userDeck.getCardHold(1)) {
					transitionCardHold1(1); //If true, then the transitionCardHold1 function is called to show the flip animation for the second card
					userDeck.setHold(1, true); //Sets the hold value of the second card to true
				}; 
			});
			
			//Creates a event object for when the red background image for the second card is clicked
			backRed1.setOnMouseClicked(e -> {
				
				//Checks if the current hold value for the second card is false
				if (!userDeck.getCardHold(1)) {
					transitionCardHold1(1); //If true, then the transitionCardHold1 function is called to show the flip animation for the second card
					userDeck.setHold(1, true); //Sets the hold value of the second card to true
				}; 
			});
			
			Button discard2 = new Button("Discard 2"); //Creates the discard button for the third card 
			buttonPane.getChildren().add(discard2); //Adds the discard2 button to the buttonPane
			
			//Creates a event object for when the discard2 is clicked 
			discard2.setOnAction(e -> {
				
				//Checks if the current hold value for the third card is true
				if (userDeck.getCardHold(2)) {
			        transitionCardDiscard1(2); 	//If true, then the transitionCardDiscard function is called to show the discarding animation for the third card 		        
			        userDeck.setHold(2, false); //Sets the hold value of the third card to false
				}; 
			});
			
			//Creates a event object for when the blue background image for the third card is clicked
			backBlue2.setOnMouseClicked(e -> {
				
				//Checks if the current hold value for the third card is false
				if (!userDeck.getCardHold(2)) {
					transitionCardHold1(2); //If true, then the transitionCardHold1 function is called to show the flip animation for the third card
					userDeck.setHold(2, true); //Sets the hold value of the third card to true
				}; 
			});
			
			//Creates a event object for when the red background image for the third card is clicked
			backRed2.setOnMouseClicked(e -> {
				
				//Checks if the current hold value for the third card is false
				if (!userDeck.getCardHold(2)) {
					transitionCardHold1(2); //If true, then the transitionCardHold1 function is called to show the flip animation for the third card
					userDeck.setHold(2, true); //Sets the hold value of the third card to true
				}; 
			});
			
			Button discard3 = new Button("Discard 3"); //Creates the discard button for the fourth card 
			buttonPane.getChildren().add(discard3); //Adds the discard3 button to the buttonPane
			
			//Creates a event object for when discard3 is clicked 
			discard3.setOnAction(e -> {
				
				//Checks if the current hold value for the fourth card is true
				if (userDeck.getCardHold(3)) {
			        transitionCardDiscard1(3); //If true, then the transitionCardDiscard function is called to show the discarding animation for the fourth card 
			        userDeck.setHold(3, false); //Sets the hold value of the fourth card to false
				}; 
			});
			
			//Creates a event object for when the blue background image for the fourth card is clicked
			backBlue3.setOnMouseClicked(e -> {
				
				//Checks if the current hold value for the fourth card is false
				if (!userDeck.getCardHold(3)) {
					transitionCardHold1(3); //If true, then the transitionCardHold1 function is called to show the flip animation for the fourth card
					userDeck.setHold(3, true); //Sets the hold value of the fourth card to true
				}; 
			});
			
			//Creates a event object for when the red background image for the fourth card is clicked
			backRed3.setOnMouseClicked(e -> {
				
				//Checks if the current hold value for the fourth card is false
				if (!userDeck.getCardHold(3)) {
					transitionCardHold1(3); //If true, then the transitionCardHold1 function is called to show the flip animation for the fourth card
					userDeck.setHold(3, true); //Sets the hold value of the fourth card to true
				}; 
			});
			
			Button discard4 = new Button("Discard 4"); //Creates the discard button for the fifth card 
			buttonPane.getChildren().add(discard4); //Adds the discard4 button to the buttonPane
			
			//Creates a event object for when discard4 is clicked 
			discard4.setOnAction(e -> { 
				
				//Checks if the current hold value for the fifth card is true
				if (userDeck.getCardHold(4)) {
			        transitionCardDiscard1(4); //If true, then the transitionCardDiscard function is called to show the discarding animation for the fifth card 
			        userDeck.setHold(4, false); //Sets the hold value of the fifth card to false 
				}; 
			});
			
			//Creates a event object for when the blue background image for the fifth card is clicked
			backBlue4.setOnMouseClicked(e -> {
				
				//Checks if the current hold value for the fifth card is false
				if (!userDeck.getCardHold(4)) {
					transitionCardHold1(4); //If true, then the transitionCardHold1 function is called to show the flip animation for the fifth card
					userDeck.setHold(4, true); //Sets the hold value of the fifth card to true
				}; 
			});
			
			//Creates a event object for when the red background image for the fifth card is clicked
			backRed4.setOnMouseClicked(e -> {
				
				//Checks if the current hold value for the fifth card is false
				if (!userDeck.getCardHold(4)) {
					transitionCardHold1(4); //If true, then the transitionCardHold1 function is called to show the flip animation for the fifth card
					userDeck.setHold(4, true); //Sets the hold value of the fifth card to true
				}; 
			});
		    
		    Button draw = new Button("Draw"); //Creates the button representing the deal functionality within a Poker game
		    
		    //Creates a event object for when the draw button is clicked
		    draw.setOnAction(e-> {
		    	boolean startTransition = transitionExchange1(primaryStage); //Begins the transition animation for the cards that are exchanged
		        count++; //Moves on to the next screen for the user
		        
		        //Checks if there were no cards to be exchanged
		        if (!startTransition) {
		        	this.start(primaryStage); //If true, then the JavaFX application is restarted with the new data 
		        }; 
		    });
		    			
		    VBox vBox = new VBox(10); //Creates a new VBox for containing all of the previous nodes in the Play Screen
		    vBox.getChildren().add(avatarHBox); //Adds the avatarHBox HBox first
		    vBox.getChildren().add(cardPane); //Adds the cardPane HBox second
		    vBox.getChildren().add(buttonPane); //Adds the buttonPane HBox third
		    vBox.getChildren().add(draw); //Adds the draw Button last
		    vBox.setAlignment(Pos.CENTER); //Centers the children nodes 
			
			Scene scene = new Scene(vBox, 550, 350); //Creates a new Scene with the vBox node, width 500, and height 300
			Camera camera = new PerspectiveCamera(); //Creates the PerspectiveCamera for the 3d flipping animation of the cards
			scene.setCamera(camera); //Sets the camera for the scene object
			
			primaryStage.setScene(scene); //Places the scene within the primaryStage
			primaryStage.setTitle("Poker Hand of 5"); // Sets the title of the primaryStage
			primaryStage.show(); //Displays the primaryStage 
							
		}
		else { 
			
			//Checks whether the user already played two turns
			if (count == 2) {
				
				//Checks whether the cpu deck had already gotten a new deck, as the old one might have the same cards as the new user deck
				if (!newCpuDeck) {
					cpuDeck.deal(); //Replaces all of cards in the cpu hand
					newCpuDeck = true; //Sets the newCpuDeck to true
				}; 
								
				TranslateTransition translate0 = new TranslateTransition(Duration.millis(200), card0); //Creates a new TranslateTransition for the specified card with a duration of 200 milliseconds
				translate0.setToY(-30); //Moves the specified card to where y = -30 point within the coordinate graph 
				translate0.play(); //Plays the TranslateTransition animation 
				
				TranslateTransition translate1 = new TranslateTransition(Duration.millis(200), card1); //Creates a new TranslateTransition for the specified card with a duration of 200 milliseconds
				translate1.setToY(-30); //Moves the specified card to where y = -30 point within the coordinate graph 
				translate1.play(); //Plays the TranslateTransition animation 
				
				TranslateTransition translate2 = new TranslateTransition(Duration.millis(200), card2); //Creates a new TranslateTransition for the specified card with a duration of 200 milliseconds
				translate2.setToY(-30); //Moves the specified card to where y = -30 point within the coordinate graph 
				translate2.play(); //Plays the TranslateTransition animation 
				
				TranslateTransition translate3 = new TranslateTransition(Duration.millis(200), card3); //Creates a new TranslateTransition for the specified card with a duration of 200 milliseconds
				translate3.setToY(-30); //Moves the specified card to where y = -30 point within the coordinate graph 
				translate3.play(); //Plays the TranslateTransition animation 
				
				TranslateTransition translate4 = new TranslateTransition(Duration.millis(200), card4); //Creates a new TranslateTransition for the specified card with a duration of 200 milliseconds
				translate4.setToY(-30); //Moves the specified card to where y = -30 point within the coordinate graph 
				translate4.play(); //Plays the TranslateTransition animation 
				
				FindWinner winner = new FindWinner(userDeck, cpuDeck); //Creates a new FindWinner object that determines if the user or the cpu got the winning hand
				
				Text resultDisplay = new Text(); //Creates a new Text object for the results
				resultDisplay.setTextAlignment(TextAlignment.CENTER); //Aligns the text within the Text object to center
				resultDisplay.setFont(new Font(17)); //Sets the font size to 17px for the Text object
				Text handDisplay = new Text(); //Creates a new Text object for the winning hand
				
				//Checks whether the winner is the user
				if (winner.findWinner() == "USER") {
					
					//Checks whether the transaction of the winning amount has not been added to the users pocket
					if (!transactionOccurred) {
						double newWinnings = winner.getWinAmount(betAmount); //Calls the getWinAmount function with the current betAmount to calculate the users winnings
						
						//Checks if the users winnings was a "Big Win" or more than 25 times the betAmount
						if (newWinnings >= betAmount * 25) {
							resultDisplay.setText("Congratulations! You won BIGGGGG!\nNew Pocket: $" + pocketAmount); //Sets the result display to a congratulations message 
							winAnimation(resultDisplay); //Calls the winAnimation since the user had a big win
						}; 
						
						pocketAmount += (betAmount + newWinnings); //Adds users bet and the winnings to the users pocket
						transactionOccurred = true; //Sets the transaction occurred to true
					}; 
					
					resultDisplay.setText("Congratulations! You won!\nNew Pocket: $" + pocketAmount); //Sets the result display to a congratulations message
					handDisplay.setText("Winning hand is " + winner.getFinalHand("USER")); //Sets the winning hand display to show the winning hand
				} else if (winner.findWinner() == "CPU") { //Else, checks whether the cpu is the winner
					resultDisplay.setText("Unfortunately you lost...\nNew Pocket: $" + pocketAmount); //Sets the result display to an unfortunate message
					handDisplay.setText("Winning hand is " + winner.getFinalHand("CPU")); //Sets the winning hand display to show the winning hand
				} else {
					
					//Checks whether the transaction of the bet amount has not been added back to the users pocket
					if (!transactionOccurred) {
						pocketAmount += betAmount; //Adds the original bet amount to the users pocket
						transactionOccurred = true; //Sets the transaction occurred to true
					}
					
					resultDisplay.setText("Welp, looks like a draw this time.\nNew Pocket: $" + pocketAmount); //Sets the result display to a draw message 
					handDisplay.setText(winner.getFinalHand("DRAW")); //Sets the winning hand display to both the cpu and the user hand
				}; 
				
				Button continueButton = new Button("Continue"); //Creates a button to continue on to the next game
				
				//Creates a event object to reinitialize all of the previously changed values
				continueButton.setOnAction(e -> {
					isFirst = true; //Sets the isFirst value to true to display the first screen
					newCpuDeck = false; //Sets the newCpuDeck to false since it will need to exchanged again
					count = 0; //Sets the count value to 0
					this.start(primaryStage); //Restarts the JavaFX application with the new data 
				});
				
			    VBox vBox = new VBox(10); //Creates a new VBox for containing all of the previous nodes in the Result Screen
			    vBox.getChildren().add(animationHBox); //Adds the animationHBox HBox first
			    vBox.getChildren().add(cardPane); //Adds the cardPane HBox second
			    vBox.getChildren().add(resultDisplay); //Adds the resultDisplay text third
			    vBox.getChildren().add(handDisplay); //Adds the handDisplay text fourth
			    vBox.getChildren().add(continueButton); //Adds the continueButton button last
			    vBox.setAlignment(Pos.CENTER); //Centers the children nodes 
				
				Scene scene = new Scene(vBox, 550, 350); //Creates a new Scene with the vBox node, width 500, and height 300
				Camera camera = new PerspectiveCamera(); //Creates the PerspectiveCamera for the 3d flipping animation of the cards
				scene.setCamera(camera); //Sets the camera for the scene object
				
				primaryStage.setScene(scene); //Places the scene within the primaryStage
				primaryStage.setTitle("Poker Hand of 5"); // Sets the title of the primaryStage
				primaryStage.show(); //Displays the primaryStage 
							
			} else { 
				
				VBox avatarVBox = new VBox(); //Creates a new VBox for the users avatar Image
				avatarVBox.setAlignment(Pos.TOP_LEFT); //Sets alignment to top_left
				avatarVBox.setStyle("-fx-padding: 15 0 0 15;"); //Sets the padding to 15px on the top and left side
				
				ImageView avatarImage = new ImageView(avatarImages.get(currentAvatar)); //Creates a new avatarImage ImageView for the users avatar Image
				
				Label avatarText = new Label("Select Avatar", avatarImage); //Creates a label for the avatarImage to instruct the user for choosing an avatar Image
				avatarText.setContentDisplay(ContentDisplay.TOP); //Sets the Label on top of the content
				
				//Creates a event object for when the avatarText label is clicked
				avatarText.setOnMouseClicked(e -> {
					
					//Checks if the current user avatar is the last image within the avatarImages ArrayList
					if (currentAvatar == 5) {
						currentAvatar = 0; //If true, then the currentAvatar is reset to the first image within the avatarImages ArrayList
					} else {
						currentAvatar++; //Moves on to the next image within the avatarImages ArrayList
					}; 
					
					avatarImage.setImage(avatarImages.get(currentAvatar)); //Sets the new avatar image for the user 	
				});
				
				avatarVBox.getChildren().add(avatarImage); //Adds the avatarImage ImageVew to the avatarVBox
				avatarVBox.getChildren().add(avatarText); //Adds the avatarText Label to the avatarVBox
						
				Text amountText = new Text("Current Pocket: $" + pocketAmount); //Creates a new Text object to display the current pocket amount of the user
				Text betText = new Text("Bet Amount: $" + betAmount); //Creates a new Text object to display the current bet amount of the user
				
				HBox betPane = new HBox(5); //Creates a new HBox object to hold all of the buttons for the bet amounts
				betPane.setAlignment(Pos.CENTER); //Aligns the child items to center
				
				RadioButton oneDollar = new RadioButton("$1"); //Creates a new RadioButton for the $1 bet amount
				RadioButton max = new RadioButton("MAX"); //Creates a new RadioButton for the $10 bet amount
				
				//Creates a event object that sets the bet amount to 1
				oneDollar.setOnAction(e -> {
					max.setSelected(false); //Unselected the max RadioButton
					betAmount = 1; //Sets the bet amount to 1
					betText.setText("Bet Amount: $" + betAmount); //Updates the betText to the new bet amount
				});
				
				//Creates a event object that sets the bet amount to max
				max.setOnAction(e -> {
					oneDollar.setSelected(false); //Unselected the oneDollar RadioButton
					betAmount = pocketAmount; //Sets the bet amount to 10
					betText.setText("Bet Amount: $" + betAmount); //Updates the betText to the new bet amount
				});
				
				betPane.getChildren().add(max); //Adds the radio button to the betPane
				betPane.getChildren().add(oneDollar); //Adds the radio button to the betPane
				
				TextField betTextField = new TextField("$ matches any number"); //Creates a new TextField for a bet amount input from the user
				betTextField.setEditable(true); //Allows it to be editable
				betTextField.setAlignment(Pos.BASELINE_LEFT); //Aligns the text to baseline_left
				betTextField.setMaxWidth(200); //Sets the max width property to 200px
				
				//Creates a event object that receives the input from the user for the bet amount
				betTextField.setOnAction(e -> {
					String betInput = betTextField.getText(); //Receives the text from the input
					String pattern = "((?<=\\$)\\d+)|(\\d+)"; //Creates a Regex pattern that matches digits with an optional preceded dollar sign
					
					Pattern regex = Pattern.compile(pattern); //Complies the Regex pattern into the Pattern object
					Matcher matcher = regex.matcher(betInput); //Matches the String input from the user with the Regex pattern
					
					//Checks if the Matcher object found a match
					if (matcher.find()) {
						double betInputNum = Double.parseDouble(matcher.group(0)); //Changes the first match string into a double value
												
						//Checks if the bet input from the user is less than or equal to the available pocket funds and greater than or equal to zero
						if (betInputNum <= pocketAmount && betInputNum >= 0) {
							max.setSelected(false); //If true, then the max RadioButton is unselected
							oneDollar.setSelected(false); //The oneDollar RadioButton is unselected
							betAmount = betInputNum; //The bet amount is set to the bet input from the user
							betTextField.setText("DONE - Press Deal or Re-Enter New Amount"); //The user is informed of the successful bet placement
							betText.setText("Bet Amount: $" + betAmount); //Updates the betText to the new bet amount
						} else {
							betTextField.setText("ERROR - Bet Amount $" + betInputNum + " Is Invalid!"); //The user is warned of inputing an invalid amount of money
						}; 
				    } else {
				    	betTextField.setText("ERROR - Delete Text and Input Numbers!"); //The user is warned of invalid usage 
				    }; 
				});
				
				Label textFieldLabel = new Label("Enter or Select Bet Amount (Press Enter if using text input)", betTextField); //A label is created to instruct the user to input a bet amount
				textFieldLabel.setContentDisplay(ContentDisplay.TOP); //The Label is set on top of the content
			
				Button dealButton = new Button("Deal"); //Creates a new button for the dealing of new cards 
				
				//Creates a event object that changes the values to the new input information from the user
				dealButton.setOnAction(e -> {
					userDeck.deal(); //Calls the deal function to exchange all of the users cards
					pocketAmount -= betAmount; //Subtracts the bet from the users pocket, if nothing is inputed then the bet amount is 0
					transactionOccurred = false; //Sets the transactionOccurred to false so that the results will determine the new pocket
					isFirst = false; //Sets isFirst to false since the other screens aren't the first option
					count = 0; //Sets the screen count to 0
					this.start(primaryStage); //Restarts the JavaFX application with the new data 
				}); 
				
				VBox moneyPane = new VBox(8); //Creates a new VBox for containing all of the previous nodes in the Bet Screen
				moneyPane.setAlignment(Pos.TOP_CENTER); //Sets the alignment for the child nodes to center
				moneyPane.getChildren().add(avatarVBox); //Adds the avatarVBox HBox first 
				moneyPane.getChildren().add(amountText); //Adds the amountText Text second
				moneyPane.getChildren().add(betText); //Adds the betText Text third
				moneyPane.getChildren().add(betPane); //Adds the betPane HBox fourth
				moneyPane.getChildren().add(textFieldLabel); //Adds the textFieldLabel Label fifth 
				moneyPane.getChildren().add(dealButton); //Adds the dealButton Button last 
				
				Scene scene = new Scene(moneyPane, 550, 350); //Creates a new Scene with the vBox node, width 500, and height 300
				Camera camera = new PerspectiveCamera(); //Creates the PerspectiveCamera for the 3d flipping animation of the cards
				scene.setCamera(camera); //Sets the camera for the scene object
				
				primaryStage.setScene(scene); //Places the scene within the primaryStage
				primaryStage.setTitle("Poker Hand of 5"); // Sets the title of the primaryStage
				primaryStage.show(); //Displays the primaryStage 
			
			}; 
		}; 
	}
		
	/** The public method main runs the program when launched within Eclipse, it is not required to run from the command line */
	public static void main(String[] args) {
		launch(args); //Launches the arguments to run the program within Eclipse 
	}
	
	/** The private method transitionExchange1 begins the flip animation of the card to its back and goes on the exchange the specified cards */ 
	private boolean transitionExchange1(Stage primaryStage) {
		int numberOfDiscard = 0; //Creates numberOfDiscard to count the number of discarded cards 
		
		//Checks whether the first card has a false isHold property and the exchanging of the card should continue
		if (!userDeck.getCardHold(0)) {
			numberOfDiscard++; //If true, then the numberOfDiscard value is increased by one
			
			RotateTransition rotator = new RotateTransition(Duration.millis(500), card0); //Creates a new RotateTransition for the first card with a duration of 1/2 second
			rotator.setAxis(Rotate.Y_AXIS); //Rotates the card around its Y axis
	        rotator.setFromAngle(0); //Sets the starting angle of the rotation to 0
	        rotator.setToAngle(90); //Sets the ending angle of the rotation to 1/4 of a circle
	        rotator.setInterpolator(Interpolator.LINEAR); //Sets the rate of change
	        rotator.setCycleCount(1); //Sets the number of times it repeats to one
	        rotator.play(); //Plays the RotateTransition animation
	        
	        //Creates an event object for when the transition ends to continue with the exchanging of the card 
	        rotator.setOnFinished(e -> {
        		switchBack(0); //Calls the switchBack method to switch the card to its back image 
        		transitionExchange2(0, primaryStage); //Calls the transition2 method to continue with the exchanging of the specified card 
	        });
		}; 
		
		//Checks whether the second card has a false isHold property and the exchanging of the card should continu
		if (!userDeck.getCardHold(1)) {
			numberOfDiscard++; //If true, then the numberOfDiscard value is increased by one
			
			RotateTransition rotator2 = new RotateTransition(Duration.millis(500), card1); //Creates a new RotateTransition for the second card with a duration of 1/2 second
			rotator2.setAxis(Rotate.Y_AXIS); //Rotates the card around its Y axis
		    rotator2.setFromAngle(0); //Sets the starting angle of the rotation to 0
		    rotator2.setToAngle(90); //Sets the ending angle of the rotation to 1/4 of a circle
		    rotator2.setInterpolator(Interpolator.LINEAR); //Sets the rate of change
		    rotator2.setCycleCount(1); //Sets the number of times it repeats to one
		    rotator2.play(); //Plays the RotateTransition animation
		    
	        //Creates an event object for when the transition ends to continue with the exchanging of the card 
		    rotator2.setOnFinished(e -> {
        		switchBack(1); //Calls the switchBack method to switch the card to its back image 
        		transitionExchange2(1, primaryStage); //Calls the transition2 method to continue with the exchanging of the specified card 
	        });
		}; 
		
		//Checks whether the third card has a false isHold property and the exchanging of the card should continu
		if (!userDeck.getCardHold(2)) {
			numberOfDiscard++; //If true, then the numberOfDiscard value is increased by one
			
			RotateTransition rotator3 = new RotateTransition(Duration.millis(500), card2); //Creates a new RotateTransition for the third card with a duration of 1/2 second
			rotator3.setAxis(Rotate.Y_AXIS); //Rotates the card around its Y axis
	        rotator3.setFromAngle(0); //Sets the starting angle of the rotation to 0
	        rotator3.setToAngle(90); //Sets the ending angle of the rotation to 1/4 of a circle
	        rotator3.setInterpolator(Interpolator.LINEAR); //Sets the rate of change
	        rotator3.setCycleCount(1); //Sets the number of times it repeats to one
	        rotator3.play(); //Plays the RotateTransition animation
	        
	        //Creates an event object for when the transition ends to continue with the exchanging of the card 
	        rotator3.setOnFinished(e -> {
	        	switchBack(2); //Calls the switchBack method to switch the card to its back image 
	        	transitionExchange2(2, primaryStage); //Calls the transition2 method to continue with the exchanging of the specified card 
	        });
		}; 
		
		//Checks whether the fourth card has a false isHold property and the exchanging of the card should continu
		if (!userDeck.getCardHold(3)) { 
			numberOfDiscard++; //If true, then the numberOfDiscard value is increased by one
			
			RotateTransition rotator4 = new RotateTransition(Duration.millis(500), card3); //Creates a new RotateTransition for the fourth card with a duration of 1/2 second
			rotator4.setAxis(Rotate.Y_AXIS); //Rotates the card around its Y axis
	        rotator4.setFromAngle(0); //Sets the starting angle of the rotation to 0
	        rotator4.setToAngle(90); //Sets the ending angle of the rotation to 1/4 of a circle
	        rotator4.setInterpolator(Interpolator.LINEAR); //Sets the rate of change
	        rotator4.setCycleCount(1); //Sets the number of times it repeats to one
	        rotator4.play(); //Plays the RotateTransition animation
	        
	        //Creates an event object for when the transition ends to continue with the exchanging of the card 
	        rotator4.setOnFinished(e -> {
	        	switchBack(3); //Calls the switchBack method to switch the card to its back image 
	        	transitionExchange2(3, primaryStage); //Calls the transition2 method to continue with the exchanging of the specified card 
	        });
		}; 
		
		//Checks whether the fifth card has a false isHold property and the exchanging of the card should continu
		if (!userDeck.getCardHold(4)) {
			numberOfDiscard++; //If true, then the numberOfDiscard value is increased by one
			
			RotateTransition rotator5 = new RotateTransition(Duration.millis(500), card4); //Creates a new RotateTransition for the fifth card with a duration of 1/2 second
			rotator5.setAxis(Rotate.Y_AXIS); //Rotates the card around its Y axis
	        rotator5.setFromAngle(0); //Sets the starting angle of the rotation to 0
	        rotator5.setToAngle(90); //Sets the ending angle of the rotation to 1/4 of a circle
	        rotator5.setInterpolator(Interpolator.LINEAR); //Sets the rate of change
	        rotator5.setCycleCount(1); //Sets the number of times it repeats to one
	        rotator5.play(); //Plays the RotateTransition animation
	        
	        //Creates an event object for when the transition ends to continue with the exchanging of the card 
	        rotator5.setOnFinished(e -> {
	        	switchBack(4); //Calls the switchBack method to switch the card to its back image 
	        	transitionExchange2(4, primaryStage); //Calls the transition2 method to continue with the exchanging of the specified card 
	        });
		}; 
		
		//Checks whether there were no cards discarded
		if (numberOfDiscard == 0) {
			return false; //If true, then false is returned
		} else {
			return true; //True is returned
		}
	}
	
	/** The private method transitionExchange2 finishes the flip animation of the card to it's back and goes on the exchange the specified card */
	private void transitionExchange2(int num, Stage primaryStage) {
		RotateTransition rotator = new RotateTransition(Duration.millis(500), cardPane.getChildren().get(num)); //Creates a new RotateTransition for the specified card with a duration of 1/2 second
        rotator.setAxis(Rotate.Y_AXIS); //Rotates the specified card around its Y axis
        rotator.setFromAngle(-90); //Sets the starting angle of the rotation to the -1/4 of a circle
        rotator.setToAngle(0); //Sets the ending angle of the rotation to 0
        rotator.setInterpolator(Interpolator.LINEAR); //Sets the rate of change
        rotator.setCycleCount(1); //Sets the number of times it repeats to one
        rotator.play(); //Plays the RotateTransition animation
        
        //Creates an event object for when the transition ends to continue with the exchanging of the card 
        rotator.setOnFinished(e -> {
        	transitionExchange3(num, primaryStage); //Calls the transition3 method to continue with the exchanging of the card 
        });
	}
	
	/** The private method transitionExchange3 begins the moving animation of the card to the bottom and exchanges the specified card */
	private void transitionExchange3(int num, Stage primaryStage) {
		TranslateTransition translate = new TranslateTransition(Duration.millis(500), cardPane.getChildren().get(num)); //Creates a new TranslateTransition for the specified card with a duration of 1/2 second
		translate.setToY(200); //Moves the specified card to where y = 200 point within the coordinate graph 
		translate.play(); //Plays the TranslateTransition animation 
		
        //Creates an event object for when the transition ends to continue with the animation of the card 
		translate.setOnFinished(e -> {
			userDeck.drawNewCard(num); //Draws a new card from the deck 
    		getResult(num); //Changes the image and index of the card to its updated version
    		switchBack(num); //Selects the background image of the new card 
    		transitionExchange4(num, primaryStage); //Calls the transition4 method to continue with the animation of the card 
		});
	}
	
	/** The private method transitionExchange4 begins the moving animation of the card to the regular position */
	private void transitionExchange4(int num, Stage primaryStage) {
		TranslateTransition translate1 = new TranslateTransition(Duration.millis(500), cardPane.getChildren().get(num)); //Creates a new TranslateTransition for the specified card with a duration of 1/2 second
		translate1.setFromY(-200); //Sets the starting point of the card to where y = -200 within the coordinate graph 
		translate1.setToY(0); //Moves the specified card to where y = 0 within the coordinate graph 
		translate1.play(); //Plays the TranslateTransition animation 
		
        //Creates an event object for when the transition ends to continue with the animation of the card 
		translate1.setOnFinished(e -> {
			transitionExchange5(num, primaryStage); //Calls the transition5 method to continue with the animation of the card 
		}); 
	}
	
	/** The private method transitionExchange5 begins the flip animation of the card from its back */
	private void transitionExchange5(int num, Stage primaryStage) {
		RotateTransition rotator = new RotateTransition(Duration.millis(500), cardPane.getChildren().get(num)); //Creates a new RotateTransition for the specified card with a duration of 1/2 second
		rotator.setAxis(Rotate.Y_AXIS); //Rotates the specified card around its Y axis
        rotator.setFromAngle(0); //Sets the starting angle of the rotation to the start of a circle
        rotator.setToAngle(-90); //Sets the ending angle of the rotation to the -1/4 of a circle
        rotator.setInterpolator(Interpolator.LINEAR); //Sets the rate of change
        rotator.setCycleCount(1); //Sets the number of times it repeats to one
        rotator.play(); //Plays the RotateTransition animation
        
        //Creates an event object for when the transition ends to continue with the animation of the card 
        rotator.setOnFinished(e -> {
        	switchFront(num); //Switches the specified card to its front image 
        	transitionExchange6(num, primaryStage); //Calls the transition6 method to finish the animation of the card 
        });
	}
	
	/** The private method transitionExchange6 begins the flip animation of the card to its front */
	private void transitionExchange6(int num, Stage primaryStage) {
		RotateTransition rotator = new RotateTransition(Duration.millis(500), cardPane.getChildren().get(num)); //Creates a new RotateTransition for the specified card with a duration of 1/2 second
		rotator.setAxis(Rotate.Y_AXIS); //Rotates the specified card around its Y axis
        rotator.setFromAngle(90); //Sets the starting angle of the rotation to 1/4 of a circle
        rotator.setToAngle(0); //Sets the ending angle of the rotation to the start of a circle
        rotator.setInterpolator(Interpolator.LINEAR); //Sets the rate of change
        rotator.setCycleCount(1); //Sets the number of times it repeats to one
        rotator.play(); //Plays the RotateTransition animation
        
        //Creates an event object to restart the JavaFX application with the new data
        rotator.setOnFinished(e -> {
        	start(primaryStage); //Restarts the JavaFX application with the primaryStage object
        });
	}
	
	/** The private method transitionCardHold1 begins the flip animation of the card to its front */
	private void transitionCardHold1(int index) {
		RotateTransition rotator = new RotateTransition(Duration.millis(300), cardPane.getChildren().get(index)); //Creates a new RotateTransition for the specified card with a duration of 300 milliseconds
		rotator.setAxis(Rotate.Y_AXIS); //Rotates the specified card around its Y axis
        rotator.setFromAngle(0); //Sets the starting angle of the rotation to the start of a circle
        rotator.setToAngle(-90); //Sets the ending angle of the rotation to the -1/4 of a circle
        rotator.setInterpolator(Interpolator.LINEAR); //Sets the rate of change
        rotator.setCycleCount(1); //Sets the number of times it repeats to one
        rotator.play(); //Plays the RotateTransition animation
        
        //Creates an event object for when the transition ends to continue with the animation of the card 
        rotator.setOnFinished(a -> {
        	switchFront(index); //Switches the specified card to its front image 
        	transitionCardHold2(index); //Calls the transitionCardHold2 method to finish the animation of the card flip animation
        });
	}
	
	/** The private method transitionCardHold2 finishes the flip animation of the card to its front */
	private void transitionCardHold2(int index) {
		RotateTransition rotator2 = new RotateTransition(Duration.millis(300), cardPane.getChildren().get(index)); //Creates a new RotateTransition for the specified card with a duration of 300 milliseconds
        rotator2.setAxis(Rotate.Y_AXIS); //Rotates the specified card around its Y axis
        rotator2.setFromAngle(90); //Sets the starting angle of the rotation to 1/4 of a circle
        rotator2.setToAngle(0); //Sets the ending angle of the rotation to the start of a circle
        rotator2.setInterpolator(Interpolator.LINEAR); //Sets the rate of change
        rotator2.setCycleCount(1); //Sets the number of times it repeats to one
        rotator2.play(); //Plays the RotateTransition animation
	}
	
	/** The private method transitionCardDiscard1 begins the flip animation of the card to its back */
	private void transitionCardDiscard1(int index) {
		RotateTransition rotator = new RotateTransition(Duration.millis(300), cardPane.getChildren().get(index)); //Creates a new RotateTransition for the first card with a duration of 300 milliseconds
		rotator.setAxis(Rotate.Y_AXIS); //Rotates the card around its Y axis
        rotator.setFromAngle(0); //Sets the starting angle of the rotation to 0
        rotator.setToAngle(90); //Sets the ending angle of the rotation to 1/4 of a circle
        rotator.setInterpolator(Interpolator.LINEAR); //Sets the rate of change
        rotator.setCycleCount(1); //Sets the number of times it repeats to one
        rotator.play(); //Plays the RotateTransition animation
        
        //Creates an event object for when the transition ends to continue with the exchanging of the card 
        rotator.setOnFinished(a -> {
    		switchBack(index); //Calls the switchBack method to switch the card to its back image 
    		transitionCardDiscard2(index); //Calls the transitionCardDiscard2 method to finish the animation of the card flip animation
        }); 
	}
	
	/** The private method transitionCardDiscard2 finishes the flip animation of the card to its front */
	private void transitionCardDiscard2(int index) {
		RotateTransition rotator2 = new RotateTransition(Duration.millis(300), cardPane.getChildren().get(index)); //Creates a new RotateTransition for the specified card with a duration of 300 milliseconds
        rotator2.setAxis(Rotate.Y_AXIS); //Rotates the specified card around its Y axis
        rotator2.setFromAngle(-90); //Sets the starting angle of the rotation to the -1/4 of a circle
        rotator2.setToAngle(0); //Sets the ending angle of the rotation to 0
        rotator2.setInterpolator(Interpolator.LINEAR); //Sets the rate of change
        rotator2.setCycleCount(1); //Sets the number of times it repeats to one
        rotator2.play(); //Plays the RotateTransition animation 
	}; 
	
	/** The private method transitionDeckSHuffle plays the shuffling animation for the user deck of cards */
	private void transitionDeckShuffle(ImageView back1, ImageView back2, HBox container) {
		TranslateTransition translateRed1 = new TranslateTransition(Duration.millis(300), back1); //Creates a new TranslateTransition for the specified card back with a duration of 300 milliseconds
		translateRed1.setToX(75); //Moves the specified card where x = 75 within the coordinate graph 
		translateRed1.setCycleCount(3); //Sets the number of times it repeats to three
		translateRed1.play(); //Plays the TranslateTransition animation 
		
		TranslateTransition translateBlue1 = new TranslateTransition(Duration.millis(300), back2); //Creates a new TranslateTransition for the specified card back with a duration of 300 milliseconds
		translateBlue1.setToX(-75); //Moves the specified card where x = -75 point within the coordinate graph 
		translateBlue1.setCycleCount(3); //Sets the number of times it repeats to three
		translateBlue1.play(); //Plays the TranslateTransition animation 
		
		TranslateTransition translateRed2 = new TranslateTransition(Duration.millis(300), back1); //Creates a new TranslateTransition for the specified card back with a duration of 300 milliseconds
		translateRed2.setFromX(75); //Begins the animation where x = 75 point within the coordinate graph
		translateRed2.setToX(0); //Moves the specified card back where x = 0 point within the coordinate graph 
		
		TranslateTransition translateBlue2 = new TranslateTransition(Duration.millis(300), back2); //Creates a new TranslateTransition for the specified card back with a duration of 300 milliseconds
		translateBlue2.setFromX(-75); //Begins the animation where x = -75 point within the coordinate graph
		translateBlue2.setToX(0); //Moves the specified card back where x = 0 point within the coordinate graph 
		
		//Creates an event object for when the transition ends to continue with the shuffle animation
		translateRed1.setOnFinished(e -> {
			translateRed2.play(); //Plays the TranslateTransition animation 
		});
		
		//Creates an event object for when the transition ends to continue with the shuffle animation
		translateBlue1.setOnFinished(e -> {
			translateBlue2.play(); //Plays the TranslateTransition animation 
		});
	}
	
	/** The private method getResults updates the image and index of a specified card */
	private void getResult(int num) {
		
		//Checks whether the number represents the first card 
		if (num == 0) { 
			card0 = userDeck.getCard(num); //If true, updates the image of the first card
			card0Index = userDeck.getCardIndex(num); //Updates the index of the first card 
		}
		else if (num == 1) { //Else, checks whether the number represents the second card 
			card1 = userDeck.getCard(num); //If true, updates the image of the second card
			card1Index = userDeck.getCardIndex(num); //Updates the index of the second card 
		}
		else if (num == 2) { //Else, checks whether the number represents the third card 
			card2 = userDeck.getCard(num); //If true, updates the image of the third card
			card2Index = userDeck.getCardIndex(num); //Updates the index of the third card 
		}
		else if (num == 3) { //Else, checks whether the number represents the fourth card 
			card3 = userDeck.getCard(num); //If true, updates the image of the fourth card
			card3Index = userDeck.getCardIndex(num); //Updates the index of the fourth card 
		}
		else if (num == 4) { //Else, checks whether the number represents the fifth card 
			card4 = userDeck.getCard(num); //If true, updates the image of the fifth card
			card4Index = userDeck.getCardIndex(num); //Updates the index of the fifth card 
		}; 
	}
	
	/** The private method switchBack updates the displayed image of a specified card to its background image */
	private void switchBack(int num) {
		
		//Checks whether the number represents the first card 
		if (num == 0) { 
			cardPane.getChildren().remove(0); //If true, removes the current image of the first card within the cardPane 
			
			//Checks whether the current card has a red or blue color 
			if ((card0Index > 12 && card0Index < 39) || card0Index == 53) {
				cardPane.getChildren().add(0, backRed0); //If true, adds the red background image of the first card if true 
			} else if (card0Index == 53) { //Else, checks whether the current card is the red Joker
				cardPane.getChildren().add(0, backRed0); //If true, adds the red background image of the first card if true 
			} else { 
				cardPane.getChildren().add(0, backBlue0); //Adds the blue background image of the first card
			}; 
		}
		else if (num == 1) { //Else, checks whether the number represents the second card 
			cardPane.getChildren().remove(1); //Removes the current image of the second card within the cardPane 
			
			//Checks whether the current card has a red or blue color 
			if ((card1Index > 12 && card1Index < 39) || card1Index == 53) {
				cardPane.getChildren().add(1, backRed1); //If true, adds the red background image of the second card if true 
			} else if (card1Index == 53) { //Else, checks whether the current card is the red Joker
				cardPane.getChildren().add(1, backRed1); //Adds the red background image of the second card if true 
			} else {
				cardPane.getChildren().add(1, backBlue1); //Adds the blue background image of the second card
			}; 
		} 
		else if (num == 2) { //Else, checks whether the number represents the third card 
			cardPane.getChildren().remove(2); //Removes the current image of the third card within the cardPane 
			
			//Checks whether the current card has a red or blue color 
			if ((card2Index > 12 && card2Index < 39) || card2Index == 53) {
				cardPane.getChildren().add(2, backRed2); //If true, adds the red background image of the third card if true 
			} else if (card2Index == 53) { //Else, checks whether the current card is the red Joker
				cardPane.getChildren().add(2, backRed2); //If true, adds the red background image of the third card if true 
			} else {
				cardPane.getChildren().add(2, backBlue2); //Adds the blue background image of the third card
			}; 
		}
		else if (num == 3) { //Else, checks whether the number represents the fourth card 
			cardPane.getChildren().remove(3); //Removes the current image of the fourth card within the cardPane 
			
			//Checks whether the current card has a red or blue color 
			if ((card3Index > 12 && card3Index < 39) || card3Index == 53) {
				cardPane.getChildren().add(3, backRed3); //If true, adds the red background image of the fourth card if true 
			} else if (card3Index == 53) { //Else, checks whether the current card is the red Joker
				cardPane.getChildren().add(3, backRed3); //If true, adds the red background image of the fourth card if true 
			} else {
				cardPane.getChildren().add(3, backBlue3); //Adds the blue background image of the fourth card
			}; 
		} 
		else if (num == 4) { //Else, checks whether the number represents the fifth card 
			cardPane.getChildren().remove(4); //Removes the current image of the fifth card within the cardPane 
			
			//Checks whether the current card has a red or blue color 
			if ((card4Index > 12 && card4Index < 39) || card4Index == 53) {
				cardPane.getChildren().add(4, backRed4); //If true, adds the red background image of the fifth card if true 
			} else if (card4Index == 53) { //Else, checks whether the current card is the red Joker
				cardPane.getChildren().add(4, backRed4); //If true, adds the red background image of the fifth card if true 
			} else {
				cardPane.getChildren().add(4, backBlue4); //Adds the blue background image of the fifth card
			}; 
		}; 
	}
	
	/** The private method switchFront updates the displayed image of a specified card to its regular image */
	private void switchFront(int num) {
		
		//Checks whether the number represents the first card 
		if (num == 0) { 
			cardPane.getChildren().remove(num); //If true, removes the current background image of the first card within the cardPane 
			cardPane.getChildren().add(num, card0); //Adds the current regular image of the first card 
		}
		else if (num == 1) { //Else, checks whether the number represents the second card 
			cardPane.getChildren().remove(num); //If true, removes the current background image of the second card within the cardPane 
			cardPane.getChildren().add(num, card1); //Adds the current regular image of the second card 
		}
		else if (num == 2) { //Else, checks whether the number represents the third card 
			cardPane.getChildren().remove(num); //If true, removes the current background image of the third card within the cardPane 
			cardPane.getChildren().add(num, card2); //Adds the current regular image of the third card 
		}
		else if (num == 3) { //Else, checks whether the number represents the fourth card 
			cardPane.getChildren().remove(num); //If true, removes the current background image of the fourth card within the cardPane 
			cardPane.getChildren().add(num, card3); //Adds the current regular image of the fourth card 
		}
		else if (num == 4) { //Else, checks whether the number represents the fifth card 
			cardPane.getChildren().remove(num); //If true, removes the current background image of the fifth card within the cardPane 
			cardPane.getChildren().add(num, card4); //Adds the current regular image of the fifth card 
		}; 
	}
	
	/** The private method update resets several components */
	private void update() {
		card0 = userDeck.getCard(0); //Retrieves the first image in a hand of 5 from the deck of cards 
		card0Index = userDeck.getCardIndex(0); //Retrieves the first image's index from the deck of cards 
		card1 = userDeck.getCard(1); //Retrieves the second image in a hand of 5 from the deck of cards 
		card1Index = userDeck.getCardIndex(1); //Retrieves the second image's index from the deck of cards 
		card2 = userDeck.getCard(2); //Retrieves the third image in a hand of 5 from the deck of cards 
		card2Index = userDeck.getCardIndex(2); //Retrieves the third image's index from the deck of cards 
		card3 = userDeck.getCard(3); //Retrieves the fourth image in a hand of 5 from the deck of cards 
		card3Index = userDeck.getCardIndex(3); //Retrieves the fourth image's index from the deck of cards 
		card4 = userDeck.getCard(4); //Retrieves the fifth image in a hand of 5 from the deck of cards 
		card4Index = userDeck.getCardIndex(4); //Retrieves the fifth image's index from the deck of cards 
		
		avatarImages.clear(); //Clears the avatarImages ArrayList
		avatarImages.add(0, new Image("avatar_image/cat.png")); //Adds the cat avatar image to avatarImages
		avatarImages.add(1, new Image("avatar_image/dog.png")); //Adds the dog avatar image to avatarImages
		avatarImages.add(2, new Image("avatar_image/dragon.jpg")); //Adds the dragon avatar image to avatarImages
		avatarImages.add(3, new Image("avatar_image/elephant.png")); //Adds the elephant avatar image to avatarImages
		avatarImages.add(4, new Image("avatar_image/lion.jpg")); //Adds the lion avatar image to avatarImages
		avatarImages.add(5, new Image("avatar_image/tiger.jpg")); //Adds the tiger avatar image to avatarImages
		
		cardPane.setAlignment(Pos.CENTER); // Set center alignment for the cardPane
		cardPane.getChildren().clear(); //Clears all of the pre-existing children that might be found in the card pane 
		
	    cardPane.getChildren().add(card0); //Add the first card within the hand of 5 to the card pane
	    cardPane.getChildren().add(card1); //Add the second card within the hand of 5 to the card pane
	    cardPane.getChildren().add(card2); //Add the third card within the hand of 5 to the card pane
	    cardPane.getChildren().add(card3); //Add the fourth card within the hand of 5 to the card pane
	    cardPane.getChildren().add(card4); //Add the fifth card within the hand of 5 to the card pane
	}
	
	/** The private method winAnimation plays the big win animation for the user */
	private void winAnimation(Text congratulationString) {
		animationHBox.setAlignment(Pos.CENTER); //Sets the alignment of the animationHBox to center
		
		ImageView dollar1 = new ImageView(new Image("animation_image/dollar.png")); //Creates a new dollar sign ImageView
		ImageView dollar2 = new ImageView(new Image("animation_image/dollar.png")); //Creates a new dollar sign ImageView
		ImageView dollar3 = new ImageView(new Image("animation_image/dollar.png")); //Creates a new dollar sign ImageView
		ImageView dollar4 = new ImageView(new Image("animation_image/dollar.png")); //Creates a new dollar sign ImageView
		
		ImageView streamerBlue = new ImageView(new Image("animation_image/streamerBlue.png")); //Creates a new streamer ImageView
		ImageView streamerGreen = new ImageView(new Image("animation_image/streamerGreen.png")); //Creates a new streamer ImageView
		ImageView streamerOrange = new ImageView(new Image("animation_image/streamerOrange.png")); //Creates a new streamer ImageView
		ImageView streamerPeach = new ImageView(new Image("animation_image/streamerPeach.png")); //Creates a new streamer ImageView
		ImageView streamerPurple = new ImageView(new Image("animation_image/streamerPurple.png")); //Creates a new streamer ImageView
		ImageView streamerRed = new ImageView(new Image("animation_image/streamerRed.png")); //Creates a new streamer ImageView
		
		animationHBox.getChildren().add(streamerBlue); //Adds the blue streamer ImageView to animationHBox
		animationHBox.getChildren().add(streamerGreen); //Adds the green streamer ImageView to animationHBox
		animationHBox.getChildren().add(dollar1); //Adds a dollar sign ImageView to animationHBox
		animationHBox.getChildren().add(streamerOrange); //Adds the orange streamer ImageView to animationHBox
		animationHBox.getChildren().add(dollar2); //Adds a dollar sign ImageView to animationHBox
		animationHBox.getChildren().add(dollar3); //Adds a dollar sign ImageView to animationHBox
		animationHBox.getChildren().add(streamerPeach); //Adds the peach streamer ImageView to animationHBox
		animationHBox.getChildren().add(streamerPurple); //Adds the purple streamer ImageView to animationHBox
		animationHBox.getChildren().add(dollar4); //Adds a dollar sign ImageView to animationHBox
		animationHBox.getChildren().add(streamerRed); //Adds the red streamer ImageView to animationHBox
				
		TranslateTransition translateBlue = new TranslateTransition(Duration.millis(3000), streamerBlue); //Creates a new TranslateTransition for the specified streamer ImageView with a duration of 3 seconds
		translateBlue.setFromY(0); //Begins the animation at the point where y = 0 within the coordinate graph
		translateBlue.setToY(500); //Begins the animation at the point where y = 500 within the coordinate graph
		translateBlue.setCycleCount(1); //Sets the number of times it repeats to one
		translateBlue.play(); //Plays the TranslateTransition animation 
		
		TranslateTransition translateGreen = new TranslateTransition(Duration.millis(4000), streamerGreen); //Creates a new TranslateTransition for the specified streamer ImageView with a duration of 4 seconds
		translateGreen.setFromY(0); //Begins the animation at the point where y = 0 within the coordinate graph
		translateGreen.setToY(500); //Begins the animation at the point where y = 500 within the coordinate graph
		translateGreen.setCycleCount(1); //Sets the number of times it repeats to one
		translateGreen.play(); //Plays the TranslateTransition animation 
		
		TranslateTransition translateOrange = new TranslateTransition(Duration.millis(3500), streamerOrange); //Creates a new TranslateTransition for the specified streamer ImageView with a duration of 3.5 seconds
		translateOrange.setFromY(0); //Begins the animation at the point where y = 0 within the coordinate graph
		translateOrange.setToY(500); //Begins the animation at the point where y = 500 within the coordinate graph
		translateOrange.setCycleCount(1); //Sets the number of times it repeats to one
		translateOrange.play(); //Plays the TranslateTransition animation 
		
		TranslateTransition translatePeach = new TranslateTransition(Duration.millis(5000), streamerPeach); //Creates a new TranslateTransition for the specified streamer ImageView with a duration of 5 seconds
		translatePeach.setFromY(0); //Begins the animation at the point where y = 0 within the coordinate graph
		translatePeach.setToY(500); //Begins the animation at the point where y = 500 within the coordinate graph
		translatePeach.setCycleCount(1); //Sets the number of times it repeats to one
		translatePeach.play(); //Plays the TranslateTransition animation 
		
		//Creates an event object for when the transition ends to quit the entire process
		translatePeach.setOnFinished(e -> {
			animationHBox.getChildren().clear(); //The animationHBox is cleared of its child nodes 
		});
		
		TranslateTransition translatePurple = new TranslateTransition(Duration.millis(3000), streamerPurple); //Creates a new TranslateTransition for the specified streamer ImageView with a duration of 3 seconds
		translatePurple.setFromY(0); //Begins the animation at the point where y = 0 within the coordinate graph
		translatePurple.setToY(500); //Ends the animation at the point where y = 500 within the coordinate graph
		translatePurple.setCycleCount(1); //Sets the number of times it repeats to one
		translatePurple.play(); //Plays the TranslateTransition animation 
		
		TranslateTransition translateRed = new TranslateTransition(Duration.millis(4000), streamerRed); //Creates a new TranslateTransition for the specified streamer ImageView with a duration of 4 seconds
		translateRed.setFromY(0); //Begins the animation at the point where y = 0 within the coordinate graph
		translateRed.setToY(500); //Ends the animation at the point where y = 500 within the coordinate graph
		translateRed.setCycleCount(1); //Sets the number of times it repeats to one
		translateRed.play(); //Plays the TranslateTransition animation 
		
		TranslateTransition translateStar1 = new TranslateTransition(Duration.millis(4500), dollar1); //Creates a new TranslateTransition for the specified dollar sign ImageView with a duration of 4.5 seconds
		translateStar1.setFromY(0); //Begins the animation at the point where y = 0 within the coordinate graph
		translateStar1.setToY(500); //Ends the animation at the point where y = 500 within the coordinate graph 
		translateStar1.setCycleCount(1); //Sets the number of times it repeats to one
		translateStar1.play(); //Plays the TranslateTransition animation 
		
		TranslateTransition translateStar2 = new TranslateTransition(Duration.millis(3000), dollar2); //Creates a new TranslateTransition for the specified dollar sign ImageView with a duration of 3 seconds
		translateStar2.setFromY(0); //Begins the animation at the point where y = 0 within the coordinate graph
		translateStar2.setToY(500); //Ends the animation at the point where y = 500 within the coordinate graph
		translateStar2.setCycleCount(1); //Sets the number of times it repeats to one
		translateStar2.play(); //Plays the TranslateTransition animation 
		
		TranslateTransition translateStar3 = new TranslateTransition(Duration.millis(4000), dollar3); //Creates a new TranslateTransition for the specified dollar sign ImageView with a duration of 4 seconds
		translateStar3.setFromY(0); //Begins the animation at the point where y = 0 within the coordinate graph
		translateStar3.setToY(500); //Ends the animation at the point where y = 500 within the coordinate graph
		translateStar3.setCycleCount(1); //Sets the number of times it repeats to one
		translateStar3.play(); //Plays the TranslateTransition animation 
		
		TranslateTransition translateStar4 = new TranslateTransition(Duration.millis(3000), dollar4); //Creates a new TranslateTransition for the specified dollar sign ImageView with a duration of 3 seconds
		translateStar4.setFromY(0); //Begins the animation at the point where y = 0 within the coordinate graph
		translateStar4.setToY(500); //Ends the animation at the point where y = 500 within the coordinate graph
		translateStar4.setCycleCount(1); //Sets the number of times it repeats to one
		translateStar4.play(); //Plays the TranslateTransition animation 
		
		congratulationString.setFill(Color.DARKORANGE); //Sets the color of congratulationString to dark orange 
		FadeTransition fadeTransition = new FadeTransition(Duration.millis(200), congratulationString); //Creates a new FadeTransition for the congratulationString with a duration of 0.2 seconds
		fadeTransition.setFromValue(0); //Begins the animation where the text color is all white 
		fadeTransition.setToValue(1); //Ends the animation where the text color is all black 
		fadeTransition.setCycleCount(10); //Sets the number of times it repeats to ten
		fadeTransition.play(); //Plays the FadeTransition animation 
	}
	
}
