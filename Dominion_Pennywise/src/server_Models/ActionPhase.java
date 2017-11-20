package server_Models;

public class ActionPhase {

	// initialize sector
	protected Player player;
	protected boolean phase;
	
	// Constructor
	protected ActionPhase(Player player) {
		this.player = player;
		phase = true;
	} // Close Constructor

	// choose this method if a card is pressed
	protected void chosenCard (String cardName) {
		if (phase = true) {
			switch (cardName) {
			case "village":
				actionVillage();
			case "woodcutter":
				actionWoodcutter();
			case "funfair":
				actionFunfair();
			case "laboratory":
				actionLaboratory();
			case "market":
				actionMarket();
			case "smith":
				actionSmith();
				break;
			}
		} else {
					// Logger Info?
		}
		
	}

	protected void actionSmith() {

	}

	protected void actionMarket() {

	}

	protected void actionLaboratory() {

	}

	protected void actionFunfair() {

	}

	protected void actionWoodcutter() {
		
	}

	protected void actionVillage() {
		player.increaseActionPoints(2);
		increaseCard(player);
	}


	protected void playActionCard() {
		// if (Player.deck.contains(kingdomCard)) {
		//
		// } else {
		// endPhase();
		// }

	}
	
	protected void increaseCard(Player player) {
	}
	
	

} // Close Class

// Written by Patrick