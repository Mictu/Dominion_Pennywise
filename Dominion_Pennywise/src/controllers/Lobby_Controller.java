package controllers;

import javafx.stage.Stage;
import server.client.Client;
import view.Board_View;
import view.Lobby_View;
import view.Login_View;

public class Lobby_Controller {

	Lobby_View lobbyView;
	Login_View loginView;
	Board_View boardView;
	Client client;
	
	public Lobby_Controller(Lobby_View lobbyView, Client client){
		this.lobbyView = lobbyView;
		this.client = client;
		
		//LOBBY		
		
		lobbyView.btnStartGame.setOnAction(event -> {
				boardView = new Board_View(lobbyView.getStage());
				Board_Controller boardController = new Board_Controller(boardView); 
				boardView.start();
		});


		lobbyView.btnLeaveGame.setOnAction((event) -> {
			exit(lobbyView.getStage());
		});

		// Chat send and appenText action
		lobbyView.btnSend.setOnAction(event -> client.sendChatMessage(lobbyView.txtChatMessage.getText()));

		client.newestMessage.addListener((o, oldValue, newValue) -> lobbyView.txtChatArea.appendText(newValue + "\n"));

//		lobbyView.stage.setOnCloseRequest( event -> client.disconnectClient());
	}// Close Constructor

	// ExitMethode for all Views
	private void exit(Stage stage) {
		stage.hide();
	}

}
