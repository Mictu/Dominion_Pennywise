package server.client;

import java.util.ArrayList;
import server_Models.GameLogic;
import server_Models.Player;

public class ServerHandler {

	GameLogic gameLogic;
	Player player;
	public ArrayList<String> handCards = new ArrayList<String>();
	
	public ServerHandler(GameLogic gl) {
		this.gameLogic = gl;
	}

	// Get Strings from Server
	public void getMessageFromServer(String msg) {
		String message = msg;

		// Create a new Player and add him to the playerList in the game logic class
		if (message.substring(0, 5).equals("lobby")) {
			player = new Player(message.substring(5));
			gameLogic.addPlayers(player);
		}

		System.out.println("is now on serverSide");
		
		if (message.equals("copper")) {
			System.out.println(message);
		}
		
		if (message.equals("silver")) {
			System.out.println(message);
		}
		
		if (message.equals("gold")) {
			System.out.println(message);
		}
		
		if (message.equals("estate")) {
			System.out.println(message);
		}
		
		if (message.equals("duchy")) {
			System.out.println(message);
		}
		
		if (message.equals("province")) {
			System.out.println(message);
		}
		
		if (message.equals("funfair")) {
			System.out.println(message);
		}
		
		if (message.equals("laboratory")) {
			System.out.println(message);
		}
		
		if (message.equals("market")) {
			System.out.println(message);
		}
		
		if (message.equals("smith")) {
			System.out.println(message);
		}
		
		if (message.equals("village")) {
			System.out.println(message);
		}
		
		if (message.equals("woodcutter")) {
			System.out.println(message);
		}
		
		
		
		
		
		
		
		
		
		
	}
	
	
	// Send Strings / ArrayLists to Server
	public void sendDeckToServer() {
		this.handCards = player.getHand();
	
	}
}
