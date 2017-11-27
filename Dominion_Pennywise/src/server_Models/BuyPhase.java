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
			
			
			// maybe set a golden border around the buttons with money to show what to press
			// player.Button(getText).setId("goldenBorder");
			
			
			// Also show bonus money somewhere to chose for buyoption - button?
			
			
			
			for (String x : player.hand) {
				if (x.equals("copper"))
					x.setId("goldenBorder");
			}
			
			
			
			
			
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