package Views;

import Server_Models.Game;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Board_View extends Application{

	private Stage stage;
//	private Game model;
	
	
	public static void main (String[] args) {
		launch(args);
	}

	public void start(Stage stage) {
		
		
		
	// Initialize the GUI Content here

	// constructor
//	protected Board_View(Stage s, Game m) {
//		this.stage = s;
//		this.model = m;

		// Set up the GUI in here
		stage.setTitle("Dominion");
//		stage.setResizable(false);
		BorderPane root = new BorderPane();

	//SET TOP
	//SET CENTER
		// use V- and H- Boxes to add the Cards (Buttons)
		VBox vCenter = new VBox(3);
		HBox hCenter1 = new HBox(); //add Victory cards
		HBox hCenter2 = new HBox();	//add Kingdom-/ Action card
		HBox hCenter3 = new HBox(); //add Treasure cards
		
		vCenter.getChildren().addAll(hCenter1, hCenter2, hCenter3);
		
		
		root.setCenter(vCenter);
	//SET RIGHT
	//SET BOTTOM

		
		
		
		
		
		
		// SET SCENE
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("Dominion.css").toExternalForm());
		stage.setScene(scene);
		
		stage.show();

	} // Close the Constructor
	
}// close class

// Written by Patrick