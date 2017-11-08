package Views;

import Server_Models.Game;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Login_View {

	BorderPane mainPane;
	Stage stage; 
	Game gamemodel; 
	
	protected Login_View(Stage stage, Game gamemodel){
		this.stage = stage; 
		this.gamemodel = gamemodel; 	
		stage.setResizable(false);
	
		stage.setTitle("Dominion Login");		
		mainPane = new BorderPane(); 
		
		
		Scene scene = new Scene(mainPane); 
		scene.getStylesheets().add(getClass().getResource("Dominion.css").toExternalForm()); 
		stage.setScene(scene);
		
	}
	

}
