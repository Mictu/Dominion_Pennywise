package controllers;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import server.client.Client;
import view.Board_View;
import view.Lobby_View;
import view.Login_View;

public class ClientHandler {
	static Client client;
	Lobby_Controller lobbyC;
	Login_Controller loginC;
	Board_Controller boardC;
	Result_Controller resultC;

	Login_View loginV;

	static String message;
	static Board_View boardview;
	Lobby_View lobbyV;

	public static String phase;
	ArrayList<Button> handCardList = new ArrayList<Button>();

	public void initializeLobbyController(Lobby_View lobbyView, Client client2) {
		this.lobbyV = lobbyView;
		ClientHandler.client = client2;
		lobbyC = new Lobby_Controller(this.lobbyV, ClientHandler.client);
	}

	public static void getMessageFromClient(String msg) {
		message = msg;
		System.out.println(message);

		// Get message to set the player hand view
		if (message.length() > 4 && message.substring(0, 4).equals("hand")) {
			message = message.substring(4);
			Platform.runLater(() -> {
				boardview.setHand(message);
			});
		}

		switch (message) {
		case "openboardview":
			Platform.runLater(() -> {
				openBoardView();
			});

			break;
		case "buy":
			phase = "buy";
			break;
		case "action":
			phase = "action";
			break;
		}

	}

	public static void openBoardView() {
		// lobbyV.stop();
		Lobby_View.stop();
		boardview = new Board_View(new Stage(), client);
		Board_Controller boardController = new Board_Controller(boardview, client);
		boardview.start();
	}

}
