package controllers;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import server_Models.Player;
import view.Board_View;
import view.CardDesign_View;
import view.Lobby_View;

public class Board_Controller {
	
	Lobby_View lobbyView; 
	Board_View boardView; 
	CardDesign_View cardView; 
	Player player; 
	
	public Board_Controller(Board_View boardView){
		//
		this.boardView = boardView;

		
		// Close Window with EscapeBtn
//		boardView.stage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
//			public void handle(KeyEvent t) {
//					if (t.getCode() == KeyCode.ESCAPE) {
//					System.out.println("Here should come a question if u really want to close the window");     // DONT FORGET!
//					if (boardView.getStage().isShowing())
//						boardView.stop();
//				}
//			}
//		});
		
		
		boardView.pay.setOnAction((Event) -> {
			Login_Controller.client.sendToServer("pay");
		});

		boardView.endPhase.setOnAction((Event) -> {
			Login_Controller.client.sendToServer("endphase");
		});
		
		boardView.bonusMoney.setOnAction((Event) -> {
			Login_Controller.client.sendToServer("bonusmoney");
		});
		
		//Chat 
		boardView.chatText.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
			if (event.getCode() == KeyCode.ENTER) {
				boardView.send.fire();
			}
		});
		boardView.send.setOnAction(event -> {
			if (!boardView.chatText.getText().trim().isEmpty()) {
			Login_Controller.client.sendChatMessage(boardView.chatText.getText());
			boardView.chatText.clear();
			}
		});
		
		Login_Controller.client.newestMessage
		.addListener((o, oldValue, newValue) -> boardView.chat.appendText(newValue + "\n"));

	}

		public void setbonusMoneybtn(String text){
			String bonusgeld = text; 
			boardView.bonusMoney.setText("Bonusgeld: " + bonusgeld);
		}

	
		

}
