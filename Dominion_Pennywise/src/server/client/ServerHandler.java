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
	public ArrayList<String> handCards = new ArrayList<String>();

	public ServerHandler() {
	}

	// Get Strings from Server
	public void getMessageFromServer(String msg) {
		String message = msg;
		phase = gamelogic.getPhase();

		// Create a new Player and add him to the playerList in the game logic
		// class
		if (message.length() > 5 && message.substring(0, 5).equals("lobby")) {
			player = new Player(message.substring(5));
			// gameLogic.addPlayers(player);
		}

		// Actions if treasure cards are pressed
		else if (message.equals("copper") || message.equals("silver") || message.equals("gold")) {
			if (phase.equalsIgnoreCase("buy") && message.substring(0, 4).equals("hand")) {				// set String on view side to hand.concat(card)
				gamelogic.runBuyPickCard(message.substring(4));
			}
			if (phase.equalsIgnoreCase("buy")) {
				gamelogic.runBuy(message);
			}
			if (phase.equalsIgnoreCase("action")) {
				gamelogic.runAction(message);
			} else {
				System.out.println("Card connot be played now");						// maybe set layout of some cards
			}
		}

		// Actions if victory cards are pressed
		else if (message.equals("estate") || message.equals("duchy") || message.equals("province")) {
			if (phase.equalsIgnoreCase("buy")) {
				gamelogic.runBuy(message);
			} else {
				System.out.println("Card connot be played now");
			}
		}

		// Actions if kingdom cards are pressed
		else if (message.equals("funfair") || message.equals("laboratory") || message.equals("market")) {
			if (phase.equalsIgnoreCase("buy")) {
				gamelogic.runBuy(message);
			} else {
				System.out.println("Card connot be played now");
			}
		}

		// Actions if kingdom cards are pressed
		else if (message.equals("smith") || message.equals("village") || message.equals("woodcutter")) {
			if (phase.equalsIgnoreCase("buy")) {
				gamelogic.runBuy(message);
			} else {
				System.out.println("Card connot be played now");
			}
		}
		
		// handle every other string from client
		else {
			switch (message) {
			case "endphase":
				gamelogic.endPhase();
				break;
				
			}
		}
		
		
	}
}
