package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import server.client.Client;
import view.Lobby_View;
import view.Login_View;

public class Login_Controller {

	Login_View loginView;
	Client client = new Client();
	Lobby_View lobbyView;

	public Login_Controller(Login_View loginView) {
		this.loginView = loginView;
		client.run();

		// LOGIN
		// Open Lobby

		loginView.lobbyBtn.setOnAction((Event) -> {
			// public void handle(ActionEvent event) {
			// Kontrolle ob Name eingegeben wurde!
			String name = loginView.nameTxtfield.getText();
			if (!name.isEmpty()) {
//				client.sendName(name);
			//	client.sendName(name);
				client.sendToServer(name);

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
