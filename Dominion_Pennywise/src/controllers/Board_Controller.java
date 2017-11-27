package controllers;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import view.Board_View;
import view.CardDesign_View;
import view.Lobby_View;

public class Board_Controller {
	
	Lobby_View lobbyView; 
	Board_View boardView; 
	CardDesign_View cardView; 
	
	
	public Board_Controller(Board_View boardView, CardDesign_View cardView){
		this.boardView = boardView; 
		this.cardView = cardView; 
		
		
		
		
		boardView = new Board_View(lobbyView.getStage());
		lobbyView.stop();
		boardView.start();
		
		//

		
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
