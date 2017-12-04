package server_Models;

import java.util.Collections;

public class ActionPhase {

	// initialize sector
	public boolean phase;

	// Constructor
	public ActionPhase() {
		phase = true;
	} // Close Constructors

	
	// choose this method if a card is pressed
	public void chosenCard(String cardName, Player player) {
		switch (cardName) {
		case "village":
			actionVillage(player);
			break;
		case "woodcutter":
			actionWoodcutter(player);
			break;
		case "funfair":
			actionFunfair(player);
			break;
		case "laboratory":
			actionLaboratory(player);
			break;
		case "market":
			actionMarket(player);
			break;
		case "smith":
			actionSmith(player);
			break;
		default:
			phase = false;
			break;
		}
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

	public void playActionCard(Player player) {
		player.hand.lastIndexOf("dd");
	}

	public void increaseCard(int amount, Player player) {
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