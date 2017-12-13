package server_Models;

public class BuyPhase {

	// initialize section
	String card;

	// Constructor
	public BuyPhase() {
	} // close constructor

	// Add Card to discard deck of player
	public void buyCard(String cardName, Player player) {
		// clicked card has to be handled also!
		int cost = getCost(cardName);
		// only if player has enough money to buy the "chosen" card
		if (player.money >= cost) {
			player.discard.add(cardName); // Karte vom Stapel abziehen !!!

			// Also show bonus money somewhere to chose for buyoption - button?

			player.getCashHand(); // show players money again

		} else {
			System.out.println("Not enough money"); // Mitteilung wenn zu wenig geld vorhanden
		}
	}

	// set chosen card, so this class knows what to buy
	public void choseCardToBuy(String chosenCard) {
		this.card = chosenCard;
		// server.sendToClient(""+getCost(chosenCard));
	}

	// get the costs of every card
	public int getCost(String cardName) {
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