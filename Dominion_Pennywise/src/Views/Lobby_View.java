package Views;

import Server_Models.Game;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Lobby_View {
	
	BorderPane mainPane; 
	Stage stage; 
	Game gamemodel; 
	
	protected Lobby_View(Stage stage, Game gamemodel){
		this.stage = stage; 
		this.gamemodel = gamemodel; 
		
		
		stage.setResizable(false);
		stage.setTitle("Dominion Lobby");
		mainPane = new BorderPane(); 
		
		Scene scene = new Scene(mainPane); 
		scene.getStylesheets().add(getClass().getResource("Dominion.css").toExternalForm());
		
		
		
	}
	
	
	
}


