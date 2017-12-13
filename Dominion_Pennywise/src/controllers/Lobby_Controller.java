package controllers;

import javafx.stage.Stage;
import server.client.Client;
import view.Board_View;
import view.Lobby_View;
import view.Login_View;

public class Lobby_Controller {

	Lobby_View lobbyView;
	Board_View boardView;
	Login_View loginView;
	
	public Lobby_Controller(Lobby_View lobbyView){
		this.lobbyView = lobbyView;


		lobbyView.btnStartGame.setOnAction(event -> {
			Login_Controller.client.sendToServer("start");
		});
			
		lobbyView.btnLeaveGame.setOnAction((event) -> {
			exit(lobbyView.getStage());
		});

		// Chat send and appenText action
		lobbyView.btnSend.setOnAction(event -> Login_Controller.client.sendChatMessage(lobbyView.txtChatMessage.getText()));

		Login_Controller.client.newestMessage.addListener((o, oldValue, newValue) -> lobbyView.txtChatArea.appendText(newValue + "\n"));

		
//		lobbyView.stage.setOnCloseRequest( event -> client.disconnectClient());
	}// Close Constructor

	// ExitMethode for all Views
	private void exit(Stage stage) {
		stage.hide();
	}

}
