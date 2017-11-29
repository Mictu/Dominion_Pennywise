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

	public Login_Controller(Login_View loginView) {
		this.loginView = loginView;
		
		ServiceLocator sl = ServiceLocator.getServiceLocator();
		Translator t = sl.getTranslator();

		// LOGIN
		// Open Lobby

		loginView.lobbyBtn.setOnAction((Event) -> {
			String name = loginView.nameTxtfield.getText();
			if (!name.isEmpty()) {

				client = new Client();
				client.run();

				String msg = "lobby".concat(name);						// Send if u connect to a server
				client.sendToServer(msg);

				lobbyView = new Lobby_View(loginView.getStage());
				Lobby_Controller lobbyController = new Lobby_Controller(lobbyView);
				// Open Lobby
				lobbyView.start();
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

}
