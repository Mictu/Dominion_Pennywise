package view;

import java.io.IOException;
import java.net.UnknownHostException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import main_Class.ServiceLocator;
import server.client.*;
import server_Models.Player;
import server_Models.Translator;


public class Client_Controller {
	
	// Initializing Sector
	ServiceLocator serviceLocator;
	Translator t;
	
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

				String name = loginView.nameTxtfield.getText();
				if (!name.isEmpty()) {
					// Connect to Server
					try {
						client = new Client();
						client.run();
					} catch (Exception e) {
						System.out.println(e);
					}
						client.sendToServer(name);
						client.getStringFromServer();
					// Open Lobby
					lobby();
				}
			}
		});
		
		loginView.exitBtn.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event) {
				exit(loginView.getStage()); 
			}
			
		});
		

		serviceLocator = ServiceLocator.getServiceLocator();
		t = serviceLocator.getTranslator();
		
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
		
		lobbyView.btnLeaveGame.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				exit(lobbyView.getStage());
				
			}
			
		});
		
	}
	
	
//BOARD	
	public void board() {
		boardView = new Board_View(lobbyView.getStage());
		lobbyView.stop();
		boardView.start();
		
		
		// Close Window with EscapeBtn
		boardView.stage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			public void handle(KeyEvent t) {
				if (t.getCode() == KeyCode.ESCAPE) {
					System.out.println("Here should come a question if u really want to close the window");     // DONT FORGET!
					if (boardView.stage.isShowing())
						boardView.stop();
				}
			}
		});
		
		
		
	}
	
	
// ExitMethode for all Views
	public void exit(Stage stage){
		stage.hide();
	}
	
	public void setPlayerName(Player p){
		String pname = loginView.nameLbl.getText(); 
		p.setName(pname);
	}
	
	
	
} // Close Class


//Written by all

