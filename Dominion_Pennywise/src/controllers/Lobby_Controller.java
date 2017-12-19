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

public class Lobby_Controller {

	Lobby_View lobbyView;
	Board_View boardView;
	Login_View loginView;

	public Lobby_Controller(Lobby_View lobbyView) {
		this.lobbyView = lobbyView;

		lobbyView.btnStartGame.setOnAction(event -> {
			Login_Controller.client.sendToServer("start");
		});

		lobbyView.btnLeaveGame.setOnAction((event) -> {
			exit(lobbyView.getStage());
		});

		// Chat send and appenText action
		lobbyView.txtChatMessage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
			if (event.getCode() == KeyCode.ENTER) {
				lobbyView.btnSend.fire();
			}
		});
		
			lobbyView.btnSend.setOnAction(event -> {
				if (!lobbyView.txtChatMessage.getText().trim().isEmpty()) {
				Login_Controller.client.sendChatMessage(lobbyView.txtChatMessage.getText());
				lobbyView.txtChatMessage.clear();
				Button_Sounds.playSendSound();
				}
			});

		Login_Controller.client.newestMessage
				.addListener((o, oldValue, newValue) -> lobbyView.txtChatArea.appendText(newValue + "\n"));

		// lobbyView.stage.setOnCloseRequest( event -> client.disconnectClient());
	}// Close Constructor

	public void updateConnectedPlayers(String s) {
		Platform.runLater(()-> {
			lobbyView.txtConnectedPlayers.appendText(s + "\n");
		});
	}

	public void clearConnectedP() {
		Platform.runLater(()-> {
			lobbyView.txtConnectedPlayers.clear();
		});
	}

	// ExitMethode for all Views
	private void exit(Stage stage) {
		stage.hide();
	}
	
	public void playSoundSend() {
		String musicFile = "pull-out.mp3"; // For example
		Media sound = new Media(new File(musicFile).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
	}

}
