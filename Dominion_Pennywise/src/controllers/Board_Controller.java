package controllers;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import view.Board_View;
import view.CardDesign_View;
import view.Lobby_View;

public class Board_Controller {
	
	Lobby_View lobbyView; 
	Board_View boardView; 
	CardDesign_View cardView = new CardDesign_View(); 
	public HBox hBoxHand;

	ArrayList <Button> testcardlist = new ArrayList<Button>(); 
	public static ArrayList <Button> controlView = new ArrayList<Button>();
	
	public Board_Controller(Board_View boardView){
		//
		this.boardView = boardView;

		
		// Close Window with EscapeBtn
		boardView.stage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			public void handle(KeyEvent t) {
					if (t.getCode() == KeyCode.ESCAPE) {
					System.out.println("Here should come a question if u really want to close the window");     // DONT FORGET!
					if (boardView.getStage().isShowing())
						boardView.stop();
				}
			}
		});
		
		Button woodcutter = cardView.getWoodcutterBtn();
		Button village = cardView.getWoodcutterBtn();
		Button smith = cardView.getWoodcutterBtn();
		
		
		controlView.add(woodcutter);
		controlView.add(village);
		controlView.add(smith);
		
		
//		
//		for(int i = 0; i < testcardlist.size(); i++){
//			Button cardbutton = testcardlist.get(i); 
//			boardView.hBoxHand.getChildren().add(cardbutton);
//		}
	
		
		//boardView.hBoxHand.getChildren().add(cardView.getFunfairBtn());
		

		
		
	}
	
	public ArrayList getList(){
		return controlView; 
	}
	
		

}
