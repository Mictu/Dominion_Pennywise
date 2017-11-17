package server_Models;

public class ActionPhase {

	// initialize sector
	protected boolean phase;
	Player player = new Player();
	

	// Constructor
	protected ActionPhase(String cardName, Player player) {
	} // Close Constructor

	protected void startPhase(String cardName, Player player) {
		this.phase = true;
		
		
		switch (cardName) {
		case "Dorf":
			actionVillage(player);
		case "Holzfaeller":
			actionWoodcutter(player);
		case "Jahrmarkt":
			actionFestival(player);
		case "Laboratorium":
			actionLaboratory(player);
		case "Markt":
			actionMarket(player);
		case "Schmiede":
			actionSmith(player);
			break;
		}
	}

	private void actionSmith(Player player) {
		// TODO Auto-generated method stub

	}

	private void actionMarket(Player player) {
		// TODO Auto-generated method stub

	}

	private void actionLaboratory(Player player) {
		// TODO Auto-generated method stub

	}

	private void actionFestival(Player player) {
		// TODO Auto-generated method stub

	}

	private void actionWoodcutter(Player player) {
		
	}

	private void actionVillage(Player player) {
		player.increaseActionPoints(2);
		player.increaseCard(1);
	}

	protected void endPhase() {
		this.phase = false;
	}

	protected void playActionCard() {
		// if (Player.deck.contains(kingdomCard)) {
		//
		// } else {
		// endPhase();
		// }

	}

} // Close Class

// Written by Patrick