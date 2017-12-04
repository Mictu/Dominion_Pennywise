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
	Board_View boardView;
	
	
	public Lobby_Controller(Lobby_View lobbyView){
		this.lobbyView = lobbyView; 
		
		
		
		
		//LOBBY		
		
		lobbyView.btnStartGame.setOnAction(new EventHandler <ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				
				System.out.println("sadf");
				
				Board_Controller boardController = new Board_Controller(boardView); 
				boardView = new Board_View(lobbyView.getStage()); 
				boardView.start();
				
			}
						
		});
			

			
			
			lobbyView.btnLeaveGame.setOnAction((event) -> {
			
				exit(lobbyView.getStage());
				
			});
			
			
		
		
	}//Close Constructor 
	

	// ExitMethode for all Views
	private void exit(Stage stage) {
		stage.hide();
	}
	
	
	
	
	
}
