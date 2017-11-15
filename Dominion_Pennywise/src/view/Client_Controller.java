package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import server.client.*;

public class Client_Controller {
	
	// Initializing Sector
	
	Client client;
	Login_View loginView;
	Lobby_View lobbyView;
	Board_View boardView;
	Stage stage;

	
	// Constructor
	public Client_Controller(Login_View loginView) {
		this.loginView = loginView;
		

//LOGIN		
		// Open Lobby
		loginView.lobbyBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				// Kontrolle ob Name eingegeben wurde!

				// Connect to Server
				try {
				client = new Client();
				client.run();
				} catch (Exception e) {
					System.out.println(e);
				}
				// Open Lobby
				lobby();
 			}
		});
		
		
		

	} // Close Constructor

	
	
//LOBBY		
	public void lobby() {
		lobbyView = new Lobby_View(loginView.getStage());
		loginView.stop();
		lobbyView.start();
		
		lobbyView.btnStartGame.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				board();
			}
		});
	}
	
	
//BOARD	
	public void board() {
		boardView = new Board_View(lobbyView.getStage());
		lobbyView.stop();
		boardView.start();
		
//		lobbyView.btnStartGame.setOnAction(new EventHandler<ActionEvent>() {
//			public void handle(ActionEvent event) {
//				
//			}
//		});
	}
	
	
} // Close Class


//Written by all
