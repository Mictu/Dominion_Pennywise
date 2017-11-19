package server_Models;

public class BuyPhase {
	
	// initialize section
	protected CleanUpPhase cleanUp;
	protected boolean phase;
	Player player;
	
	// Constructor
	protected BuyPhase(Player player) {
		this.player = player;
		if (player.buyPoint == 0)
			endPhase();
	} // close constructor
	
	
	// If Phase is over start the cleanUpPhase
	protected void endPhase() {
		cleanUp = new CleanUpPhase(player);
	}
	
	// Add Card to discard deck of player
	protected void buyCard() {
		player.discard.add();			// Add Card to Deck
	}
	
	protected void playCard() {
		
	}
	
	

} // Close Class



// Written by Patrick