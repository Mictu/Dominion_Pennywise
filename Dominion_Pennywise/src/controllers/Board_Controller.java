package controllers;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import server.client.Client;
import server_Models.Player;
import view.Board_View;
import view.CardDesign_View;
import view.Lobby_View;

public class Board_Controller {
	
	Lobby_View lobbyView; 
	Board_View boardView; 
	CardDesign_View cardView; 
	Player player; 
	
	Client client = new Client("sojo");


	public Board_Controller(Board_View boardView, Client client){
		//
		this.client = client;
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
			client.sendToServer("pay");
		});

		boardView.endPhase.setOnAction((Event) -> {
			System.out.println("reached endphase");
			client.sendToServer("endphase");
		});
		
		boardView.bonusMoney.setOnAction((Event) -> {
			client.sendToServer("bonusmoney");
		});

		
		
	}

		public void setbonusMoneybtn(String text){
			String bonusgeld = text; 
			boardView.bonusMoney.setText("Bonusgeld: " + bonusgeld);
		}

	
		

}
