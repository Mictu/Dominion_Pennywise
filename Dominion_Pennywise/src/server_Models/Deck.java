package server_Models;

import java.util.ArrayList;

public class Deck {
	
	protected ArrayList<Cards> startDeck;
	protected TreasureCard treasureCard;
	
	protected final int COPPER_BEGIN = 7, ESTATE_BEGIN = 3;
	
	public Deck() {
		this.startDeck = new ArrayList<Cards>();
//		treasureCard = new TreasureCard();
	}
	
	//Start Deck with 7 copper Cards and 3 estate cards
	
	protected ArrayList startDeck() {
		
		for (int i = 0; i < COPPER_BEGIN; i++) {
//			startDeck.add(treasureCard);
		}			
		for (int j = 0; j < ESTATE_BEGIN; j++) {
//			startDeck.add(victoryCard);
		}
		
		
		
		return startDeck;	
	}
	
	
}
