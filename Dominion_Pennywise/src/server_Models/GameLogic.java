package server_Models;

import server.client.Server;
import server.client.ServerHandler;

public class GameLogic {

	// Initialize sector here
	protected CleanUpPhase cleanUpPhase;
	ActionPhase actionPhase = new ActionPhase();
	BuyPhase buyPhase = new BuyPhase();

	protected int countRounds;
	protected final int START_MONEY = 7;
	protected final int START_ESTATE = 3;

	protected String actualPhase;

	ServerHandler serverHandler;
	Server server;
	CleanUpPhase cleanPhase;
	int index;
	boolean firstRound;

	Player player;

	// Constructor
	public GameLogic(Server server) {
		this.server = server;
		index = 0;
		firstRound = true;
		actualPhase = "action";
		for (Player player : Player.player) {
			gameStart(player);
		}
	} // Close Constructor

	protected void gameStart(Player player) {
		for (int i = 0; i < START_MONEY; i++) {
			player.discard.add("copper"); // don't forget to counter++
		}

		for (int i = 0; i < START_ESTATE; i++) {
			player.discard.add("estate");
		}

		cleanPhase = new CleanUpPhase(player);
	}

	public void theGame() {
		server.sendStringToClient("cleanup", index);
		getIndex();

		if (firstRound == true) {
			index = 0;
			firstRound = false;

			int ind = 0;

			for (Player p : Player.player) {
				if (p.equals(this.player)) {
					server.sendStringToClient("action", index);
				} else {
					server.sendStringToClient("cleanup", ind);
				}
				ind++;
			}

		}

		this.player = Player.player.get(index);
		server.sendStringToClient("action", index);

		this.player.startRound();
		// sendPlayersHand();
	}

	public void playCard(String message) {
		switch (actualPhase) {
		case "action":
			actionPhase.chosenCard(message, this.player);
			if(actionPhase.getActionMadeBoolean())
				sendPlayersHand();
			break; 
		case "buy":
			buyPhase.buyCard(message, this.player);
			if (buyPhase.sendHandAgain())
				sendPlayersHand();
			break; 
		}
	}

	public int getIndex() {
		index++;
		if (index == Player.player.size()) {
			index = 0;
		}
		return this.index;
	}

	// get the actual phase to let the client know what cards can be pressed
	public String getPhase() {
		return this.actualPhase;
	}

	// get the player which is playing in this moment
	public Player getPlayer() {
		return this.player;
	}

	// start following phase if button on board view is clicked
	public void endPhase() {
		switch (actualPhase) {
		case "action":
			actualPhase = "buy";
			server.sendStringToClient(actualPhase, index);
			sendPlayersHand();
			break;
		case "buy":
			actualPhase = "cleanup";
			cleanUpPhase = new CleanUpPhase(this.player);
			server.sendStringToClient(actualPhase, index);
			sendPlayersHand();
		case "cleanup":
			theGame();
			actualPhase = "action";
			server.sendStringToClient(actualPhase, index);
			sendPlayersHand();
			break; // block this monitor
		}
	}

	// This method should actualize the hand on the view (e.g. call it in buy
	// phase
	// when u gave away a copper card)
	public void sendPlayersHand() {
		String theHand = "hand.";
		for (String card : player.hand) {
			theHand = theHand.concat(card + ".");
		}
		theHand = theHand.substring(0, theHand.length() - 1);
		server.sendStringToClient(theHand, index);
		for (int i = 0; i <= 1999999999; i++) {
			for (int j = 0; j <= 1999999999; j++) {
			}
		}
	}

	public String getActualPhase() {
		return this.actualPhase;
	}

	public Player getActualPlayer() {
		return this.player;
	}

}

// Patrick