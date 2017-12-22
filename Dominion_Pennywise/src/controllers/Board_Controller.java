package controllers;

import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import view.Board_View;
import view.CardDesign_View;
import view.Lobby_View;

/**
 * Controller Class for Board_View Manage the elements from Board_View. Get
 * Resources from ClientHandler and use it for Board_View to show the game
 * field. Handles the setOnActions from Board_View ABM = Actionpoints, Buypoints
 * and Money
 * 
 * @author Michael Tu
 * @author Patrick Ziörjen
 */
public class Board_Controller {

	Lobby_View lobbyView;
	Board_View boardView;
	CardDesign_View cardView;

	/**
	 * Constructor from Board_Controller Contains setOnActions for the elements from
	 * Board_View Tell the program what it should do after clicking an element on
	 * Board_View
	 * 
	 * @param boardView
	 *            - get the correct boardView from ClientHandler to handle the view
	 * @author Patrick Ziörjen
	 * @return -
	 */
	public Board_Controller(Board_View boardView) {
		this.boardView = boardView;

		// setOnAction for button endPhase
		boardView.endPhase.setOnAction((Event) -> {
			Login_Controller.client.sendToServer("endphase");
		});

		// Send chat message if the player clicks on "enter"
		boardView.chatText.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
			if (event.getCode() == KeyCode.ENTER) {
				boardView.send.fire();
			}
		});

		// setOnActon for the chat send button
		boardView.send.setOnAction(event -> {
			if (!boardView.chatText.getText().trim().isEmpty()) {
				Login_Controller.client.sendChatMessage(boardView.chatText.getText());
				boardView.chatText.clear();
				Button_Sounds.playSendSound();
			}
		});

		Login_Controller.client.newestMessage
				.addListener((o, oldValue, newValue) -> boardView.chat.appendText(newValue + "\n"));

		// Close the window and disconnect from the server if the client close the
		// window
		boardView.stage.setOnCloseRequest(event -> Login_Controller.client.disconnectClient());
	}

	/**
	 * Update the Action-, Buypoints and money for every player after one action
	 * Update the Board_View with the actual player stats
	 * 
	 * @author Michael Tu
	 * @param points
	 *            - get a String with stats from the server for every player
	 * @return -
	 */
	public void updateABMpoints(String points) {
		Platform.runLater(() -> {
			if (boardView.aBMpoints.getText().equals("")) {
				boardView.aBMpoints.setText(points);
			} else {
				boardView.aBMpoints.setText(boardView.aBMpoints.getText() + "\n" + points);
			}
		});
	}

	/**
	 * Clear the ABM Points and reset them to an empty String
	 * 
	 * @author Patrick Ziörjen
	 * @param -
	 * @return -
	 */
	public void clearABMpoints() {
		Platform.runLater(() -> {
			boardView.aBMpoints.setText("");
		});
	}

	/**
	 * Clear the winpoints and reset them to an empty String
	 * 
	 * @author Patrick Ziörjen
	 * @param -
	 * @return -
	 */
	public void clearWinPoints() {
		Platform.runLater(() -> {
			boardView.playerStats.setText("");
		});
	}

	/**
	 * Update the winpoints for every player after one action Update the Board_View
	 * with the actual player stats
	 * 
	 * @author Patrick Ziörjen
	 * @param score
	 *            - contains the winpoint from a player
	 * @return -
	 */
	public void updateWinPoints(String score) {
		Platform.runLater(() -> {
			if (boardView.playerStats.getText().equals("")) {
				boardView.playerStats.setText(score);
			} else {
				boardView.playerStats.setText(boardView.playerStats.getText() + "\n" + score);
			}
		});
	}

	/**
	 * Handle the Logger Message in infobox and send the logger message to boardview
	 * to display it in the correct box
	 * 
	 * @author Patrick Ziörjen
	 * @param loggerMessage
	 *            - logger message from the server that are generated after specific
	 *            actions
	 * @return -
	 */
	public void showLoggerMsg(String loggerMessage) {
		Button_Sounds.playLoggerSound();
		Platform.runLater(() -> {
			if (boardView.logger.getText().equals("")) {
				boardView.logger.appendText(loggerMessage);
			} else {
				boardView.logger.appendText("\n" + loggerMessage);
			}
		});
	}

	/**
	 * Handle the Info Messages in the infobox for player after specific actions and
	 * send the info message to boardview to display it in the correct box
	 * 
	 * @author Patrick Ziörjen
	 * @param infoMessage
	 *            - info message from the server that are generated after specific
	 *            actions
	 * @return -
	 */
	public void showInfoMsg(String infoMessage) {
		Platform.runLater(() -> {
			boardView.info.setText(infoMessage);
		});
	}

	/**
	 * Delete the last info message and set the info message to an empty String
	 * 
	 * @author Patrick Ziörjen
	 * @param -
	 * @return -
	 */
	public void deleteInfo() {
		Platform.runLater(() -> {
			boardView.info.setText("");
		});
	}

	/**
	 * Show the info to every card. Because of the bad quality of card images the
	 * stats of every card are display right in the center of the game field
	 * 
	 * @author Patrick Ziörjen
	 * @param cardInfo
	 *            - Informations to a card from the server
	 * @return -
	 */
	public static void showCard(String cardInfo) {
		Platform.runLater(() -> {
			Board_View.cardInfo.setText(cardInfo);
		});
	}

	/**
	 * Delete the last card info set the infobox to an empty String
	 * 
	 * @author Patrick Ziörjen
	 * @param -
	 * @return -
	 */
	public static void deleteCardInfo() {
		Platform.runLater(() -> {
			Board_View.cardInfo.setText("");
		});
	}

	/**
	 * Counters for a cardpile. Count the cards on the pile and send them to
	 * Board_View to display the amount of card left
	 * 
	 * @author Patrick Ziörjen
	 * @param cardLeft
	 *            - the amount of card left
	 * @return -
	 */
	public void setCardCounters(String[] cardLeft) {
		Platform.runLater(() -> {
			for (int i = 0; i < 12; i++) {
				boardView.labelCountStack.get(i).setText(cardLeft[i]);
			}
		});
	}

	/**
	 * Get a round counter from the server and send this to Board_View to display
	 * the Round Counter
	 * 
	 * @author Patrick Ziörjen
	 * @param roundNumber
	 *            - the current round
	 * @return -
	 */
	public void showRoundCounter(String roundNumber) {
		Platform.runLater(() -> {
			boardView.roundCounter.setText(roundNumber);
		});
	}

}
