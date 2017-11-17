package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Login_View {

	BorderPane mainPane;
	Stage stage; 
	
	Button lobbyBtn; 
	Button exitBtn; 
	Label welcomeLbl; 
	public Label nameLbl; 
	Label languageLbl; 
	
	TextField nameTxtfield; 
	ComboBox <String> language = new ComboBox<String>(); 

	VBox topvbox = new VBox(); 
	VBox vbox = new VBox(); //for the center
	HBox hbox1 = new HBox(); //for name and textfield 
	HBox hbox2 = new HBox(); //for laguage and dropdown 
	
	
	public Login_View(Stage stage){
		this.stage = stage; 
//		stage.setResizable(false);
		stage.setTitle("Dominion Login");		
		stage.setFullScreen(true);
	
		//Hauptpane as Grundgerüst  
		mainPane = new BorderPane();
		mainPane.setId("mainPane");
		
		mainPane.setCenter(vbox);
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(20.00);
		hbox1.setAlignment(Pos.CENTER);
		hbox2.setAlignment(Pos.CENTER);
		
		
		
		//BORDER PANE TOP
		
		//Wilkommens Label erstellen und Top
		welcomeLbl = new Label("Willkommen"); 
		welcomeLbl.setId("welcomeLbl");
		topvbox.getChildren().add(welcomeLbl);
		topvbox.setAlignment(Pos.CENTER);
		mainPane.setTop(topvbox);
		
		
		// BORDERPANE CENTER
		
		//
		nameLbl = new Label("Name: "); 
		nameLbl.setId("nameLbl");
		languageLbl = new Label("Sprache: "); 
		languageLbl.setId("languageLbl");
		nameTxtfield = new TextField(); 
		
		
		language.getSelectionModel().select("Auswählen");
		language.getItems().addAll("Deutsch","English");
		
		hbox1.getChildren().addAll(nameLbl,nameTxtfield); 
		hbox2.getChildren().addAll(languageLbl, language); 
		
		
		lobbyBtn = new Button("Lobby"); 
		exitBtn = new Button("Verlassen"); 
		
		
		
		
		vbox.getChildren().addAll(hbox1, hbox2,lobbyBtn,exitBtn); 
		Scene scene = new Scene(mainPane); 
		scene.getStylesheets().add(getClass().getResource("Login.css").toExternalForm()); 
		stage.setScene(scene);
		stage.setFullScreen(true);
		
	}
	
	public void start() {
		this.stage.show();
	}
	
	public void stop() {
		this.stage.hide();
	}
	
	public Stage getStage() {
		return this.stage;
	}
	

}
