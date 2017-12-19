package controllers;

import java.util.ArrayList;
import java.util.Locale;

import javafx.application.Platform;
import javafx.stage.Stage;
import main_Class.ServiceLocator;
import server_Models.Translator;
import view.Board_View;
import view.Lobby_View;
import view.Login_View;

public class ClientHandler {
	static Lobby_Controller lobbyC;
	Login_Controller loginC;
	static Board_Controller boardController;
	Result_Controller resultC;

	public static ArrayList<String> tempHandCard = new ArrayList<String>();

	Login_View loginV;
	ServiceLocator sl = ServiceLocator.getServiceLocator();
	Translator t = sl.getTranslator();

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
		boardController.clearABMpoints();
		for (String s : points) {
			boardController.updateABMpoints(s);
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
				boardController.deleteInfo();
			});
		} else if (message.equals("empty")) {
			Platform.runLater(() -> {
				boardview.setEmptyHand();
				boardController.deleteInfo();
			});
		} else {
			switch (message) {
			case "openboardview":
				Platform.runLater(() -> {
					openBoardView();
					boardview.setCards();
					boardview.changePhaseLabel();
					boardController.deleteInfo();
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
					boardController.deleteInfo();
				});
				break;
			case "cleanup":
				phase = "cleanup";
				Platform.runLater(() -> {
					boardController.deleteInfo();
					boardview.blockWindow();
				});
				break;
			}
		}
	}

	public void HandleLoggerMsg(String loggerMsg) {
		String lMessage = loggerMsg;
		String sendMessage = "";
		String language = t.getCurrentLocale().getLanguage();

		if (lMessage.substring(0, 4).equals("name") && (lMessage.length() > 4)) {
			if (language.equalsIgnoreCase("de")) {
				sendMessage = "Spieler " + lMessage.substring(4) + " ist am Zug.";
			} else if (language.equalsIgnoreCase("en")) {
				sendMessage = "It's " + lMessage.substring(4) + "'s turn.";
			}
		} else if (lMessage.equals("playaction")) {
			if (language.equalsIgnoreCase("de")) {
				sendMessage = "Aktionsphase hat gestartet.";
			} else if (language.equalsIgnoreCase("en")) {
				sendMessage = "Actionphase has started.";
			}
		} else if (lMessage.equals("playbuy")) {
			if (language.equalsIgnoreCase("de")) {
				sendMessage = "Kaufphase hat gestartet.";
			} else if (language.equalsIgnoreCase("en")) {
				sendMessage = "Buyphase has started.";
			}
		} else if (lMessage.substring(0,2).equals("ac")) {
			if (language.equalsIgnoreCase("de")) {
				sendMessage = getGermanName(lMessage.substring(2))+" wurde gespielt";
			} else if (language.equalsIgnoreCase("en")) {
				sendMessage = lMessage.substring(2)+" has been played";
			}
		} else if (lMessage.substring(0,2).equals("bc")) {
			if (language.equalsIgnoreCase("de")) {
				sendMessage = getGermanName(lMessage.substring(2))+" wurde gekauft";
			} else if (language.equalsIgnoreCase("en")) {
				sendMessage = lMessage.substring(2)+" has been bought";
			}
		}
		boardController.showLoggerMsg("> " + sendMessage);
	}
	
	public String getGermanName(String cardPlayed) {
		String germanCard = cardPlayed;
		switch(germanCard){
		case "copper" : germanCard = "Kupfer"; break;
		case "silver" : germanCard = "Silber"; break;
		case "gold" : germanCard = "Gold"; break;
		case "estate" : germanCard = "Anwesen"; break;
		case "duchy" : germanCard = "Herzogtum"; break;
		case "province" : germanCard = "Provinz"; break;
		case "village" : germanCard = "Dorf"; break;
		case "woodcutter" : germanCard = "Holzfäller"; break;
		case "funfair" : germanCard = "Jahrmarkt"; break;
		case "laboratory" : germanCard = "Laboratorium"; break;
		case "market" : germanCard = "Markt"; break;
		case "smith" : germanCard = "Schmiede"; break;
		}
		return germanCard;
	}

	public static void openBoardView() {
		// lobbyV.stop();
		Lobby_View.stop();
		boardview = new Board_View(new Stage());
		boardController = new Board_Controller(boardview);
		boardview.start();
	}

	public void HandleInfoMessage(String loggerMessage) {
		String lMessage = loggerMessage;
		String sendMessage = "";
		String language = t.getCurrentLocale().getLanguage();
		
		if (language.equalsIgnoreCase("de")) {
			if (lMessage.equalsIgnoreCase("not enough money")) {
				sendMessage = "Nicht genug Geld";
				boardController.showInfoMsg(sendMessage);
			} else if (lMessage.equalsIgnoreCase("not enough action - points")) {
				sendMessage = "Nicht genug Aktionspunkte";
				boardController.showInfoMsg(sendMessage);
			} else if (lMessage.equalsIgnoreCase("not enough buy - points")) {
				sendMessage = "Nicht genug Kaufpunkte";
				boardController.showInfoMsg(sendMessage);
			} else if (lMessage.equalsIgnoreCase("no card selected")) {
				sendMessage = "Keine Karte ausgewählt";
				boardController.showInfoMsg(sendMessage);
			} else if (lMessage.equalsIgnoreCase("card already picked")) {
				sendMessage = "Karte bereits ausgewählt";
				boardController.showInfoMsg(sendMessage);
			}
		} else {
			boardController.showInfoMsg(lMessage);
		}
	}

}
