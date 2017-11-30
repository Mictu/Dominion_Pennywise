package server.client;

import java.util.ArrayList;

import server_Models.Player;

public class ServerHandler {

	Player player;
	public ArrayList<String> handCards = new ArrayList<String>();

	public void getMessageFromServer(String msg) {
		String message = msg;

		if (message.substring(0, 5).equals("lobby")) {
			player = new Player(message.substring(5));
		}

	}

	public void sendDeckToServer() {
		this.handCards = player.getHand();
		
		
	}
}
