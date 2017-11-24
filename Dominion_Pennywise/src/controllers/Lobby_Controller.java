package controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import view.Board_View;
import view.Lobby_View;
import view.Login_View;

public class Lobby_Controller {

	Lobby_View lobbyView; 
	Login_View loginView; 
	Board_View bordView;
	
	
	public Lobby_Controller(){
	
		//LOBBY		

			lobbyView = new Lobby_View(loginView.getStage());
			loginView.stop();
			lobbyView.start();
			
			
			lobbyView.btnStartGame.setOnAction((ereignis)->{
				
				board();
			});
			
			
			lobbyView.btnLeaveGame.setOnAction(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent event) {
					exit(lobbyView.getStage());
					
				}
				
			});
			
		
		
	}//Close Constructor 
	

	// ExitMethode for all Views
	private void exit(Stage stage) {
		stage.hide();
	}
	
	
	
	
	
}
