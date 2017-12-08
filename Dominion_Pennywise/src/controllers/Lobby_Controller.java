package controllers;

import javafx.concurrent.Task;
import javafx.stage.Stage;
import server.client.Client;
import server_Models.Player;
import view.Board_View;
import view.Lobby_View;
import view.Login_View;

public class Lobby_Controller {

	Lobby_View lobbyView;
	Login_View loginView;
	Board_View boardView;
	Client client;
	ClientHandler clientH;

	public Lobby_Controller(Lobby_View lobbyView, Client client) {
		this.lobbyView = lobbyView;
		this.client = client;

		// LOBBY

		lobbyView.btnStartGame.setOnAction(event -> {
			client.sendToServer("start");
			new Thread(waitForOpenBV).start();
		});

		lobbyView.btnLeaveGame.setOnAction((event) -> {
			exit(lobbyView.getStage());
		});

		// Chat send and appenText action
		lobbyView.btnSend.setOnAction(event -> client.sendChatMessage(lobbyView.txtChatMessage.getText()));

		client.newestMessage.addListener((o, oldValue, newValue) -> lobbyView.txtChatArea.appendText(newValue + "\n"));

		// lobbyView.stage.setOnCloseRequest( event -> client.disconnectClient());
	}// Close Constructor

	// ExitMethode for all Views
	private void exit(Stage stage) {
		stage.hide();
	}

	public void openBoardView() {
		boardView = new Board_View(lobbyView.getStage(), client);
		Board_Controller boardController = new Board_Controller(boardView, client);
		boardView.start();
	}

	final Task<Void> waitForOpenBV = new Task<Void>() {
		@Override
		protected Void call() throws Exception {
			System.out.println("thread1");
			while (true) {
				if (clientH.getopenBV()) {
					System.out.println("thread2");
					openBoardView();
					System.out.println("thread3");
				}
			}
		}
	};

}
