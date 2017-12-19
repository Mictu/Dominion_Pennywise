package server_Models;

public class BuyPhase {

	int cost;
	String cardName, card , infoMessage;
	String buyThisCard;
	String boughtCard;
	Player player;
	boolean cardChosen = false;
	boolean payStarted = false;
	boolean sendingHand = false;
	boolean successfull = false;
	boolean sendInfo = false;


	public BuyPhase() {
		buyThisCard = null;
	}

	// Add Card to discard deck of player
	public void buyCard(String message, Player player) {
		this.player = player;
		cardName = message;
		sendInfo = false;
		successfull = false;
		if (player.getBuyPoints() > 0) {
			if (!cardChosen && !payStarted && cardName.equals("copper")) {
				buyThisCard = "copper";
				doTheBuy();
			} else if (cardName.contains("hand") && cardChosen) {
				pay();
			} else if (!cardName.contains("hand") && !payStarted) {
				buy();
			} else if (cardChosen) {
				infoMessage = "card already picked";
				sendInfo = true;
			} else {
				infoMessage = "no card selected";
				sendInfo = true;
			}
		} else {
			infoMessage = "not enough buy - points";
			sendInfo = true;
		}
	}

	public void buy() {
		buyThisCard = cardName;
		sendingHand = false;
		cost = getCost(cardName);
		if (cost > player.getCashHand() + player.money) {
			infoMessage = "not enough money";
			sendInfo = true;
		} else if (cost <= player.money){
			doTheBuy();
		} else {
			cardChosen = true;
			infoMessage = "cardischosen"+buyThisCard;
			sendInfo = true;
		}
	}

	public void pay() {
		sendingHand = false;
		payStarted = true;
		String card = cardName.substring(4);
		switch (card) {
		case "gold":
			this.player.hand.remove(this.player.hand.lastIndexOf("gold"));
			this.player.discard.add("gold");
			player.money += 3;
			break;
		case "silver":
			this.player.hand.remove(this.player.hand.lastIndexOf("silver"));
			this.player.discard.add("silver");
			player.money += 2;
			break;
		case "copper":
			this.player.hand.remove(this.player.hand.lastIndexOf("copper"));
			this.player.discard.add("copper");
			player.money += 1;
			break;
		}
		// Set everything to null for the next round
		if (player.money >= cost) {
			doTheBuy();
		}
	}

	public void doTheBuy() {
		successfull = true;
		boughtCard = buyThisCard;
		String cardHandled = buyThisCard;
		this.player.discard.add(buyThisCard);
		buyThisCard = null;
		cardName = null;
		cardChosen = false;
		payStarted = false;
		player.money = player.money - cost;
		cost = 0;
		player.decreaseBuyPoints();
		sendingHand = true;
		if (cardHandled.equals("copper")) {
			sendingHand = false;
		}
	}
	
	public void resetVariablesForBuyPhase() {
		player.money = 0;
		cost = 0;
		buyThisCard = null;
		cardName = null;
		cardChosen = false;
		payStarted = false;
		sendingHand = true;
	}

	public boolean sendHandAgain() {
		if (payStarted)
			return payStarted;
		else
			return sendingHand;
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
		default:
			System.out.println("string couldn't be handled by buyphase");
			costs = 100;
			break;
		}
		return costs;
	}
	
	public boolean buySuccessfull() {
		return this.successfull;
	}
	
	public String getBoughtCard() {
		return this.boughtCard;
	}
	
	public String getInfoString() {
		return this.infoMessage;
	}
	
	public boolean getInfoMsg() {
		return this.sendInfo;
	}
	
}

// Written by Patrick