package server_Models;

import java.util.ArrayList;
import java.util.Collections;

public class GameLogic {

	// Initialize sector here
	protected ArrayList<Player> playerList = new ArrayList<Player>();
	
	protected ActionPhase actionPhase;
	protected BuyPhase buyPhase;
	protected CleanUpPhase cleanUpPhase;
	
	protected int countRounds;
	
	// Constructor
	protected GameLogic() {
		theGame();
	} // Close Constructor
	
	// The GameLogic
	protected void theGame() {
		countRounds = 0;
		
		do {
			// Every Player does his Phases
			for (Player player: playerList) {
				player.startRound();
				actionPhase = new ActionPhase(player);
				buyPhase = new BuyPhase(player);
				cleanUpPhase = new CleanUpPhase(player);
			}
			
		countRounds++;	
		// Play as long until these options aren't true anymore
		} while (countRounds <= 15 || Cards.CardType.Kingdom.Province > 0);
	}
	
	// Add Players to playerList
	protected void addPlayers(Player newPlayer) {
		playerList.add(newPlayer);
		Collections.shuffle(playerList);		// Random StartList
	}
	
} // Close Class


// Patrick