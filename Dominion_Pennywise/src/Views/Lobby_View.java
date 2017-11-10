package Views;

import Server_Models.Game;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Lobby_View extends Application{
	
//	protected Lobby_View(Stage stage, Game gamemodel){
//	this.stage = stage; 
//	this.gamemodel = gamemodel; 
//}
	
	BorderPane mainPane; 
	Stage stage; 
	Game gamemodel;
	
	//connected Players and online players area
	TextArea txtConnectedPlayers = new TextArea();
	TextArea txtOnlinePlayers = new TextArea();
	
	//history area
	TextArea txtHistory = new TextArea();
	
	// Chat area and its controlls
	TextArea txtChatArea = new TextArea();
	TextField txtChatMessage = new TextField();
	Button btnSend = new Button("Send");
	
	//controls button
	Button btnNewGame = new Button("Spiel erstellen");
	Button btnLeaveGame = new Button("Verlassen");
	Button btnStartGame = new Button("Spiel starten");
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		stage.setResizable(false);
		stage.setTitle("Dominion Lobby");
		
		HBox bottomChatBox = new HBox(txtChatMessage,btnSend);
		bottomChatBox.getStyleClass().add("hboxBottom");
		HBox.setHgrow(txtChatMessage, Priority.ALWAYS);
		
		btnNewGame.getStyleClass().add("btn");
		btnLeaveGame.getStyleClass().add("btn");
		btnStartGame.getStyleClass().add("btn");
		
		HBox topBox = new HBox(btnNewGame, btnStartGame, btnLeaveGame);
		topBox.getStyleClass().add("topBox");
		
		VBox rightBox = new VBox(topBox, txtHistory,txtChatArea, bottomChatBox);
		rightBox.getStyleClass().add("rightBox");
		VBox.setVgrow(txtChatArea, Priority.ALWAYS);
		
		VBox leftBox = new VBox(txtConnectedPlayers, txtOnlinePlayers);
		leftBox.getStyleClass().add("leftBox");
		VBox.setVgrow(txtConnectedPlayers, Priority.ALWAYS);
		VBox.setVgrow(txtOnlinePlayers, Priority.ALWAYS);
		
		mainPane = new BorderPane(); 
		mainPane.setLeft(leftBox);
		mainPane.setCenter(rightBox);
		mainPane.getStyleClass().add("mainPane");
		
		Scene scene = new Scene(mainPane);
		scene.getStylesheets().add(getClass().getResource("Lobby.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	
	
	
}


