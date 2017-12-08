package server.client;

import java.util.ArrayList;

import server_Models.ActionPhase;
import server_Models.BuyPhase;
import server_Models.CleanUpPhase;
import server_Models.GameLogic;
import server_Models.Player;

public class ServerHandler {

	BuyPhase buyphase;
	ActionPhase actionphase;
	CleanUpPhase cleanupphase;

	GameLogic gamelogic;
	String phase;
	Player player;
	Client_Chat ClientC;

	public ArrayList<String> handCards = new ArrayList<String>();

	public ServerHandler() {
	}
	
	public Player addPlayerToList(String name) {
		Player.players.add(name);
		int count = 0;
		for(String s : Player.players) {
			player = new Player(s);
			if(count == 0) {
				player.turn = true;
			}
			System.out.println(s);
			count++;
		}
		
		return player;
	}
	
	public String getFirstPlayerName() {
		
		return Player.players.get(0);
			
//			player = new Player(s);
////			if(s.equals(Player.players.get(0))) {
////				player.turn = true;
////			}
//		return player;
	}
	
//	public Player getPlayer(String name) {
//		for(String s : Player.players) {
//			if(s.equals(name)) {
//				
//			}
//		}
//		return player;
//	}
//	
//	public Player setPlayer(Player player) {
//		if(player.turn == true) {
//			return player;
//		}else {
//			return null;
//		}
//	}
	
	// Get Strings from Server
	public void getMessageFromServer(String msg) {
		String message = msg;
		gamelogic = new GameLogic();
		actionphase = new ActionPhase();
		String phase = gamelogic.getActualPhase();
		System.out.println(phase);

		if (message.contains("endphase")) {
			gamelogic.endPhase(message);
		}
		
		switch (phase) {
		case "action":
			actionphase.chosenCard(message, player);
		case "buy":
			buyphase.buyCard(message, player);
		}


		// switch (message) {
		// case "funfair":
		// System.out.println(message);
		// case "laboratory":
		// System.out.println(message);
		// case "market":
		// System.out.println(message);
		// case "smith":
		// System.out.println(message);
		// case "village":
		// System.out.println(message);
		// case "woodcutter":
		// System.out.println(message);
		// }
		// // phase = gamelogic.getPhase();
		//
		// // Create a new Player and add him to the playerList in the game logic class
		// if (message.length() > 5 && message.substring(0, 5).equals("lobby")) {
		// player = new Player(message.substring(5));
		// // gameLogic.addPlayers(player);
		// }
		//
		// // Actions if treasure cards are pressed
		// else if (message.contains("copper") || message.contains("silver") ||
		// message.contains("gold")) {
		// // getOptionsTreasure(message);
		// }
		//
		// // Actions if victory cards are pressed
		// else if (message.equals("estate") || message.equals("duchy") ||
		// message.equals("province")) {
		// // getOptionsVictory(message);
		// }
		//
		// // Actions if kingdom cards are pressed
		// else if (message.equals("funfair") || message.equals("laboratory") ||
		// message.equals("market")) {
		// // getOptionsKingdom(message);
		// }
		//
		// // Actions if kingdom cards are pressed
		// else if (message.equals("smith") || message.equals("village") ||
		// message.equals("woodcutter")) {
		// // getOptionsKingdom(message);
		// }
		//
		// // handle every other string from client
		// else {
		// switch (message) {
		// case "endphase":
		// gamelogic.endPhase();
		// break;
		// case "pay":
		//
		// break;
		// case "bonusmoney":
		// System.out.println("blablablablablablabla");
		// break;
		//
		// }
		// }
	}

	// only do something when it makes sense for the clicked card - treasure
	public void getOptionsTreasure(String message) {
		if (phase.equalsIgnoreCase("buy") && message.substring(0, 4).equalsIgnoreCase("hand")) { // set String on view
																									// side to
																									// hand.concat(card)
			gamelogic.runBuy(message);
		} else if (phase.equalsIgnoreCase("buy")) {
			gamelogic.runBuyPickCard(message.substring(4));
		} else {
			System.out.println("Card can not be played now"); // maybe set layout of some cards
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
