package server_Models;

import java.util.Collections;

public class CleanUpPhase {
	
	// initialize section
	Player player;
	final int HAND_SIZE = 5;
	
	
	// Constructor
	protected CleanUpPhase(Player player) {
		this.player = player;
		throwCard();
		drawCard();
	} // close constructor
	
	
	
	// clears the hand of the player (push cards on to discard deck)
	protected void throwCard() {
		for (int i = 1; i <= player.hand.size(); i = 1) {
			player.discard.add(player.hand.get(0));
			player.hand.remove(0);
		}
	}
	
	// get cards from your deck into your hand (5 in the end)
	protected void drawCard() {
		for(int i = 1; i <= 5; i++) {
			if (player.deck.isEmpty()) {
				shuffle();
			}
			player.hand.add(player.deck.get((player.deck.size() - 1)));
			player.deck.remove((player.deck.size() - 1));
		}
	}
	
	// shuffles the discard-deck and adds the cards to the deck
	protected void shuffle() {
		Collections.shuffle(player.discard);
		for (int i = 1; i <= player.discard.size() ; i = 1){
			player.deck.add(player.discard.get(0));
			player.discard.remove(0);
		}
	}
	

} // Close Class



//Written by Patrick