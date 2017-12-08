package controllers;

import java.util.ArrayList;

import javafx.scene.control.Button;
import server.client.Client;
import view.Board_View;
import view.Lobby_View;
import view.Login_View;

public class ClientHandler {
	Lobby_Controller lobbyC;
	Login_Controller loginC;
	Board_Controller boardC;
	Result_Controller resultC;
	
	Login_View loginV;
	
	public boolean openBV = false;
	Board_View boardview;
	Lobby_View lobbyV;
	Client client;
	

	String phase;
	ArrayList<Button> handCardList = new ArrayList<Button>();
	

	public void initializeLobbyController(Lobby_View lobbyView, Client client2) {
		this.lobbyV = lobbyView;
		this.client = client2;
		lobbyC = new Lobby_Controller(this.lobbyV, this.client);
	}

	public void getMessageFromClient(String msg) {
		String message = msg;

		// Get message to set the player hand view
		if (message.length() > 4 && message.substring(0, 4).equals("hand")) {
			message = message.substring(4);
			boardview.setHand(message);
		}
		
		switch (message) {
		case "openboardview":
			openField();
			break;
		case "buy":
			phase = "buy";
			break;
		case "action":
			phase = "action";
			break;
		}

	}

	public void openField() {
		lobbyC.openBoardView();
	}

	public String getPhase() { // get the actual phases
		return this.phase;
	}
	
	public boolean getopenBV() {
		return openBV;
	}
}
