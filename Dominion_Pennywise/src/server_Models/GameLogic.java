package server_Models;

import java.util.ArrayList;
import java.util.Collections;

import server.client.Server;

public class GameLogic {

	// Initialize sector here
	protected ArrayList<Player> playerList = new ArrayList<Player>();

	protected ActionPhase actionPhase;
	protected BuyPhase buyPhase;
	protected CleanUpPhase cleanUpPhase;

	protected int countRounds;
	protected final int START_MONEY = 7;
	protected final int START_ESTATE = 3;
	
	protected String actualPhase;
	
	protected Server server;
	protected CleanUpPhase cleanPhase;
	protected TreasureCard tCard = new TreasureCard();
	protected VictoryCard vCard = new VictoryCard();

	// Constructor
	public GameLogic() {
		theGame();
	} // Close Constructor

	protected void gameStart(Player player) {
		for (int i = 0; i < START_MONEY; i++) {
			player.discard.add(tCard.getCopper());
		}

		for (int i = 0; i < START_ESTATE; i++) {
			player.discard.add(vCard.getEstate());
		}

		cleanPhase = new CleanUpPhase(player);
	}

	protected void theGame() {
		countRounds = 0;

		do {
			// Every Player does his Phases
			for (Player player : playerList) {
				if (countRounds == 0) {
					gameStart(player);
				}
				player.startRound();
				
				actualPhase = "action";
//				server.sendToClient(actualPhase);
				actionPhase = new ActionPhase(player);
				
				actualPhase = "buy";
//				server.sendToClient(actualPhase);
				buyPhase = new BuyPhase(player);

				actualPhase = "cleanup";
//				server.sendToClient(actualPhase);
				cleanUpPhase = new CleanUpPhase(player);
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
	
	// This method should actualize the hand on the view (e.g. call it in buy phase when u gave away a copper card)
	public void setHandView(Player player) {
		for (String o : player.hand)
			server.sendToClient(o);
	}
	
	
}

// Patrick