package server_Models;

import view.CardDesign_View;

public class BuyPhase {

	int cost;
	String cardName, card;
	String buyThisCard;
	Player player;
	boolean cardChosen = false;
	boolean payStarted = false;
	boolean sendingHand = false;
	
	String empty;
		
	
	
	int aVCounter = 10; 
	int aMCounter = 10; 
	int aSCounter = 10; 
	int aWCounter = 10; 
	int aFCounter = 10; 
	int aLCounter = 10; 
	
	int cCounter = 60;
	int sCounter = 40; 
	int gCounter = 10; 
	int eCounter = 24; 
	int dCounter = 12; 
	int pCounter = 12;
	
	
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
		sendingHand = false;
		cardChosen = true;
		buyThisCard = cardName;
		cost = getCost(cardName);
		if (cost > player.getCashHand()+player.money) {
			System.out.println("Not enough cash in your hand and money(buyphase)");
		}
	}
	
	
	public void pay() {
		sendingHand = false;
		payStarted = true;
		System.out.println("pay");
		if (buyThisCard == null) {
			System.out.println("Zuerst eine Karte zum kaufen auswählen");
		} else {
			String card = cardName.substring(4);
			switch (card) {
			case "gold":
				this.player.hand.remove(this.player.hand.lastIndexOf("gold"));
				this.player.discard.add("gold");
				player.money += 3;
				System.out.println("PAY() PlayerMoney: " + player.money);
				break;
			case "silver":
				this.player.hand.remove(this.player.hand.lastIndexOf("silver"));
				this.player.discard.add("silver");
				player.money += 2;
				System.out.println("PAY() PlayerMoney: " + player.money);
				break;
			case "copper":
				this.player.hand.remove(this.player.hand.lastIndexOf("copper"));
				this.player.discard.add("copper");
				player.money += 1;
				System.out.println("PAY() PlayerMoney: " + player.money);
				break;
			}
			// Set everything to null for the next round
			doTheBuy();
		}
	}
	
	public void doTheBuy() {
		if (player.money >= cost) {
			decreaseCard(buyThisCard);
			this.player.discard.add(buyThisCard);
			buyThisCard = null;
			cardName = null;
			cardChosen = false;
			payStarted = false;
			player.money = 0;
			cost = 0;
			player.decreaseBuyPoints();
			sendingHand = true;
		}
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
	
	public void decreaseCard(String cardName){
		switch (cardName) {
		case "village":
			aVCounter--;
			isFinished();
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
	
	public String isFinished(){
		empty = ""; 
		if( aVCounter == 0 ){empty = "cardemptyvillage";}
		if( aWCounter == 0 ){empty = "cardemptywoodcutter";}
		if( aFCounter == 0 ){empty = "cardemptyfunfair";}
		if( aLCounter == 0 ){empty = "cardemptylaboratory";}
		if( aMCounter == 0 ){empty = "cardemptymarket";}
		if( aSCounter == 0 ){empty = "cardemptysmith";}
		if( cCounter == 0 ){empty = "cardemptycopper";}
		if( sCounter == 0 ){empty = "cardemptysilver";}
		if( gCounter == 0 ){empty = "cardemptygold";}
		if( eCounter == 0 ){empty = "cardemptyestate";}
		if( dCounter == 0 ){empty = "cardemptyduchy";}
		if( pCounter == 0 ){empty = "cardemptyprovince";}
		return empty;
		
	}

	


}

// Written by Patrick