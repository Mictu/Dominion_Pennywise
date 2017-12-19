package server_Models;

import java.util.Collections;

import server.client.Server;

public class ActionPhase {

	// initialize sector
	boolean madeAnAction = false; 

	boolean sendInfo = false; 
	String playedCard, info;
	Server server;
	
	// Constructor
	public ActionPhase() {
	} // Close Constructors
	
	// choose this method if a card is pressed
	public void chosenCard(String message, Player player) {
		sendInfo = false;
		madeAnAction = false;
		String cardName = message.substring(4);
		this.playedCard = cardName;
		if(player.getActionPoints() >0 ) {
			switch (cardName) {
			case "village":
				actionVillage(player);
				reloadHand(cardName, player);
				break;
			case "woodcutter":
				actionWoodcutter(player);
				reloadHand(cardName, player);
				break;
			case "funfair":
				actionFunfair(player);
				reloadHand(cardName, player);
				break;
			case "laboratory":
				actionLaboratory(player);
				reloadHand(cardName, player);
				break;
			case "market":
				actionMarket(player);
				reloadHand(cardName, player);
				break;
			case "smith":
				actionSmith(player);
				reloadHand(cardName, player);
				break;
			} 
		} else {
			sendInfo = true;
		}
	} 

	public boolean getActionMadeBoolean(){
		return madeAnAction; 
	}
	
	public void actionSmith(Player player) {
		increaseCard(3, player);
	}

	public void actionMarket(Player player) {
		increaseCard(1, player);
		player.increaseActionPoints(1);
		player.increaseBuyPoints(1);
		player.increaseMoney(1);
	}

	public void actionLaboratory(Player player) {
		increaseCard(2, player);
		player.increaseActionPoints(1);
	}

	public void actionFunfair(Player player) {
		player.increaseActionPoints(2);
		player.increaseBuyPoints(1);
		player.increaseMoney(2);
	}

	public void actionWoodcutter(Player player) {
		player.increaseBuyPoints(1);
		player.increaseMoney(2);
	}

	public void actionVillage(Player player) {
		player.increaseActionPoints(2);
		increaseCard(1, player);
	}

	public void increaseCard(int amount, Player player) {
		int have = (player.deck.size() + player.discard.size());
		
		if (amount > have) {
			System.out.println("zu wenige karten vorhanden");
			amount = have;
		}
		
		for (int i = 1; i <= amount; i++) {
			if (player.deck.size() == 0) {
				for (int j = 1; j <= player.discard.size() ; j = 1){
					player.deck.add(player.discard.get(0));
					player.discard.remove(0);
				}
				Collections.shuffle(player.deck);
			}
			player.hand.add(player.deck.get(0));
			player.deck.remove(0);
			// shuffles Cards if deck is empty
		}
	}
	
	public String getPlayedCard() {
		return this.playedCard;
	}
	
	public boolean getInfoMessage() {
		info = "not enough action - points";
		return this.sendInfo;
	}
	
	public String getInfoString() {
		return this.info;
	}

	public void reloadHand(String card, Player player) {
		player.discard.add(player.hand.get(player.hand.lastIndexOf(card)));
		player.hand.remove(player.hand.lastIndexOf(card));
		madeAnAction = true;
		player.decreaseActionPoints();
	}
} 


// Written by Patrick