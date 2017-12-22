package server.client;

import java.util.ArrayList;
import server_Models.ActionPhase;
import server_Models.BuyPhase;
import server_Models.CleanUpPhase;
import server_Models.GameLogic;
import server_Models.Player;

/**
 * Handle the messages on the server side and send messages to client or receive
 * message from client
 * 
 * @author Yujia Shi
 *
 */
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
	int playerCounter = 1;

	public ArrayList<String> handCards = new ArrayList<String>();
	public ArrayList<String> playerName = new ArrayList<String>();

	public ServerHandler(Server server) {
		this.server = server;
	}

	/**
	 * get Playername from Server, create a new player and add player to the player
	 * list. check if the playername is already use. if it is already used, add a
	 * name addition to the name.
	 * 
	 * @author Yujia Shi
	 * 
	 * @param name
	 *            - playername from server
	 */
	public void addPlayerToList(String name) {
		for (Player player : Player.player) {
			playerName.add(player.getName());
		}

		if (playerName.contains(name)) {
			player = new Player(name + playerCounter);
			Player.player.add(player);
			playerCounter++;
		} else {
			player = new Player(name);
			Player.player.add(player);
		}
	}

	/**
	 * handle messages from server and start the gamelogic. gives the data to the
	 * gamelogic
	 * 
	 * @author Yujia Shi
	 * @param msg
	 *            - message from server
	 */
	public void getMessageFromServer(String msg) {
		message = msg;

		if (message.equals("start")) {
			if (Player.player.size() > 1 && Player.player.size() < 5) {
				server.sendToClient("openboardview");
				gamelogic = new GameLogic(this.server);
				gamelogic.theGame();
			}
		}

		else if (message.contains("endphase")) {
			gamelogic.endPhase();
		} else {
			gamelogic.playCard(message);
		}
	}

}
