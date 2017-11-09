package Views;

import Server_Models.Game;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Board_View extends Application{

	private Stage stage;
//	private Game model;
	
	
	public static void main (String[] args) {
		launch(args);
	}

	// Initialize the GUI Content here

	// constructor
//	protected Board_View(Stage s, Game m) {
//		this.stage = s;
//		this.model = m;

		// Set up the GUI in here
	public void start(Stage stage) {
		stage.setTitle("Dominion");
//		stage.setResizable(false);
		BorderPane root = new BorderPane();

	//SET TOP
	//SET CENTER
	//SET RIGHT
	//SET BOTTOM
		Button a = new Button();
		root.setTop(a);

		
		
		
		
		
		
		// SET SCENE
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("Dominion.css").toExternalForm());
		stage.setScene(scene);
		
		stage.show();

	} // Close the Constructor
	
}// close class

// Written by Patrick