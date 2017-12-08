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

	public void addPlayerToList(String name) {
		Player.players.add(name);
		player = new Player(name);
		for (String s: Player.players) {
			System.out.println(s);
		}
	}

	public String getFirstPlayerName() {
		return Player.players.get(0);
	}

	// public Player getPlayer(String name) {
	// for(String s : Player.players) {
	// if(s.equals(name)) {
	//
	// }
	// }
	// return player;
	// }
	//
	// public Player setPlayer(Player player) {
	// if(player.turn == true) {
	// return player;
	// }else {
	// return null;
	// }
	// }

	// Get Strings from Server
	public void getMessageFromServer(String msg) {
		String message = msg;
		gamelogic = new GameLogic();
		actionphase = new ActionPhase();
		String phase = gamelogic.getActualPhase();

		if (message.contains("endphase")) {
			gamelogic.endPhase(message);
		}

		switch (phase) {
		case "action":
			actionphase.chosenCard(message, player);
		case "buy":
			buyphase.buyCard(message, player);
		}
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
