package controllers;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import server.client.Client;
import view.Board_View;
import view.Lobby_View;

public class Board_Controller {
	
	Lobby_View lobbyView; 
	Board_View boardView; 
	Client client;
	
	
	public Board_Controller(Client client){
		this.client = client;
		
		
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
	
		

}
