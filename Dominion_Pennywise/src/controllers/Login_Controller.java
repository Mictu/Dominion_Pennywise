package controllers;

import javafx.stage.Stage;
import main_Class.ServiceLocator;
import server.client.Client;
import server_Models.Translator;
import view.Lobby_View;
import view.Login_View;

public class Login_Controller {

	Login_View loginView;
	Client client;
	Lobby_View lobbyView;
	String playerName;
	Lobby_Controller lobbyController;

	public Login_Controller(Login_View loginView) {
		this.loginView = loginView;
		
		ServiceLocator sl = ServiceLocator.getServiceLocator();
		Translator t = sl.getTranslator();

		// LOGIN
		// Open Lobby

		loginView.lobbyBtn.setOnAction((Event) -> {
			playerName = loginView.nameTxtfield.getText();
			if (!playerName.isEmpty()) {
				lobbyView = new Lobby_View(loginView.getStage());
				
				client = new Client(playerName);
				client.run();
				lobbyController = new Lobby_Controller(lobbyView, client);
				lobbyView.start();

				// Open Lobby

			} else {
				loginView.warning.setText(t.getString("dominion.login.warning"));
			}

		});

		loginView.exitBtn.setOnAction((event) -> {
			exit(loginView.getStage());
		});

	}// Close Constructore

	// ExitMethode for all Views

	private void exit(Stage stage) {
		stage.hide();
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
}
