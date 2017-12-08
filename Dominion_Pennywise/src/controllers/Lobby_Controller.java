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
<<<<<<< HEAD

		System.out.println("kam lobby");
		// LOBBY

=======
		
		//LOBBY		
		
>>>>>>> branch 'master' of https://github.com/Mictu/Dominion_Pennywise.git
		lobbyView.btnStartGame.setOnAction(event -> {
<<<<<<< HEAD
			System.out.println("button clicked?");
			client.sendToServer("start");
=======
				client.sendToServer("start");
>>>>>>> branch 'master' of https://github.com/Mictu/Dominion_Pennywise.git
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
	
	public void openBoardView() {
<<<<<<< HEAD
		System.out.println("kam an lol");
		boardView = new Board_View(lobbyView.getStage(), client);
		Board_Controller boardController = new Board_Controller(boardView, client);
=======
		boardView = new Board_View(lobbyView.getStage(),this.client);
		Board_Controller boardController = new Board_Controller(boardView, this.client); 
>>>>>>> branch 'master' of https://github.com/Mictu/Dominion_Pennywise.git
		boardView.start();
	}
}
