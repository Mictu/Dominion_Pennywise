package controllers;

import java.util.ArrayList;
import javafx.application.Platform;
import javafx.stage.Stage;
import main_Class.ServiceLocator;
import server_Models.Translator;
import view.Board_View;
import view.Lobby_View;
import view.Result_View;

/**
 * Main Controller Class for the client side. Handles all the messages from the
 * server which are sent to the clients. the ClientHandler is a Controller Class
 * and with the Server Handler together, they are the center nodes of this IT
 * Project.
 * 
 * @author Michael Tu
 * @author Patrick Ziörjen
 * @author Sojo Nagaroor
 * @author Yujia Shi
 *
 */
public class ClientHandler {
	static Lobby_Controller lobbyC;
	Login_Controller loginC;
	static Board_Controller boardC;
	static Result_Controller resultC;
	Result_View resultView;
	Lobby_View lobbyV;
	static Board_View boardview;
	ServiceLocator sl = ServiceLocator.getServiceLocator();
	Translator t = sl.getTranslator();

	static String message;
	public static String phase = "action";

	public static ArrayList<String> tempHandCard = new ArrayList<String>();

	/**
	 * Initialize the lobby controller for ClientHandler
	 * 
	 * @author Yujia Shi
	 * @param lobbyView
	 *            - the correct lobbyView from Login_Controller
	 * @return -
	 */
	public void initializeLobbyController(Lobby_View lobbyView) {
		this.lobbyV = lobbyView;
		lobbyC = new Lobby_Controller(this.lobbyV);
	}

	/**
	 * Get the names of the clients from the client class after they connected
	 * to the server. Display it on the Lobby_View in a list
	 * 
	 * @author Michael Tu
	 * @param clientNames
	 *            - an Array with the names in -> Type String
	 * @return -
	 */
	public static void getNamesFormClient(String[] clientNames) {
		lobbyC.clearConnectedP();
		for (String s : clientNames) {
			lobbyC.updateConnectedPlayers(s);
		}
	}

	/**
	 * Method to handle all the messages that are received by the client. This
	 * method opens views after receving specific messages from the server. This
	 * method refresh cards after receving specific messages. This method update
	 * the infos on Board_View after receving specific messages. This method
	 * switch phases on client side after reciving specific messages. This
	 * method close views after receving specific messages.
	 * 
	 * @author Michael Tu
	 * @author Patrick Ziörjen
	 * @author Sojo Nagaroor
	 * @author Yujia Shi
	 * 
	 * @param msg
	 *            - message from the server
	 * @return -
	 */
	public void getMessageFromClient(String msg) {
		message = msg;
		// Get message to set the players hand
		if (message.length() > 4 && message.substring(0, 4).equals("hand")) {
			message = message.substring(5);

			String[] handCards = message.split("\\.");

			for (String s : handCards) {
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

	/**
	 * Send Resultpoints to Result_Controller to display it on Result_View
	 * 
	 * @author Sojo Nagaroor
	 * @param resultsPlayerAndPoints
	 *            - Array with Player and Winpoints
	 * @return -
	 */
	public void sendResultPointsToDisplay(String[] resultsPlayerAndPoints) {
		resultC.showResultInView(resultsPlayerAndPoints);
	}

	/**
	 * Send the empty cardpile to Board_View to turn back the cardpile if the
	 * pile is empty.
	 * 
	 * @author Yujia Shi
	 * @param emptyCardName
	 *            - String with the cardpile name which is empty
	 * @return -
	 */
	public void emptyCard(String emptyCardName) {
		String emptyCardN = emptyCardName;
		Platform.runLater(() -> {
			boardview.turnCardBack(emptyCardN);
		});
	}

	/**
	 * Open the Result_View and close the Board_View
	 * 
	 * @author Sojo Nagaroor
	 * @param -
	 * @return -
	 */
	public void openResultView() {
		boardview.stop();
		resultView = new Result_View(new Stage());
		resultC = new Result_Controller(resultView);
		resultView.start();
	}

	/**
	 * Open the Board_View and close the Lobby_View
	 * 
	 * @author Michael Tu
	 * @param -
	 * @return -
	 */
	public static void openBoardView() {
		Lobby_View.stop();
		boardview = new Board_View(new Stage());
		boardC = new Board_Controller(boardview);
		boardview.start();
	}

	/**
	 * Split the Array with the winpoints and update the Board_Controller to
	 * display it on Board_View.
	 * 
	 * @author Michael Tu
	 * @param winP
	 *            - String Array contains winpoints from the server after a game
	 *            finished
	 * @return -
	 */
	public void getWinPoints(String[] winP) {
		String[] winPoints;
		boardC.clearWinPoints();
		for (String s : winP) {
			winPoints = s.split("\\-");
			boardC.updateWinPoints(
					winPoints[0] + " " + t.getString("dominion.board.points.winpoints") + ": " + winPoints[1]);
		}
	}

	/**
	 * Update the Action-, Buypoints and Money of each player. Send the correct
	 * Points to the correct view element of Board_View
	 * 
	 * @author Michael Tu
	 * 
	 * @param abmPoints
	 *            - String Array contains Action-, Buypoints and Money from the
	 *            server
	 * @return -
	 */
	public void getABMpoints(String[] abmPoints) {
		int i = 0;
		boardC.clearABMpoints();
		for (String s : abmPoints) {
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

	/**
	 * Send the amount of rounds to Board_Controller to display it on Board_View
	 * 
	 * @author Patrick Ziörjen
	 * @param roundAmount
	 *            - amount of rounds that a player played. From server
	 * @return -
	 */
	public void showRoundCounter(String roundAmount) {
		boardC.showRoundCounter(t.getString("round.board") + " " + roundAmount + " / 30");
	}

	/**
	 * Handle the amount of cards that are left on the cardpile after every card
	 * that a player bought. Split the String from server.
	 * 
	 * @author Patrick Ziörjen
	 * @param cardAmount
	 *            - get the left amount of cards on a cardpile from the server
	 * @return -
	 */
	public void HandleCardAmount(String cardAmount) {
		String[] amount = cardAmount.split("\\.");
		for (int i = 0; i < amount.length; i++) {
			if (amount[i].equals("-1"))
				amount[i] = "0";
		}
		boardC.setCardCounters(amount);
	}

	/**
	 * Handle the logger message from the server. Check which message it is and
	 * send it to Board_Controller so Board_Controller can send it to Board_View
	 * to display it in the Logger box
	 * 
	 * @author Yujia Shi
	 * @param loggerMsg
	 *            - message from the server after specific actions from a player
	 * @return -
	 */
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

	/**
	 * Handle the info message from the server. Check which message it is and
	 * send it to Board_Controller so Board_Controller can send it to Board_View
	 * to display it in the Logger box
	 * 
	 * @author Yujia Shi
	 * @param infoMessage
	 *            - message from the server after specific actions from a player
	 * @return -
	 */
	public void HandleInfoMessage(String infoMessage) {
		String lMessage = infoMessage;
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
