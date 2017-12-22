package controllers;

import java.io.File;

import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import view.Board_View;
import view.Lobby_View;
import view.Login_View;

/**
 * Controller for Lobby_View. Get informations from ClientHandler and send them
 * to Lobby_View to display the right informations to the client.
 * 
 * @author Michael Tu
 * @author Patrick Ziörjen
 * @author Yujia Shi
 */
public class Lobby_Controller {

	Lobby_View lobbyView;
	Board_View boardView;
	Login_View loginView;

	/**
	 * Constructor from Board_Controller Contains setOnActions for the elements from
	 * Lobby_View Tell the program what it should do after clicking an element on
	 * Lobby_View
	 * 
	 * @param lobbyView
	 *            - get the correct lobbyView from ClientHandler to handle the view
	 * @author Patrick Ziörjen
	 * @author Sojo Nagaroor
	 * @author Yujia Shi
	 * @return -
	 */
	public Lobby_Controller(Lobby_View lobbyView) {
		this.lobbyView = lobbyView;

		// setOnAction for button start game
		lobbyView.btnStartGame.setOnAction(event -> {
			Login_Controller.client.sendToServer("start");
		});

		// setOnAction for button leave game
		lobbyView.btnLeaveGame.setOnAction((event) -> {
			Login_Controller.client.disconnectClient();
			exit(lobbyView.getStage());
		});

		// Send chat message if the player clicks on "enter"
		lobbyView.txtChatMessage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
			if (event.getCode() == KeyCode.ENTER) {
				lobbyView.btnSend.fire();
			}
		});

		// setOnAction for button send
		lobbyView.btnSend.setOnAction(event -> {
			if (!lobbyView.txtChatMessage.getText().trim().isEmpty()) {
				Login_Controller.client.sendChatMessage(lobbyView.txtChatMessage.getText());
				lobbyView.txtChatMessage.clear();
				Button_Sounds.playSendSound();
			}
		});

		Login_Controller.client.newestMessage
				.addListener((o, oldValue, newValue) -> lobbyView.txtChatArea.appendText(newValue + "\n"));

		lobbyView.stage.setOnCloseRequest(event -> Login_Controller.client.disconnectClient());

	}

	/**
	 * Update te players who are connected to the server. Show a list of player who
	 * are in the lobby at the moment.
	 * 
	 * @author Michael Tu
	 * @param playerName
	 * @return -
	 */
	public void updateConnectedPlayers(String playerName) {
		Platform.runLater(() -> {
			lobbyView.txtConnectedPlayers.appendText(playerName + "\n");
		});
	}

	/**
	 * Delete the old list of player
	 * 
	 * @author Michael Tu
	 * @param -
	 * @return -
	 */
	public void clearConnectedP() {
		Platform.runLater(() -> {
			lobbyView.txtConnectedPlayers.clear();
		});
	}

	/**
	 * Close the Lobby_View if Board_View starts
	 * 
	 * @author Sojo Nagaroor
	 * @param stage
	 *            - get Stage from Lobby_View
	 * @return -
	 */
	private void exit(Stage stage) {
		stage.hide();
	}

	/**
	 * Play the sound effect if the player clicks on the send button.
	 * 
	 * @author Patrick Ziörjen
	 * @param -
	 * @return -
	 */
	public void playSoundSend() {
		String musicFile = "pull-out.mp3"; // For example
		Media sound = new Media(new File(musicFile).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
	}

}
