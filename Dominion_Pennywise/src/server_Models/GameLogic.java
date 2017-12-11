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
	
	protected String actualPhase = "action";
	protected int playerIndex;	
	
	ServerHandler serverHandler;
	Server server;
	CleanUpPhase cleanPhase;
	
	Player player;

	// Constructor
	public GameLogic() {
	} // Close Constructor

	protected void gameStart(Player player) {
		for (int i = 0; i < START_MONEY; i++) {
			player.discard.add("copper");							// don't forget to counter++
		}

		for (int i = 0; i < START_ESTATE; i++) {
			player.discard.add("estate");
		}
		cleanPhase = new CleanUpPhase(player);
	}

	public void theGame() {
		countRounds = 1;
		do {
			// Every Player does his Phases
			playerIndex = 0;
			for (Player player : Player.player) {
				this.player = player;
				if (countRounds == 1) {
					gameStart(this.player);
				}
				this.player.startRound();
				
				sendPlayersHand();
				
				actualPhase = "action";
				server.sendStringToClient(actualPhase, playerIndex);
				playerIndex++;
			}

			countRounds++;
			// Play as long until these options aren't true anymore
			// } while (countRounds <= 15 || Cards.CardType.Kingdom.Province > 0);
		} while (countRounds <= 15);
	}

	// get the actual phase to let the client know what cards can be pressed
	public String getPhase() {
		return this.actualPhase;
	}
	
	// get the player which is playing in this moment
	public Player getPlayer() {
		return this.player;
	}
	
	// This method should actualize the hand on the view (e.g. call it in buy phase when u gave away a copper card)
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
			buyPhase.buyCard(message, player);
			break;
		case "buy":
			actualPhase = "cleanup";
			cleanUpPhase = new CleanUpPhase(this.player);
			break;																// block this monitor
		}
	}
	
	public void sendPlayersHand() {
		for (String card : player.hand) 
			server.sendStringToClient("hand"+card, playerIndex);
		server.sendStringToClient("hand"+"end", playerIndex);
	}
	
	public String getActualPhase() {
		return this.actualPhase;
	}
	
	
}

// Patrick