package server_Models;

public class BuyPhase {
	
	// initialize section
	Player player;
	
	// Constructor
	protected BuyPhase(Player player) {
		this.player = player;
	} // close constructor
	
	
	// Add Card to discard deck of player
	protected void buyCard(String cardName) {
		int cost = getCost(cardName);
		// only if player has enough money to buy 'the' clicked card
		if (player.money >= cost) {
			player.discard.add(cardName);							// Karte vom Stapel abziehen !!!
			
			
			// Calculate how player is going to pay -gold : 3--silver : 2--copper : 1-
			// How NOT to get too much money from players hand
			// maybe start with the cheapest / highest or even wait for player to pick how he wants to pay
			
			// 1 - copper / silver / gold
			// 2 - 2*copper / silver / gold
			// 3 - 3*copper / silver + copper / 2*silver / gold
			// 4 - 4*copper / 2*silver / 2*copper + silver/ copper + gold / silver + gold / gold + gold
			// etc....
			// Maybe a method which pays up to 3 or 4 gold and do this in a loop (still not the best, e.g. 5)
			
			// -- >> seems the player has to chose how to pay the price
			// Otherwise there will be a shit-load of code which doesn't look very nice and is copy-pasted from itself
			
			// maybe set a golden border around the buttons with money to show what to press
			// player.Button(getText).setId("goldenBorder");
			
			
			
			player.getCashHand(); // show players money again
			
			
		} else {
			System.out.println("Not enough money");					// Mitteilung wenn zu wenig geld vorhanden
		}
	}
	
	// get the costs of every card
	protected int getCost(String cardName) {
		int costs = 0;
		
		switch (cardName) {
		case "village":
			costs = 3;
		case "woodcutter":
			costs = 3;
		case "funfair":
			costs = 5;
		case "laboratory":
			costs = 5;
		case "market":
			costs = 5;
		case "smith":
			costs = 4;
		case "copper":
			costs = 0;
		case "silver":
			costs = 3;
		case "gold":
			costs = 6;
		case "estate":
			costs = 2;
		case "duchy":
			costs = 5;
		case "province":
			costs = 8;
			break;
		}
		return costs;
	}

} // Close Class



// Written by Patrick