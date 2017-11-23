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
		if (player.money >= cost) {
			player.discard.add(cardName);							// Karte vom Stapel abziehen !!!
			
			
			// Calculate how player is going to pay
			if (cost == 1 && player.hand.contains("copper"))
				player.hand.remove(player.hand.lastIndexOf("copper"));
			if (cost == 2 && player.hand.contains("silver"))
				player.hand.remove(player.hand.lastIndexOf("silver"));
			if (cost == 3 && player.hand.contains("gold"))
				player.hand.remove(player.hand.lastIndexOf("gold"));
			
			
		} else {
			System.out.println("Not enough money");					// Mitteilung wenn zu wenig geld vorhanden
		}
	}
	
	
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