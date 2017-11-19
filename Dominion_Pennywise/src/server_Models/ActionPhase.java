package server_Models;

public class ActionPhase {

	// initialize sector
	protected Player player;

	// Constructor
	protected ActionPhase(Player player) {
		this.player = player;
		playPhase();
	} // Close Constructor

	
	protected void playPhase() {
		
		
		//Better get a Card - String and then do the action
		
//		switch (cardName) {
//		case "Dorf":
//			actionVillage(player);
//		case "Holzfaeller":
//			actionWoodcutter(player);
//		case "Jahrmarkt":
//			actionFestival(player);
//		case "Laboratorium":
//			actionLaboratory(player);
//		case "Markt":
//			actionMarket(player);
//		case "Schmiede":
//			actionSmith(player);
//			break;
//		}
	}

	private void actionSmith(Player player) {

	}

	private void actionMarket(Player player) {

	}

	private void actionLaboratory(Player player) {

	}

	private void actionFestival(Player player) {

	}

	private void actionWoodcutter(Player player) {
		
	}

	private void actionVillage(Player player) {
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