package controllers;

import java.util.ArrayList;
import javafx.application.Platform;
import javafx.stage.Stage;
import view.Board_View;
import view.Lobby_View;
import view.Login_View;

public class ClientHandler {
	static Lobby_Controller lobbyC;
	Login_Controller loginC;
	static Board_Controller boardC;
	Result_Controller resultC;

	public static ArrayList<String> tempHandCard = new ArrayList<String>();

	Login_View loginV;

	static String message;
	static Board_View boardview;
	Lobby_View lobbyV;

	public static String phase = "action";

	public void initializeLobbyController(Lobby_View lobbyView) {
		this.lobbyV = lobbyView;
		lobbyC = new Lobby_Controller(this.lobbyV);
	}

	public static void getNamesFormClient(String[] names) {
		lobbyC.clearConnectedP();
		for (String s : names) {
			lobbyC.updateConnectedPlayers(s);
		}
	}
	public static void getABMpoints(String[] points) {
		boardC.clearABMpoints();
		for(String s : points) {
			boardC.updateABMpoints(s);
		}
	}
	

	public void getMessageFromClient(String msg) {
		message = msg;
		// Get message to set the player hand view
		if (message.length() > 4 && message.substring(0, 4).equals("hand")) {
			message = message.substring(5);

			String[] laHand = message.split("\\.");

			for (String s : laHand) {
				tempHandCard.add(s);
			}
			Platform.runLater(() -> {
				boardview.setHand();
			});
		} else if (message.equals("empty")) {
				Platform.runLater(() -> {
					boardview.setEmptyHand();
				});
		} else {
			switch (message) {
			case "openboardview":
				Platform.runLater(() -> {
					openBoardView();
					boardview.setCards();
					boardview.changePhaseLabel();
				});
				break;
			case "buy":
				phase = "buy";
				break;
			case "action":
				phase = "action";
				Platform.runLater(() -> {
					boardview.setCards();
					boardview.enableWindow();
					boardview.changePhaseLabel();
				});
				break;
			case "cleanup":
				phase = "cleanup";
				Platform.runLater(() -> {
					boardview.blockWindow();
				});
				break;
			}
		}
	}
	
	

	public static void openBoardView() {
		// lobbyV.stop();
		Lobby_View.stop();
		boardview = new Board_View(new Stage());
		boardC = new Board_Controller(boardview);
		boardview.start();
	}

}
