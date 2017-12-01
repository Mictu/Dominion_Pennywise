package server.client;

import java.util.ArrayList;

import server_Models.ActionPhase;
import server_Models.BuyPhase;
import server_Models.CleanUpPhase;
import server_Models.GameLogic;
import server_Models.Player;

public class ServerHandler {

	ActionPhase actionphase;
	BuyPhase buyphase;
	CleanUpPhase cleanup;

	String phase;
	Player player;
	public ArrayList<String> handCards = new ArrayList<String>();

	public ServerHandler(String phase, Player player) {
		this.phase = phase;
		this.player = player;
		this.actionphase = new ActionPhase(player);
		this.buyphase = new BuyPhase(player);
		this.cleanup = new CleanUpPhase(player);
	}

	// Get Strings from Server
	public void getMessageFromServer(String msg) {
		String message = msg;

		// Create a new Player and add him to the playerList in the game logic class
		if (message.substring(0, 5).equals("lobby")) {
			player = new Player(message.substring(5));
			// gameLogic.addPlayers(player);
		}

		// Actions if treasure cards are pressed
		else if (message.equals("copper") || message.equals("silver") || message.equals("golden")) {
			if (phase.equalsIgnoreCase("buy")) {

			} else {
				System.out.println("Card connot be played now");
			}
		}

		// Actions if victory cards are pressed
		else if (message.equals("estate") || message.equals("duchy") || message.equals("province")) {
			System.out.println(message);
			if (phase.equalsIgnoreCase("buy")) {

			} else {
				System.out.println("Card connot be played now");
			}
		}

		// Actions if kingdom cards are pressed
		else if (message.equals("funfair") || message.equals("laboratory") || message.equals("market")) {
			if (phase.equalsIgnoreCase("buy")) {

			} else {
				System.out.println("Card connot be played now");
			}
		}

		// Actions if kingdom cards are pressed
		else if (message.equals("smith") || message.equals("village") || message.equals("woodcutter")) {
			if (phase.equalsIgnoreCase("buy")) {

			} else {
				System.out.println("Card connot be played now");
			}
		}

		// if String cannot be utilized
		else {
			System.out.println("message konnte nicht verwertet werden:");
			System.out.println(message);
		}

	}

	public void startActionPhase(String card) {
		this.actionphase.chosenCard(card);
	}

	public void startBuyPhase(String card) {

		this.buyphase.buyCard(card);
	}

	public void startCleanUpPhase() {
		this.cleanup.;
	}

	// Send Strings / ArrayLists to Server
	public void sendDeckToServer() {
		this.handCards = player.getHand();

	}
}
