package server_Models;

/**
 * Play an buy-phase and reset the used content for the next player. This class
 * only starts while actual player is in buy-phase. Contains everything it
 * needed for a proper buy Phase in game
 * 
 * @author Patrick Ziörjen
 * @author Sojo Nagaroor
 * @author Yujia Shi
 */
public class BuyPhase {

	private int cost;
	private String cardName, infoMessage, buyThisCard, boughtCard, empty;
	private Player player;
	private boolean cardChosen = false, payStarted = false, successfull = false, sendInfo = false, sendingHand = false;

	public int aVCounter = 10, aMCounter = 10, aSCounter = 10, aWCounter = 10, aFCounter = 10, aLCounter = 10;
	public int cCounter = 60, sCounter = 40, gCounter = 30, eCounter = 24, dCounter = 12, pCounter = 12;
	public String card;

	public BuyPhase() {
		buyThisCard = null;
	}

	/**
	 * Handle the buyphase. Get cardName and the actualplayer from ServerHandler
	 * Check if he player has enough buypoints. Check if the card is already picked.
	 * Check a card is selected. Check start the buy and pay phase of buyphase.
	 * 
	 * @author Patrick Ziörjen
	 * @param message
	 *            - cardName of the card that has been chose
	 * @param player
	 *            - acutal player from ServerHanlder
	 */
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

	/**
	 * Handle buy phase of buyphase of the game. Check if the player has enough
	 * money
	 * 
	 * @author Sojo Nagaroor
	 */
	public void buy() {
		buyThisCard = cardName;
		sendingHand = false;
		cost = getCost(cardName);
		if (cost > player.getCashHand() + player.money) {
			infoMessage = "not enough money";
			sendInfo = true;
		} else if (cost <= player.money) {
			doTheBuy();
		} else {
			cardChosen = true;
			infoMessage = "cardischosen" + buyThisCard;
			sendInfo = true;
		}
	}

	/**
	 * Handle pay phase of buyphase of the game. remove the hand card if the player
	 * used the card to pay for the card he want buy. Increase the money if the
	 * player clicks on a card in his hand.
	 * 
	 * @author Yujia Shi
	 */
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
		if (player.money >= cost) {
			doTheBuy();
		}
	}

	/**
	 * Reset all variables for buyphase reset bonusmoney, card chosen...etc.
	 * 
	 * @author Yujia Shi
	 */
	public void doTheBuy() {
		successfull = true;
		boughtCard = buyThisCard;
		String cardHandled = buyThisCard;
		decreaseCard(buyThisCard);
		this.player.discard.add(buyThisCard);
		buyThisCard = null;
		cardName = null;
		payStarted = false;
		cardChosen = false;
		player.money = player.money - cost;
		cost = 0;
		player.decreaseBuyPoints();
		sendingHand = true;
		if (cardHandled.equals("copper")) {
			sendingHand = false;
		}
	}

	/**
	 * Reset the variables for every buyphase for the acutal player
	 * 
	 * @author Sojo Nagaroor
	 * @param player
	 *            - actual player from GameLogic
	 */
	public void resetVariablesForBuyPhase(Player player) {
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

	/**
	 * get Cost from all cards that can be bought
	 * 
	 * @author Patrick Ziörjen
	 * @param cardName
	 *            - name of the card that has been chosed
	 * @return costs - the cost of the card that has been chosed
	 */
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

	/**
	 * decrease the cards from cardpile if a card has been chosed.
	 * 
	 * @author Yujia Shi
	 * @param cardName
	 *            - name of the card that has been chosen
	 */
	public void decreaseCard(String cardName) {
		switch (cardName) {
		case "village":
			aVCounter--;
			break;
		case "woodcutter":
			aWCounter--;
			break;
		case "funfair":
			aFCounter--;
			break;
		case "laboratory":
			aLCounter--;
			break;
		case "market":
			aMCounter--;
			break;
		case "smith":
			aSCounter--;
			break;
		case "copper":
			cCounter--;
			break;
		case "silver":
			sCounter--;
			break;
		case "gold":
			gCounter--;
			break;
		case "estate":
			eCounter--;
			break;
		case "duchy":
			dCounter--;
			break;
		case "province":
			pCounter--;
			break;
		}
	}

	/**
	 * check if the counter from decrease card is 0. if there are no cards anymore
	 * on a cardpile, send tell the server, that he should flip the cardpile on
	 * client side
	 * 
	 * @author Sojo Nagaroor
	 */
	public String isFinished() {
		empty = "";
		if (aVCounter == 0) {
			empty = "cardemptyvillage";
			aVCounter = -1;
		}
		if (aWCounter == 0) {
			empty = "cardemptywoodcutter";
			aWCounter = -1;
		}
		if (aFCounter == 0) {
			empty = "cardemptyfunfair";
			aFCounter = -1;
		}
		if (aLCounter == 0) {
			empty = "cardemptylaboratory";
			aLCounter = -1;
		}
		if (aMCounter == 0) {
			empty = "cardemptymarket";
			aMCounter = -1;
		}
		if (aSCounter == 0) {
			empty = "cardemptysmith";
			aSCounter = -1;
		}
		if (cCounter == 0) {
			empty = "cardemptycopper";
			cCounter = -1;
		}
		if (sCounter == 0) {
			empty = "cardemptysilver";
			sCounter = -1;
		}
		if (gCounter == 0) {
			empty = "cardemptygold";
			gCounter = -1;
		}
		if (eCounter == 0) {
			empty = "cardemptyestate";
			eCounter = -1;
		}
		if (dCounter == 0) {
			empty = "cardemptyduchy";
			dCounter = -1;
		}
		if (pCounter == 0) {
			empty = "cardemptyprovince";
			pCounter = -1;
		}

		return empty;
	}

	/**
	 * send the cards that are left on a cardpile to clientside to display the
	 * cardpile counter correctly
	 * 
	 * @author Patrick Ziörjen
	 * @return nums - String with left cards number
	 */
	public String sendRestCards() {
		String nums = "amount" + "." + eCounter + "." + dCounter + "." + pCounter + "." + aFCounter + "." + aLCounter
				+ "." + aMCounter + "." + aSCounter + "." + aVCounter + "." + aWCounter + "." + cCounter + "."
				+ sCounter + "." + gCounter;
		return nums;
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
