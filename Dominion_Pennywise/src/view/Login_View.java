package view;

import org.ietf.jgss.GSSName;

import javafx.application.Application;
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

public class Login_View extends Application {

	BorderPane mainPane;
	Stage stage; 
	GSSName gamemodel; 
	
	Button lobbyBtn; 
	Button exitBtn; 
	Label welcomeLbl; 
	Label nameLbl; 
	Label languageLbl; 
	
	TextField nameTxtfield; 
	ComboBox <String> language = new ComboBox<String>(); 

	VBox topvbox = new VBox(); 
	VBox vbox = new VBox(); //for the center
	HBox hbox1 = new HBox(); //for name and textfield 
	HBox hbox2 = new HBox(); //for laguage and dropdown 
	
	
	public static void main (String[] args){
		launch(args);
	}
	
	public void start(Stage stage){
//	protected Login_View(Stage stage, Game gamemodel){
//		this.stage = stage; 
//		this.gamemodel = gamemodel; 	
		stage.setResizable(false);
		stage.setTitle("Dominion Login");		
	
		//Hauptpane as Grundgerüst  
		mainPane = new BorderPane();
		mainPane.setId("mainPane");
		
		mainPane.setCenter(vbox);
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(20.00);
		hbox1.setAlignment(Pos.CENTER);
		hbox2.setAlignment(Pos.CENTER);
		
		
		
		//BORDER PANE TOP
		
		//Wilkommens Label erstelle nund Top
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
		stage.show();
		
	}


}
