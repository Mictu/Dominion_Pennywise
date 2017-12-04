package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import server.client.Client;
import view.Board_View;
import view.CardDesign_View;
import view.Lobby_View;
import view.Login_View;

public class Lobby_Controller {

	Lobby_View lobbyView; 
	Login_View loginView; 
	Board_View boardView;
	CardDesign_View cardView; 
	Client client; 

	
	public Lobby_Controller(Lobby_View lobbyView, Client client){
		this.lobbyView = lobbyView;
		this.client = client;
		client.run();
		
		
		
		//LOBBY		
		
		lobbyView.btnStartGame.setOnAction(new EventHandler <ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				
				System.out.println("sadf");
				
	
				boardView = new Board_View(lobbyView.getStage()); 
				Board_Controller boardController = new Board_Controller(boardView); 
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
