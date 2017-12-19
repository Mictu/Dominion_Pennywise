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

	static GameLogic gamelogic;
	String phase;
	Player player;
	Client_Chat ClientC;
	Server server;
	public String message;

	public ArrayList<String> handCards = new ArrayList<String>();

	public ServerHandler(Server server) {
		this.server = server;
	}

	public void addPlayerToList(String name) {
		player = new Player(name);
		Player.player.add(player);
		// Collections.shuffle(Player.player);
	}

	// Get Strings from Server
	public void getMessageFromServer(String msg) {
		message = msg;

		if (message.equals("start")) {
			if (Player.player.size() > 1 && Player.player.size() < 3) {
				server.sendToClient("openboardview");
				gamelogic = new GameLogic(this.server);
				gamelogic.theGame();
			}
//			System.out.println("Not enough player");
		}
			
		else if (message.contains("endphase")) {
			gamelogic.endPhase();
		} else {
			gamelogic.playCard(message);
		}
	}

}
