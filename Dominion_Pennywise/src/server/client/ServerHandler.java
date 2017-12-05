package server.client;

import java.util.ArrayList;

import server_Models.ActionPhase;
import server_Models.BuyPhase;
import server_Models.GameLogic;
import server_Models.Player;

public class ServerHandler {
	
	BuyPhase buyphase;
	ActionPhase actionphase;
	
	GameLogic gamelogic;
	String phase;
	Player player;
	Client_Chat ClientC;
	
	public ArrayList<String> handCards = new ArrayList<String>();

	public ServerHandler() {
	}
	

	// Get Strings from Server
	public void getMessageFromServer(String msg) {
		String message = msg;

//		phase = gamelogic.getPhase();

		// Create a new Player and add him to the playerList in the game logic class
		if (message.length() > 5 && message.substring(0, 5).equals("lobby")) {
			player = new Player(message.substring(5));
			// gameLogic.addPlayers(player);
		}
		
		// Actions if treasure cards are pressed
		else if (message.contains("copper") || message.contains("silver") || message.contains("gold")) {
			System.out.println("Server: Cash clicked");
//			getOptionsTreasure(message);

		}

		// Actions if victory cards are pressed
		else if (message.equals("estate") || message.equals("duchy") || message.equals("province")) {
//			getOptionsVictory(message);
		}

		// Actions if kingdom cards are pressed
		else if (message.equals("funfair") || message.equals("laboratory") || message.equals("market")) {
//			getOptionsKingdom(message);
		}

		// Actions if kingdom cards are pressed
		else if (message.equals("smith") || message.equals("village") || message.equals("woodcutter")) {
//			getOptionsKingdom(message);
		}
		
		// handle every other string from client
		else {
			switch (message) {
			case "endphase":
				gamelogic.endPhase();
				break;
			case "pay":
				
				break;
			case "bonusmoney":
				System.out.println("blablablablablablabla");
				break;
				
				
			}
		}
	}
	
	
	
	
	// only do something when it makes sense for the clicked card - treasure
	public void getOptionsTreasure(String message) {
		if (phase.equalsIgnoreCase("buy") && message.substring(0, 4).equalsIgnoreCase("hand")) {		// set String on view side to hand.concat(card)
			gamelogic.runBuy(message);
		} else if (phase.equalsIgnoreCase("buy")) {
			gamelogic.runBuyPickCard(message.substring(4));
		} else {
			System.out.println("Card can not be played now");											// maybe set layout of some cards
		}
	}
	
	// only do something when it makes sense for the clicked card - kingdom
	public void getOptionsKingdom(String message) {
		if (phase.equalsIgnoreCase("action")) {
			gamelogic.runAction(message);
		} else if (phase.equalsIgnoreCase("buy")) {
			gamelogic.runBuyPickCard(message);
		} else {
			System.out.println("Card can not be played now");
		}
	}
	
	// only do something when it makes sense for the clicked card - victory
	public void getOptionsVictory(String message) {
		if (phase.equalsIgnoreCase("buy")) {
			gamelogic.runBuyPickCard(message);
		} else {
			System.out.println("Card can not be played now");
			}
	}
		
		
	
}
