package controllers;

import java.util.ArrayList;

import javafx.scene.control.Button;
import server.client.Client;
import view.Board_View;
import view.Lobby_View;

public class ClientHandler {
	Client client;
	Lobby_View lobbyv;
	Lobby_Controller lobbyC;
	boolean openBV = false;
	Board_View boardview;

	String phase;
	ArrayList<Button> handCardList = new ArrayList<Button>();
	

	public void getMessageFromClient(String msg) {
		String message = msg;

		// Get message to set the player hand view
		if (message.length() > 4 && message.substring(0, 4).equals("hand")) {
			message = message.substring(4);
			boardview.setHand(message);
		}

		switch (message) {
		case "openboardview":
			openBV = true;
			break;
		case "buy":
			phase = "buy";
			break;
		case "action":
			phase = "action";
			break;
		}

	}

	public String getPhase() { // get the actual phases
		return this.phase;
	}
	
	public boolean getopenBV() {
		return openBV;
	}
}
