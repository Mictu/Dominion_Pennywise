package controllers;

import javafx.stage.Stage;
import server.client.Client;
import view.Lobby_View;
import view.Login_View;

public class Login_Controller {

	Login_View loginView;
	Client client;
	Lobby_View lobbyView;

	public Login_Controller(Login_View loginView) {
		this.loginView = loginView;

		// LOGIN
		// Open Lobby

		loginView.lobbyBtn.setOnAction((Event) -> {
			String name = loginView.nameTxtfield.getText();
			if (!name.isEmpty()) {

				client = new Client();
				client.run();

				String msg = "lobby".concat(name);
				client.sendToServer(msg);

				lobbyView = new Lobby_View(loginView.getStage());
				// Open Lobby
				lobbyView.start();
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
