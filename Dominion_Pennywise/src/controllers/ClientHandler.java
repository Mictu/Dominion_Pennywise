package controllers;

import java.util.ArrayList;
import javafx.application.Platform;
import javafx.stage.Stage;
import main_Class.ServiceLocator;
import server_Models.Translator;
import view.Board_View;
import view.Lobby_View;
import view.Result_View;

public class ClientHandler {
	static Lobby_Controller lobbyC;
	Login_Controller loginC;
	static Board_Controller boardC;
	static Result_Controller resultC;
	Result_View resultView;
	// CardDesign_View cdV;

	public static ArrayList<String> tempHandCard = new ArrayList<String>();

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
				boardC.deleteInfo();
			});
		} else if (message.equals("empty")) {
			Platform.runLater(() -> {
				boardview.setEmptyHand();
				boardC.deleteInfo();
			});
		} else if (message.contains("cardempty")) {
			String emptyCardN = message.substring(9);
			emptyCard(emptyCardN);
		} else {
			switch (message) {
			case "openboardview":
				Platform.runLater(() -> {
					openBoardView();
					boardview.setCards();
					boardview.changePhaseLabel();
					boardC.deleteInfo();
				});
				break;
			case "gameover":
				openResultView();
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
					boardC.deleteInfo();
				});
				break;
			case "cleanup":
				phase = "cleanup";
				Platform.runLater(() -> {
					boardC.deleteInfo();
					boardview.blockWindow();
				});
				break;
			}
		}
	}

	public static void getResultPoints(String[] resultsPlayerAndPoints) {
		for (String s : resultsPlayerAndPoints) {
			System.out.println(s);
		}
		resultC.showResultInView(resultsPlayerAndPoints);
	}

	public void emptyCard(String message) {
		System.out.println(message);
		String emptyCardN = message;
		Platform.runLater(() -> {
			boardview.turnCardBack(emptyCardN);
		});
	}

	public void openResultView() {
		boardview.stop();
		resultView = new Result_View(new Stage());
		resultC = new Result_Controller(resultView);
		resultView.start();
	}

	public static void openBoardView() {
		// lobbyV.stop();
		Lobby_View.stop();
		boardview = new Board_View(new Stage());
		boardC = new Board_Controller(boardview);
		boardview.start();
	}

	public void getWinPoints(String[] win) {
		String[] winPoints;
		boardC.clearWinPoints();
		for (String s : win) {
			winPoints = s.split("\\-");
			boardC.updateWinPoints(winPoints[0] + " " + t.getString("dominion.board.points.winpoints") + ": " + winPoints[1]);
		}
	}

	public void getABMpoints(String[] points) {
		int i = 0;
		boardC.clearABMpoints();
		for (String s : points) {
			if (i == 0) {
				boardC.updateABMpoints(t.getString("dominion.board.points.action") + " " + s);
			} else if (i == 1) {
				boardC.updateABMpoints(t.getString("dominion.board.points.buy") + " " + s);
			} else {
				boardC.updateABMpoints(t.getString("dominion.board.points.money") + " " + s);
			}
			i++;
		}
	}
	
	public void HandleCardAmount(String amounts) {
		String[] amount = amounts.split("\\.");
		for (int i = 0; i < amount.length; i++) {
			if (amount[i].equals("-1"))
				amount[i] = "0";
		}
		boardC.setCardCounters(amount);
	}

	public void HandleLoggerMsg(String loggerMsg) {
		String lMessage = loggerMsg;
		if ((lMessage.length() > 4) && lMessage.substring(0, 4).equals("name")) {
			boardC.showLoggerMsg(lMessage.substring(4) + " " + t.getString("dominion.board.logger.Nr1"));
		} else if (lMessage.equals("playaction")) {
			boardC.showLoggerMsg(">> " + t.getString("dominion.board.logger.Nr2"));
		} else if (lMessage.equals("playbuy")) {
			boardC.showLoggerMsg(">> " + t.getString("dominion.board.logger.Nr3"));
		} else if (lMessage.substring(0, 2).equals("ac")) {
			boardC.showLoggerMsg("++ " + t.getString("dominion.board.card." + lMessage.substring(2)) + " "
					+ t.getString("dominion.board.logger.Nr5"));
		} else if (lMessage.substring(0, 2).equals("bc")) {
			boardC.showLoggerMsg("++ " + t.getString("dominion.board.card." + lMessage.substring(2)) + " "
					+ t.getString("dominion.board.logger.Nr4"));
		} else {
			System.out.println("Fehler (ClientHandler / HandleLoggerMsg)");
		}
	}

	public void HandleInfoMessage(String loggerMessage) {
		String lMessage = loggerMessage;
		if (lMessage.equalsIgnoreCase("not enough money")) {
			boardC.showInfoMsg(t.getString("dominion.board.info.Nr1"));
		} else if (lMessage.equalsIgnoreCase("not enough action - points")) {
			boardC.showInfoMsg(t.getString("dominion.board.info.Nr2"));
		} else if (lMessage.equalsIgnoreCase("not enough buy - points")) {
			boardC.showInfoMsg(t.getString("dominion.board.info.Nr3"));
		} else if (lMessage.equalsIgnoreCase("no card selected")) {
			boardC.showInfoMsg(t.getString("dominion.board.info.Nr4"));
		} else if (lMessage.equalsIgnoreCase("card already picked")) {
			boardC.showInfoMsg(t.getString("dominion.board.info.Nr5"));
		} else if (lMessage.contains("cardischosen")) {
			boardC.showInfoMsg(t.getString("dominion.board.card." + lMessage.substring(12)) + " "
					+ t.getString("dominion.board.info.Nr6"));
		} else {
			System.out.println("Fehler (ClientHandler / HandleInfoMsg)");
		}
	}

}
