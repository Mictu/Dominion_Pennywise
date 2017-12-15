package server_Models;

public class BuyPhase {

	int cost, moneyForBuy;
	String cardName, card;
	String buyThisCard;
	Player player;
	boolean cardChosen = false;
	boolean payStarted = false;

	public BuyPhase() {
		buyThisCard = null;
	}

	// Add Card to discard deck of player
	public void buyCard(String message, Player player) {
		this.player = player;
		cardName = message;
		if (player.getBuyPoints() > 0) {
			if (cardName.contains("hand") && cardChosen) {
				pay();
			} else if (!cardName.contains("hand") && !payStarted) {
				buy();
			} else if (cardChosen) {
				System.out.println("Card already picked");
			} else {
				System.out.println("No Card picked to buy");
			}
		} else {
			System.out.println("not enough buy-points");
		}
	}

	// BONUS BUY BUTTON - DONT FORGET

	
	public void buy() {
		System.out.println("buy");
		cardChosen = true;
		buyThisCard = cardName;
		cost = getCost(cardName);
		if (cost > player.getCashHand()) {
			System.out.println("Not enough cash in your hand (buyphase)");
		}
	}
	
	
	public void pay() {
		payStarted = true;
		System.out.println("pay");
		if (buyThisCard == null) {
			System.out.println("Zuerst eine Karte zum kaufen auswählen");
		} else {
			cardChosen = true;
			String card = cardName.substring(4);
			switch (card) {
			case "gold":
				this.player.hand.remove(this.player.hand.lastIndexOf("gold"));
				this.player.discard.add("gold");
				moneyForBuy += 3;
				break;
			case "silver":
				this.player.hand.remove(this.player.hand.lastIndexOf("silver"));
				this.player.discard.add("silver");
				moneyForBuy += 2;
				break;
			case "copper":
				this.player.hand.remove(this.player.hand.lastIndexOf("copper"));
				this.player.discard.add("copper");
				moneyForBuy += 1;
				break;
			}
			if (moneyForBuy >= cost) {
				this.player.discard.add(buyThisCard);
				buyThisCard = null;
				cardChosen = false;
				payStarted = false;
				player.decreaseBuyPoints();
			}
		}
	}


	// get the costs of every card
	public int getCost(String cardName) {
		int costs = 0;

		switch (cardName) {
		case "village":
			costs = 3;
			break;
		case "woodcutter":
			costs = 3;
			break;
		case "funfair":
			costs = 5;
			break;
		case "laboratory":
			costs = 5;
			break;
		case "market":
			costs = 5;
			break;
		case "smith":
			costs = 4;
			break;
		case "copper":
			costs = 0;
			break;
		case "silver":
			costs = 3;
			break;
		case "gold":
			costs = 6;
			break;
		case "estate":
			costs = 2;
			break;
		case "duchy":
			costs = 5;
			break;
		case "province":
			costs = 8;
			break;
		}
		return costs;
	}

}

// Written by Patrick