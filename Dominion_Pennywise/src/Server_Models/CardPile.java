package Server_Models;

import java.util.ArrayList;

public class CardPile {
	
	//initialize zone here
	protected ArrayList<Card> deck = new ArrayList<Card>();
	protected ArrayList<Card> discard = new ArrayList<Card>();
	protected ArrayList<Card> pile = new ArrayList<Card>();
	protected ArrayList<Card> hand = new ArrayList<Card>();
	
	//Constructor to Create all Cards
	protected CardPile() {
		// create
		
		
		
		
	}//close constructor
	
	// shuffle the hand
	protected ArrayList<Card> shuffle(ArrayList<Card> toShuffle) {
		return hand;
	}
	
	// give new cards (from players deck to hand) and shuffle discards if needed
	protected void cleanUp () {
		
	}
	
	// create a cardpile with every card
	protected void createCardPile() {
		
	}
	
	// draw a card 
	protected void drawCard() {
		
	}
	
	// put used card to discards
	protected void throwCard() {
		
	}
	
	
	

}



// Written by Patrick