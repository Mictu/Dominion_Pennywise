package server.client;

import server_Models.Player;

public class ServerHandler {

	Player player;

	public void getMessageFromServer(String msg) {
		String message = msg;

		if (message.substring(0, 5).equals("lobby")) {
			player = new Player(message.substring(5));
		}

	}

}
