package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import server.client.Client;
import server_Models.Player;

public class Lobby_View {
	
	Client client = new Client();
	protected Stage stage;
	
	BorderPane mainPane; 
	
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
	Button btnNewGame = new Button(client.getPlayersName());
	Button btnLeaveGame = new Button("Verlassen");
	Button btnStartGame = new Button("Spiel starten");

	public Lobby_View(Stage stage){
		this.stage = stage; 
//		stage.setResizable(false);
		stage.setTitle("Dominion Lobby");
		
		txtConnectedPlayers.setEditable(false);
		
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
		stage.setFullScreen(true);
	}
	
	public void start() {
		stage.setFullScreen(true);
		stage.show();
	}
	
	public void stop() {
		this.stage.hide();
	}
	
	public Stage getStage() {
		return this.stage;
	}
	
}


