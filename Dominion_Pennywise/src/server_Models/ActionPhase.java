package server_Models;

public class ActionPhase {

	// initialize sector
	protected boolean phase;
	Player player = new Player();
	

	// Constructor
	protected ActionPhase(String cardName, String cardDescription) {
	}

	protected void startPhase(String cardName, String cardDescription) {
		this.phase = true;
		
		
		switch (cardName) {
		case "Dorf":
			actionVillage();
		case "Holzfaeller":
			actionWoodcutter();
		case "Jahrmarkt":
			actionFestival();
		case "Laboratorium":
			actionLaboratory();
		case "Markt":
			actionMarket();
		case "Schmiede":
			actionSmith();
			break;
		}
	}

	private void actionSmith() {
		// TODO Auto-generated method stub

	}

	private void actionMarket() {
		// TODO Auto-generated method stub

	}

	private void actionLaboratory() {
		// TODO Auto-generated method stub

	}

	private void actionFestival() {
		// TODO Auto-generated method stub

	}

	private void actionWoodcutter() {
		// TODO Auto-generated method stub

	}

	private void actionVillage() {
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