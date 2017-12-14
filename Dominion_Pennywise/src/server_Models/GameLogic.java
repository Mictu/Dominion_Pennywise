package server_Models;

import server.client.Server;
import server.client.ServerHandler;

public class GameLogic {

	// Initialize sector here
	protected CleanUpPhase cleanUpPhase;
	ActionPhase actionPhase;
	BuyPhase buyPhase;

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
//		for (int i = 0; i < START_MONEY; i++) {
//			player.discard.add("copper"); // don't forget to counter++
//		}
//
//		for (int i = 0; i < START_ESTATE; i++) {
//			player.discard.add("estate");
//		}
		
		for (int i = 0; i < 10; i++) {
		player.discard.add("funfair");
	}
		
//		for (int i = 0; i < 6; i++) {
//			player.discard.add("village");
//		}
//		
//		for (int i = 0; i < 6; i++) {
//			player.discard.add("smith");
//		}
		
		cleanPhase = new CleanUpPhase(player);
	}

	public void theGame() {
		getIndex();
		if (firstRound == true) {
			index = 0;
			firstRound = false;
		}
		this.player = Player.player.get(index);
		this.player.startRound();
		
		server.sendStringToClient(actualPhase, index);
		sendPlayersHand();
		actionPhase = new ActionPhase();
	}

	public void playCard(String message) {
		switch (actualPhase) {
		case "action":
			actionPhase.chosenCard(message, this.player);
			sendPlayersHand();
		case "buy":
			buyPhase = new BuyPhase();
			buyPhase.buyCard(message, this.player);
			sendPlayersHand();
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

	// This method should actualize the hand on the view (e.g. call it in buy phase
	// when u gave away a copper card)
	public void setHandView(Player player) {
		for (String o : player.hand)
			server.sendToClient(o);
	}

	// Run an Action
	public void runAction(String card) {
		actionPhase.chosenCard(card, this.player);
	}

	// Run a buying
	public void runBuy(String card) {
		buyPhase.buyCard(card, this.player);
	}

	public void runBuyPickCard(String card) {
		buyPhase.choseCardToBuy(card);
	}

	// start following phase if button on board view is clicked
	public void endPhase(String message) {
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
			break; // block this monitor
		}
	}

	public void sendPlayersHand() {
		String theHand = "hand.";
		for (String card : player.hand) {
			theHand = theHand.concat(card + ".");
		}
		theHand = theHand.substring(0, theHand.length() - 1);
		server.sendStringToClient(theHand, index);
		for (int i = 0; i < 1000000000; i++) {
			// get some time for the connection (time to send it)
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