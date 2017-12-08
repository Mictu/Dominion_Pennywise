package controllers;

import java.util.ArrayList;

import javafx.scene.control.Button;
import server.client.Client;
import view.Board_View;
import view.Lobby_View;

public class ClientHandler {
	Lobby_Controller lobbyC;
	
	Board_View boardview;
	public int opened;
	Lobby_View lobbyV;
	Client client;

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
			System.out.println("switchhhhhhhhhh");
			lobbyC.openBoardView();
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
	
	public int getOpened() {
		return this.opened;
	}
	
	public void setOpened(int opened) {
		this.opened = opened;
	}
}
