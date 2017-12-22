package controllers;

import javafx.stage.Stage;
import main_Class.ServiceLocator;
import server.client.Client;
import server_Models.Translator;
import view.Lobby_View;
import view.Login_View;

/**
 * Controller Class for Login_View Handle the texfield and initialize the game
 * Start the connection from a client to server
 * 
 * @author Patrick Ziörjen
 * @author Sojo Nagaroor
 * @author Yujia Shi
 */
public class Login_Controller {

	Login_View loginView;
	public static Client client;
	Lobby_View lobbyView;
	String playerName;
	Lobby_Controller lobbyController;
	ClientHandler ch = new ClientHandler();

	/**
	 * Constructor from Login_Controller Contains setOnActions for the elements from
	 * Login_View. Tell the program what it should do after clicking an element on
	 * Login_View.
	 * 
	 * @param loginView
	 *            - get the correct loginView from ClientHandler to handle the view
	 * @author Patrick Ziörjen
	 * @author Yujia Shi
	 * @return -
	 */
	public Login_Controller(Login_View loginView) {
		this.loginView = loginView;
		ServiceLocator sl = ServiceLocator.getServiceLocator();

		// setOnAction for button lobby
		// starts the game if the conditions for oben lobbyview are true
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

		// setOnAction for button exit game
		loginView.exitBtn.setOnAction((event) -> {
			exit(loginView.getStage());
		});
	}

	/**
	 * Close the Login_View if Lobby_View starts
	 * 
	 * @author Sojo Nagaroor
	 * @param stage
	 *            - get Stage from Login_View
	 * @return -
	 */
	private void exit(Stage stage) {
		stage.hide();
	}
}
