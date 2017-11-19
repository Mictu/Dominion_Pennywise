package server_Models;

public class BuyPhase {
	
	// initialize section
	Player player;
	
	// Constructor
	protected BuyPhase(Player player) {
		this.player = player;
	} // close constructor
	
	
	// Add Card to discard deck of player
	protected void buyCard() {
		player.discard.add();			// Add Card to Deck
	}
	
	protected void playCard() {
		
	}
	
	

} // Close Class



// Written by Patrick