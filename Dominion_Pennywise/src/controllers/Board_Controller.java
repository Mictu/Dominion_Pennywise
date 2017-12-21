package controllers;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import view.Board_View;
import view.CardDesign_View;
import view.Lobby_View;

public class Board_Controller {
	
	Lobby_View lobbyView; 
	Board_View boardView; 
	CardDesign_View cardView; 
	
	public Board_Controller(Board_View boardView){
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

		boardView.endPhase.setOnAction((Event) -> {
			Login_Controller.client.sendToServer("endphase");
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
			Button_Sounds.playSendSound();
			}
		});
		
		Login_Controller.client.newestMessage
		.addListener((o, oldValue, newValue) -> boardView.chat.appendText(newValue + "\n"));

		boardView.stage.setOnCloseRequest(event -> Login_Controller.client.disconnectClient());
	}

		
		//Action-, Buy-,Moneypoints update textarea
		public void updateABMpoints(String s) {
			Platform.runLater(()->{
				if (boardView.aBMpoints.getText().equals("")) {
					boardView.aBMpoints.setText(s);
				} else {
					boardView.aBMpoints.setText(boardView.aBMpoints.getText()+"\n"+s);
				}
			});
		}

// May be useless now
		public void clearABMpoints() {
			Platform.runLater(()-> {
				boardView.aBMpoints.setText("");
			});	
		}

// May be useless now
		public void clearWinPoints() {
			Platform.runLater(()->{
				boardView.playerStats.setText("");
			});	
		}

		public void updateWinPoints(String s) {
			Platform.runLater(()-> {
				if (boardView.playerStats.getText().equals("")) {
					boardView.playerStats.setText(s);
				} else {
					boardView.playerStats.setText(boardView.playerStats.getText()+"\n"+s);
				}
			});
		}
		
		public void showLoggerMsg(String message) {
			Button_Sounds.playLoggerSound();
			Platform.runLater(()->{
				if (boardView.logger.getText().equals("")) {
					boardView.logger.appendText(message);
				} else {
					boardView.logger.appendText("\n"+message);
				}
			});
		}
		public void showInfoMsg(String message) {
			Platform.runLater(()->{
				boardView.info.setText(message);
			});
		}
		public void deleteInfo() {
			Platform.runLater(()->{
				boardView.info.setText("");
			});
		}
		public static void showCard(String cardInfo) {
			Platform.runLater(()->{
				Board_View.cardInfo.setText(cardInfo);
			});
		}
		public static void deleteCard() {
			Platform.runLater(()->{
				Board_View.cardInfo.setText("");
			});
		}
		public void setCardCounters(String[] amounts) {
			Platform.runLater(()->{
				for (int i = 0; i < 12; i++) {
					boardView.labelCountStack.get(i).setText(amounts[i]);
				}
			});
		}
		
		public void showRoundCounter(String roundNumber) {
			Platform.runLater(()->{
				boardView.roundCounter.setText(roundNumber);
			});
		}

}
