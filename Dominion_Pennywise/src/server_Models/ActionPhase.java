package server_Models;

import java.util.Collections;

public class ActionPhase {

	// initialize sector
	public Player player;
	public boolean phase;

	// Constructor
	public ActionPhase(Player player) {
		this.player = player;
		phase = true;
	} // Close Constructors

	// choose this method if a card is pressed
	public void chosenCard(String cardName) {
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

	public void actionSmith() {
		increaseCard(3, player);
	}

	public void actionMarket() {
		increaseCard(1, player);
		player.increaseActionPoints(1);
		player.increaseBuyPoints(1);
		player.increaseMoney(1);
	}

	public void actionLaboratory() {
		increaseCard(2, player);
		player.increaseActionPoints(1);
	}

	public void actionFunfair() {
		player.increaseActionPoints(2);
		player.increaseBuyPoints(1);
		player.increaseMoney(2);
	}

	public void actionWoodcutter() {
		player.increaseBuyPoints(1);
		player.increaseMoney(2);
	}

	public void actionVillage() {
		player.increaseActionPoints(2);
		increaseCard(1, player);
	}

	public void playActionCard() {
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