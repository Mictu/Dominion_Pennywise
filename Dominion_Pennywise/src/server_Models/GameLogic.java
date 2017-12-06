package server_Models;

import java.util.ArrayList;
import java.util.Collections;

import server.client.Server;
import server.client.ServerHandler;

public class GameLogic {

	// Initialize sector here
	protected ArrayList<Player> playerList = new ArrayList<Player>();

	protected CleanUpPhase cleanUpPhase;
	ActionPhase actionPhase;
	BuyPhase buyPhase;
	
	protected int countRounds;
	protected final int START_MONEY = 7;
	protected final int START_ESTATE = 3;
	
	protected String actualPhase = "action";
	
	ServerHandler serverHandler;
	protected Server server;
	protected CleanUpPhase cleanPhase;
	
	Player player;

	// Constructor
	public GameLogic() {
		actionPhase = new ActionPhase();
		buyPhase = new BuyPhase();
		theGame();
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

	protected void theGame() {
		countRounds = 1;

		do {
			// Every Player does his Phases
			for (Player player : playerList) {
				this.player = player;
				if (countRounds == 1) {
					gameStart(player);
				}
				player.startRound();
				
				actualPhase = "action";
				System.out.println(actualPhase);
				server.sendToClient(actualPhase);
			}

			countRounds++;
			// Play as long until these options aren't true anymore
			// } while (countRounds <= 15 || Cards.CardType.Kingdom.Province > 0);
		} while (countRounds <= 15);
	}

	// Add Players to playerList
	public void addPlayers(Player newPlayer) {
		playerList.add(newPlayer);
		Collections.shuffle(playerList); // Random StartList
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
			server.sendToClient("hand"+card);
		server.sendToClient("end");
	}
	
	public String getActualPhase() {
		return this.actualPhase;
	}
	
	
}

// Patrick