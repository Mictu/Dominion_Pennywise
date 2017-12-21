package controllers;

import javafx.stage.Stage;
import main_Class.ServiceLocator;
import server.client.Client;
import server_Models.Translator;
import view.Lobby_View;
import view.Login_View;

public class Login_Controller {

	Login_View loginView;
	public static Client client;
	Lobby_View lobbyView;
	String playerName;
	Lobby_Controller lobbyController;
	ClientHandler ch = new ClientHandler();

	
	public Login_Controller(Login_View loginView) {
		this.loginView = loginView;
		
		ServiceLocator sl = ServiceLocator.getServiceLocator();
		
		// LOGIN
		// Open Lobby

		loginView.lobbyBtn.setOnAction((Event) -> {
			playerName = loginView.nameTxtfield.getText();
			Translator t = sl.getTranslator();
			if (playerName.isEmpty()) {
				loginView.warning.setText(t.getString("dominion.login.warning1"));
			} else if (playerName.contains(".")) {
				loginView.warning.setText(t.getString("dominion.login.warning2"));
			} else if (playerName.length() > 15) {
				loginView.warning.setText(t.getString("dominion.login.warning3"));
			} else {
				lobbyView = new Lobby_View(loginView.getStage());
				Login_Controller.client = new Client(playerName);
				Login_Controller.client.run();
				ch.initializeLobbyController(lobbyView);
				lobbyView.start();
			}

		});
		loginView.exitBtn.setOnAction((event) -> {
			exit(loginView.getStage());
		});
	}

	// ExitMethode for all Views

	private void exit(Stage stage) {
		stage.hide();
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	
}
