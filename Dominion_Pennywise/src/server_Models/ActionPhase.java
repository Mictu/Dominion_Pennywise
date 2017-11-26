package server_Models;

import java.util.Collections;

public class ActionPhase {

	// initialize sector
	protected Player player;
	protected boolean phase;

	// Constructor
	protected ActionPhase(Player player, String cardName) {
		this.player = player;
		phase = true;
		chosenCard(cardName);						// Call Class and end phase - rethink..
	} // Close Constructor

	// choose this method if a card is pressed
	protected void chosenCard(String cardName) {
		switch (cardName) {
		case "village":
			actionVillage();
			break;
		case "woodcutter":
			actionWoodcutter();
			break;
		case "funfair":
			actionFunfair();
			break;
		case "laboratory":
			actionLaboratory();
			break;
		case "market":
			actionMarket();
			break;
		case "smith":
			actionSmith();
			break;
		default:
			phase = false;
			break;
		}
	}

	protected void actionSmith() {
		increaseCard(3, player);
	}

	protected void actionMarket() {
		increaseCard(1, player);
		player.increaseActionPoints(1);
		player.increaseBuyPoints(1);
		player.increaseMoney(1);
	}

	protected void actionLaboratory() {
		increaseCard(2, player);
		player.increaseActionPoints(1);
	}

	protected void actionFunfair() {
		player.increaseActionPoints(2);
		player.increaseBuyPoints(1);
		player.increaseMoney(2);
	}

	protected void actionWoodcutter() {
		player.increaseBuyPoints(1);
		player.increaseMoney(2);
	}

	protected void actionVillage() {
		player.increaseActionPoints(2);
		increaseCard(1, player);
	}

	protected void playActionCard() {
		player.hand.lastIndexOf("dd");
	}

	protected void increaseCard(int amount, Player player) {
		for (int i = 1; i <= amount; i++) {
			player.hand.add(player.deck.get(player.deck.size() - 1));
			player.deck.remove(player.deck.size() - 1);
			// shuffles Cards if deck is empty
			if (player.deck.isEmpty()) {
				Collections.shuffle(player.discard);
				for (int j = 1; j <= player.discard.size() ; j = 1){
					player.deck.add(player.discard.get(0));
					player.discard.remove(0);
				}
			}
		}
	}

} // Close Class

// Written by Patrick